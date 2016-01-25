package it.unical.pizzamanager.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import it.unical.pizzamanager.models.BookingModel;
import it.unical.pizzamanager.persistence.dao.BookingDAO;
import it.unical.pizzamanager.persistence.dao.PizzeriaDAO;
import it.unical.pizzamanager.persistence.dto.Booking;
import it.unical.pizzamanager.persistence.dto.Pizzeria;
import it.unical.pizzamanager.serializers.BookingSerializer;
import it.unical.pizzamanager.utils.SessionUtils;
import it.unical.pizzamanager.utils.ValidatorUtils;
import it.unical.pizzamanager.utils.mail.MailSenderUtils;


@Controller
public class PizzeriaBookingController {

	private static final Logger logger = LoggerFactory.getLogger(PizzeriaBookingController.class);

	@Autowired
	private WebApplicationContext context;

	@RequestMapping(value = "/pizzeriabooking", method = RequestMethod.GET)
	public String pizzeriaBooking(Model model,HttpSession session) {
		logger.info("Booking controller page requested. Loading list of users.");
		if (!SessionUtils.isPizzeria(session)) {
			return null;
		}
		return "pizzeriabooking";
	}

	@RequestMapping(value = "/pizzeriabookingAjax", method = RequestMethod.GET)
	public @ResponseBody List<Booking> processAJAXRequest(HttpServletRequest request, Model model,HttpSession session) {
		
		if (!SessionUtils.isPizzeria(session)) {
			return null;
		}

		PizzeriaDAO pizzeriaDAO = (PizzeriaDAO) context.getBean("pizzeriaDAO");
		Pizzeria pizzeria = pizzeriaDAO.get(SessionUtils.getPizzeriaIdFromSessionOrNull(session));

		BookingDAO bookingDAO = (BookingDAO) context.getBean("bookingDAO");
		List<Booking> allBookings = (List<Booking>) bookingDAO.getBookingsFromPizzeria(pizzeria);
		List<Booking> filteredBookings=new ArrayList<>();
		for (Booking booking : allBookings) {
			if(booking.getConfirmed()==false && booking.getCompletionDate()==null)
				filteredBookings.add(booking);
		}
		
		ObjectMapper mapper = new ObjectMapper();
		SimpleModule module = new SimpleModule();
		module.addSerializer(Booking.class, new BookingSerializer());
		mapper.registerModule(module);
		try {
			for (int i = 0; i < filteredBookings.size(); i++) {
				String serialized = mapper.writeValueAsString(filteredBookings.get(i));
				System.out.println(serialized);				
			}
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return filteredBookings;
	}
	
	@RequestMapping(value = "/pizzeriabookingAction", method = RequestMethod.POST)
	public @ResponseBody String actionOnBooking(HttpServletRequest request,@RequestParam("action") String jsonAction, @RequestParam("booking") String jsonBooking){
		
		System.out.println(jsonAction);
		System.out.println(jsonBooking);
		
		String REGEX = "[^&%$#!~]*";
		if(ValidatorUtils.ValidateString(REGEX, jsonAction)==false || ValidatorUtils.ValidateString(REGEX, jsonBooking)==false){
			return null;
		}
		
		ObjectMapper objectMapper = new ObjectMapper();
		BookingModel book;
		String message="error";
		try {
			book = objectMapper.readValue(jsonBooking, BookingModel.class);
			System.out.println(book.getId());
			//TODO prendere la sessione e solo i booking della pizzeria loggata
			BookingDAO bookingDAO = (BookingDAO) context.getBean("bookingDAO");
			
			Booking booking=bookingDAO.getBooking(book.getId());
			switch (jsonAction) {
			case "conferme":

				//IL CAST è necessario?
				booking.setConfirmed(true);
				bookingDAO.update(booking);
				message="confermed";
				break;
				
			//IL CASO DI EDIT È GESTITO DIRETTAMENTE DAL LIVEORDERcONTROLLER				
			case "remove":
				
				booking=bookingDAO.getBooking(book.getId());
				if(booking.getUser().getEmail()!=null || booking.getUser().getEmail()!="")
					MailSenderUtils.SendMail("Booking Elimination",booking.getUser().getEmail(), booking,MailSenderUtils.DELETE);
					//MailSenderUtils.SendMail("Booking Elimination","cosentinomarco90@gmail.com", booking);
				bookingDAO.delete(booking);
				message="removed";
				break;

			default:
				break;
			}
			
			System.out.println(book.getId());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
	}
}

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

import com.fasterxml.jackson.databind.ObjectMapper;

import it.unical.pizzamanager.models.BookingModel;
import it.unical.pizzamanager.persistence.dao.BookingDAO;
import it.unical.pizzamanager.persistence.dao.PizzeriaDAO;
import it.unical.pizzamanager.persistence.entities.Booking;
import it.unical.pizzamanager.persistence.entities.Pizzeria;
import it.unical.pizzamanager.utils.SessionUtils;
import it.unical.pizzamanager.utils.ValidatorUtils;
import it.unical.pizzamanager.utils.mail.MailSenderUtils;

@Controller
public class PizzeriaBookingController {

	private static final Logger logger = LoggerFactory.getLogger(PizzeriaBookingController.class);

	@Autowired
	private WebApplicationContext context;

	@RequestMapping(value = "/pizzeriabooking", method = RequestMethod.GET)
	public String pizzeriaBooking(Model model, HttpSession session) {
		logger.info("Booking controller page requested. Loading list of users.");
		if (!SessionUtils.isPizzeria(session)) {
			return null;
		}
		return "pizzeriabooking";
	}

	@RequestMapping(value = "/pizzeriabookingAjax", method = RequestMethod.GET)
	public @ResponseBody List<Booking> processAJAXRequest(HttpServletRequest request, Model model,
			HttpSession session) {

		if (!SessionUtils.isPizzeria(session)) {
			return null;
		}

		PizzeriaDAO pizzeriaDAO = (PizzeriaDAO) context.getBean("pizzeriaDAO");
		Pizzeria pizzeria = pizzeriaDAO.get(SessionUtils.getPizzeriaIdFromSessionOrNull(session));

		logger.info("Booking request from Pizzeria with id:"+pizzeria.getId());
		
		BookingDAO bookingDAO = (BookingDAO) context.getBean("bookingDAO");
		List<Booking> allBookings = (List<Booking>) bookingDAO.getBookingsFromPizzeria(pizzeria);
		List<Booking> filteredBookings = new ArrayList<>();
		for (Booking booking : allBookings) {
			if (booking.getConfirmed() == false && booking.getCompletionDate() == null)
				filteredBookings.add(booking);
		}

		//SERVE SOLO PER STAMPARE cosa fa la serializzazione
		/*ObjectMapper mapper = new ObjectMapper();
		SimpleModule module = new SimpleModule();
		module.addSerializer(Booking.class, new BookingSerializer());
		mapper.registerModule(module);
		try {
			for (int i = 0; i < filteredBookings.size(); i++) {
				String serialized = mapper.writeValueAsString(filteredBookings.get(i));
			}
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

		return filteredBookings;
	}

	@RequestMapping(value = "/pizzeriabookingAction", method = RequestMethod.POST)
	public @ResponseBody String actionOnBooking(HttpServletRequest request, @RequestParam("action") String jsonAction,
			@RequestParam("booking") String jsonBooking) {

		String REGEX = "[^&%$#!~]*";
		if (ValidatorUtils.ValidateString(REGEX, jsonAction) == false
				|| ValidatorUtils.ValidateString(REGEX, jsonBooking) == false) {
			return null;
		}

		ObjectMapper objectMapper = new ObjectMapper();
		BookingModel book;
		String message = "error";
		try {
			book = objectMapper.readValue(jsonBooking, BookingModel.class);
			BookingDAO bookingDAO = (BookingDAO) context.getBean("bookingDAO");
			Booking booking = bookingDAO.getBooking(book.getId());
			
			logger.info("Operation"+jsonAction+ "on not confirmed booking with id:"+booking.getId());
			switch (jsonAction) {
			case "conferme":

				booking.setConfirmed(true);
				bookingDAO.update(booking);
				message = "confermed";
				break;

			// IL CASO DI EDIT È GESTITO DIRETTAMENTE DAL LIVEORDERcONTROLLER
			case "remove":
				booking = bookingDAO.getBooking(book.getId());
				if (booking.getUser()!= null)
					if(booking.getUser().getEmail()!=null)
						MailSenderUtils.SendMail("Booking Elimination", booking.getUser().getEmail(), booking,MailSenderUtils.DELETE);
					else
						System.out.println("user: "+booking.getUser().getId()+" non ha email");
				
				bookingDAO.delete(booking);
				message = "removed";
				break;

			default:
				break;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
	}
}

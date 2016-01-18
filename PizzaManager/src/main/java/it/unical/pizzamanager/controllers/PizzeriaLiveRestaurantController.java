package it.unical.pizzamanager.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
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
import it.unical.pizzamanager.persistence.dto.Booking;
import it.unical.pizzamanager.persistence.dto.Pizzeria;
import it.unical.pizzamanager.utils.SessionUtils;

@Controller
public class PizzeriaLiveRestaurantController {

	private static final Logger logger = LoggerFactory
			.getLogger(PizzeriaLiveRestaurantController.class);

	@Autowired
	private WebApplicationContext context;

	@RequestMapping(value = "/pizzerialiverestaurant", method = RequestMethod.GET)
	public String pizzeriaLiveRestaurant(Model model, HttpSession session) {
		logger.info("liveRestaurant page requested.");
		if (!SessionUtils.isPizzeria(session)) {
			return null;
		}
		return "pizzerialiverestaurant";
	}

	@RequestMapping(value = "/pizzerialiverestaurantAjax", method = RequestMethod.GET)
	public @ResponseBody List<Booking> processAJAXRequest(HttpServletRequest request, Model model, HttpSession session) {
		if (!SessionUtils.isPizzeria(session)) {
			return null;
		}

		PizzeriaDAO pizzeriaDAO = (PizzeriaDAO) context.getBean("pizzeriaDAO");
		Pizzeria pizzeria = pizzeriaDAO.get(SessionUtils.getPizzeriaIdFromSessionOrNull(session));

		BookingDAO bookingDAO = (BookingDAO) context.getBean("bookingDAO");
		List<Booking> allBookings = (List<Booking>) bookingDAO.getBookingsFromPizzeria(pizzeria);
		List<Booking> filteredBookings=new ArrayList<>();
		for (Booking booking : allBookings) {
			if(booking.getConfirmed()==true && booking.getCompletionDate()==null)
				filteredBookings.add(booking);
		}

		return filteredBookings;
	}
	
	@RequestMapping(value = "/pizzerialiverestaurantAction", method = RequestMethod.POST)
	public @ResponseBody String actionOnBooking(HttpServletRequest request,@RequestParam("action") String jsonAction, @RequestParam("booking") String jsonBooking){
		
		System.out.println(jsonAction);
		System.out.println(jsonBooking);
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
			case "collect":

				//TODO set format data per aggiungere ora
				booking.setCompletionDate(Calendar.getInstance().getTime());
				bookingDAO.update(booking);
				message="collected";
				break;
				
			//IL CASO DI EDIT È GESTITO DIRETTAMENTE DAL LIVEORDERcONTROLLER
			case "sendBack":
				
				//BookingUtils.createBookingFromBookingModel(book, booking.getPizzeria(), context);
				booking.setConfirmed(false);
				bookingDAO.update(booking);
				message="sendBack";
				break;
				
				
			case "remove":
				
				booking=bookingDAO.getBooking(book.getId());
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

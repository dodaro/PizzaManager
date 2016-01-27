package it.unical.pizzamanager.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import it.unical.pizzamanager.persistence.dao.PaymentDAO;
import it.unical.pizzamanager.persistence.dao.PizzeriaDAO;
import it.unical.pizzamanager.persistence.entities.Booking;
import it.unical.pizzamanager.persistence.entities.BookingDelivery;
import it.unical.pizzamanager.persistence.entities.BookingPizzeriaTable;
import it.unical.pizzamanager.persistence.entities.BookingTakeAway;
import it.unical.pizzamanager.persistence.entities.Payment;
import it.unical.pizzamanager.persistence.entities.Pizzeria;
import it.unical.pizzamanager.utils.SessionUtils;
import it.unical.pizzamanager.utils.ValidatorUtils;

@Controller
public class PizzeriaLiveRestaurantController {

	private static final Logger logger = LoggerFactory.getLogger(PizzeriaLiveRestaurantController.class);

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
		logger.info("Request of live booking from Pizzeria with id: "+ pizzeria.getId());

		BookingDAO bookingDAO = (BookingDAO) context.getBean("bookingDAO");
		List<Booking> allBookings = (List<Booking>) bookingDAO.getBookingsFromPizzeria(pizzeria);
		List<Booking> filteredBookings=new ArrayList<>();
		for (Booking booking : allBookings) {
			if(booking.getConfirmed()==true && booking.getCompletionDate()==null)
				filteredBookings.add(booking);
		}
		settingPriorityToBookingList(filteredBookings, context);
		//calcolare priorità

		return filteredBookings;
	}
	
	@RequestMapping(value = "/pizzerialiverestaurantAction", method = RequestMethod.POST)
	public @ResponseBody String actionOnBooking(HttpServletRequest request,@RequestParam("action") String jsonAction, @RequestParam("booking") String jsonBooking){
		
		String REGEX = "[^&%$#!~]*";
		if(ValidatorUtils.ValidateString(REGEX, jsonAction)==false || ValidatorUtils.ValidateString(REGEX, jsonBooking)==false){
			return null;
		}
		
		
		ObjectMapper objectMapper = new ObjectMapper();
		BookingModel book;
		String message="error";
		try {
			book = objectMapper.readValue(jsonBooking, BookingModel.class);
			BookingDAO bookingDAO = (BookingDAO) context.getBean("bookingDAO");
			PaymentDAO paymentDAO = (PaymentDAO) context.getBean("paymentDAO");
			
			Booking booking=bookingDAO.getBooking(book.getId());
			logger.info("Request of "+jsonAction + "for completed booking with id:"+ booking.getId());
			switch (jsonAction) {
			case "collect":
				
				booking.setCompletionDate(Calendar.getInstance().getTime());
				Payment payment=new Payment();
				payment.setPaid(true);
				paymentDAO.create(payment);
				booking.setPayment(payment);
				bookingDAO.update(booking);
				message="collected";
				break;
				
			//IL CASO DI EDIT È GESTITO DIRETTAMENTE DAL LIVEORDERcONTROLLER
			case "sendBack":
				
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
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return message;
	}

	
	private void settingPriorityToBookingList(List<Booking> bookings,WebApplicationContext context ){
		
		BookingDAO bookingDAO = (BookingDAO) context.getBean("bookingDAO");
		
		Date currentDate=new Date();
		for (Booking booking : bookings) {
			Integer priority=0;
			
			if(currentDate.before(booking.getDate())){
				if((booking.getDate().getTime()+booking.getTime().getTime())-currentDate.getTime()>3000){
					priority+=1;
				}
				else{
					priority+=3;
				}
			}
			else{
				priority+=4;
			}

			if(booking instanceof BookingDelivery)
				priority+=2;
			else if(booking instanceof BookingTakeAway)
				priority+=1;
			else if (booking instanceof BookingPizzeriaTable) {
				priority+=0;
			}
			
			booking.setPriority(priority);
			bookingDAO.update(booking);
		}
	}
}

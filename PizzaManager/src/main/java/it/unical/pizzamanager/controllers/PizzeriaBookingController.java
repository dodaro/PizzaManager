package it.unical.pizzamanager.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import it.unical.pizzamanager.persistence.dao.BookingDAO;
import it.unical.pizzamanager.persistence.dao.UserDAO;
import it.unical.pizzamanager.persistence.dto.Booking;
import it.unical.pizzamanager.persistence.dto.BookingTakeAway;
import it.unical.pizzamanager.persistence.dto.OrderItem;
import it.unical.pizzamanager.persistence.dto.PizzaOrderItem;
import it.unical.pizzamanager.persistence.dto.User;

@Controller
public class PizzeriaBookingController {
	
	private static final Logger logger = LoggerFactory.getLogger(PizzeriaBookingController.class);

	@Autowired
	private WebApplicationContext context;

	@SuppressWarnings("unused")
	@RequestMapping(value = "/pizzeriabooking", method = RequestMethod.GET)
	public String pizzeriaBooking(Model model) {
		logger.info("Home page requested. Loading list of users.");

		//ogni qualvolta si riavvia l'applicazione il database viene azzerato
		BookingDAO dao=(BookingDAO) context.getBean("bookingDAO");
		dao.create(new BookingTakeAway(3));
		List<Booking> bookings= (List<Booking>)dao.getBookingList();
		System.out.println("Elemento");
		System.out.println(bookings.get(0));
		

		model.addAttribute("bookings", bookings);

		return "pizzeriabooking";
	}
	
	@RequestMapping(value = "/pizzeriabookingAjax", method = RequestMethod.GET)
	public @ResponseBody List<Booking> processAJAXRequest(HttpServletRequest request, Model model)
	{
		BookingDAO dao=(BookingDAO) context.getBean("bookingDAO");
		//System.out.println(firstname);
		//System.out.println(lastname);
		// Process the request
		// Prepare the response string
		List<Booking> bookings= (List<Booking>)dao.getBookingList();
		for (int i = 0; i < bookings.size(); i++) {
			List<OrderItem> list=new ArrayList<OrderItem>();
			list.add(new PizzaOrderItem(true,20.00));
			bookings.get(0).setOrderItems(list);
		}
		return bookings;
	}


}

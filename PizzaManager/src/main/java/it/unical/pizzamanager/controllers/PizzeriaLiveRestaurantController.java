package it.unical.pizzamanager.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;


@Controller
public class PizzeriaLiveRestaurantController {
	
	private static final Logger logger = LoggerFactory.getLogger(PizzeriaLiveRestaurantController.class);

	@Autowired
	private WebApplicationContext context;

	@RequestMapping(value = "/pizzerialiverestaurant", method = RequestMethod.GET)
	public String pizzeriaLiveRestaurant(Model model) {
		logger.info("liveRestaurant page requested.");

		// ogni qualvolta si riavvia l'applicazione il database viene azzerato
		/*BookingDAO bookingDAO = (BookingDAO) context.getBean("bookingDAO");
		List<Booking> bookings = (List<Booking>) bookingDAO.getBookingList();
		model.addAttribute("bookings", bookings);*/

		return "pizzerialiverestaurant";
	}

}

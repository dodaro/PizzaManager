package it.unical.pizzamanager.controllers;

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
import org.springframework.web.context.WebApplicationContext;

import it.unical.pizzamanager.persistence.dao.BookingDAO;
import it.unical.pizzamanager.persistence.dto.Booking;

@Controller
public class PizzeriaLiveRestaurantController {

	private static final Logger logger = LoggerFactory
			.getLogger(PizzeriaLiveRestaurantController.class);

	@Autowired
	private WebApplicationContext context;

	@RequestMapping(value = "/pizzerialiverestaurant", method = RequestMethod.GET)
	public String pizzeriaLiveRestaurant(Model model) {
		logger.info("liveRestaurant page requested.");

		// ogni qualvolta si riavvia l'applicazione il database viene azzerato
		/*
		 * BookingDAO bookingDAO = (BookingDAO) context.getBean("bookingDAO"); List<Booking>
		 * bookings = (List<Booking>) bookingDAO.getBookingList(); model.addAttribute("bookings",
		 * bookings);
		 */

		return "pizzerialiverestaurant";
	}

	@RequestMapping(value = "/pizzerialiverestaurantAjax", method = RequestMethod.GET)
	public @ResponseBody List<Booking> processAJAXRequest(HttpServletRequest request, Model model) {
		BookingDAO bookingDAO = (BookingDAO) context.getBean("bookingDAO");
		List<Booking> bookings = (List<Booking>) bookingDAO.getBookingList();

		// TODO filtrare i booking confermati
		// System.out.println(bookings.get(0).getDate());

		// FIXME Le lambda danno fastidio a jetty.
		// bookings.removeIf(b -> b.getConfirmed() == false);

		return bookings;
	}

}

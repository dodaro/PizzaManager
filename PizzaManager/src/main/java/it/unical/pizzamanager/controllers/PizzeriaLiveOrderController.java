package it.unical.pizzamanager.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;

@Controller
public class PizzeriaLiveOrderController {

	private static final Logger logger = LoggerFactory.getLogger(PizzeriaLiveOrderController.class);
	

	@RequestMapping(value = "/pizzerialiveorder", method = RequestMethod.GET)
	public String pizzeriaLiveOrder(Model model) {
		logger.info("Live order requested");

		//ogni qualvolta si riavvia l'applicazione il database viene azzerato
		/*BookingDAO dao=(BookingDAO) context.getBean("bookingDAO");
		dao.create(new BookingTakeAway(3));
		List<Booking> bookings= (List<Booking>)dao.getBookingList();
		System.out.println("Elemento");
		System.out.println(bookings.get(0));
		

		model.addAttribute("bookings", bookings);*/

		return "pizzerialiveorder";
	}
}
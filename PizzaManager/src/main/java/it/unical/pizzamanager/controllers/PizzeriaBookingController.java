package it.unical.pizzamanager.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import it.unical.pizzamanager.persistence.dto.Booking;
import it.unical.pizzamanager.serializers.BookingSerializer;
import it.unical.pizzamanager.utils.BookingUtils;


@Controller
public class PizzeriaBookingController {

	private static final Logger logger = LoggerFactory.getLogger(PizzeriaBookingController.class);

	@Autowired
	private WebApplicationContext context;

	@RequestMapping(value = "/pizzeriabooking", method = RequestMethod.GET)
	public String pizzeriaBooking(Model model) {
		logger.info("Home page requested. Loading list of users.");

		// ogni qualvolta si riavvia l'applicazione il database viene azzerato
		BookingDAO bookingDAO = (BookingDAO) context.getBean("bookingDAO");
		List<Booking> bookings = (List<Booking>) bookingDAO.getBookingList();
		model.addAttribute("bookings", bookings);

		return "pizzeriabooking";
	}

	@RequestMapping(value = "/pizzeriabookingAjax", method = RequestMethod.GET)
	public @ResponseBody List<Booking> processAJAXRequest(HttpServletRequest request, Model model) {
		BookingDAO bookingDAO = (BookingDAO) context.getBean("bookingDAO");
		List<Booking> bookings = (List<Booking>) bookingDAO.getBookingList();
		//TODO filtrare i booking non confermati
		
		
		ObjectMapper mapper = new ObjectMapper();

		SimpleModule module = new SimpleModule();
		module.addSerializer(Booking.class, new BookingSerializer());
		mapper.registerModule(module);

		try {
			for (int i = 0; i < bookings.size(); i++) {
				String serialized = mapper.writeValueAsString(bookings.get(i));
				System.out.println(serialized);				
			}
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		return bookings;
	}
	
	@RequestMapping(value = "/pizzeriabookingAction", method = RequestMethod.POST)
	public @ResponseBody String actionOnBooking(HttpServletRequest request,@RequestParam("action") String jsonAction, @RequestParam("booking") String jsonBooking){
		
		System.out.println(jsonAction);
		System.out.println(jsonBooking);
		ObjectMapper objectMapper = new ObjectMapper();
		BookingModel book;
		String message="error";
		try {
			book = objectMapper.readValue(jsonBooking, BookingModel.class);
			System.out.println(book.getId());
			BookingDAO bookingDAO = (BookingDAO) context.getBean("bookingDAO");
			
			Booking booking=bookingDAO.getBooking(book.getId());
			switch (jsonAction) {
			case "conferme":

				//IL CAST è necessario?
				booking.setConfirmed(true);
				bookingDAO.update(booking);
				message="confermed";
				break;
				
			case "edit":
				
				BookingUtils.createBookingFromBookingModel(book, booking.getPizzeria(), context);
				message="updated";
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

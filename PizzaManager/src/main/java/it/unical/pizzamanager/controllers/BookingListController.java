package it.unical.pizzamanager.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

import it.unical.pizzamanager.model.BookingUserDisplay;
import it.unical.pizzamanager.persistence.dao.BookingDAO;
import it.unical.pizzamanager.persistence.dao.OrderItemDAO;
import it.unical.pizzamanager.persistence.dao.UserDAO;
import it.unical.pizzamanager.persistence.dto.Booking;
import it.unical.pizzamanager.persistence.dto.OrderItem;
import it.unical.pizzamanager.persistence.dto.User;
import it.unical.pizzamanager.utils.BookingUserDisplayUtils;
import it.unical.pizzamanager.utils.SessionUtils;

@Controller
public class BookingListController {

	@Autowired
	WebApplicationContext context;

	@RequestMapping(value = "/orders", method = RequestMethod.GET)
	public String orders(Model model, HttpSession session,HttpServletRequest request) {
		if (!SessionUtils.isUser(session)) {
			return "index";
		}
		UserDAO userDAO = (UserDAO) context.getBean("userDAO");
		BookingDAO bookingDAO = (BookingDAO) context.getBean("bookingDAO");
		User user = userDAO.get(SessionUtils.getUserIdFromSessionOrNull(session));

		model.addAttribute("user", user);
		List<Booking> bookings = bookingDAO.getActiveUserBookings(user);
		List<BookingUserDisplay> bookingsList = createBookingList(bookings,bookingDAO);
		model.addAttribute("bookings", bookingsList);
		return "orders";
	}
	
	
	@RequestMapping(value="/orders/removeItem",method=RequestMethod.POST)
	public String removeItem(@RequestParam String toRemove,Model model,HttpSession session){
		System.out.println(toRemove);
		if (!SessionUtils.isUser(session)) {
			return "index";
		}
		UserDAO userDAO = (UserDAO) context.getBean("userDAO");
		BookingDAO bookingDAO = (BookingDAO) context.getBean("bookingDAO");
		OrderItemDAO orderItemDAO = (OrderItemDAO) context.getBean("orderItemDAO");
		User user = userDAO.get(SessionUtils.getUserIdFromSessionOrNull(session));
		String[] ids=toRemove.split(";");
		int itemId=Integer.valueOf(ids[0]);
		int bookingId=Integer.valueOf(ids[1]);
		Booking booking=userDAO.getBooking(bookingId,user.getId());
		if(booking==null)
			return "index";
		
		for (OrderItem item : booking.getOrderItems()) {
			if(item.getId()==itemId){
				orderItemDAO.delete(item);
			}		
		}
		bookingDAO.update(booking);
		model.addAttribute("user", user);
		return "orders";
	}

	private List<BookingUserDisplay> createBookingList(List<Booking> bookings, BookingDAO bookingDAO) {
		List<BookingUserDisplay> bookingList = new ArrayList<>();
	
		for (Booking booking : bookings) {
			List<Booking> activeBooking=bookingDAO.getOrderedBookings(booking.getPizzeria());
			BookingUserDisplay bookingUserDisplay=BookingUserDisplayUtils.createBookingUserDisplay(booking,activeBooking);
			bookingList.add(bookingUserDisplay);
		}
		return bookingList;
	}

	
	
	
}

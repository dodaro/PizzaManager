package it.unical.pizzamanager.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;

import it.unical.pizzamanager.models.BookingUserDisplay;
import it.unical.pizzamanager.persistence.dao.BookingDAO;
import it.unical.pizzamanager.persistence.dao.OrderItemDAO;
import it.unical.pizzamanager.persistence.dao.PaymentDAO;
import it.unical.pizzamanager.persistence.dao.PizzeriaDAO;
import it.unical.pizzamanager.persistence.dao.UserDAO;
import it.unical.pizzamanager.persistence.entities.Booking;
import it.unical.pizzamanager.persistence.entities.OrderItem;
import it.unical.pizzamanager.persistence.entities.User;
import it.unical.pizzamanager.utils.BookingUserDisplayUtils;
import it.unical.pizzamanager.utils.SessionUtils;

@Controller
public class BookingListController {

	@Autowired
	WebApplicationContext context;

	@RequestMapping(value = "/orders", method = RequestMethod.GET)
	public String orders(Model model, HttpSession session, HttpServletRequest request) {
		if (!SessionUtils.isUser(session)) {
			return "index";
		}
		UserDAO userDAO = (UserDAO) context.getBean("userDAO");
		BookingDAO bookingDAO = (BookingDAO) context.getBean("bookingDAO");
		User user = userDAO.get(SessionUtils.getUserIdFromSessionOrNull(session));
		model.addAttribute("user", user);
		List<Booking> bookings = bookingDAO.getActiveUserBookings(user);
		List<BookingUserDisplay> bookingsList = createBookingList(bookings, bookingDAO);
		model.addAttribute("bookings", bookingsList);
		return "orders";
	}

	@RequestMapping(value = "/orders/removeItem", method = RequestMethod.POST)
	public String removeItem(@RequestParam int iditem, @RequestParam int idbooking, Model model, HttpSession session) {
		if (!SessionUtils.isUser(session)) {
			return "index";
		}
		UserDAO userDAO = (UserDAO) context.getBean("userDAO");
		BookingDAO bookingDAO = (BookingDAO) context.getBean("bookingDAO");
		OrderItemDAO orderItemDAO = (OrderItemDAO) context.getBean("orderItemDAO");
		PizzeriaDAO pizzeriaDAO = (PizzeriaDAO) context.getBean("pizzeriaDAO");
		User user = userDAO.get(SessionUtils.getUserIdFromSessionOrNull(session));
		int itemId = iditem;
		int bookingId = idbooking;
		Booking booking = userDAO.getBooking(bookingId, user.getId());
		if (booking == null)
			return "index";

		for (OrderItem item : booking.getOrderItems()) {
			if (item.getId() == itemId) {
				booking.getOrderItems().remove(item);
				bookingDAO.update(booking);
				orderItemDAO.delete(item);

				if (booking.getOrderItems().size() == 0) {
					bookingDAO.delete(booking);
					model.addAttribute("user", user);
					return "orders";
				}
				break;
			}
		}

		PaymentDAO paymentDAO = (PaymentDAO) context.getBean("paymentDAO");
		BookingUserDisplayUtils.cancelPayment(booking, paymentDAO);
		bookingDAO.update(booking);
		model.addAttribute("user", user);
		return "orders";
	}

	private List<BookingUserDisplay> createBookingList(List<Booking> bookings, BookingDAO bookingDAO) {
		List<BookingUserDisplay> bookingList = new ArrayList<>();

		for (Booking booking : bookings) {
			List<Booking> activeBooking = bookingDAO.getOrderedBookings(booking.getPizzeria());
			BookingUserDisplay bookingUserDisplay = BookingUserDisplayUtils.createBookingUserDisplay(booking,
					activeBooking);
			bookingList.add(bookingUserDisplay);
		}
		return bookingList;
	}

}

package it.unical.pizzamanager.controllers;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;

import it.unical.pizzamanager.model.CartBooking;
import it.unical.pizzamanager.model.ItemToBook;
import it.unical.pizzamanager.model.OrderItemDisplay;
import it.unical.pizzamanager.model.PizzeriaCartBooking;
import it.unical.pizzamanager.persistence.dao.BookingDAO;
import it.unical.pizzamanager.persistence.dao.CartDAO;
import it.unical.pizzamanager.persistence.dao.OrderItemDAO;
import it.unical.pizzamanager.persistence.dao.PizzeriaDAO;
import it.unical.pizzamanager.persistence.dao.UserDAO;
import it.unical.pizzamanager.persistence.dto.BeverageOrderItem;
import it.unical.pizzamanager.persistence.dto.Booking;
import it.unical.pizzamanager.persistence.dto.BookingDelivery;
import it.unical.pizzamanager.persistence.dto.BookingTakeAway;
import it.unical.pizzamanager.persistence.dto.Cart;
import it.unical.pizzamanager.persistence.dto.OrderItem;
import it.unical.pizzamanager.persistence.dto.PizzaOrderItem;
import it.unical.pizzamanager.persistence.dto.User;
import it.unical.pizzamanager.utils.SessionUtils;

@Controller
public class UserBookingController {

	@Autowired
	private WebApplicationContext context;

	@RequestMapping(value = "/userBooking", method = RequestMethod.GET)
	public String bookingCart(Model model, HttpSession session) {

		UserDAO userDAO = (UserDAO) context.getBean("userDAO");
		User user = userDAO.get(SessionUtils.getUserIdFromSessionOrNull(session));

		model.addAttribute("user", user);

		return "/userBooking";
	}

	@RequestMapping(value = "/userBooking/book", method = RequestMethod.POST)
	public String book(@ModelAttribute("pizzeriaCartBook") PizzeriaCartBooking pizzeriaBook, Model model,
			HttpSession session) {

		UserDAO userDAO = (UserDAO) context.getBean("userDAO");
		BookingDAO bookingDAO = (BookingDAO) context.getBean("bookingDAO");
		CartDAO cartDAO = (CartDAO) context.getBean("cartDAO");
		PizzeriaDAO pizzeriaDAO = (PizzeriaDAO) context.getBean("pizzeriaDAO");
		OrderItemDAO orderItemDAO = (OrderItemDAO) context.getBean("orderItemDAO");

		User user = userDAO.get(SessionUtils.getUserIdFromSessionOrNull(session));
		Cart cart = cartDAO.getUserCart(user);
		ArrayList<OrderItem> toBook = itemToBook(pizzeriaBook, cart);

		Booking booking = createBooking(pizzeriaBook.getBookingType());
		booking.setConfirmed(false);
		booking.setDate(pizzeriaBook.getData());
		booking.setTime(pizzeriaBook.getData());
		booking.setPizzeria(pizzeriaDAO.getByName(pizzeriaBook.getPizzeria()));
		booking.setUser(user);
		booking.setOrderItems(toBook);
		if(booking instanceof BookingDelivery)
			((BookingDelivery) booking).setDeliveryAddress(user.getAddress());
		for (OrderItem orderItem : toBook) {
			orderItem.setBooking(booking);
			orderItem.setCart(null);
			orderItemDAO.update(orderItem);

		}
		bookingDAO.create(booking);
		model.addAttribute("user", user);
		return "/userBooking/bookCart";
	}

	@RequestMapping(value = "/userBooking/bookCart", method = RequestMethod.GET)
	public String bookCart(Model model, HttpSession session) {
		UserDAO userDAO = (UserDAO) context.getBean("userDAO");
		User user = userDAO.get(SessionUtils.getUserIdFromSessionOrNull(session));
		CartDAO cartDAO = (CartDAO) context.getBean("cartDAO");
		Cart cart = cartDAO.getUserCart(user);

		ArrayList<CartBooking> bookings = createBookingsToDisplay(cart);
		model.addAttribute("bookings", bookings);
		model.addAttribute("user", user);

		return "/userBooking";
	}

	private ArrayList<CartBooking> createBookingsToDisplay(Cart cart) {
		ArrayList<CartBooking> bookings = new ArrayList<>();
		int numb = 0;
		for (OrderItem item : cart.getOrderItems()) {
			boolean exist = false;
			OrderItemDisplay itemToDisplay = createItemToDisplay(item);
			for (CartBooking b : bookings) {
				if (itemToDisplay.getPizzeria() == b.getPizzeria()) {
					b.getItems().add(itemToDisplay);
					exist = true;
					break;
				}
			}
			if (!exist) {
				CartBooking booking = new CartBooking();
				booking.setNumber(numb);
				booking.setPizzeria(itemToDisplay.getPizzeria());
				booking.getItems().add(itemToDisplay);
				bookings.add(booking);
				numb++;

			}
		}
		return bookings;
	}

	private Booking createBooking(String bookingType) {
		if (bookingType.equals("takeAway"))
			return new BookingTakeAway();
		else if (bookingType.equals("delivery"))
			return new BookingDelivery();
		return null;

	}

	private ArrayList<OrderItem> itemToBook(PizzeriaCartBooking pizzeriaBook, Cart cart) {
		ArrayList<OrderItem> toBook = new ArrayList<>();
		for (Integer id : pizzeriaBook.getItems()) {
			for (OrderItem orderItem : cart.getOrderItems()) {
				if (orderItem.getId() == id) {
					toBook.add(orderItem);
					break;
				}
			}
		}
		return toBook;
	}

	private OrderItemDisplay createItemToDisplay(OrderItem item) {
		OrderItemDisplay itemToDisplay = new OrderItemDisplay();
		itemToDisplay.setId(item.getId());
		if (item instanceof PizzaOrderItem) {
			itemToDisplay.setItemName(((PizzaOrderItem) item).pizzaName());
			itemToDisplay.setPizzeria(((PizzaOrderItem) item).pizzeriaName());
		} else if (item instanceof BeverageOrderItem) {
			itemToDisplay.setItemName(((BeverageOrderItem) item).beverageName());
			itemToDisplay.setPizzeria(((BeverageOrderItem) item).pizzeriaName());
		}
		itemToDisplay.setCost(item.getCost());
		itemToDisplay.setNumber(item.getNumber());
		itemToDisplay.setImageItem("not found");
		return itemToDisplay;
	}
}

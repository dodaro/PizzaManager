package it.unical.pizzamanager.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;


import it.unical.pizzamanager.persistence.dto.Pizzeria;
import it.unical.pizzamanager.utils.*;
import it.unical.pizzamanager.model.CartBooking;
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
import it.unical.pizzamanager.persistence.dto.RelationPizzaIngredient;
import it.unical.pizzamanager.persistence.dto.RelationPizzaOrderItemIngredient;
import it.unical.pizzamanager.persistence.dto.User;
import it.unical.pizzamanager.utils.BookingUserDisplayUtils;
import it.unical.pizzamanager.utils.SessionUtils;

@Controller
public class UserBookingController {

	@Autowired
	private WebApplicationContext context;

	@RequestMapping(value = "/userBooking", method = RequestMethod.GET)
	public String bookingCart(Model model, HttpSession session) {
		if (!SessionUtils.isUser(session)) {
			return "index";
		}
		UserDAO userDAO = (UserDAO) context.getBean("userDAO");
		User user = userDAO.get(SessionUtils.getUserIdFromSessionOrNull(session));
		CartDAO cartDAO = (CartDAO) context.getBean("cartDAO");
		Cart cart = cartDAO.getUserCart(user);

		ArrayList<CartBooking> bookings = createBookingsToDisplay(cart);
		model.addAttribute("bookings", bookings);
		model.addAttribute("user", user);

		return "userBooking";
	}

	@ResponseBody
	@RequestMapping(value = "/userBooking/book", method = RequestMethod.POST)
	public String book(@RequestParam String type, @RequestParam String pizzeria, @RequestParam("date") String date,
			Model model, HttpSession session) {

		DateFormat format = new SimpleDateFormat("YYYY/MM/DD HH:mm");

		PizzeriaCartBooking pizzeriaBook = new PizzeriaCartBooking();
		pizzeriaBook.setPizzeria(pizzeria);
		pizzeriaBook.setBookingType(type);

		try {
			pizzeriaBook.setDate(format.parse(date));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// set correctly date
		UserDAO userDAO = (UserDAO) context.getBean("userDAO");
		BookingDAO bookingDAO = (BookingDAO) context.getBean("bookingDAO");
		CartDAO cartDAO = (CartDAO) context.getBean("cartDAO");
		PizzeriaDAO pizzeriaDAO = (PizzeriaDAO) context.getBean("pizzeriaDAO");
		OrderItemDAO orderItemDAO = (OrderItemDAO) context.getBean("orderItemDAO");

		User user = userDAO.get(SessionUtils.getUserIdFromSessionOrNull(session));
		Cart cart = cartDAO.getUserCart(user);
		ArrayList<OrderItem> toBook = itemToBook(pizzeriaBook, cart);
		Pizzeria pizzerias = pizzeriaDAO.getByName(pizzeriaBook.getPizzeria());
		Booking booking = createBooking(pizzeriaBook.getBookingType());
		booking.setConfirmed(false);
		booking.setDate(pizzeriaBook.getDate());
		booking.setTime(pizzeriaBook.getDate());
		booking.setPizzeria(pizzerias);
		booking.setUser(user);

		if (booking instanceof BookingDelivery)
			((BookingDelivery) booking).setDeliveryAddress(user.getAddress());
		booking.setBill(BookingUserDisplayUtils.calculateBill(toBook));

		bookingDAO.create(booking);

		for (OrderItem orderItem : toBook) {
			orderItem.setBooking(booking);
			orderItem.setCart(null);
			if (orderItem instanceof PizzaOrderItem)
				OrderItemUtils.setPizzaOrderCost((PizzaOrderItem) orderItem, pizzerias);
			else if (orderItem instanceof BeverageOrderItem)
				OrderItemUtils.setBeverageOrderCost((BeverageOrderItem) orderItem, pizzerias);
			orderItemDAO.update(orderItem);

		}
		BookingUtils.calculateBill(booking, pizzerias);
		bookingDAO.update(booking);

		// ArrayList<CartBooking> bookings = createBookingsToDisplay(cart);
		// model.addAttribute("bookings", bookings);
		model.addAttribute("user", user);
		return "{\"success\" : true}";
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
				booking.setIdentifier("pizzeria" + numb);
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
		for (OrderItem item : cart.getOrderItems()) {
			if (item instanceof PizzaOrderItem) {
				if (((PizzaOrderItem) item).getPizzeria_pizza().getPizzeria().getName()
						.equals(pizzeriaBook.getPizzeria())) {
					toBook.add(item);
				}
			} else if (item instanceof BeverageOrderItem) {
				if (((BeverageOrderItem) item).getPizzeria_beverage().getPizzeria().getName()
						.equals(pizzeriaBook.getPizzeria())) {
					toBook.add(item);
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
			itemToDisplay.setIngredients(stringFyIngredientsList(
					((PizzaOrderItem) item).getPizzeria_pizza().getPizza().getPizzaIngredients(),
					((PizzaOrderItem) item).getPizzaOrderIngredients()));
		} else if (item instanceof BeverageOrderItem) {
			itemToDisplay.setItemName(((BeverageOrderItem) item).beverageName());
			itemToDisplay.setPizzeria(((BeverageOrderItem) item).pizzeriaName());
		}
		itemToDisplay.setCost(item.getCost());
		itemToDisplay.setNumber(item.getNumber());
		itemToDisplay.setImageItem("not found");
		return itemToDisplay;
	}

	private String stringFyIngredientsList(List<RelationPizzaIngredient> pizzaIngredients,
			List<RelationPizzaOrderItemIngredient> pizzaOrderIngredients) {
		String ingredients = "(";
		for (RelationPizzaIngredient relationPizzaIngredient : pizzaIngredients) {
			ingredients = ingredients.concat(relationPizzaIngredient.getIngredient().getName());
			ingredients = ingredients.concat(",");
		}
		for (RelationPizzaOrderItemIngredient relationPizzaOrderItemIngredient : pizzaOrderIngredients) {
			ingredients = ingredients.concat(relationPizzaOrderItemIngredient.getIngredient().getName());
			ingredients = ingredients.concat(",");
		}
		ingredients = ingredients.substring(0, ingredients.length() - 1);
		ingredients = ingredients.concat(")");
		System.out.println(ingredients);
		return ingredients;
	}
}

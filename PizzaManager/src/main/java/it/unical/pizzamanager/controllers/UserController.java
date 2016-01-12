package it.unical.pizzamanager.controllers;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;

import it.unical.pizzamanager.model.CartBooking;
import it.unical.pizzamanager.persistence.dao.CartDAO;
import it.unical.pizzamanager.persistence.dao.OrderItemDAO;
import it.unical.pizzamanager.persistence.dao.UserDAO;
import it.unical.pizzamanager.persistence.dto.BeverageOrderItem;
import it.unical.pizzamanager.persistence.dto.Cart;
import it.unical.pizzamanager.persistence.dto.OrderItem;
import it.unical.pizzamanager.persistence.dto.PizzaOrderItem;
import it.unical.pizzamanager.persistence.dto.User;
import it.unical.pizzamanager.utils.SessionUtils;

@Controller
public class UserController {

	@Autowired
	private WebApplicationContext context;

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String user(Model model, HttpSession session) {

		if (SessionUtils.isUser(session)) {
			setUserAttribute(session, model);
		} else {
			return "index";
		}
		return "user";
	}

	@RequestMapping(value = "/cart", method = RequestMethod.GET)
	public String cart(Model model, HttpSession session) {

		setUserAttribute(session, model);
		setCartAttribute(session, model);
		return "cart";
	}

	private void setCartAttribute(HttpSession session, Model model) {
		UserDAO userDAO = (UserDAO) context.getBean("userDAO");
		User user = userDAO.get(SessionUtils.getUserIdFromSessionOrNull(session));
		CartDAO cartDAO = (CartDAO) context.getBean("cartDAO");
		Cart cart = cartDAO.getUserCart(user);
		ArrayList<BeverageOrderItem> beverageItems = new ArrayList<>();
		ArrayList<PizzaOrderItem> pizzaItems = new ArrayList<>();
		for (OrderItem items : cart.getOrderItems()) {
			if (items instanceof PizzaOrderItem)
				pizzaItems.add((PizzaOrderItem) items);
			else if (items instanceof BeverageOrderItem)
				beverageItems.add((BeverageOrderItem) items);
		}
		model.addAttribute("pizzaItems", pizzaItems);
		model.addAttribute("beverageItems", beverageItems);

	}

	@RequestMapping(value = "/removeItem", method = RequestMethod.GET)
	public String removeItem(@RequestParam Integer id, Model model, HttpSession session) {
		UserDAO userDAO = (UserDAO) context.getBean("userDAO");
		User user = userDAO.get(SessionUtils.getUserIdFromSessionOrNull(session));
		CartDAO cartDAO = (CartDAO) context.getBean("cartDAO");
		Cart cart = cartDAO.getUserCart(user);
		OrderItemDAO orderItemDAO = (OrderItemDAO) context.getBean("orderItemDAO");
		OrderItem item = orderItemDAO.getItem(id);
		cart.getOrderItems().remove(item);
		cartDAO.update(cart);
		return "cart";
	}

	@RequestMapping(value = "/bookCart", method = RequestMethod.GET)
	public String bookCart(Model model, HttpSession session) {
		UserDAO userDAO = (UserDAO) context.getBean("userDAO");
		User user = userDAO.get(SessionUtils.getUserIdFromSessionOrNull(session));
		CartDAO cartDAO = (CartDAO) context.getBean("cartDAO");
		Cart cart = cartDAO.getUserCart(user);
		ArrayList<CartBooking> bookings = new ArrayList<>();
		int i=0;
		for (OrderItem item : cart.getOrderItems()) {
			boolean exist = false;
			for (CartBooking b : bookings) {
				if (item instanceof PizzaOrderItem) {
					if (((PizzaOrderItem) item).getPizzeria_pizza().getPizzeria().getId() == b.getPizzeria().getId()) {
						b.getItems().add(item);
						exist = true;
						break;
					}
				} else if (item instanceof BeverageOrderItem) {
					if (((BeverageOrderItem) item).getPizzeria_beverage().getPizzeria().getId() == b.getPizzeria()
							.getId()) {
						b.getItems().add(item);
						exist = true;
						break;
					}
				}
			}
			if (!exist) {
				CartBooking booking = new CartBooking(i);
				if (item instanceof PizzaOrderItem) {
					booking.setPizzeria(((PizzaOrderItem) item).getPizzeria_pizza().getPizzeria());
				} else if (item instanceof BeverageOrderItem) {
					booking.setPizzeria(((BeverageOrderItem) item).getPizzeria_beverage().getPizzeria());
				}
				booking.getItems().add(item);
				bookings.add(booking);
				i++;
			}
		}
		model.addAttribute("bookings",bookings);
		return "bookCart";
	}
	
	@RequestMapping(value = "/settings", method = RequestMethod.GET)
	public String settings(Model model, HttpSession session) {

		setUserAttribute(session, model);
		return "settings";
	}

	private void setUserAttribute(HttpSession session, Model model) {
		UserDAO userDAO = (UserDAO) context.getBean("userDAO");
		User user = userDAO.get(SessionUtils.getUserIdFromSessionOrNull(session));
		model.addAttribute("user", user);
	}
}

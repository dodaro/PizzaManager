package it.unical.pizzamanager.controllers;

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

import it.unical.pizzamanager.models.CartBooking;
import it.unical.pizzamanager.models.CartDisplay;
import it.unical.pizzamanager.models.OrderItemDisplay;
import it.unical.pizzamanager.persistence.dao.CartDAO;
import it.unical.pizzamanager.persistence.dao.OrderItemDAO;
import it.unical.pizzamanager.persistence.dao.RelationPizzeriaBeverageDAO;
import it.unical.pizzamanager.persistence.dao.RelationPizzeriaPizzaDAO;
import it.unical.pizzamanager.persistence.dao.UserDAO;
import it.unical.pizzamanager.persistence.entities.BeverageOrderItem;
import it.unical.pizzamanager.persistence.entities.Cart;
import it.unical.pizzamanager.persistence.entities.OrderItem;
import it.unical.pizzamanager.persistence.entities.PizzaOrderItem;
import it.unical.pizzamanager.persistence.entities.RelationPizzeriaBeverage;
import it.unical.pizzamanager.persistence.entities.RelationPizzeriaPizza;
import it.unical.pizzamanager.persistence.entities.User;
import it.unical.pizzamanager.utils.SessionUtils;

@Controller
public class CartController {

	@Autowired
	private WebApplicationContext context;

	@RequestMapping(value = "/cart", method = RequestMethod.GET)
	public String cart(Model model, HttpSession session) {
		if (!SessionUtils.isUser(session)) {
			return "index";
		}
		UserDAO userDAO = (UserDAO) context.getBean("userDAO");
		User user = userDAO.get(SessionUtils.getUserIdFromSessionOrNull(session));
		CartDAO cartDAO = (CartDAO) context.getBean("cartDAO");
		Cart cart = cartDAO.getUserCart(user);
		CartDisplay cartDisplay = createCartToDisplay(cart.getOrderItems());
		model.addAttribute("user", user);

		model.addAttribute("cart", cartDisplay);
		return "cart";
	}

	@RequestMapping(value = "/cart/removeItem", method = RequestMethod.POST)
	public String cart(@RequestParam("id") Integer id, Model model, HttpSession session) {
		if (!SessionUtils.isUser(session)) {
			return "index";
		}
		UserDAO userDAO = (UserDAO) context.getBean("userDAO");
		User user = userDAO.get(SessionUtils.getUserIdFromSessionOrNull(session));
		CartDAO cartDAO = (CartDAO) context.getBean("cartDAO");

		OrderItemDAO orderItemDAO = (OrderItemDAO) context.getBean("orderItemDAO");
		OrderItem item = orderItemDAO.getItem(id);

		orderItemDAO.delete(item);

		Cart cart = cartDAO.getUserCart(user);
		CartDisplay cartDisplay = createCartToDisplay(cart.getOrderItems());

		model.addAttribute("user", user);

		model.addAttribute("cart", cartDisplay);
		return "cart";
	}

	@ResponseBody
	@RequestMapping(value = "/bookCart", method = RequestMethod.POST)
	public String bookCart(@RequestParam("itemToBook") String itemToBook, Model model, HttpSession session) {
		if (!SessionUtils.isUser(session)) {
			return "index";
		}
		UserDAO userDAO = (UserDAO) context.getBean("userDAO");
		User user = userDAO.get(SessionUtils.getUserIdFromSessionOrNull(session));
		CartDAO cartDAO = (CartDAO) context.getBean("cartDAO");
		Cart cart = cartDAO.getUserCart(user);
		String[] itemsToBook = itemToBook.split(";");
		updateCart(itemsToBook, cart, cartDAO);

		ArrayList<CartBooking> bookings = createBookingsToDisplay(cart);
		model.addAttribute("bookings", bookings);
		return "{\"success\" : true}";
	}

	@ResponseBody
	@RequestMapping(value = "/cart/addItem", method = RequestMethod.POST)
	public String addItem(@RequestParam("itemToBook") int id, Model model, HttpSession session) {
		if (!SessionUtils.isUser(session)) {
			return "index";
		}
		UserDAO userDAO = (UserDAO) context.getBean("userDAO");
		User user = userDAO.get(SessionUtils.getUserIdFromSessionOrNull(session));
		CartDAO cartDAO = (CartDAO) context.getBean("cartDAO");
		Cart cart = cartDAO.getUserCart(user);

		RelationPizzeriaPizzaDAO menuDAO = (RelationPizzeriaPizzaDAO) context.getBean("relationPizzeriaPizzaDAO");
		RelationPizzeriaPizza pizza = menuDAO.get(id);

		addToCart(pizza, cart);

		model.addAttribute("user", user);
		return "{\"success\" : true}";
	}

	@ResponseBody
	@RequestMapping(value = "/cart/addBeverage", method = RequestMethod.POST)
	public String addBeverage(@RequestParam("itemToBook") int id, Model model, HttpSession session) {
		if (!SessionUtils.isUser(session)) {
			return "index";
		}
		UserDAO userDAO = (UserDAO) context.getBean("userDAO");
		User user = userDAO.get(SessionUtils.getUserIdFromSessionOrNull(session));
		CartDAO cartDAO = (CartDAO) context.getBean("cartDAO");
		Cart cart = cartDAO.getUserCart(user);

		RelationPizzeriaBeverageDAO menuDAO = (RelationPizzeriaBeverageDAO) context
				.getBean("relationPizzeriaBeverageDAO");
		RelationPizzeriaBeverage beverage = menuDAO.get(id);

		addToCart(beverage, cart);

		model.addAttribute("user", user);
		return "{\"success\" : true}";
	}

	private void addToCart(RelationPizzeriaPizza pizza, Cart cart) {
		OrderItemDAO orderItemDAO = (OrderItemDAO) context.getBean("orderItemDAO");
		for (OrderItem o : cart.getOrderItems()) {
			if (o instanceof PizzaOrderItem) {
				if (((PizzaOrderItem) o).getPizzeria_pizza().getId() == pizza.getId()) {
					o.setNumber(o.getNumber() + 1);
					orderItemDAO.update(o);
					return;
				}
			}
		}
		PizzaOrderItem item = new PizzaOrderItem();
		item.setCart(cart);
		item.setCost(pizza.getPrice());
		item.setPizzeria_pizza(pizza);
		item.setNumber(1);

		orderItemDAO.create(item);
	}

	private void addToCart(RelationPizzeriaBeverage beverage, Cart cart) {
		OrderItemDAO orderItemDAO = (OrderItemDAO) context.getBean("orderItemDAO");
		for (OrderItem o : cart.getOrderItems()) {
			if (o instanceof BeverageOrderItem) {
				if (((BeverageOrderItem) o).getPizzeria_beverage().getId() == beverage.getId()) {
					o.setNumber(o.getNumber() + 1);
					orderItemDAO.update(o);
					return;
				}
			}
		}
		BeverageOrderItem item = new BeverageOrderItem();
		item.setCart(cart);
		item.setCost(beverage.getPrice());
		item.setPizzeria_beverage(beverage);
		item.setNumber(1);

		orderItemDAO.create(item);
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
		for (CartBooking books : bookings) {
			books.performTotal();
		}
		return bookings;
	}

	private void updateCart(String[] itemsToBook, Cart cart, CartDAO cartDAO) {

		for (int j = 0; j < itemsToBook.length; j++) {
			String[] i = itemsToBook[j].split("-");
			int id = Integer.valueOf(i[0]);
			int number = Integer.valueOf(i[1]);
			for (OrderItem items : cart.getOrderItems()) {
				if (items.getId() == id) {

					items.setNumber(number);
					break;
				}
			}
		}

		cartDAO.update(cart);
	}

	private CartDisplay createCartToDisplay(List<OrderItem> list) {
		CartDisplay cart = new CartDisplay();
		for (OrderItem item : list) {
			OrderItemDisplay itemToDisplay = createItemToDisplay(item);
			cart.getItems().add(itemToDisplay);
		}
		return cart;
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

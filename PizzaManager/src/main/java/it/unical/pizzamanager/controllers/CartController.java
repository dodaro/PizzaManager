package it.unical.pizzamanager.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;

import it.unical.pizzamanager.model.CartBooking;
import it.unical.pizzamanager.model.CartDisplay;
import it.unical.pizzamanager.model.ItemToBook;
import it.unical.pizzamanager.model.OrderItemDisplay;
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
		System.out.println("id " + id);
		UserDAO userDAO = (UserDAO) context.getBean("userDAO");
		User user = userDAO.get(SessionUtils.getUserIdFromSessionOrNull(session));
		CartDAO cartDAO = (CartDAO) context.getBean("cartDAO");

		OrderItemDAO orderItemDAO = (OrderItemDAO) context.getBean("orderItemDAO");
		OrderItem item = orderItemDAO.getItem(id);
		System.out.println("Cancello " + item.getId());
		orderItemDAO.delete(item);
		System.out.println("Cancello ");
		Cart cart = cartDAO.getUserCart(user);
		System.out.println("cart" + cart.getOrderItems().size());
		CartDisplay cartDisplay = createCartToDisplay(cart.getOrderItems());

		model.addAttribute("user", user);

		model.addAttribute("cart", cartDisplay);
		return "cart";
	}

	
	@RequestMapping(value = "/bookCart", method = RequestMethod.POST)
	public String bookCart(@ModelAttribute("itemsToBook") ItemToBook itemsToBook,Model model, HttpSession session) {
		if (!SessionUtils.isUser(session)) {
			return "index";
		}
		System.out.println(itemsToBook.getIds().size());
		System.out.println("");
		System.out.println("");
		updateCart(itemsToBook);
		UserDAO userDAO = (UserDAO) context.getBean("userDAO");
		User user = userDAO.get(SessionUtils.getUserIdFromSessionOrNull(session));
		CartDAO cartDAO = (CartDAO) context.getBean("cartDAO");
		Cart cart = cartDAO.getUserCart(user);
		
		ArrayList<CartBooking> bookings = createBookingsToDisplay(cart);
		model.addAttribute("bookings", bookings);
		return "redirect:/userBooking";
	}

	private ArrayList<CartBooking> createBookingsToDisplay(Cart cart) {
		ArrayList<CartBooking> bookings = new ArrayList<>();
		int numb=0;
		for (OrderItem item : cart.getOrderItems()) {
			boolean exist = false;
			OrderItemDisplay itemToDisplay=createItemToDisplay(item);
			for (CartBooking b : bookings) {
				if(itemToDisplay.getPizzeria()==b.getPizzeria()){
					b.getItems().add(itemToDisplay);
					exist=true;
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

	private void updateCart(ItemToBook itemsToBook) {
		OrderItemDAO orderItemDAO = (OrderItemDAO) context.getBean("orderItemDAO");
		
		for (int j=0;j<itemsToBook.getIds().size();j++) {
			Integer id=itemsToBook.getIds().get(j);
			Integer number=itemsToBook.getNumbers().get(j);
			OrderItem item = orderItemDAO.getItem(id);
			item.setNumber(number);
			orderItemDAO.update(item);
		}
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

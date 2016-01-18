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
import org.springframework.web.context.WebApplicationContext;

import it.unical.pizzamanager.model.BookingUserDispay;
import it.unical.pizzamanager.model.OrderItemDisplay;
import it.unical.pizzamanager.persistence.dao.BookingDAO;
import it.unical.pizzamanager.persistence.dao.OrderItemDAO;
import it.unical.pizzamanager.persistence.dao.UserDAO;
import it.unical.pizzamanager.persistence.dto.BeverageOrderItem;
import it.unical.pizzamanager.persistence.dto.Booking;
import it.unical.pizzamanager.persistence.dto.BookingDelivery;
import it.unical.pizzamanager.persistence.dto.BookingPizzeriaTable;
import it.unical.pizzamanager.persistence.dto.BookingTakeAway;
import it.unical.pizzamanager.persistence.dto.OrderItem;
import it.unical.pizzamanager.persistence.dto.PizzaOrderItem;
import it.unical.pizzamanager.persistence.dto.RelationPizzaIngredient;
import it.unical.pizzamanager.persistence.dto.RelationPizzaOrderItemIngredient;
import it.unical.pizzamanager.persistence.dto.User;
import it.unical.pizzamanager.utils.SessionUtils;

@Controller
public class BookingListController {

	@Autowired
	WebApplicationContext context;

	@RequestMapping(value = "/orders", method = RequestMethod.GET)
	public String orders(Model model, HttpSession session) {
		if (!SessionUtils.isUser(session)) {
			return "index";
		}
		UserDAO userDAO = (UserDAO) context.getBean("userDAO");
		BookingDAO bookingDAO = (BookingDAO) context.getBean("bookingDAO");
		User user = userDAO.get(SessionUtils.getUserIdFromSessionOrNull(session));

		model.addAttribute("user", user);
		List<Booking> bookings = bookingDAO.getUserBookings(user);

		List<BookingUserDispay> bookingsList = createBookingList(bookings);
		System.out.println(bookings.size());
		model.addAttribute("bookings", bookingsList);
		return "orders";
	}
	
	@RequestMapping(value="/orders/removeItem")
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

	private List<BookingUserDispay> createBookingList(List<Booking> bookings) {
		List<BookingUserDispay> bookingList = new ArrayList<>();
		for (Booking booking : bookings) {
			BookingUserDispay userBooking = new BookingUserDispay();
			userBooking.setId(booking.getId());
			userBooking.setIdentifier("Booking"+booking.getId());
			userBooking.setActived(booking.getConfirmed());
			userBooking.setBill(booking.calculateBill());
			if (booking instanceof BookingDelivery)
				userBooking.setBookingType("Delivery");
			else if (booking instanceof BookingTakeAway)
				userBooking.setBookingType("Take Away");
			else if (booking instanceof BookingPizzeriaTable)
				userBooking.setBookingType("Table");
			userBooking.setDate(booking.getDate());
			userBooking.setPizzeria(booking.getPizzeria().getName());
			userBooking.setPreparationTime(booking.getPreparationTimeString(booking.evaluatePreparationTime()));
			userBooking.setItems(createItemList(booking.getOrderItems()));
			bookingList.add(userBooking);
		}
		return bookingList;
	}

	private List<OrderItemDisplay> createItemList(List<OrderItem> orderItems) {
		List<OrderItemDisplay> itemsToDisplay = new ArrayList<>();
		for (OrderItem item : orderItems) {
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
			itemsToDisplay.add(itemToDisplay);
		}

		return itemsToDisplay;
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

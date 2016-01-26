package it.unical.pizzamanager.utils;

import java.util.ArrayList;
import java.util.List;

import it.unical.pizzamanager.models.BookingUserDisplay;
import it.unical.pizzamanager.models.OrderItemDisplay;
import it.unical.pizzamanager.persistence.dao.BookingDAO;
import it.unical.pizzamanager.persistence.dao.PaymentDAO;
import it.unical.pizzamanager.persistence.entities.BeverageOrderItem;
import it.unical.pizzamanager.persistence.entities.Booking;
import it.unical.pizzamanager.persistence.entities.BookingDelivery;
import it.unical.pizzamanager.persistence.entities.BookingPizzeriaTable;
import it.unical.pizzamanager.persistence.entities.BookingTakeAway;
import it.unical.pizzamanager.persistence.entities.OrderItem;
import it.unical.pizzamanager.persistence.entities.Payment;
import it.unical.pizzamanager.persistence.entities.PizzaOrderItem;
import it.unical.pizzamanager.persistence.entities.RelationPizzaIngredient;
import it.unical.pizzamanager.persistence.entities.RelationPizzaOrderItemIngredient;

public class BookingUserDisplayUtils {

	public static void createPayment(Integer id, BookingDAO bookingDAO, PaymentDAO paymentDAO, String token) {

		Booking booking = bookingDAO.getBooking(id);
		Payment payment = booking.getPayment();
		if (payment == null) {
			payment = new Payment();
			paymentDAO.create(payment);
		}
		if (token == null)
			payment.setPaid(true);
		else {
			payment.setToken(token);
		}
		paymentDAO.update(payment);
		booking.setPayment(payment);
		bookingDAO.update(booking);
	}

	public static BookingUserDisplay createBookingUserDisplay(Booking booking, List<Booking> activeBooking) {
		BookingUserDisplay userBooking = new BookingUserDisplay();
		userBooking.setId(booking.getId());
		userBooking.setPizzeriaId(booking.getPizzeria().getId());
		userBooking.setIdentifier("Booking" + booking.getId());
		userBooking.setActived(booking.getConfirmed());
		userBooking.setBill(calculateBill(booking.getOrderItems()));
		if (booking.getPayment() != null)
			userBooking.setPayed(true);
		if (booking instanceof BookingDelivery)
			userBooking.setBookingType("Delivery");
		else if (booking instanceof BookingTakeAway)
			userBooking.setBookingType("Take Away");
		else if (booking instanceof BookingPizzeriaTable)
			userBooking.setBookingType("Table");
		userBooking.setDate(booking.getDate());
		userBooking.setPizzeria(booking.getPizzeria().getName());
		int prepTime = evaluatePreparationTime(booking.getOrderItems());
		userBooking.setPreparationTime(getPreparationTimeString(prepTime));
		// include my preparation time?
		userBooking.setCompletationTime(evalueteCompletationTime(activeBooking, booking.getId(), prepTime));
		userBooking.setItems(createItemList(booking.getOrderItems()));
		if (booking.getPayment() != null) {
			if (booking.getPayment().getPaid())
				userBooking.setPayed(true);
			else if (booking.getPayment().getToken() != null) {

				userBooking.setPayed(false);
				userBooking.setToken(booking.getPayment().getToken());
			} else {
				userBooking.setToken("null");
				userBooking.setPayed(false);
			}

		} else {
			userBooking.setToken("null");
			userBooking.setPayed(false);
		}

		return userBooking;
	}

	private static String evalueteCompletationTime(List<Booking> activeBooking, Integer myId, int prepTime) {
		Integer completationTime = 0;
		for (Booking b : activeBooking) {
			if (b.getId() == myId) {
				break;
			}
			completationTime += BookingUserDisplayUtils.evaluatePreparationTime(b.getOrderItems());
		}
		completationTime += prepTime;
		return BookingUserDisplayUtils.getPreparationTimeString(completationTime);
	}

	public static Integer evaluatePreparationTime(List<OrderItem> orderItems) {
		Integer preparationTime = 0;
		for (OrderItem orderItem : orderItems) {
			if (orderItem instanceof PizzaOrderItem)
				preparationTime += ((PizzaOrderItem) orderItem).getPizzeria_pizza().getPreparationTime()
						* orderItem.getNumber();
		}

		return preparationTime;
	}

	public static Double calculateBill(List<OrderItem> orderItems) {
		Double bill = 0.0;
		for (OrderItem orderItem : orderItems) {
			
			bill += orderItem.getCost();
		}
		return bill;
	}

	public static Double getCostPizzaPlusIngredients(PizzaOrderItem order) {
		Double cost = order.getCost();
		for (RelationPizzaOrderItemIngredient ingredient : order.getPizzaOrderIngredients()) {
			cost += ingredient.getCost();
		}
		return cost;
	}

	public static String getPreparationTimeString(Integer preparationTime) {
		Integer minutes = preparationTime / 60;
		Integer seconds = preparationTime % 60;

		String minutesString = Integer.toString(minutes);
		String secondsString = Integer.toString(seconds);

		if (minutes <= 9) {
			minutesString = "0" + minutesString;
		}

		if (seconds <= 9) {
			secondsString = "0" + secondsString;
		}

		return minutesString + ":" + secondsString;
	}

	private static List<OrderItemDisplay> createItemList(List<OrderItem> orderItems) {
		List<OrderItemDisplay> itemsToDisplay = new ArrayList<>();
		for (OrderItem item : orderItems) {
			OrderItemDisplay itemToDisplay = new OrderItemDisplay();
			itemToDisplay.setId(item.getId());
			if (item instanceof PizzaOrderItem) {
				itemToDisplay.setItemName(((PizzaOrderItem) item).pizzaName());
				itemToDisplay.setPizzeria(((PizzaOrderItem) item).pizzeriaName());
				itemToDisplay.setIngredients(stringfyIngredientsList(
						((PizzaOrderItem) item).getPizzeria_pizza().getPizza().getPizzaIngredients(),
						((PizzaOrderItem) item).getPizzaOrderIngredients()));
			} else if (item instanceof BeverageOrderItem) {
				itemToDisplay.setItemName(((BeverageOrderItem) item).beverageName());
				itemToDisplay.setPizzeria(((BeverageOrderItem) item).pizzeriaName());
			}
			itemToDisplay.setCost(item.getCost()/item.getNumber());
			itemToDisplay.setNumber(item.getNumber());
			itemToDisplay.setImageItem("not found");
			itemsToDisplay.add(itemToDisplay);
		}

		return itemsToDisplay;
	}

	private static String stringfyIngredientsList(List<RelationPizzaIngredient> pizzaIngredients,
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
		return ingredients;
	}

	public static BookingUserDisplay createSimpleBookingUserDisplay(Booking booking) {
		BookingUserDisplay userBooking = new BookingUserDisplay();
		userBooking.setId(booking.getId());
		userBooking.setBill(calculateBill(booking.getOrderItems()));
		if (booking instanceof BookingDelivery)
			userBooking.setBookingType("Delivery");
		else if (booking instanceof BookingTakeAway)
			userBooking.setBookingType("Take Away");
		else if (booking instanceof BookingPizzeriaTable)
			userBooking.setBookingType("Table");
		userBooking.setItems(createSimpleItemList(booking.getOrderItems()));
		return userBooking;
	}

	private static List<OrderItemDisplay> createSimpleItemList(List<OrderItem> orderItems) {
		List<OrderItemDisplay> itemsToDisplay = new ArrayList<>();
		for (OrderItem item : orderItems) {
			OrderItemDisplay itemToDisplay = new OrderItemDisplay();
			itemToDisplay.setId(item.getId());
			if (item instanceof PizzaOrderItem) {
				itemToDisplay.setItemName(((PizzaOrderItem) item).pizzaName());
			} else if (item instanceof BeverageOrderItem) {
				itemToDisplay.setItemName(((BeverageOrderItem) item).beverageName());
			}
			itemToDisplay.setCost(item.getCost()/item.getNumber());
			itemToDisplay.setNumber(item.getNumber());
			itemsToDisplay.add(itemToDisplay);
		}

		return itemsToDisplay;
	}

	public static void cancelPayment(Booking booking, PaymentDAO paymentDAO) {
		Payment payment = booking.getPayment();

		if (payment != null) {
			payment.setToken(null);
			paymentDAO.update(payment);
		}
	}
}

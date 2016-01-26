package it.unical.pizzamanager.populator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;

import it.unical.pizzamanager.persistence.dao.AddressDAO;
import it.unical.pizzamanager.persistence.dao.BookingDAO;
import it.unical.pizzamanager.persistence.dao.OrderItemDAO;
import it.unical.pizzamanager.persistence.dao.PizzeriaDAO;
import it.unical.pizzamanager.persistence.dao.UserDAO;
import it.unical.pizzamanager.persistence.entities.Address;
import it.unical.pizzamanager.persistence.entities.BeverageOrderItem;
import it.unical.pizzamanager.persistence.entities.Booking;
import it.unical.pizzamanager.persistence.entities.BookingDelivery;
import it.unical.pizzamanager.persistence.entities.BookingPizzeriaTable;
import it.unical.pizzamanager.persistence.entities.BookingTakeAway;
import it.unical.pizzamanager.persistence.entities.OrderItem;
import it.unical.pizzamanager.persistence.entities.PizzaOrderItem;
import it.unical.pizzamanager.persistence.entities.Pizzeria;
import it.unical.pizzamanager.persistence.entities.RelationBookingTablePizzeriaTable;
import it.unical.pizzamanager.persistence.entities.RelationPizzaOrderItemIngredient;
import it.unical.pizzamanager.persistence.entities.User;
import it.unical.pizzamanager.utils.BookingUtils;
import it.unical.pizzamanager.utils.OrderItemUtils;

public class BookingPopulator extends Populator {

	public BookingPopulator(ApplicationContext context) {
		super(context);
	}

	@Override
	protected void populate() {
		UserDAO userDAO = (UserDAO) context.getBean("userDAO");
		AddressDAO addressDAO = (AddressDAO) context.getBean("addressDAO");
		BookingDAO bookingDAO = (BookingDAO) context.getBean("bookingDAO");
		PizzeriaDAO pizzeriaDAO = (PizzeriaDAO) context.getBean("pizzeriaDAO");
		OrderItemDAO orderDAO = (OrderItemDAO) context.getBean("orderItemDAO");

		Pizzeria pizzeria=pizzeriaDAO.getAll().get(0);
		
		User user1 = userDAO.get(2);
		User user2 = userDAO.get(1);

		PizzaOrderItem pizzaOrder1 = new PizzaOrderItem();
		pizzaOrder1.setPizzeria_pizza(pizzeria.getPizzasPriceList().get(0));
		pizzaOrder1.setModified(false);
		pizzaOrder1.setNumber(3);
		//pizzaOrder1.setGlutenFree(PizzaOrderItem.NO);
		//pizzaOrder1.setSize(PizzaOrderItem.MEDIUM);
			pizzaOrder1=OrderItemUtils.setPizzaOrderCost(pizzaOrder1, pizzeria);
		orderDAO.create(pizzaOrder1);

		PizzaOrderItem pizzaOrder2 = new PizzaOrderItem();
		pizzaOrder2.setPizzeria_pizza(pizzeria.getPizzasPriceList().get(1));
		pizzaOrder2.setModified(true);
		pizzaOrder2.setNumber(2);
		//pizzaOrder2.setGlutenFree(PizzaOrderItem.NO);
		//pizzaOrder2.setSize(PizzaOrderItem.LARGE);
		orderDAO.create(pizzaOrder2);

		PizzaOrderItem pizzaOrder3 = new PizzaOrderItem();
		pizzaOrder3.setPizzeria_pizza(pizzeria.getPizzasPriceList().get(2));
		pizzaOrder3.setModified(false);
		pizzaOrder3.setNumber(3);
		//pizzaOrder3.setGlutenFree(PizzaOrderItem.NO);
		//pizzaOrder3.setSize(PizzaOrderItem.MEDIUM);
			pizzaOrder3=OrderItemUtils.setPizzaOrderCost(pizzaOrder3, pizzeria);
		orderDAO.create(pizzaOrder3);

		PizzaOrderItem pizzaOrder4 = new PizzaOrderItem();
		pizzaOrder4.setPizzeria_pizza(pizzeria.getPizzasPriceList().get(2));
		pizzaOrder4.setModified(true);
		pizzaOrder4.setNumber(2);
		//pizzaOrder4.setGlutenFree(PizzaOrderItem.YES);
		//pizzaOrder4.setSize(PizzaOrderItem.MEDIUM);
		orderDAO.create(pizzaOrder4);

		List<RelationPizzaOrderItemIngredient> ingredientsAdded = new ArrayList<>();
		ingredientsAdded.add(new RelationPizzaOrderItemIngredient(RelationPizzaOrderItemIngredient.ADDITION,
				pizzeria.getIngredientsPriceList().get(7).getIngredient(), pizzaOrder2));
		pizzaOrder2.setPizzaOrderIngredients(ingredientsAdded);
			pizzaOrder2=OrderItemUtils.setPizzaOrderCost(pizzaOrder2, pizzeria);
		orderDAO.update(pizzaOrder2);
		
		List<RelationPizzaOrderItemIngredient> ingredientsAdded1 = new ArrayList<>();
		ingredientsAdded1.add(new RelationPizzaOrderItemIngredient(RelationPizzaOrderItemIngredient.ADDITION,
				pizzeria.getIngredientsPriceList().get(4).getIngredient(), pizzaOrder4));
		ingredientsAdded1.add(new RelationPizzaOrderItemIngredient(RelationPizzaOrderItemIngredient.ADDITION,
				pizzeria.getIngredientsPriceList().get(2).getIngredient(), pizzaOrder4));
		pizzaOrder4.setPizzaOrderIngredients(ingredientsAdded1);
			pizzaOrder4=OrderItemUtils.setPizzaOrderCost(pizzaOrder4, pizzeria);
		orderDAO.update(pizzaOrder4);

		BeverageOrderItem beverageOrder1 = new BeverageOrderItem();
		beverageOrder1.setPizzeria_beverage(pizzeria.getBeveragesPriceList().get(0));
		beverageOrder1.setNumber(4);
			beverageOrder1=OrderItemUtils.setBeverageOrderCost(beverageOrder1, pizzeria);
		orderDAO.create(beverageOrder1);

		BeverageOrderItem beverageOrder2 = new BeverageOrderItem();
		beverageOrder2.setPizzeria_beverage(pizzeria.getBeveragesPriceList().get(1));
		beverageOrder2.setNumber(1);
			beverageOrder2=OrderItemUtils.setBeverageOrderCost(beverageOrder2, pizzeria);
		orderDAO.create(beverageOrder2);

		BeverageOrderItem beverageOrder3 = new BeverageOrderItem();
		beverageOrder3.setPizzeria_beverage(pizzeria.getBeveragesPriceList().get(2));
		beverageOrder3.setNumber(3);
			beverageOrder3=OrderItemUtils.setBeverageOrderCost(beverageOrder3, pizzeria);
		orderDAO.create(beverageOrder3);

		BeverageOrderItem beverageOrder4 = new BeverageOrderItem();
		beverageOrder4.setPizzeria_beverage(pizzeria.getBeveragesPriceList().get(3));
		beverageOrder4.setNumber(2);
			beverageOrder4=OrderItemUtils.setBeverageOrderCost(beverageOrder4, pizzeria);
		orderDAO.create(beverageOrder4);

		List<OrderItem> orderItemsTakeAway = new ArrayList<>();
		orderItemsTakeAway.add(pizzaOrder1);
		orderItemsTakeAway.add(pizzaOrder2);
		orderItemsTakeAway.add(beverageOrder1);
		orderItemsTakeAway.add(beverageOrder2);

		List<OrderItem> orderItemsDelivery = new ArrayList<>();
		orderItemsDelivery.add(pizzaOrder3);
		orderItemsDelivery.add(beverageOrder3);

		List<OrderItem> orderItemsTable = new ArrayList<>();
		orderItemsTable.add(pizzaOrder4);
		orderItemsTable.add(beverageOrder4);

		SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/M/yyyy hh:mm:ss");
		String dateTakeAway = "31/08/2015";
		String timeTakeAway = "31/08/2015 10:20:12";
		String dateDelivery = "13/10/2016";
		String timeDelivery = "13/10/2016 10:20:12";
		String dateTable = "23/01/2019";
		String timeTable = "31/08/2015 10:20:12";
		BookingTakeAway takeAway;
		BookingDelivery delivery;
		BookingPizzeriaTable tableBooking;

		try {
			Date date1TakeAway = sdf.parse(dateTakeAway);
			Date time1TakeAway = sdf1.parse(timeTakeAway);
			Date date1Delivery = sdf.parse(dateDelivery);
			Date time1Delivery = sdf1.parse(timeDelivery);
			Date date1Table = sdf.parse(dateTable);
			Date time1Table = sdf1.parse(timeTable);

			takeAway = new BookingTakeAway(date1TakeAway, time1TakeAway, false, Booking.PRIORITY_DEFAULT);
			takeAway.setPizzeria(pizzeria);
			takeAway.setOrderItems(orderItemsTakeAway);
			takeAway.setUser(user1);
				takeAway=(BookingTakeAway)BookingUtils.calculateBill(takeAway, pizzeria);
			bookingDAO.create(takeAway);

			delivery = new BookingDelivery(date1Delivery, time1Delivery, false, Booking.PRIORITY_DEFAULT, null);
			delivery.setPizzeria(pizzeria);
			delivery.setOrderItems(orderItemsDelivery);
			delivery.setBookerName("Tommaso Berardi");
			Address deliveryAddress = new Address();
			deliveryAddress.setCity("Cosenza");
			deliveryAddress.setStreet("via Popilia");
			deliveryAddress.setNumber(44);
			addressDAO.create(deliveryAddress);
			delivery.setDeliveryAddress(deliveryAddress);
				delivery=(BookingDelivery)BookingUtils.calculateBill(delivery, pizzeria);
			bookingDAO.create(delivery);

			tableBooking = new BookingPizzeriaTable(date1Table, time1Table, false, Booking.PRIORITY_DEFAULT, null);
			tableBooking.setPizzeria(pizzeria);
			tableBooking.setOrderItems(orderItemsTable);
			tableBooking.setUser(user2);
				tableBooking=(BookingPizzeriaTable)BookingUtils.calculateBill(tableBooking, pizzeria);
			bookingDAO.create(tableBooking);
			List<RelationBookingTablePizzeriaTable> tables = new ArrayList<>();
			tables.add(new RelationBookingTablePizzeriaTable(pizzeria.getTables().get(0),
					tableBooking));
			tableBooking.setTableBooking(tables);
			bookingDAO.update(tableBooking);

			pizzaOrder1.setBooking(takeAway);
			pizzaOrder2.setBooking(takeAway);
			pizzaOrder3.setBooking(delivery);
			pizzaOrder4.setBooking(tableBooking);
			beverageOrder1.setBooking(takeAway);
			beverageOrder2.setBooking(takeAway);
			beverageOrder3.setBooking(delivery);
			beverageOrder4.setBooking(tableBooking);
			orderDAO.update(pizzaOrder1);
			orderDAO.update(pizzaOrder2);
			orderDAO.update(pizzaOrder3);
			orderDAO.update(pizzaOrder4);
			orderDAO.update(beverageOrder1);
			orderDAO.update(beverageOrder2);
			orderDAO.update(beverageOrder3);
			orderDAO.update(beverageOrder4);

		} catch (ParseException e) {

			e.printStackTrace();
		}
	}

}

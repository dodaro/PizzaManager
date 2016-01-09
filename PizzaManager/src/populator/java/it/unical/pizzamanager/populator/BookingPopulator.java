package it.unical.pizzamanager.populator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;

import it.unical.pizzamanager.persistence.dao.BookingDAO;
import it.unical.pizzamanager.persistence.dao.OrderItemDAO;
import it.unical.pizzamanager.persistence.dao.PizzeriaDAO;
import it.unical.pizzamanager.persistence.dto.BookingTakeAway;
import it.unical.pizzamanager.persistence.dto.OrderItem;
import it.unical.pizzamanager.persistence.dto.PizzaOrderItem;
import it.unical.pizzamanager.persistence.dto.RelationPizzaOrderItemIngredient;

public class BookingPopulator extends Populator {

	public BookingPopulator(ApplicationContext context) {
		super(context);
	}

	@Override
	protected void populate() {
		BookingDAO bookingDAO = (BookingDAO) context.getBean("bookingDAO");
		PizzeriaDAO pizzeriaDAO = (PizzeriaDAO) context.getBean("pizzeriaDAO");
		OrderItemDAO order = (OrderItemDAO) context.getBean("orderItemDAO");

		PizzaOrderItem pizzaOrder1 = new PizzaOrderItem();
		// MANCA L'UTENTE O IL NOME DI CHI HA PRENOTATO
		pizzaOrder1.setPizzeria_pizza(pizzeriaDAO.getAll().get(0).getPizzasPriceList().get(0));
		pizzaOrder1.setModified(false);
		pizzaOrder1.setNumber(3);
		pizzaOrder1.setGlutenFree(PizzaOrderItem.NO);
		pizzaOrder1.setSize(PizzaOrderItem.SMALL);
		order.create(pizzaOrder1);

		PizzaOrderItem pizzaOrder2 = new PizzaOrderItem();
		pizzaOrder2.setPizzeria_pizza(pizzeriaDAO.getAll().get(0).getPizzasPriceList().get(1));
		pizzaOrder2.setModified(true);
		pizzaOrder2.setNumber(2);
		pizzaOrder2.setGlutenFree(PizzaOrderItem.YES);
		pizzaOrder2.setSize(PizzaOrderItem.LARGE);
		order.create(pizzaOrder2);

		List<RelationPizzaOrderItemIngredient> ingredientsAdded = new ArrayList<>();
		ingredientsAdded.add(new RelationPizzaOrderItemIngredient(
				RelationPizzaOrderItemIngredient.ADDITION,
				pizzeriaDAO.getAll().get(0).getIngredientsPriceList().get(7).getIngredient(),
				pizzaOrder2));
		pizzaOrder2.setPizzaOrderIngredients(ingredientsAdded);

		List<OrderItem> orderItems = new ArrayList<>();
		orderItems.add(pizzaOrder1);
		orderItems.add(pizzaOrder2);

		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
		String date = "31-08-2015";
		String time = "31-08-2015 10:20:12";
		BookingTakeAway b;
		try {
			Date date1 = sdf.parse(date);
			Date time1 = sdf.parse(time);
			System.out.println(date1);
			b = new BookingTakeAway(date1, time1, false, -1);
			b.setPizzeria(pizzeriaDAO.getAll().get(0));
			b.setOrderItems(orderItems);

			bookingDAO.create(b);
			pizzaOrder1.setBooking(b);
			pizzaOrder2.setBooking(b);
			order.update(pizzaOrder1);
			order.update(pizzaOrder2);

		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}

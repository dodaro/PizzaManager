package tests;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import persistence.Beverage;
import persistence.BeverageOrderItem;
import persistence.DatabaseHandler;
import persistence.Order;
import persistence.OrderItem;
import persistence.Pizza;
import persistence.PizzaOrderItem;
import persistence.Pizzeria;

public class Tests {

	@BeforeClass
	public static void init() {
		Order order = new Order("danilo");
		List<OrderItem> orderItems = new ArrayList<>();
		Pizzeria pizzeria1 = new Pizzeria("pizzeria1");

		for (int i = 0; i < 10; i++) {
			Pizza pizza = new Pizza("pizza" + i, i % 2 == 0 ? Pizza.SIZE_NORMAL : Pizza.SIZE_MAXI);
			Beverage beverage = new Beverage("beverage" + i, "type" + i);
			DatabaseHandler.create(pizza);
			DatabaseHandler.create(beverage);

			if (i == 5) {
				System.out.println("BLABLA");
				pizzeria1.addPizza(pizza, 3.33);
			}

			OrderItem orderItem;

			if (i % 2 == 0) {
				orderItem = new PizzaOrderItem(order, pizza, i % 2 == 0);
			} else {
				orderItem = new BeverageOrderItem(order, beverage,
						i % 2 == 0 ? BeverageOrderItem.TEMPERATURE_ROOM : BeverageOrderItem.TEMPERATURE_COLD);
			}

			orderItems.add(orderItem);
		}

		order.setOrderItems(orderItems);

		DatabaseHandler.create(order);
		DatabaseHandler.create(pizzeria1);
	}

	@Test
	public void dummyTest() {
		Pizzeria pizzeria = DatabaseHandler.getPizzeria(1);

		Pizza pizza = new Pizza("pizzaBlaBla", 0);
		DatabaseHandler.create(pizza);

		pizzeria.addPizza(pizza, 6.66);
		DatabaseHandler.update(pizzeria);
	}

	@Test
	public void dummyTest2() {
		Pizzeria pizzeria = DatabaseHandler.getPizzeria(1);
		System.out.println(pizzeria.getPizzasPrices().get(1).toString());
	}
}

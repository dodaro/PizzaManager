package it.unical.pizzamanager.populator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;

import it.unical.pizzamanager.persistence.dao.CartDAO;
import it.unical.pizzamanager.persistence.dao.OrderItemDAO;
import it.unical.pizzamanager.persistence.dao.PizzaDAO;
import it.unical.pizzamanager.persistence.dao.PizzeriaDAO;
import it.unical.pizzamanager.persistence.dao.RelationPizzeriaBeverageDAO;
import it.unical.pizzamanager.persistence.dao.UserDAO;
import it.unical.pizzamanager.persistence.dto.BeverageOrderItem;
import it.unical.pizzamanager.persistence.dto.Cart;
import it.unical.pizzamanager.persistence.dto.OrderItem;
import it.unical.pizzamanager.persistence.dto.Pizza;
import it.unical.pizzamanager.persistence.dto.PizzaOrderItem;
import it.unical.pizzamanager.persistence.dto.Pizzeria;
import it.unical.pizzamanager.persistence.dto.RelationPizzeriaBeverage;
import it.unical.pizzamanager.persistence.dto.RelationPizzeriaPizza;
import it.unical.pizzamanager.persistence.dto.User;
import it.unical.pizzamanager.persistence.dto.Pizza.PizzaSize;

public class CartPopulator extends Populator {

	public CartPopulator(ApplicationContext context) {
		super(context);

	}

	@Override
	protected void populate() {
		createCart();
	}

	private void createCart() {
		CartDAO cartDAO = (CartDAO) context.getBean("cartDAO");
		User user = addUser();
		Cart cart = new Cart();

		// cascade type save update insert orderitems in the list
		cartDAO.create(cart);
		ArrayList<OrderItem> orderItems = createOrderItems(cart);
		cart.setUser(user);
		cart.setOrderItems(orderItems);
		cartDAO.update(cart);

	}

	private ArrayList<OrderItem> createOrderItems(Cart cart) {
		PizzeriaDAO pizzeriaDAO = (PizzeriaDAO) context.getBean("pizzeriaDAO");
		List<Pizzeria> pizzerias = pizzeriaDAO.getAll();
		ArrayList<OrderItem> orderItems = new ArrayList<OrderItem>();
		// to change if init function will be removed

		createOrderItemList(orderItems, pizzerias, pizzeriaDAO, cart, 0);
		createOrderItemList(orderItems, pizzerias, pizzeriaDAO, cart, 1);

		return orderItems;
	}

	private void createOrderItemList(ArrayList<OrderItem> orderItems, List<Pizzeria> pizzerias, PizzeriaDAO pizzeriaDAO,
			Cart cart, int i) {
		OrderItemDAO orderItemDAO = (OrderItemDAO) context.getBean("orderItemDAO");
		List<RelationPizzeriaPizza> pizzeriaMenuPriceList = pizzerias.get(i).getPizzasPriceList();
		List<RelationPizzeriaBeverage> pizzeriaBeveragePriceList = pizzerias.get(i).getBeveragesPriceList();
		for (int j = 0; j < (i + 1); j++) {
			PizzaOrderItem pizzaItem = new PizzaOrderItem();
			pizzaItem.setPizzeria_pizza(pizzeriaMenuPriceList.get(j));
			pizzaItem.setCost(pizzeriaMenuPriceList.get(j).getPrice());
			pizzaItem.setModified(false);
			pizzaItem.setNumber(2);
			pizzaItem.setCart(cart);
			BeverageOrderItem beverageItem = new BeverageOrderItem();
			beverageItem.setPizzeria_beverage(pizzeriaBeveragePriceList.get(j));
			beverageItem.setCost(pizzeriaBeveragePriceList.get(j).getPrice());
			beverageItem.setNumber(2);
			beverageItem.setCart(cart);
			orderItemDAO.create(beverageItem);
			orderItemDAO.create(pizzaItem);
			orderItems.add(beverageItem);
			orderItems.add(pizzaItem);

		}
	}

	private User addUser() {
		UserDAO userDAO = (UserDAO) context.getBean("userDAO");
		User user = new User("mar@mail.com", "password33");
		user.setName("Marco");
		userDAO.create(user);

		return user;
	}

}

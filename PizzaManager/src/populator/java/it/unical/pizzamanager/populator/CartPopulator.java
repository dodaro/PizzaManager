package it.unical.pizzamanager.populator;

import java.util.List;

import org.springframework.context.ApplicationContext;

import it.unical.pizzamanager.persistence.dao.AddressDAO;
import it.unical.pizzamanager.persistence.dao.CartDAO;
import it.unical.pizzamanager.persistence.dao.OrderItemDAO;
import it.unical.pizzamanager.persistence.dao.PizzeriaDAO;
import it.unical.pizzamanager.persistence.dao.UserDAO;
import it.unical.pizzamanager.persistence.entities.Address;
import it.unical.pizzamanager.persistence.entities.BeverageOrderItem;
import it.unical.pizzamanager.persistence.entities.Cart;
import it.unical.pizzamanager.persistence.entities.PizzaOrderItem;
import it.unical.pizzamanager.persistence.entities.Pizzeria;
import it.unical.pizzamanager.persistence.entities.RelationPizzeriaBeverage;
import it.unical.pizzamanager.persistence.entities.RelationPizzeriaPizza;
import it.unical.pizzamanager.persistence.entities.User;
import it.unical.pizzamanager.utils.PasswordHashing;

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
		createOrderItems(cart);
		cart.setUser(user);
		cartDAO.update(cart);

	}

	private void createOrderItems(Cart cart) {
		PizzeriaDAO pizzeriaDAO = (PizzeriaDAO) context.getBean("pizzeriaDAO");
		List<Pizzeria> pizzerias = pizzeriaDAO.getAll();

		// to change if init function will be removed

		createOrderItemList(pizzerias, pizzeriaDAO, cart, 0);
		createOrderItemList(pizzerias, pizzeriaDAO, cart, 1);

	}

	private void createOrderItemList(List<Pizzeria> pizzerias, PizzeriaDAO pizzeriaDAO, Cart cart,
			int i) {
		OrderItemDAO orderItemDAO = (OrderItemDAO) context.getBean("orderItemDAO");
		List<RelationPizzeriaPizza> pizzeriaMenuPriceList = pizzerias.get(i).getPizzasPriceList();
		List<RelationPizzeriaBeverage> pizzeriaBeveragePriceList = pizzerias.get(i)
				.getBeveragesPriceList();
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

		}
	}

	private User addUser() {
		UserDAO userDAO = (UserDAO) context.getBean("userDAO");
		AddressDAO addressDAO = (AddressDAO) context.getBean("addressDAO");

		PasswordHashing hashing = (PasswordHashing) context.getBean("passwordHashing");
		String[] tokens = hashing.getHashAndSalt("password33").split(":");

		User user = new User("mar@mail.com", tokens[0], tokens[1]);
		user.setName("Marco");
		userDAO.create(user);
		Address address = new Address();
		address.setStreet("Via Giovanni Nova");
		address.setNumber(41);
		address.setCity("Cosenza");
		addressDAO.create(address);
		user.setAddress(address);
		userDAO.update(user);
		return user;
	}

}

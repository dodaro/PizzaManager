package it.unical.pizzamanager.populator;

import org.springframework.context.ApplicationContext;

import it.unical.pizzamanager.persistence.dao.CartDAO;
import it.unical.pizzamanager.persistence.dao.UserDAO;
import it.unical.pizzamanager.persistence.entities.Cart;
import it.unical.pizzamanager.persistence.entities.User;
import it.unical.pizzamanager.utils.PasswordHashing;

public class UserPopulator extends Populator {

	public UserPopulator(ApplicationContext context) {
		super(context);
	}

	@Override
	protected void populate() {
		createUsers();
	}

	private void createUsers() {
		UserDAO userDAO = (UserDAO) context.getBean("userDAO");
		CartDAO cartDAO = (CartDAO) context.getBean("cartDAO");
		PasswordHashing hashing = (PasswordHashing) context.getBean("passwordHashing");

		for (int i = 1; i <= 10; i++) {
			String[] tokens = hashing.getHashAndSalt("password" + i).split(":");
			User u = new User("mail" + i + "@mail.com", tokens[0], tokens[1]);
			u.setName("User" + i);
			u.setFirstName("FirstName" + i);
			u.setLastName("LastName" + i);
			userDAO.create(u);

			Cart cart = new Cart(u);
			cartDAO.create(cart);
		}
	}
}
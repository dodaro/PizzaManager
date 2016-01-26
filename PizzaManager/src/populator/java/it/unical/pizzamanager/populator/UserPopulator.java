package it.unical.pizzamanager.populator;

import org.springframework.context.ApplicationContext;

import it.unical.pizzamanager.persistence.dao.AddressDAO;
import it.unical.pizzamanager.persistence.dao.CartDAO;
import it.unical.pizzamanager.persistence.dao.UserDAO;
import it.unical.pizzamanager.persistence.entities.Address;
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
		AddressDAO addressDAO = (AddressDAO) context.getBean("addressDAO");

		PasswordHashing hashing = (PasswordHashing) context.getBean("passwordHashing");

		for (int i = 1; i <= 10; i++) {
			String[] tokens = hashing.getHashAndSalt("password" + i).split(":");
			User user = new User("mail" + i + "@mail.com", tokens[0], tokens[1]);

			Address address = new Address("Street" + i, i, "City" + i);
			addressDAO.create(address);

			user.setName("User" + i);
			user.setFirstName("FirstName" + i);
			user.setLastName("LastName" + i);
			user.setAddress(address);

			userDAO.create(user);

			Cart cart = new Cart(user);
			cartDAO.create(cart);
		}
	}
}
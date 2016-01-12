package it.unical.pizzamanager.populator;

import org.springframework.context.ApplicationContext;

import it.unical.pizzamanager.persistence.dao.UserDAO;
import it.unical.pizzamanager.persistence.dto.User;

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
		for (int i = 1; i <= 10; i++) {
			User u = new User("mail" + i + "@mail.com", "password" + i);
			u.setName("User" + i);
			userDAO.create(u);
		}
	}
}
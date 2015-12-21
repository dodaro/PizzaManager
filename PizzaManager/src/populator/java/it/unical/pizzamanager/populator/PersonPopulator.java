package it.unical.pizzamanager.populator;

import org.springframework.context.ApplicationContext;

import it.unical.pizzamanager.persistence.dao.CustomerDAO;
import it.unical.pizzamanager.persistence.dao.UserDAO;
import it.unical.pizzamanager.persistence.dto.Customer;
import it.unical.pizzamanager.persistence.dto.User;

public class PersonPopulator extends Populator {

	public PersonPopulator(ApplicationContext context) {
		super(context);
	}

	@Override
	protected void populate() {
		createUsers();
		createCustomers();
	}

	private void createUsers() {
		UserDAO userDAO = (UserDAO) context.getBean("userDAO");
		for (int i = 1; i <= 10; i++) {
			userDAO.create(new User("email" + i, "password" + i));
		}
	}

	private void createCustomers() {
		CustomerDAO customerDAO = (CustomerDAO) context.getBean("customerDAO");

		/*
		 * From 11 to 20, so that, for example, the Customer with id 13 will
		 * have "firstName13" as his firstName, since User and Customer share
		 * the same ids.
		 */
		for (int i = 11; i <= 20; i++) {
			customerDAO.create(new Customer("firstName" + i, "lastName" + i, "phoneNumber" + i));
		}
	}
}
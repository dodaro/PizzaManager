package it.unical.pizzamanager.populator;

import org.springframework.context.ApplicationContext;

import it.unical.pizzamanager.persistence.dao.PizzeriaDAO;
import it.unical.pizzamanager.persistence.dao.UserDAO;
import it.unical.pizzamanager.persistence.dto.Address;
import it.unical.pizzamanager.persistence.dto.Pizzeria;
import it.unical.pizzamanager.persistence.dto.User;

public class PizzeriaPopulator extends Populator {

	public PizzeriaPopulator(ApplicationContext context) {
		super(context);
	}

	@Override
	protected void populate() {
		PizzeriaDAO pizzeriaDAO = (PizzeriaDAO) context.getBean("pizzeriaDAO");
		UserDAO userDAO = (UserDAO) context.getBean("userDAO");

		for (int i = 1; i <= 5; i++) {
			User user = userDAO.get(i);
			Pizzeria pizzeria = new Pizzeria("pizzeria" + i, "email" + i, "description" + i,
					new Address("streetPizzeria" + i, i, "cityPizzeria" + i), "phoneNumber" + i,
					user);
			pizzeriaDAO.create(pizzeria);
		}
	}
}

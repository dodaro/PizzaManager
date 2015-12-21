package it.unical.pizzamanager.populator;

import org.springframework.context.ApplicationContext;

import it.unical.pizzamanager.persistence.dao.PizzeriaDAO;
import it.unical.pizzamanager.persistence.dao.UserDAO;
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
			Pizzeria pizzeria = new Pizzeria("email" + i, "password" + i, "name" + i,
					"phoneNumber" + i);
			pizzeriaDAO.create(pizzeria);
		}
	}
}

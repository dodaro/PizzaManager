package it.unical.pizzamanager.populator;

import org.springframework.context.ApplicationContext;

import it.unical.pizzamanager.persistence.dao.PizzeriaDAO;
import it.unical.pizzamanager.persistence.dto.Pizzeria;

public class PizzeriaPopulator extends Populator {

	public PizzeriaPopulator(ApplicationContext context) {
		super(context);
	}

	@Override
	protected void populate() {
		PizzeriaDAO pizzeriaDAO = (PizzeriaDAO) context.getBean("pizzeriaDAO");

		// From 11 to 15 to make it consistent with ids, since Users will take ids from 1 to 10.
		for (int i = 11; i <= 15; i++) {
			Pizzeria pizzeria = new Pizzeria("email" + i, "password" + i, "name" + i,
					"phoneNumber" + i);
			pizzeriaDAO.create(pizzeria);
		}
	}
}

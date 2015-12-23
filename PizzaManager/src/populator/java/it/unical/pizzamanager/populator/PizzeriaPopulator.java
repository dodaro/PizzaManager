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

		for (int i = 1; i <= 5; i++) {
			Pizzeria pizzeria = new Pizzeria("email" + i, "password" + i, "name" + i,
					"phoneNumber" + i);
			pizzeriaDAO.create(pizzeria);
		}
	}
}

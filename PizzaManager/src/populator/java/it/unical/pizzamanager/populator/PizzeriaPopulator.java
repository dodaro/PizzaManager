package it.unical.pizzamanager.populator;

import org.springframework.context.ApplicationContext;

import it.unical.pizzamanager.persistence.dao.PizzeriaDAO;
import it.unical.pizzamanager.persistence.dao.PizzeriaTableDAO;
import it.unical.pizzamanager.persistence.dto.Pizzeria;
import it.unical.pizzamanager.persistence.dto.PizzeriaTable;

public class PizzeriaPopulator extends Populator {

	public PizzeriaPopulator(ApplicationContext context) {
		super(context);
	}

	@Override
	protected void populate() {
		PizzeriaDAO pizzeriaDAO = (PizzeriaDAO) context.getBean("pizzeriaDAO");
		PizzeriaTableDAO pizzeriaTableDAO = (PizzeriaTableDAO) context.getBean("pizzeriaTableDAO");

		// From 11 to 15 to make it consistent with ids, since Users will take ids from 1 to 10.
		for (int i = 11; i <= 20; i++) {
			Pizzeria pizzeria = new Pizzeria("mail" + i + "@mail.com", "password" + i,
					"PizzeriaName" + i, "PizzeriaPhone" + i);
			pizzeriaDAO.create(pizzeria);

			// Create 5 tables for the pizzeria.
			for (int j = 1; j <= 5; j++) {
				PizzeriaTable table = new PizzeriaTable(j, j + 1, j + 3, pizzeria);
				pizzeriaTableDAO.create(table);
			}
		}
	}
}

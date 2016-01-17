package it.unical.pizzamanager.populator;

import org.springframework.context.ApplicationContext;

import it.unical.pizzamanager.persistence.dao.PizzeriaDAO;
import it.unical.pizzamanager.persistence.dao.PizzeriaTableDAO;
import it.unical.pizzamanager.persistence.dto.Location;
import it.unical.pizzamanager.persistence.dto.Pizzeria;
import it.unical.pizzamanager.persistence.dto.PizzeriaTable;

public class PizzeriaPopulator extends Populator {

	public PizzeriaPopulator(ApplicationContext context) {
		super(context);
	}

	@Override
	protected void populate() {

		createPizzeria("mail11@mail.com", "password11", "PizzeriaName11", "PizzeriaPhone11",
				new Location(39.349557, 16.240456999999992));
		createPizzeria("mail12@mail.com", "password12", "PizzeriaName12", "PizzeriaPhone12",
				new Location(39.346295, 16.242162000000008));
		createPizzeria("mail13@mail.com", "password13", "PizzeriaName13", "PizzeriaPhone13",
				new Location(39.3426187, 16.2438224));
		createPizzeria("mail14@mail.com", "password14", "PizzeriaName14", "PizzeriaPhone14",
				new Location(39.3515039, 16.222334300000057));
		createPizzeria("mail15@mail.com", "password15", "PizzeriaName15", "PizzeriaPhone15",
				new Location(39.3370249, 16.23834970000007));
		createPizzeria("mail16@mail.com", "password16", "PizzeriaName16", "PizzeriaPhone16",
				new Location(39.3007463, 16.255667700000004));
		createPizzeria("mail17@mail.com", "password17", "PizzeriaName17", "PizzeriaPhone17",
				new Location(39.293503, 16.256400600000006));
		createPizzeria("mail18@mail.com", "password18", "PizzeriaName18", "PizzeriaPhone18",
				new Location(39.3129443, 16.168734200000017));
		createPizzeria("mail19@mail.com", "password19", "PizzeriaName19", "PizzeriaPhone19",
				new Location(39.3401494, 16.1459873));
		createPizzeria("mail20@mail.com", "password20", "PizzeriaName20", "PizzeriaPhone20",
				new Location(39.39725589999999, 16.29328380000004));
	}

	private void createPizzeria(String mail, String password, String name, String phone,
			Location location) {
		PizzeriaDAO pizzeriaDAO = (PizzeriaDAO) context.getBean("pizzeriaDAO");
		PizzeriaTableDAO pizzeriaTableDAO = (PizzeriaTableDAO) context.getBean("pizzeriaTableDAO");

		Pizzeria pizzeria = new Pizzeria(mail, password, name, phone, location);
		pizzeriaDAO.create(pizzeria);

		// Create 5 tables for the pizzeria.
		for (int j = 1; j <= 5; j++) {
			PizzeriaTable table = new PizzeriaTable(j, j + 1, j + 3, pizzeria);
			pizzeriaTableDAO.create(table);
		}
	}
}

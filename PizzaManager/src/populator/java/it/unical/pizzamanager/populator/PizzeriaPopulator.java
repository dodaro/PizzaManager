package it.unical.pizzamanager.populator;

import org.springframework.context.ApplicationContext;

import it.unical.pizzamanager.persistence.dao.AddressDAO;
import it.unical.pizzamanager.persistence.dao.PizzeriaDAO;
import it.unical.pizzamanager.persistence.dao.PizzeriaTableDAO;
import it.unical.pizzamanager.persistence.entities.Address;
import it.unical.pizzamanager.persistence.entities.Location;
import it.unical.pizzamanager.persistence.entities.Pizzeria;
import it.unical.pizzamanager.persistence.entities.PizzeriaTable;
import it.unical.pizzamanager.utils.PasswordHashing;

public class PizzeriaPopulator extends Populator {

	public PizzeriaPopulator(ApplicationContext context) {
		super(context);
	}

	@Override
	protected void populate() {
		createPizzeria("mail11@mail.com", "password11", "PizzeriaName11", "PizzeriaPhone11",
				new Address("Street11", 11, "City11"), new Location(39.349557, 16.240456999999992));
		createPizzeria("mail12@mail.com", "password12", "PizzeriaName12", "PizzeriaPhone12",
				new Address("Street12", 12, "City12"), new Location(39.346295, 16.242162000000008));
		createPizzeria("mail13@mail.com", "password13", "PizzeriaName13", "PizzeriaPhone13",
				new Address("Street13", 13, "City13"), new Location(39.3426187, 16.2438224));
		createPizzeria("mail14@mail.com", "password14", "PizzeriaName14", "PizzeriaPhone14",
				new Address("Street14", 14, "City14"),
				new Location(39.3515039, 16.222334300000057));
		createPizzeria("mail15@mail.com", "password15", "PizzeriaName15", "PizzeriaPhone15",
				new Address("Street15", 15, "City15"), new Location(39.3370249, 16.23834970000007));
		createPizzeria("mail16@mail.com", "password16", "PizzeriaName16", "PizzeriaPhone16",
				new Address("Street16", 16, "City16"),
				new Location(39.3007463, 16.255667700000004));
		createPizzeria("mail17@mail.com", "password17", "PizzeriaName17", "PizzeriaPhone17",
				new Address("Street17", 17, "City17"), new Location(39.293503, 16.256400600000006));
		createPizzeria("mail18@mail.com", "password18", "PizzeriaName18", "PizzeriaPhone18",
				new Address("Street18", 18, "City18"),
				new Location(39.3129443, 16.168734200000017));
		createPizzeria("mail19@mail.com", "password19", "PizzeriaName19", "PizzeriaPhone19",
				new Address("Street19", 19, "City19"), new Location(39.3401494, 16.1459873));
		createPizzeria("mail20@mail.com", "password20", "PizzeriaName20", "PizzeriaPhone20",
				new Address("Street20", 20, "City20"),
				new Location(39.39725589999999, 16.29328380000004));
	}

	private void createPizzeria(String mail, String password, String name, String phone,
			Address address, Location location) {
		PizzeriaDAO pizzeriaDAO = (PizzeriaDAO) context.getBean("pizzeriaDAO");
		PizzeriaTableDAO pizzeriaTableDAO = (PizzeriaTableDAO) context.getBean("pizzeriaTableDAO");

		AddressDAO addressDAO = (AddressDAO) context.getBean("addressDAO");
		addressDAO.create(address);

		PasswordHashing hashing = (PasswordHashing) context.getBean("passwordHashing");

		String[] tokens = hashing.getHashAndSalt(password).split(":");
		Pizzeria pizzeria = new Pizzeria(mail, tokens[0], tokens[1], name, phone, address,
				location);
		pizzeriaDAO.create(pizzeria);

		// Create 5 tables for the pizzeria.
		for (int j = 1; j <= 5; j++) {
			PizzeriaTable table = new PizzeriaTable(j, j + 1, j + 3, pizzeria);
			pizzeriaTableDAO.create(table);
		}
	}
}

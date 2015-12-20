package it.unical.pizzamanager.tests;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;

import it.unical.pizzamanager.persistence.dao.CustomerDAO;
import it.unical.pizzamanager.persistence.dao.IngredientDAO;
import it.unical.pizzamanager.persistence.dao.PizzaDAO;
import it.unical.pizzamanager.persistence.dao.UserDAO;
import it.unical.pizzamanager.persistence.dto.Customer;
import it.unical.pizzamanager.persistence.dto.Ingredient;
import it.unical.pizzamanager.persistence.dto.Ingredient.IngredientType;
import it.unical.pizzamanager.persistence.dto.Pizza;
import it.unical.pizzamanager.persistence.dto.Pizza.PizzaSize;
import it.unical.pizzamanager.persistence.dto.RelationPizzaIngredient;
import it.unical.pizzamanager.persistence.dto.User;

public class TestUtils {

	public static void populateDatabase(ApplicationContext context) {
		UserDAO userDAO = (UserDAO) context.getBean("userDAO");
		if (userDAO.getAll().isEmpty()) {
			createUsers(userDAO);
		}

		CustomerDAO customerDAO = (CustomerDAO) context.getBean("customerDAO");
		if (customerDAO.getAll().isEmpty()) {
			createCustomers(customerDAO);
		}

		IngredientDAO ingredientDAO = (IngredientDAO) context.getBean("ingredientDAO");
		if (ingredientDAO.getAll().isEmpty()) {
			createIngredients(ingredientDAO);
		}

		PizzaDAO pizzaDAO = (PizzaDAO) context.getBean("pizzaDAO");
		if (pizzaDAO.getAll().isEmpty()) {
			createPizzas(pizzaDAO, ingredientDAO);
		}
	}

	private static void createUsers(UserDAO userDAO) {
		for (int i = 1; i <= 10; i++) {
			userDAO.create(new User("email" + i, "password" + i));
		}
	}

	private static void createCustomers(CustomerDAO customerDAO) {
		/*
		 * Da 11 a 20 così, ad esempio, il Customer con id 13 avrà firstName
		 * "firstName13", poiché gli id sono condivisi tra User e Customer.
		 */
		for (int i = 11; i <= 20; i++) {
			customerDAO.create(new Customer("firstName" + i, "lastName" + i, "phoneNumber" + i));
		}
	}

	private static void createIngredients(IngredientDAO ingredientDAO) {
		Ingredient mozzarella = new Ingredient("Mozzarella", IngredientType.CHEESE);
		Ingredient tomato = new Ingredient("Tomato", IngredientType.VEGETABLE);
		Ingredient ham = new Ingredient("Cooked Ham", IngredientType.MEAT);
		Ingredient sausage = new Ingredient("Sausage", IngredientType.MEAT);

		ingredientDAO.create(mozzarella);
		ingredientDAO.create(tomato);
		ingredientDAO.create(ham);
		ingredientDAO.create(sausage);
	}

	private static void createPizzas(PizzaDAO pizzaDAO, IngredientDAO ingredientDAO) {
		Pizza margherita = new Pizza("Margherita", 4, false, "MargheritaDescription",
				PizzaSize.NORMAL, false);
		Pizza cardinale = new Pizza("Cardinale", 5, false, "CardinaleDescription", PizzaSize.MAXI,
				false);
		Pizza calabrese = new Pizza("Calabrese", 5, false, "CalabreseDescription", PizzaSize.MAXI,
				false);

		Ingredient mozzarella = ingredientDAO.get("Mozzarella");
		Ingredient tomato = ingredientDAO.get("Tomato");
		Ingredient cookedHam = ingredientDAO.get("Cooked Ham");
		Ingredient sausage = ingredientDAO.get("Sausage");

		List<RelationPizzaIngredient> margheritaIngredients = new ArrayList<>();
		margheritaIngredients.add(new RelationPizzaIngredient(margherita, tomato));
		margheritaIngredients.add(new RelationPizzaIngredient(margherita, mozzarella));

		List<RelationPizzaIngredient> cardinaleIngredients = new ArrayList<>();
		cardinaleIngredients.add(new RelationPizzaIngredient(cardinale, tomato));
		cardinaleIngredients.add(new RelationPizzaIngredient(cardinale, mozzarella));
		cardinaleIngredients.add(new RelationPizzaIngredient(cardinale, cookedHam));

		List<RelationPizzaIngredient> calabreseIngredients = new ArrayList<>();
		calabreseIngredients.add(new RelationPizzaIngredient(calabrese, tomato));
		calabreseIngredients.add(new RelationPizzaIngredient(calabrese, mozzarella));
		calabreseIngredients.add(new RelationPizzaIngredient(calabrese, sausage));

		/*
		 * Bisognerebbe prima salvare nel database gli oggetti
		 * RelationPizzaIngredient (quindi avere una DAO apposita), e solo in
		 * seguito chiamare setPizzaIngredient. Tramite l'annotazione @Cascade
		 * per le relazioni evitiamo il problema e scriviamo meno codice.
		 * 
		 * L'uso si potrebbe estendere anche alle entità non-relazioni.
		 */

		margherita.setPizzaIngredient(margheritaIngredients);
		cardinale.setPizzaIngredient(cardinaleIngredients);
		calabrese.setPizzaIngredient(calabreseIngredients);

		pizzaDAO.create(cardinale);
		pizzaDAO.create(margherita);
		pizzaDAO.create(calabrese);
	}
}

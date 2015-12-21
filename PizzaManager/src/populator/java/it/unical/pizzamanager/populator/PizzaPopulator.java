package it.unical.pizzamanager.populator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;

import it.unical.pizzamanager.persistence.dao.IngredientDAO;
import it.unical.pizzamanager.persistence.dao.PizzaDAO;
import it.unical.pizzamanager.persistence.dto.Ingredient;
import it.unical.pizzamanager.persistence.dto.Pizza;
import it.unical.pizzamanager.persistence.dto.RelationPizzaIngredient;

public class PizzaPopulator extends Populator {

	public PizzaPopulator(ApplicationContext context) {
		super(context);
	}

	@Override
	protected void populate() {
		IngredientDAO ingredientDAO = (IngredientDAO) context.getBean("ingredientDAO");
		PizzaDAO pizzaDAO = (PizzaDAO) context.getBean("pizzaDAO");

		Pizza margherita = new Pizza("Margherita", "MargheritaDescription", false);
		Pizza cardinale = new Pizza("Cardinale", "CardinaleDescription", false);
		Pizza calabrese = new Pizza("Calabrese", "CalabreseDescription", false);

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

		margherita.setPizzaIngredients(margheritaIngredients);
		cardinale.setPizzaIngredients(cardinaleIngredients);
		calabrese.setPizzaIngredients(calabreseIngredients);

		pizzaDAO.create(cardinale);
		pizzaDAO.create(margherita);
		pizzaDAO.create(calabrese);
	}
}

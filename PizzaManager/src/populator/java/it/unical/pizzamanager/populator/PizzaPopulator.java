package it.unical.pizzamanager.populator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;

import it.unical.pizzamanager.persistence.dao.IngredientDAO;
import it.unical.pizzamanager.persistence.dao.PizzaDAO;
import it.unical.pizzamanager.persistence.dto.Ingredient;
import it.unical.pizzamanager.persistence.dto.Pizza;
import it.unical.pizzamanager.persistence.dto.Pizza.PizzaSize;
import it.unical.pizzamanager.persistence.dto.RelationPizzaIngredient;

public class PizzaPopulator extends Populator {

	public PizzaPopulator(ApplicationContext context) {
		super(context);
	}

	@Override
	protected void populate() {
		IngredientDAO ingredientDAO = (IngredientDAO) context.getBean("ingredientDAO");
		PizzaDAO pizzaDAO = (PizzaDAO) context.getBean("pizzaDAO");

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

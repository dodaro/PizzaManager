package it.unical.pizzamanager.populator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;

import it.unical.pizzamanager.persistence.dao.IngredientDAO;
import it.unical.pizzamanager.persistence.dao.PizzaDAO;
import it.unical.pizzamanager.persistence.dao.PizzeriaDAO;
import it.unical.pizzamanager.persistence.dto.Ingredient;
import it.unical.pizzamanager.persistence.dto.Pizza;
import it.unical.pizzamanager.persistence.dto.Pizza.PizzaSize;
import it.unical.pizzamanager.persistence.dto.Pizzeria;
import it.unical.pizzamanager.persistence.dto.RelationPizzaIngredient;
import it.unical.pizzamanager.persistence.dto.RelationPizzeriaPizza;

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
		Pizza capricciosa = new Pizza("Capricciosa", "CapricciosaDescription", false);
		Pizza funghi = new Pizza("Funghi", "FunghiDescription", false);
		Pizza diavola = new Pizza("Diavola", "DiavolaDescription", false);
		Pizza ortolana = new Pizza("Ortolana", "DiavolaDescription", false);

		Ingredient mozzarella = ingredientDAO.get("Mozzarella");
		Ingredient tomato = ingredientDAO.get("Tomato");
		Ingredient cookedHam = ingredientDAO.get("Cooked Ham");
		Ingredient sausage = ingredientDAO.get("Sausage");
		Ingredient zucchini = ingredientDAO.get("Zucchini");
		Ingredient eggplant = ingredientDAO.get("Eggplant");
		Ingredient olives = ingredientDAO.get("Olives");
		Ingredient mushrooms = ingredientDAO.get("Mushrooms");
		Ingredient artichokes = ingredientDAO.get("Artichokes");
		Ingredient pepper = ingredientDAO.get("Pepper");

		List<RelationPizzaIngredient> margheritaIngredients = new ArrayList<RelationPizzaIngredient>();
		margheritaIngredients.add(new RelationPizzaIngredient(margherita, tomato));
		margheritaIngredients.add(new RelationPizzaIngredient(margherita, mozzarella));

		List<RelationPizzaIngredient> cardinaleIngredients = new ArrayList<RelationPizzaIngredient>();
		cardinaleIngredients.add(new RelationPizzaIngredient(cardinale, tomato));
		cardinaleIngredients.add(new RelationPizzaIngredient(cardinale, mozzarella));
		cardinaleIngredients.add(new RelationPizzaIngredient(cardinale, cookedHam));

		List<RelationPizzaIngredient> calabreseIngredients = new ArrayList<RelationPizzaIngredient>();
		calabreseIngredients.add(new RelationPizzaIngredient(calabrese, tomato));
		calabreseIngredients.add(new RelationPizzaIngredient(calabrese, mozzarella));
		calabreseIngredients.add(new RelationPizzaIngredient(calabrese, sausage));

		List<RelationPizzaIngredient> capricciosaIngredients = new ArrayList<RelationPizzaIngredient>();
		capricciosaIngredients.add(new RelationPizzaIngredient(capricciosa, tomato));
		capricciosaIngredients.add(new RelationPizzaIngredient(capricciosa, mozzarella));
		capricciosaIngredients.add(new RelationPizzaIngredient(capricciosa, cookedHam));
		capricciosaIngredients.add(new RelationPizzaIngredient(capricciosa, artichokes));
		capricciosaIngredients.add(new RelationPizzaIngredient(capricciosa, mushrooms));
		capricciosaIngredients.add(new RelationPizzaIngredient(capricciosa, olives));

		List<RelationPizzaIngredient> funghiIngredients = new ArrayList<RelationPizzaIngredient>();
		funghiIngredients.add(new RelationPizzaIngredient(funghi, tomato));
		funghiIngredients.add(new RelationPizzaIngredient(funghi, mozzarella));
		funghiIngredients.add(new RelationPizzaIngredient(funghi, mushrooms));

		List<RelationPizzaIngredient> diavolaIngredients = new ArrayList<RelationPizzaIngredient>();
		diavolaIngredients.add(new RelationPizzaIngredient(diavola, tomato));
		diavolaIngredients.add(new RelationPizzaIngredient(diavola, mozzarella));
		diavolaIngredients.add(new RelationPizzaIngredient(diavola, sausage));
		diavolaIngredients.add(new RelationPizzaIngredient(diavola, pepper));

		List<RelationPizzaIngredient> ortolanaIngredients = new ArrayList<RelationPizzaIngredient>();
		ortolanaIngredients.add(new RelationPizzaIngredient(ortolana, tomato));
		ortolanaIngredients.add(new RelationPizzaIngredient(ortolana, pepper));
		ortolanaIngredients.add(new RelationPizzaIngredient(ortolana, eggplant));
		ortolanaIngredients.add(new RelationPizzaIngredient(ortolana, zucchini));

		margherita.setPizzaIngredients(margheritaIngredients);
		cardinale.setPizzaIngredients(cardinaleIngredients);
		calabrese.setPizzaIngredients(calabreseIngredients);
		capricciosa.setPizzaIngredients(capricciosaIngredients);
		funghi.setPizzaIngredients(funghiIngredients);
		diavola.setPizzaIngredients(diavolaIngredients);
		ortolana.setPizzaIngredients(ortolanaIngredients);

		pizzaDAO.create(cardinale);
		pizzaDAO.create(margherita);
		pizzaDAO.create(calabrese);
		pizzaDAO.create(capricciosa);
		pizzaDAO.create(funghi);
		pizzaDAO.create(diavola);
		pizzaDAO.create(ortolana);

		// Add pizzas to pizzerias.

		PizzeriaDAO pizzeriaDAO = (PizzeriaDAO) context.getBean("pizzeriaDAO");
		List<Pizzeria> pizzerias = pizzeriaDAO.getAll();

		// Pizzeria 0
		Pizzeria pizzeria0 = pizzerias.get(0);
		List<RelationPizzeriaPizza> priceLists0 = new ArrayList<RelationPizzeriaPizza>();
		priceLists0.add(new RelationPizzeriaPizza(pizzeria0, margherita, 4.00, PizzaSize.NORMAL,
				3.0, false));
		priceLists0.add(
				new RelationPizzeriaPizza(pizzeria0, cardinale, 6.00, PizzaSize.MAXI, 4.0, false));
		priceLists0.add(new RelationPizzeriaPizza(pizzeria0, calabrese, 5.50, PizzaSize.NORMAL, 4.0,
				false));

		// Pizzeria 1
		Pizzeria pizzeria1 = pizzerias.get(1);
		List<RelationPizzeriaPizza> priceLists1 = new ArrayList<RelationPizzeriaPizza>();
		priceLists1.add(
				new RelationPizzeriaPizza(pizzeria1, margherita, 4.50, PizzaSize.MAXI, 4.0, true));
		priceLists1.add(
				new RelationPizzeriaPizza(pizzeria1, ortolana, 6.00, PizzaSize.MAXI, 5.0, false));
		priceLists1.add(new RelationPizzeriaPizza(pizzeria1, capricciosa, 5.50, PizzaSize.NORMAL,
				6.0, false));
		priceLists1.add(
				new RelationPizzeriaPizza(pizzeria1, funghi, 6.00, PizzaSize.MAXI, 6.0, false));

		// Pizzeria 2
		Pizzeria pizzeria2 = pizzerias.get(2);
		List<RelationPizzeriaPizza> priceLists2 = new ArrayList<RelationPizzeriaPizza>();
		priceLists2.add(
				new RelationPizzeriaPizza(pizzeria2, diavola, 4.50, PizzaSize.MAXI, 5.0, true));
		priceLists2.add(
				new RelationPizzeriaPizza(pizzeria2, funghi, 5.00, PizzaSize.NORMAL, 6.0, true));
		priceLists2.add(new RelationPizzeriaPizza(pizzeria2, capricciosa, 6.00, PizzaSize.NORMAL,
				4.5, false));
		priceLists2.add(
				new RelationPizzeriaPizza(pizzeria2, calabrese, 7.00, PizzaSize.MAXI, 6.0, true));

		// Pizzeria 3
		Pizzeria pizzeria3 = pizzerias.get(3);
		List<RelationPizzeriaPizza> priceLists3 = new ArrayList<RelationPizzeriaPizza>();
		priceLists3.add(new RelationPizzeriaPizza(pizzeria3, margherita, 3.00, PizzaSize.NORMAL,
				4.0, false));
		priceLists3.add(new RelationPizzeriaPizza(pizzeria3, cardinale, 4.00, PizzaSize.NORMAL, 5.5,
				false));
		priceLists3.add(
				new RelationPizzeriaPizza(pizzeria3, ortolana, 5.00, PizzaSize.MAXI, 6.0, false));

		// Pizzeria4
		Pizzeria pizzeria4 = pizzerias.get(4);
		List<RelationPizzeriaPizza> priceLists4 = new ArrayList<RelationPizzeriaPizza>();
		priceLists4.add(new RelationPizzeriaPizza(pizzeria4, margherita, 3.50, PizzaSize.NORMAL,
				3.0, true));
		priceLists4.add(
				new RelationPizzeriaPizza(pizzeria4, cardinale, 4.50, PizzaSize.MAXI, 4.0, false));
		priceLists4.add(
				new RelationPizzeriaPizza(pizzeria4, diavola, 5.0, PizzaSize.NORMAL, 5.0, false));
		priceLists4
				.add(new RelationPizzeriaPizza(pizzeria4, funghi, 7.0, PizzaSize.MAXI, 3.5, false));
		priceLists4.add(
				new RelationPizzeriaPizza(pizzeria4, calabrese, 6.0, PizzaSize.NORMAL, 4.5, false));

		pizzeria0.setPizzasPriceList(priceLists0);
		pizzeria1.setPizzasPriceList(priceLists1);
		pizzeria2.setPizzasPriceList(priceLists2);
		pizzeria3.setPizzasPriceList(priceLists3);
		pizzeria4.setPizzasPriceList(priceLists4);

		pizzeriaDAO.update(pizzeria0);
		pizzeriaDAO.update(pizzeria1);
		pizzeriaDAO.update(pizzeria2);
		pizzeriaDAO.update(pizzeria3);
		pizzeriaDAO.update(pizzeria4);
	}
}

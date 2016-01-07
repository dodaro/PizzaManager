package it.unical.pizzamanager.populator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;

import it.unical.pizzamanager.persistence.dao.IngredientDAO;
import it.unical.pizzamanager.persistence.dao.PizzeriaDAO;
import it.unical.pizzamanager.persistence.dto.Ingredient;
import it.unical.pizzamanager.persistence.dto.Ingredient.IngredientType;
import it.unical.pizzamanager.persistence.dto.Pizzeria;
import it.unical.pizzamanager.persistence.dto.RelationPizzeriaIngredient;

public class IngredientPopulator extends Populator {

	public IngredientPopulator(ApplicationContext context) {
		super(context);
	}

	@Override
	protected void populate() {
		IngredientDAO ingredientDAO = (IngredientDAO) context.getBean("ingredientDAO");

		Ingredient mozzarella = new Ingredient("Mozzarella", IngredientType.CHEESE);
		Ingredient tomato = new Ingredient("Tomato", IngredientType.VEGETABLE);
		Ingredient cookedHam = new Ingredient("Cooked Ham", IngredientType.MEAT);
		Ingredient sausage = new Ingredient("Sausage", IngredientType.MEAT);
		Ingredient zucchini = new Ingredient("Zucchini", IngredientType.VEGETABLE);
		Ingredient eggplant = new Ingredient("Eggplant", IngredientType.VEGETABLE);
		Ingredient olives = new Ingredient("Olives", IngredientType.VEGETABLE);
		Ingredient mushrooms = new Ingredient("Mushrooms", IngredientType.VEGETABLE);
		Ingredient artichokes = new Ingredient("Artichokes", IngredientType.VEGETABLE);
		Ingredient pepper = new Ingredient("Pepper", IngredientType.VEGETABLE);

		ingredientDAO.create(mozzarella);
		ingredientDAO.create(tomato);
		ingredientDAO.create(cookedHam);
		ingredientDAO.create(sausage);
		ingredientDAO.create(zucchini);
		ingredientDAO.create(eggplant);
		ingredientDAO.create(olives);
		ingredientDAO.create(mushrooms);
		ingredientDAO.create(artichokes);
		ingredientDAO.create(pepper);

		PizzeriaDAO pizzeriaDAO = (PizzeriaDAO) context.getBean("pizzeriaDAO");
		List<Pizzeria> pizzerias = pizzeriaDAO.getAll();

		// Pizzeria 0
		Pizzeria pizzeria0 = pizzerias.get(0);
		List<RelationPizzeriaIngredient> priceList0 = new ArrayList<>();
		priceList0.add(new RelationPizzeriaIngredient(pizzeria0, mozzarella, 2.5));
		priceList0.add(new RelationPizzeriaIngredient(pizzeria0, tomato, 1.5));
		priceList0.add(new RelationPizzeriaIngredient(pizzeria0, cookedHam, 0.5));
		priceList0.add(new RelationPizzeriaIngredient(pizzeria0, sausage, 1.0));
		priceList0.add(new RelationPizzeriaIngredient(pizzeria0, zucchini, 2.0));
		priceList0.add(new RelationPizzeriaIngredient(pizzeria0, eggplant, 0.4));
		priceList0.add(new RelationPizzeriaIngredient(pizzeria0, olives, 4.0));
		priceList0.add(new RelationPizzeriaIngredient(pizzeria0, mushrooms, 2.0));
		priceList0.add(new RelationPizzeriaIngredient(pizzeria0, artichokes, 3.0));
		priceList0.add(new RelationPizzeriaIngredient(pizzeria0, pepper, 0.4));

		pizzeria0.setIngredientsPriceList(priceList0);
		// pizzeria1.setIngredientsPriceList(priceList1);
		// pizzeria2.setIngredientsPriceList(priceList2);
		// pizzeria3.setIngredientsPriceList(priceList3);
		// pizzeria4.setIngredientsPriceList(priceList4);

		pizzeriaDAO.update(pizzeria0);
		// pizzeriaDAO.update(pizzeria1);
		// pizzeriaDAO.update(pizzeria2);
		// pizzeriaDAO.update(pizzeria3);
		// pizzeriaDAO.update(pizzeria4);

	}
}
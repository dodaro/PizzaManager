package it.unical.pizzamanager.populator;

import org.springframework.context.ApplicationContext;

import it.unical.pizzamanager.persistence.dao.IngredientDAO;
import it.unical.pizzamanager.persistence.dto.Ingredient;
import it.unical.pizzamanager.persistence.dto.Ingredient.IngredientType;

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

	}
}
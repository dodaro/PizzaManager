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
		Ingredient ham = new Ingredient("Cooked Ham", IngredientType.MEAT);
		Ingredient sausage = new Ingredient("Sausage", IngredientType.MEAT);

		ingredientDAO.create(mozzarella);
		ingredientDAO.create(tomato);
		ingredientDAO.create(ham);
		ingredientDAO.create(sausage);
	}
}

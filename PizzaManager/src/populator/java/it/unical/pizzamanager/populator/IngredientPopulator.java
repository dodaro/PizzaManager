package it.unical.pizzamanager.populator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;

import it.unical.pizzamanager.persistence.dao.IngredientDAO;
import it.unical.pizzamanager.persistence.dao.PizzeriaDAO;
import it.unical.pizzamanager.persistence.entities.Ingredient;
import it.unical.pizzamanager.persistence.entities.Ingredient.IngredientType;
import it.unical.pizzamanager.persistence.entities.Pizzeria;
import it.unical.pizzamanager.persistence.entities.RelationPizzeriaIngredient;

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

		for (Pizzeria pizzeria : pizzerias) {
			List<RelationPizzeriaIngredient> priceList = new ArrayList<>();
			priceList.add(new RelationPizzeriaIngredient(pizzeria, mozzarella, 2.5));
			priceList.add(new RelationPizzeriaIngredient(pizzeria, tomato, 1.5));
			priceList.add(new RelationPizzeriaIngredient(pizzeria, cookedHam, 0.5));
			priceList.add(new RelationPizzeriaIngredient(pizzeria, sausage, 1.0));
			priceList.add(new RelationPizzeriaIngredient(pizzeria, zucchini, 2.0));
			priceList.add(new RelationPizzeriaIngredient(pizzeria, eggplant, 0.4));
			priceList.add(new RelationPizzeriaIngredient(pizzeria, olives, 4.0));
			priceList.add(new RelationPizzeriaIngredient(pizzeria, mushrooms, 2.0));
			priceList.add(new RelationPizzeriaIngredient(pizzeria, artichokes, 3.0));
			priceList.add(new RelationPizzeriaIngredient(pizzeria, pepper, 0.4));
			
			pizzeria.setIngredientsPriceList(priceList);
			pizzeriaDAO.update(pizzeria);
		}
	}
}
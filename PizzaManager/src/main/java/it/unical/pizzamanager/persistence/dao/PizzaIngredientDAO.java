package it.unical.pizzamanager.persistence.dao;

import java.util.List;

import it.unical.pizzamanager.persistence.dto.PizzaIngredient;

public interface PizzaIngredientDAO {

	public void create(PizzaIngredient pizzaIngredient);

	public void delete(PizzaIngredient pizzaIngredient);

	public void update(PizzaIngredient pizzaIngredient);

	public List<PizzaIngredient> get();
}

package it.unical.pizzamanager.persistence.dao;

import java.util.List;

import it.unical.pizzamanager.persistence.dto.Ingredient.IngredientType;
import it.unical.pizzamanager.persistence.dto.Pizza;

public interface PizzaDAO {

	public void create(Pizza pizza);

	public void delete(Pizza pizza);

	public void update(Pizza pizza);

	Pizza get(Integer id);

	List<Pizza> getByName(String name);

	public List<Pizza> getAll();

	public List<Pizza> getByIngredientType(IngredientType type);
}

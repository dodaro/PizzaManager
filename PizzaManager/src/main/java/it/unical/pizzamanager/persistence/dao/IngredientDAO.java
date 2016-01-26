package it.unical.pizzamanager.persistence.dao;

import java.util.List;

import it.unical.pizzamanager.persistence.entities.Ingredient;

public interface IngredientDAO {

	public void create(Ingredient ingredient);

	public void delete(Ingredient ingredient);

	public void update(Ingredient ingredient);

	public Ingredient get(String name);

	public List<Ingredient> getAll();
}

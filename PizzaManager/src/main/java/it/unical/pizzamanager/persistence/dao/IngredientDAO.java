package it.unical.pizzamanager.persistence.dao;

import java.util.List;

import it.unical.pizzamanager.persistence.dto.Ingredient;

public interface IngredientDAO {

	public void create(Ingredient ingredient);

	public void delete(Ingredient ingredient);

	public void update(Ingredient ingredient);

	public List<Ingredient> get();
}

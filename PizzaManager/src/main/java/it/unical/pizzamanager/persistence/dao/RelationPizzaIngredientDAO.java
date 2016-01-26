package it.unical.pizzamanager.persistence.dao;

import java.util.List;

import it.unical.pizzamanager.persistence.entities.RelationPizzaIngredient;

public interface RelationPizzaIngredientDAO {

	public void create(RelationPizzaIngredient pizzaIngredient);

	public void delete(RelationPizzaIngredient pizzaIngredient);

	public void update(RelationPizzaIngredient pizzaIngredient);

	public List<RelationPizzaIngredient> get();
}

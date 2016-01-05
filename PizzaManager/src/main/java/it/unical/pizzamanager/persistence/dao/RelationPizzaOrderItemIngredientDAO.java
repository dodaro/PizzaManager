package it.unical.pizzamanager.persistence.dao;

import java.util.List;

import it.unical.pizzamanager.persistence.dto.RelationPizzaOrderItemIngredient;

public interface RelationPizzaOrderItemIngredientDAO {

	
	public void create(RelationPizzaOrderItemIngredient pizzaOrderItemIngredient);

	public void delete(RelationPizzaOrderItemIngredient pizzaOrderItemIngredient);

	public void update(RelationPizzaOrderItemIngredient pizzaOrderItemIngredient);

	public List<RelationPizzaOrderItemIngredient> get();
}

package it.unical.pizzamanager.persistence.dao;

import java.util.List;

import it.unical.pizzamanager.persistence.dto.RelationPizzeriaIngredient;

public interface RelationPizzeriaIngredientDAO {

	
	public void create(RelationPizzeriaIngredient ingredientPriceList);

	public void delete(RelationPizzeriaIngredient ingredientPriceList);

	public void update(RelationPizzeriaIngredient ingredientPriceList);

	public List<RelationPizzeriaIngredient> get();
}

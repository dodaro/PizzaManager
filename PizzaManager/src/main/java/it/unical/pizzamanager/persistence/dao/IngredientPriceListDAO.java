package it.unical.pizzamanager.persistence.dao;

import java.util.List;

import it.unical.pizzamanager.persistence.dto.IngredientPriceList;

public interface IngredientPriceListDAO {

	
	public void create(IngredientPriceList ingredientPriceList);

	public void delete(IngredientPriceList ingredientPriceList);

	public void update(IngredientPriceList ingredientPriceList);

	public List<IngredientPriceList> get();
}

package it.unical.pizzamanager.persistence.dao;

import java.util.List;

import it.unical.pizzamanager.persistence.dto.RelationPizzeriaBeverage;

public interface RelationPizzeriaBeverageDAO {

	public void create(RelationPizzeriaBeverage beveragePriceList);

	public void delete(RelationPizzeriaBeverage beveragePriceList);

	public void update(RelationPizzeriaBeverage beveragePriceList);

	public List<RelationPizzeriaBeverage> get();
}

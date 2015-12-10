package it.unical.pizzamanager.persistence.dao;

import java.util.List;

import it.unical.pizzamanager.persistence.dto.Beverage;

public interface BeverageDAO {

	public void create(Beverage beverage);

	public void delete(Beverage beverage);

	public void update(Beverage beverage);

	public List<Beverage> get();

}

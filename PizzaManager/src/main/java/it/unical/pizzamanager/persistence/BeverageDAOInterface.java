package it.unical.pizzamanager.persistence;

import java.util.List;

public interface BeverageDAOInterface {

	
	public void create(Beverage beverage);
	
	public List<Beverage> get();
	
}

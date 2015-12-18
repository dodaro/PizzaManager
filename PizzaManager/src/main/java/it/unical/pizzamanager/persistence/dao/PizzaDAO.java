package it.unical.pizzamanager.persistence.dao;

import java.util.List;

import it.unical.pizzamanager.persistence.dto.Pizza;

public interface PizzaDAO {

	public void create(Pizza pizza);

	public void delete(Pizza pizza);

	public void update(Pizza pizza);

	public List<Pizza> getAllPizzas();
}

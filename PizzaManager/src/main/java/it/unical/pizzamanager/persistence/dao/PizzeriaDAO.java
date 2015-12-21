package it.unical.pizzamanager.persistence.dao;

import java.util.List;

import it.unical.pizzamanager.persistence.dto.Pizzeria;

public interface PizzeriaDAO {

	public void create(Pizzeria pizzeria);

	public void delete(Pizzeria pizzeria);

	public void update(Pizzeria pizzeria);

	Pizzeria get(Integer id);

	public List<Pizzeria> getAll();
}

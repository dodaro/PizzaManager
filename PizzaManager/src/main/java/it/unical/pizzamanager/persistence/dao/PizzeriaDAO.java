package it.unical.pizzamanager.persistence.dao;

import java.util.List;

import it.unical.pizzamanager.persistence.dto.Location;
import it.unical.pizzamanager.persistence.dto.Pizzeria;
import it.unical.pizzamanager.persistence.dto.RelationPizzeriaPizza;

public interface PizzeriaDAO {

	public void create(Pizzeria pizzeria);

	public void delete(Pizzeria pizzeria);

	public void update(Pizzeria pizzeria);

	Pizzeria get(Integer id);

	Pizzeria get(String email);

	public List<Pizzeria> getAll();

	public List<RelationPizzeriaPizza> getMenuPizze(Integer id);

	public Pizzeria getByName(String name);

	List<Pizzeria> getPizzeriasWithin(Location center, Double radius);
}

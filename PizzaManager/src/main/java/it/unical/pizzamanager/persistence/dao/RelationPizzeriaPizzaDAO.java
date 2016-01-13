package it.unical.pizzamanager.persistence.dao;

import java.util.List;

import it.unical.pizzamanager.persistence.dto.Pizzeria;
import it.unical.pizzamanager.persistence.dto.RelationPizzeriaPizza;

public interface RelationPizzeriaPizzaDAO {

	public void create(RelationPizzeriaPizza pizzaPriceList);

	public void delete(RelationPizzeriaPizza pizzaPriceList);

	public void update(RelationPizzeriaPizza pizzaPriceList);

	RelationPizzeriaPizza get(Integer id);

	List<RelationPizzeriaPizza> get(Pizzeria pizzeria);
}

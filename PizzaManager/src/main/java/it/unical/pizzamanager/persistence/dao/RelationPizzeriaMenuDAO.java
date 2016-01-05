package it.unical.pizzamanager.persistence.dao;

import java.util.List;

import it.unical.pizzamanager.persistence.dto.RelationPizzeriaMenu;

public interface RelationPizzeriaMenuDAO {

	public void create(RelationPizzeriaMenu menuPriceList);

	public void delete(RelationPizzeriaMenu menuPriceList);

	public void update(RelationPizzeriaMenu menuPriceList);

	public List<RelationPizzeriaMenu> get();
}

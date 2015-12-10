package it.unical.pizzamanager.persistence.dao;

import java.util.List;

import it.unical.pizzamanager.persistence.dto.PizzaPriceList;

public interface PizzaPriceListDAO {

	public void create(PizzaPriceList pizzaPriceList);

	public void delete(PizzaPriceList pizzaPriceList);

	public void update(PizzaPriceList pizzaPriceList);

	public List<PizzaPriceList> get();
}

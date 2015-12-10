package it.unical.pizzamanager.persistence.dao;

import java.util.List;

import it.unical.pizzamanager.persistence.dto.MenuPriceList;

public interface MenuPriceListDAO {

	public void create(MenuPriceList menuPriceList);

	public void delete(MenuPriceList menuPriceList);

	public void update(MenuPriceList menuPriceList);

	public List<MenuPriceList> get();
}

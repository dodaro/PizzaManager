package it.unical.pizzamanager.persistence.dao;

import java.util.List;

import it.unical.pizzamanager.persistence.dto.Menu;

public interface MenuDAO {

	public void create(Menu menu);

	public void delete(Menu menu);

	public void update(Menu menu);

	public List<Menu> get();
}

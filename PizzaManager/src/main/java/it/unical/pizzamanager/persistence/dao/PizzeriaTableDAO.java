package it.unical.pizzamanager.persistence.dao;

import java.util.List;

import it.unical.pizzamanager.persistence.entities.Pizzeria;
import it.unical.pizzamanager.persistence.entities.PizzeriaTable;

public interface PizzeriaTableDAO {

	public void create(PizzeriaTable table);

	public void delete(PizzeriaTable table);

	public void update(PizzeriaTable table);

	public PizzeriaTable get(Integer idTable);

	public List<PizzeriaTable> getAllTablesList();

	List<PizzeriaTable> getTablesOfPizzeria(Pizzeria pizzeria);
	
}

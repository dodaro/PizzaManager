package it.unical.pizzamanager.persistence.dao;

import java.util.List;

import it.unical.pizzamanager.persistence.dto.TablePizzeria;

public interface TablePizzeriaDAO {
	
	public void create(TablePizzeria table);
	
	public void delete(TablePizzeria table);
	
	public void update(TablePizzeria table);
	
	public TablePizzeria get(Integer idTable);
	
	public List<TablePizzeria> getAllTablesList();
	
	//QUESTE INFORMAZIONI POSSONO ESSERE RICAVATE DALL'ENTITÀ PIZZERIA IN QUANTO ESSA POSSIEDE LA LISTA DEI PROPRI TAVOLI
	//ATTENDIAMO DODARO
	
	//SARANNO IN CASO FUNZIONI DENTRO PIZZERIA!!
	
	//public TablePizzeria getFromNumber(/*Pizzeria pizzeria,*/Integer number);
	
	//public List<TablePizzeria> getTablesList(/*Pizzeria pizzeria*/);
		
	//public List<TablePizzeria> getTablesListFromSits(/*Pizzeria pizzeria,*/Integer sits);
		
	//public List<TablePizzeria> getTablesListFromMaxSits(/*Pizzeria pizzeria,*/Integer maxSits);
		
	//public List<TablePizzeria> getTablesListFromMinSits(/*Pizzeria pizzeria,*/Integer minSits);
		
	//public List<TablePizzeria> getAvailableTablesList(/*Pizzeria pizzeria*/);

}

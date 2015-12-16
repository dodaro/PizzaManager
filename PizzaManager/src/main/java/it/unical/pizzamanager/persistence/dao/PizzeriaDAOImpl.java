package it.unical.pizzamanager.persistence.dao;

import java.util.List;

import org.hibernate.Session;

import it.unical.pizzamanager.persistence.dto.Pizza;
import it.unical.pizzamanager.persistence.dto.Pizzeria;

public class PizzeriaDAOImpl implements PizzeriaDAO {

	private DatabaseHandler databaseHandler;
	
	public PizzeriaDAOImpl() {
		databaseHandler=null;
	}
	
	@Override
	public void create(Pizzeria pizzeria) {
		databaseHandler.create(pizzeria);

	}

	@Override
	public void delete(Pizzeria pizzeria) {
		databaseHandler.delete(pizzeria);

	}

	@Override
	public void update(Pizzeria pizzeria) {
		databaseHandler.update(pizzeria);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pizzeria> get() {
		Session session = databaseHandler.getSessionFactory().openSession();
		List<Pizzeria> pizzerias = session.createSQLQuery("Select * from pizzerias").addEntity(Pizzeria.class).list();
		session.close();
		return pizzerias;
	}

	public DatabaseHandler getDatabaseHandler() {
		return databaseHandler;
	}

	public void setDatabaseHandler(DatabaseHandler databaseHandler) {
		this.databaseHandler = databaseHandler;
	}

}

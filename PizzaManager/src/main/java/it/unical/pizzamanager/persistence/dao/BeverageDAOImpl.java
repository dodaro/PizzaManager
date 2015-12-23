package it.unical.pizzamanager.persistence.dao;

import java.util.List;

import org.hibernate.Session;

import it.unical.pizzamanager.persistence.dto.Beverage;

public class BeverageDAOImpl implements BeverageDAO {

	private DatabaseHandler databaseHandler;

	public BeverageDAOImpl() {
		databaseHandler = null;
	}

	@Override
	public void create(Beverage beverage) {
		databaseHandler.create(beverage);
	}

	@Override
	public void delete(Beverage beverage) {
		databaseHandler.delete(beverage);
	}

	@Override
	public void update(Beverage beverage) {
		databaseHandler.update(beverage);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Beverage> getAll() {
		Session session = databaseHandler.getSessionFactory().openSession();
		List<Beverage> beverages = (List<Beverage>) session.createQuery("from Beverage").list();
		session.close();
		return beverages;
	}

	public DatabaseHandler getDatabaseHandler() {
		return databaseHandler;
	}

	public void setDatabaseHandler(DatabaseHandler databaseHandler) {
		this.databaseHandler = databaseHandler;
	}
}

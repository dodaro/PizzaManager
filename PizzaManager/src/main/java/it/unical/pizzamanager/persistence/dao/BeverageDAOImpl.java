package it.unical.pizzamanager.persistence.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import it.unical.pizzamanager.persistence.entities.Beverage;

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
	public Beverage get(Integer id) {
		Session session = databaseHandler.getSessionFactory().openSession();
		Query query = session.createQuery("from Beverage where id = :id");
		query.setParameter("id", id);
		Beverage beverage = (Beverage) query.uniqueResult();
		session.close();

		return beverage;
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

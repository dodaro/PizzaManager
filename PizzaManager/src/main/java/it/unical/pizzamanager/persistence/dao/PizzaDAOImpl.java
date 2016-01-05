package it.unical.pizzamanager.persistence.dao;

import java.util.List;

import org.hibernate.Session;

import it.unical.pizzamanager.persistence.dto.Pizza;

public class PizzaDAOImpl implements PizzaDAO {

	private DatabaseHandler databaseHandler;

	public PizzaDAOImpl() {
		databaseHandler=null;
	}
	@Override
	public void create(Pizza pizza) {
		databaseHandler.create(pizza);
	}

	@Override
	public void delete(Pizza pizza) {
	databaseHandler.delete(pizza);

	}

	@Override
	public void update(Pizza pizza) {
		databaseHandler.update(pizza);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pizza> get() {
		Session session = databaseHandler.getSessionFactory().openSession();
		List<Pizza> pizze = session.createSQLQuery("Select *from pizze").addEntity(Pizza.class).list();
		session.close();
		return pizze;
	}
	public DatabaseHandler getDatabaseHandler() {
		return databaseHandler;
	}
	public void setDatabaseHandler(DatabaseHandler databaseHandler) {
		this.databaseHandler = databaseHandler;
	}
	@Override
	public List<Pizza> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Pizza> getByName(String string) {
		// TODO Auto-generated method stub
		return null;
	}

}

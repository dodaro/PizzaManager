package it.unical.pizzamanager.persistence.dao;

import java.util.List;

import org.hibernate.Session;

import it.unical.pizzamanager.persistence.dto.Pizza;

public class PizzaDAOImpl implements PizzaDAO {

	private DatabaseHandler databaseHandler;

	public PizzaDAOImpl() {
		databaseHandler = null;
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
	public List<Pizza> getAllPizzas() {
		Session session = databaseHandler.getSessionFactory().openSession();
		List<Pizza> pizzas = session.createQuery("from Pizza").list();
		session.close();
		return pizzas;
	}

	public DatabaseHandler getDatabaseHandler() {
		return databaseHandler;
	}

	public void setDatabaseHandler(DatabaseHandler databaseHandler) {
		this.databaseHandler = databaseHandler;
	}

}

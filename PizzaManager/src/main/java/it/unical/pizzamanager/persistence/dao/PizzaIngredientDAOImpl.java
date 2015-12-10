package it.unical.pizzamanager.persistence.dao;

import java.util.List;

import org.hibernate.Session;

import it.unical.pizzamanager.persistence.dto.PizzaIngredient;

public class PizzaIngredientDAOImpl implements PizzaIngredientDAO {

	private DatabaseHandler databaseHandler;

	public PizzaIngredientDAOImpl() {
		databaseHandler = null;
	}

	@Override
	public void create(PizzaIngredient pizzaIngredient) {
		databaseHandler.create(pizzaIngredient);

	}

	@Override
	public void delete(PizzaIngredient pizzaIngredient) {
		databaseHandler.delete(pizzaIngredient);

	}

	@Override
	public void update(PizzaIngredient pizzaIngredient) {
		databaseHandler.update(pizzaIngredient);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PizzaIngredient> get() {
		Session session = databaseHandler.getSessionFactory().openSession();
		List<PizzaIngredient> pizzaIngredients = session.createSQLQuery("Select *from pizzaIngredients")
				.addEntity(PizzaIngredient.class).list();
		session.close();
		return pizzaIngredients;
	}

	public DatabaseHandler getDatabaseHandler() {
		return databaseHandler;
	}

	public void setDatabaseHandler(DatabaseHandler databaseHandler) {
		this.databaseHandler = databaseHandler;
	}

}

package it.unical.pizzamanager.persistence.dao;

import java.util.List;

import org.hibernate.Session;

import it.unical.pizzamanager.persistence.dto.RelationPizzaIngredient;

public class RelationPizzaIngredientDAOImpl implements RelationPizzaIngredientDAO {

	private DatabaseHandler databaseHandler;

	public RelationPizzaIngredientDAOImpl() {
		databaseHandler = null;
	}

	@Override
	public void create(RelationPizzaIngredient pizzaIngredient) {
		databaseHandler.create(pizzaIngredient);

	}

	@Override
	public void delete(RelationPizzaIngredient pizzaIngredient) {
		databaseHandler.delete(pizzaIngredient);

	}

	@Override
	public void update(RelationPizzaIngredient pizzaIngredient) {
		databaseHandler.update(pizzaIngredient);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RelationPizzaIngredient> get() {
		Session session = databaseHandler.getSessionFactory().openSession();
		List<RelationPizzaIngredient> pizzaIngredients = session.createSQLQuery("Select * from pizza_ingredient")
				.addEntity(RelationPizzaIngredient.class).list();
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

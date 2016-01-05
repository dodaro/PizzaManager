package it.unical.pizzamanager.persistence.dao;

import java.util.List;

import org.hibernate.Session;

import it.unical.pizzamanager.persistence.dto.RelationPizzaOrderItemIngredient;

public class RelationPizzaOrderItemIngredientDAOImpl implements RelationPizzaOrderItemIngredientDAO {

	private DatabaseHandler databaseHandler;

	public RelationPizzaOrderItemIngredientDAOImpl() {
		databaseHandler = null;
	}

	@Override
	public void create(RelationPizzaOrderItemIngredient pizzaOrderItemIngredient) {
		databaseHandler.create(pizzaOrderItemIngredient);

	}

	@Override
	public void delete(RelationPizzaOrderItemIngredient pizzaOrderItemIngredient) {
		databaseHandler.delete(pizzaOrderItemIngredient);

	}

	@Override
	public void update(RelationPizzaOrderItemIngredient pizzaOrderItemIngredient) {
		databaseHandler.update(pizzaOrderItemIngredient);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RelationPizzaOrderItemIngredient> get() {
		Session session = databaseHandler.getSessionFactory().openSession();
		List<RelationPizzaOrderItemIngredient> pizzaOrderItemIngredients = session
				.createSQLQuery("select * from pizzaOrderItem_ingredient")
				.addEntity(RelationPizzaOrderItemIngredient.class).list();
		return pizzaOrderItemIngredients;
	}

	public DatabaseHandler getDatabaseHandler() {
		return databaseHandler;
	}

	public void setDatabaseHandler(DatabaseHandler databaseHandler) {
		this.databaseHandler = databaseHandler;
	}

}

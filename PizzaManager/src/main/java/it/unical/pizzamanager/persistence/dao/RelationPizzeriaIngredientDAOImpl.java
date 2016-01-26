package it.unical.pizzamanager.persistence.dao;

import java.util.List;

import org.hibernate.Session;

import it.unical.pizzamanager.persistence.entities.RelationPizzeriaIngredient;

public class RelationPizzeriaIngredientDAOImpl implements RelationPizzeriaIngredientDAO {

	private DatabaseHandler databaseHandler;

	public RelationPizzeriaIngredientDAOImpl() {
		databaseHandler=null;
	}
	@Override
	public void create(RelationPizzeriaIngredient ingredientPriceList) {
		databaseHandler.create(ingredientPriceList);

	}

	@Override
	public void delete(RelationPizzeriaIngredient ingredientPriceList) {
		databaseHandler.delete(ingredientPriceList);

	}

	@Override
	public void update(RelationPizzeriaIngredient ingredientPriceList) {
		databaseHandler.update(ingredientPriceList);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RelationPizzeriaIngredient> get() {
		Session session = databaseHandler.getSessionFactory().openSession();
		List<RelationPizzeriaIngredient> ingredientPriceLists = session.createSQLQuery("Select * from pizzeria_ingredient_price")
				.addEntity(RelationPizzeriaIngredient.class).list();
		session.close();
		return ingredientPriceLists;
	}
	public DatabaseHandler getDatabaseHandler() {
		return databaseHandler;
	}
	public void setDatabaseHandler(DatabaseHandler databaseHandler) {
		this.databaseHandler = databaseHandler;
	}

}

package it.unical.pizzamanager.persistence.dao;

import java.util.List;

import org.hibernate.Session;

import it.unical.pizzamanager.persistence.dto.Ingredient;

public class IngredientDAOImpl implements IngredientDAO {

	private DatabaseHandler databaseHandler;

	public IngredientDAOImpl() {
		databaseHandler = null;
	}

	@Override
	public void create(Ingredient ingredient) {
		databaseHandler.create(ingredient);

	}

	@Override
	public void delete(Ingredient ingredient) {
		databaseHandler.delete(ingredient);

	}

	@Override
	public void update(Ingredient ingredient) {
		databaseHandler.update(ingredient);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ingredient> get() {
		Session session = databaseHandler.getSessionFactory().openSession();
		List<Ingredient> ingredients = session.createSQLQuery("Select *from ingredients").addEntity(Ingredient.class)
				.list();
		session.close();
		return ingredients;
	}

	public DatabaseHandler getDatabaseHandler() {
		return databaseHandler;
	}

	public void setDatabaseHandler(DatabaseHandler databaseHandler) {
		this.databaseHandler = databaseHandler;
	}

}

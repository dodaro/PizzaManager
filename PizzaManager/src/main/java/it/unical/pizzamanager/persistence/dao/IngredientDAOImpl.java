package it.unical.pizzamanager.persistence.dao;

import java.util.List;

import org.hibernate.Query;
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

	@Override
	public Ingredient get(String name) {
		Session session = databaseHandler.getSessionFactory().openSession();
		Query query = session.createQuery("from Ingredient where name = :name");
		query.setParameter("name", name);
		Ingredient ingredient = (Ingredient) query.uniqueResult();
		session.close();
		return ingredient;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ingredient> getAll() {
		Session session = databaseHandler.getSessionFactory().openSession();
		List<Ingredient> ingredients = session.createQuery("from Ingredient").list();
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

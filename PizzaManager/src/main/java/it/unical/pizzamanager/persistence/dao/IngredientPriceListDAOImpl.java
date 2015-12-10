package it.unical.pizzamanager.persistence.dao;

import java.util.List;

import org.hibernate.Session;

import it.unical.pizzamanager.persistence.dto.IngredientPriceList;

public class IngredientPriceListDAOImpl implements IngredientPriceListDAO {

	private DatabaseHandler databaseHandler;

	public IngredientPriceListDAOImpl() {
		databaseHandler=null;
	}
	@Override
	public void create(IngredientPriceList ingredientPriceList) {
		databaseHandler.create(ingredientPriceList);

	}

	@Override
	public void delete(IngredientPriceList ingredientPriceList) {
		databaseHandler.delete(ingredientPriceList);

	}

	@Override
	public void update(IngredientPriceList ingredientPriceList) {
		databaseHandler.update(ingredientPriceList);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<IngredientPriceList> get() {
		Session session = databaseHandler.getSessionFactory().openSession();
		List<IngredientPriceList> ingredientPriceLists = session.createSQLQuery("Select *from ingredientPriceLists")
				.addEntity(IngredientPriceList.class).list();
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

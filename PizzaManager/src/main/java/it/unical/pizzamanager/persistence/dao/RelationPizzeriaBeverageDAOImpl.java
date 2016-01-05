package it.unical.pizzamanager.persistence.dao;

import java.util.List;

import org.hibernate.Session;

import it.unical.pizzamanager.persistence.dto.RelationPizzeriaBeverage;

public class RelationPizzeriaBeverageDAOImpl implements RelationPizzeriaBeverageDAO {

	private DatabaseHandler databaseHandler;

	@SuppressWarnings("unchecked")
	@Override
	public List<RelationPizzeriaBeverage> get() {
		Session session = databaseHandler.getSessionFactory().openSession();
		List<RelationPizzeriaBeverage> beveragePriceLists = session.createSQLQuery("Select * from pizzeria_beverage_price")
				.addEntity(RelationPizzeriaBeverage.class).list();
		session.close();
		return beveragePriceLists;
	}

	public DatabaseHandler getDatabaseHandler() {
		return databaseHandler;
	}

	public void setDatabaseHandler(DatabaseHandler databaseHandler) {
		this.databaseHandler = databaseHandler;
	}

	@Override
	public void create(RelationPizzeriaBeverage beveragePriceList) {
		databaseHandler.create(beveragePriceList);

	}

	@Override
	public void delete(RelationPizzeriaBeverage beveragePriceList) {
		databaseHandler.delete(beveragePriceList);

	}

	@Override
	public void update(RelationPizzeriaBeverage beveragePriceList) {
		databaseHandler.update(beveragePriceList);
	}

}

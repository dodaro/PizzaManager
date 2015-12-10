package it.unical.pizzamanager.persistence.dao;

import java.util.List;

import org.hibernate.Session;

import it.unical.pizzamanager.persistence.dto.BeveragePriceList;

public class BeveragePriceListDAOImpl implements BeveragePriceListDAO {

	private DatabaseHandler databaseHandler;

	public BeveragePriceListDAOImpl() {
		databaseHandler = null;
	}

	@Override
	public void create(BeveragePriceList beveragePriceList) {
		databaseHandler.create(beveragePriceList);

	}

	@Override
	public void delete(BeveragePriceList beveragePriceList) {
		databaseHandler.delete(beveragePriceList);

	}

	@Override
	public void update(BeveragePriceList beveragePriceList) {
		databaseHandler.update(beveragePriceList);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BeveragePriceList> get() {
		Session session = databaseHandler.getSessionFactory().openSession();
		List<BeveragePriceList> beveragePriceLists = session.createSQLQuery("Select *from beveragePriceLists")
				.addEntity(BeveragePriceList.class).list();
		session.close();
		return beveragePriceLists;
	}

	public DatabaseHandler getDatabaseHandler() {
		return databaseHandler;
	}

	public void setDatabaseHandler(DatabaseHandler databaseHandler) {
		this.databaseHandler = databaseHandler;
	}

}

package it.unical.pizzamanager.persistence.dao;

import java.util.List;

import org.hibernate.Session;

import it.unical.pizzamanager.persistence.dto.PizzaPriceList;

public class PizzaPriceListDAOImpl implements PizzaPriceListDAO {

	private DatabaseHandler databaseHandler;

	public PizzaPriceListDAOImpl() {
		databaseHandler=null;
	}
	@Override
	public void create(PizzaPriceList pizzaPriceList) {
		databaseHandler.create(pizzaPriceList);

	}

	@Override
	public void delete(PizzaPriceList pizzaPriceList) {
		databaseHandler.delete(pizzaPriceList);

	}

	@Override
	public void update(PizzaPriceList pizzaPriceList) {
		databaseHandler.update(pizzaPriceList);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PizzaPriceList> get() {
		Session session = databaseHandler.getSessionFactory().openSession();
		List<PizzaPriceList> pizzaPriceLists = session.createSQLQuery("Select *from pizzaPriceLists")
				.addEntity(PizzaPriceList.class).list();
		session.close();
		return pizzaPriceLists;
	}
	public DatabaseHandler getDatabaseHandler() {
		return databaseHandler;
	}
	public void setDatabaseHandler(DatabaseHandler databaseHandler) {
		this.databaseHandler = databaseHandler;
	}

}

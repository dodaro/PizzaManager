package it.unical.pizzamanager.persistence.dao;

import java.util.List;

import org.hibernate.Session;

import it.unical.pizzamanager.persistence.dto.MenuPriceList;

public class MenuPriceListDAOImpl implements MenuPriceListDAO {
	
	private DatabaseHandler databaseHandler;

	public MenuPriceListDAOImpl() {
		databaseHandler=null;
	}

	@Override
	public void create(MenuPriceList menuPriceList) {
		databaseHandler.create(menuPriceList);

	}

	@Override
	public void delete(MenuPriceList menuPriceList) {
		databaseHandler.delete(menuPriceList);

	}

	@Override
	public void update(MenuPriceList menuPriceList) {
		databaseHandler.update(menuPriceList);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MenuPriceList> get() {
		Session session = databaseHandler.getSessionFactory().openSession();
		List<MenuPriceList> menuPriceLists = session.createSQLQuery("Select *from menuPriceLists")
				.addEntity(MenuPriceList.class).list();
		session.close();
		return menuPriceLists;
	}

	public DatabaseHandler getDatabaseHandler() {
		return databaseHandler;
	}

	public void setDatabaseHandler(DatabaseHandler databaseHandler) {
		this.databaseHandler = databaseHandler;
	}

}

package it.unical.pizzamanager.persistence.dao;

import java.util.List;

import org.hibernate.Session;

import it.unical.pizzamanager.persistence.dto.Menu;

public class MenuDAOImpl implements MenuDAO {

	private DatabaseHandler databaseHandler;

	public MenuDAOImpl() {
		setDatabaseHandler(null);
	}

	@Override
	public void create(Menu menu) {
		getDatabaseHandler().create(menu);

	}

	@Override
	public void delete(Menu menu) {
		getDatabaseHandler().delete(menu);

	}

	@Override
	public void update(Menu menu) {
		getDatabaseHandler().update(menu);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Menu> getAll() {
		Session session = getDatabaseHandler().getSessionFactory().openSession();
		List<Menu> menus = session.createQuery("from Menu").list();
		session.close();
		return menus;
	}

	public DatabaseHandler getDatabaseHandler() {
		return databaseHandler;
	}

	public void setDatabaseHandler(DatabaseHandler databaseHandler) {
		this.databaseHandler = databaseHandler;
	}

}

package it.unical.pizzamanager.persistence.dao;

import java.util.List;

import org.hibernate.Session;

import it.unical.pizzamanager.persistence.dto.RelationPizzeriaMenu;

public class RelationPizzeriaMenuDAOImpl implements RelationPizzeriaMenuDAO {
	
	private DatabaseHandler databaseHandler;

	public RelationPizzeriaMenuDAOImpl() {
		databaseHandler=null;
	}

	@Override
	public void create(RelationPizzeriaMenu menuPriceList) {
		databaseHandler.create(menuPriceList);

	}

	@Override
	public void delete(RelationPizzeriaMenu menuPriceList) {
		databaseHandler.delete(menuPriceList);

	}

	@Override
	public void update(RelationPizzeriaMenu menuPriceList) {
		databaseHandler.update(menuPriceList);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RelationPizzeriaMenu> get() {
		Session session = databaseHandler.getSessionFactory().openSession();
		List<RelationPizzeriaMenu> menuPriceLists = session.createSQLQuery("Select * from pizzeria_menu_price")
				.addEntity(RelationPizzeriaMenu.class).list();
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

package it.unical.pizzamanager.persistence.dao;

import java.util.List;

import org.hibernate.Session;

import it.unical.pizzamanager.persistence.dto.RelationPizzeriaPizza;

public class RelationPizzeriaPizzaDAOImpl implements RelationPizzeriaPizzaDAO {

	private DatabaseHandler databaseHandler;

	public RelationPizzeriaPizzaDAOImpl() {
		databaseHandler=null;
	}
	@Override
	public void create(RelationPizzeriaPizza pizzaPriceList) {
		databaseHandler.create(pizzaPriceList);

	}

	@Override
	public void delete(RelationPizzeriaPizza pizzaPriceList) {
		databaseHandler.delete(pizzaPriceList);

	}

	@Override
	public void update(RelationPizzeriaPizza pizzaPriceList) {
		databaseHandler.update(pizzaPriceList);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RelationPizzeriaPizza> get() {
		Session session = databaseHandler.getSessionFactory().openSession();
		List<RelationPizzeriaPizza> pizzaPriceLists = session.createSQLQuery("Select * from pizzeria_pizza_price")
				.addEntity(RelationPizzeriaPizza.class).list();
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

package it.unical.pizzamanager.persistence.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import it.unical.pizzamanager.persistence.dto.Pizzeria;
import it.unical.pizzamanager.persistence.dto.RelationPizzeriaPizza;

public class RelationPizzeriaPizzaDAOImpl implements RelationPizzeriaPizzaDAO {

	private DatabaseHandler databaseHandler;

	public RelationPizzeriaPizzaDAOImpl() {
		databaseHandler = null;
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
	public List<RelationPizzeriaPizza> get(Pizzeria pizzeria) {
		Session session = databaseHandler.getSessionFactory().openSession();
		Query query = session.createQuery("from RelationPizzeriaPizza where pizzeria = :pizzeria");
		query.setParameter("pizzeria", pizzeria);
		List<RelationPizzeriaPizza> pizzeriaPizzas = query.list();
		session.close();
		return pizzeriaPizzas;
	}

	public DatabaseHandler getDatabaseHandler() {
		return databaseHandler;
	}

	public void setDatabaseHandler(DatabaseHandler databaseHandler) {
		this.databaseHandler = databaseHandler;
	}

}

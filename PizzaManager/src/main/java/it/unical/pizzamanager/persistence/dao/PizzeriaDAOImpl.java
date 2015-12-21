package it.unical.pizzamanager.persistence.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import it.unical.pizzamanager.persistence.dto.Pizzeria;

public class PizzeriaDAOImpl implements PizzeriaDAO {

	private DatabaseHandler databaseHandler;

	public PizzeriaDAOImpl() {
		databaseHandler = null;
	}

	@Override
	public void create(Pizzeria pizzeria) {
		databaseHandler.create(pizzeria);
	}

	@Override
	public void delete(Pizzeria pizzeria) {
		databaseHandler.delete(pizzeria);
	}

	@Override
	public void update(Pizzeria pizzeria) {
		databaseHandler.update(pizzeria);
	}

	@Override
	public Pizzeria get(Integer id) {
		Session session = databaseHandler.getSessionFactory().openSession();
		Query query = session.createQuery("from Pizzeria where id = :id");
		Pizzeria pizzeria = (Pizzeria) query.uniqueResult();
		session.close();
		return pizzeria;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pizzeria> getAll() {
		Session session = databaseHandler.getSessionFactory().openSession();
		List<Pizzeria> pizzerias = session.createQuery("from Pizzeria").list();
		session.close();
		return pizzerias;
	}

	public DatabaseHandler getDatabaseHandler() {
		return databaseHandler;
	}

	public void setDatabaseHandler(DatabaseHandler databaseHandler) {
		this.databaseHandler = databaseHandler;
	}

}

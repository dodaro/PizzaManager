package it.unical.pizzamanager.persistence.dao;

import java.util.List;

import org.hibernate.Session;

import it.unical.pizzamanager.persistence.dto.RelationPizzeriaPizza;
import it.unical.pizzamanager.persistence.dto.User;

public class UserDAOImpl implements UserDAO {

	private DatabaseHandler databaseHandler;

	UserDAOImpl() {
		databaseHandler = null;
	}

	@Override
	public void create(User user) {
		databaseHandler.create(user);
	}

	@Override
	public void update(User user) {
		databaseHandler.update(user);
	}

	public void delete(User user) {
		databaseHandler.delete(user);
	};

	@SuppressWarnings("unchecked")
	@Override
	public List<User> get() {
		Session session = databaseHandler.getSessionFactory().openSession();

		List<User> users = session.createSQLQuery("SELECT * FROM users").addEntity(User.class)
				.list();

		session.close();
		return users;
	}
	
	public void setDatabaseHandler(DatabaseHandler databaseHandler) {
		this.databaseHandler = databaseHandler;
	}
	
	public DatabaseHandler getDatabaseHandler() {
		return databaseHandler;
	}

	@Override
	public List<RelationPizzeriaPizza> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
}

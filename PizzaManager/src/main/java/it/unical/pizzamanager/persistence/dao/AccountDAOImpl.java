package it.unical.pizzamanager.persistence.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import it.unical.pizzamanager.persistence.entities.Account;

public class AccountDAOImpl implements AccountDAO {

	private DatabaseHandler databaseHandler;

	AccountDAOImpl() {
		databaseHandler = null;
	}

	@Override
	public Account get(Integer id) {
		Session session = databaseHandler.getSessionFactory().openSession();
		Query query = session.createQuery("from Account where id = :id");
		query.setParameter("id", id);

		Account account = (Account) query.uniqueResult();

		session.close();
		return account;
	}

	@Override
	public Account get(String email) {
		Session session = databaseHandler.getSessionFactory().openSession();
		Query query = session.createQuery("from Account where email = :email");
		query.setParameter("email", email);
		Account account = (Account) query.uniqueResult();
		session.close();
		return account;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Account> getAll() {
		Session session = databaseHandler.getSessionFactory().openSession();
		List<Account> accounts = session.createQuery("from Account").list();
		session.close();
		return accounts;
	}

	public void setDatabaseHandler(DatabaseHandler databaseHandler) {
		this.databaseHandler = databaseHandler;
	}

	public DatabaseHandler getDatabaseHandler() {
		return databaseHandler;
	}
}

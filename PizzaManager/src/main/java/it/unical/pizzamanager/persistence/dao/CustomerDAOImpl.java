package it.unical.pizzamanager.persistence.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import it.unical.pizzamanager.persistence.dto.Customer;

public class CustomerDAOImpl implements CustomerDAO {

	private DatabaseHandler databaseHandler;

	CustomerDAOImpl() {
		databaseHandler = null;
	}

	@Override
	public void create(Customer customer) {
		databaseHandler.create(customer);
	}

	@Override
	public void update(Customer customer) {
		databaseHandler.update(customer);
	}

	public void delete(Customer customer) {
		databaseHandler.delete(customer);
	};

	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> getAllCustomers() {
		Session session = databaseHandler.getSessionFactory().openSession();
		List<Customer> customers = session.createQuery("from Customer").list();
		session.close();
		return customers;
	}

	@Override
	public Customer get(Integer id) {
		Session session = databaseHandler.getSessionFactory().openSession();
		Query query = session.createQuery("from Customer where id = :id");
		query.setParameter("id", id);
		Customer customer = (Customer) query.uniqueResult();
		// user.getBookings().size();
		// if (user != null) {
		// for (Booking b : user.getBookings()) {
		// Hibernate.initialize(b);
		// }
		// }
		session.close();
		return customer;
	}

	public void setDatabaseHandler(DatabaseHandler databaseHandler) {
		this.databaseHandler = databaseHandler;
	}

	public DatabaseHandler getDatabaseHandler() {
		return databaseHandler;
	}
}

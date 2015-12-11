package it.unical.pizzamanager.persistence.dao;

import java.util.List;

import org.hibernate.Session;

import it.unical.pizzamanager.persistence.dto.Favourites;
import it.unical.pizzamanager.persistence.dto.Feedback;
import it.unical.pizzamanager.persistence.dto.Payment;
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
	public List<User> getUsers() {
		Session session = databaseHandler.getSessionFactory().openSession();

		List<User> users = session.createSQLQuery("SELECT * FROM users").addEntity(User.class)
				.list();

		session.close();
		return users;
	}
	
	@Override
	public int numberOfUsers() {
		Session session = databaseHandler.getSessionFactory().openSession();
		int size = session.createSQLQuery("SELECT * FROM users").list().size();
		session.close();
		return size;
	}

	@Override
	public User getById(Integer id) {
		Session session = databaseHandler.getSessionFactory().openSession();
		User user = (User) session.createSQLQuery("SELECT * FROM users where id = " + id).addEntity(User.class)
				.uniqueResult();		
		//user.getBookings().size();
		// if (user != null) {
		// for (Booking b : user.getBookings()) {
		// Hibernate.initialize(b);
		// }
		// }
		session.close();
		return user;
	}

	@Override
	public User getByUsername(String username) {
		Session session = databaseHandler.getSessionFactory().openSession();
		User user = (User) session.createSQLQuery("SELECT * FROM users where username = '" + username + "'")
				.addEntity(User.class).uniqueResult();		
//		if(user!=null)
//			user.getBookings().size();
		session.close();		
		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Payment> getPayments(User user) {
		Session session = databaseHandler.getSessionFactory().openSession();
		List<Payment> payments = (List<Payment>) session.createQuery("from Payment p where user =" + user.getId()).list();
		session.close();
		return payments;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Feedback> getFeedbacks(User user) {
		Session session = databaseHandler.getSessionFactory().openSession();
		List<Feedback> feedback = (List<Feedback>) session.createQuery("from Feedback p where user =" + user.getId()).list();
		session.close();
		return feedback;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Favourites> getFavourites(User user) {
		Session session = databaseHandler.getSessionFactory().openSession();
		List<Favourites> favourites = (List<Favourites>) session.createQuery("from Favourites p where user =" + user.getId()).list();
		session.close();
		return favourites;
	}

	
	public void setDatabaseHandler(DatabaseHandler databaseHandler) {
		this.databaseHandler = databaseHandler;
	}
	
	public DatabaseHandler getDatabaseHandler() {
		return databaseHandler;
	}
}

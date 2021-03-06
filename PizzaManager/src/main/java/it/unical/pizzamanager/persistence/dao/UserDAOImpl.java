package it.unical.pizzamanager.persistence.dao;

import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;

import it.unical.pizzamanager.persistence.entities.Booking;
import it.unical.pizzamanager.persistence.entities.User;

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
	public List<User> getAll() {
		Session session = databaseHandler.getSessionFactory().openSession();
		List<User> users = session.createQuery("from User").list();
		session.close();
		return users;
	}

	@Override
	public User get(Integer id) {
		Session session = databaseHandler.getSessionFactory().openSession();
		Query query = session.createQuery("from User where id = :id");
		query.setParameter("id", id);
		User user = (User) query.uniqueResult();
		user.getFeedbacks().size();
		user.getBookings().size();
		// user.getBookings().size();
		// if (user != null) {
		// for (Booking b : user.getBookings()) {
		// Hibernate.initialize(b);
		// }
		// }
		session.close();
		return user;
	}

	@Override
	public User get(String email) {
		Session session = databaseHandler.getSessionFactory().openSession();
		Query query = session.createQuery("from User where email = :email");
		query.setParameter("email", email);
		User user = (User) query.uniqueResult();
		// user.getBookings().size();
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
		Query query = session.createQuery("from User where name = :name");
		query.setParameter("name", username);
		User user = (User) query.uniqueResult();
		session.close();
		return user;
	}


	@Override
	public Booking getBooking(int bookingId, Integer id) {
		Session session = databaseHandler.getSessionFactory().openSession();
		Query query = session.createQuery("from User where id = :id");
		query.setParameter("id", id);
		User user = (User) query.uniqueResult();
		Booking book = null;
		for (Booking booking : user.getBookings()) {
			if (booking.getId() == bookingId) {
				Hibernate.initialize(booking);
				booking.getOrderItems().size();
				booking.getPayment();
				book = booking;
			}

		}

		session.close();
		return book;
	}


	public void setDatabaseHandler(DatabaseHandler databaseHandler) {
		this.databaseHandler = databaseHandler;
	}

	public DatabaseHandler getDatabaseHandler() {
		return databaseHandler;
	}

}

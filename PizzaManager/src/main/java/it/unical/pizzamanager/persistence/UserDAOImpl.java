package it.unical.pizzamanager.persistence;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/*
 * This class is not public since instances of it will be provided by the DAOFactory class.
 */
class UserDAOImpl implements UserDAO {

	private SessionFactory sessionFactory;

	UserDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void create(User user) {
		Session session = sessionFactory.openSession();

		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			session.save(user);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> get() {
		Session session = sessionFactory.openSession();

		List<User> users = session.createSQLQuery("SELECT * FROM users").addEntity(User.class)
				.list();

		session.close();
		return users;
	}
}

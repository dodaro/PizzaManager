package it.unical.pizzamanager.persistence.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import it.unical.pizzamanager.persistence.dto.User;

/*
 * This class is not public since instances of it will be provided by the DAOFactory class.
 */
class UserDAOImpl extends AbstractDAO implements UserDAO {

	UserDAOImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public void create(User user) {
		performOperation(user, Operation.CREATE);
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

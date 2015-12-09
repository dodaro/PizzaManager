package it.unical.pizzamanager.persistence.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * This class is a singleton which handles the creation of DAOs. It stores the one and only
 * SessionFactory object needed by the application, and injects it into each new DAO it creates.
 */
public class DAOFactory {

	private static DAOFactory instance;

	private SessionFactory sessionFactory;

	private DAOFactory() {
		/*
		 * The code used by the professor to build a new SessionFactory won't work on Hibernate 5,
		 * so use this instead.
		 */
		Configuration configuration = new Configuration().configure();
		this.sessionFactory = configuration.buildSessionFactory();
	}

	public static DAOFactory get() {
		if (instance == null) {
			instance = new DAOFactory();
		}

		return instance;
	}

	public UserDAO getUserDAO() {
		return new UserDAOImpl(sessionFactory);
	}
}
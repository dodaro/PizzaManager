package persistence;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class DatabaseHandler {

	private static SessionFactory factory;

	private static enum Operation {
		CREATE, UPDATE, DELETE
	};

	public DatabaseHandler() {
		factory = null;
	}

	private static void performOperation(Object object, Operation operation) {
		Session session = getSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();

			switch (operation) {
			case CREATE:
				session.save(object);
				break;
			case UPDATE:
				session.update(object);
				break;
			case DELETE:
				session.delete(object);
				break;
			}

			transaction.commit();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	public static void create(Object object) {
		performOperation(object, Operation.CREATE);
	}

	public static void delete(Object object) {
		performOperation(object, Operation.DELETE);
	}

	public static void update(Object object) {
		performOperation(object, Operation.UPDATE);
	}

	public static Pizzeria getPizzeria(Integer id) {
		Session session = getSession();
		String queryString = "from Pizzeria where id = :id_pizzeria";
		Query query = session.createQuery(queryString);
		query.setParameter("id_pizzeria", id);
		Pizzeria pizzeria = (Pizzeria) query.uniqueResult();
		session.close();
		return pizzeria;
	}

	private static void createSessionFactory() {
		if (factory != null)
			return;

		Configuration configuration = new Configuration().configure();
		factory = configuration.buildSessionFactory();
	}

	public static Session getSession() {
		if (factory == null)
			createSessionFactory();
		return factory.openSession();
	}
}
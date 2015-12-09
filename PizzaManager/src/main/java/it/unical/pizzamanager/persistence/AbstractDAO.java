package it.unical.pizzamanager.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public abstract class AbstractDAO {

	protected SessionFactory sessionFactory;
	
	protected enum Operation {
		CREATE, UPDATE, DELETE
	}

	public AbstractDAO(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	protected void performOperation(Object object, Operation operation) {
		Session session = sessionFactory.openSession();
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
}
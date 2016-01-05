package it.unical.pizzamanager.persistence.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class DatabaseHandler {
	
	public final static int NO_ID=-1;

	private SessionFactory sessionFactory;
	
	private static enum Operation {
		CREATE, UPDATE, DELETE
	};

	public DatabaseHandler() {
		sessionFactory = null;
	}
	
	private void performOperation(Object obj, Operation op) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();
			switch (op) {
			case CREATE:
				session.save(obj);
				break;
			case UPDATE:
				session.update(obj);
				break;
			case DELETE:
				session.delete(obj);
				break;
			}
			
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();			
		} finally {
			session.close();
		}
	}
	
	protected void create(Object object) {
		performOperation(object, Operation.CREATE);
	}
	
	protected void delete(Object object) {
		performOperation(object, Operation.DELETE);
	}
	
	protected void update(Object object) {
		performOperation(object, Operation.UPDATE);
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
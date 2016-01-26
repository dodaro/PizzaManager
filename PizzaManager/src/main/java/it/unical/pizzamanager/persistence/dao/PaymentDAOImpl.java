package it.unical.pizzamanager.persistence.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import it.unical.pizzamanager.persistence.entities.Payment;

public class PaymentDAOImpl implements PaymentDAO {	

private DatabaseHandler databaseHandler;
	
	PaymentDAOImpl() {
		databaseHandler = null;
	}
	
	@Override
	public void create(Payment payment) {
		databaseHandler.create(payment);
	}

	@Override
	public void update(Payment payment) {
		databaseHandler.update(payment);
	}

	@Override
	public void delete(Payment payment) {
		databaseHandler.delete(payment);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Payment> getAllPayments() {
		Session session = databaseHandler.getSessionFactory().openSession();
		List<Payment> all = (List<Payment>) session.createQuery("from Payment").list();
		return all;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Payment> getSaved(boolean saved) {
		Session session = databaseHandler.getSessionFactory().openSession();
		List<Payment> paymentRequested = (List<Payment>) session.createQuery("from Payment p where p.saved= "+saved).list();
		session.close();
		return paymentRequested;
	}

	@Override
	public Payment getPayment(Integer i) {
		Session session = databaseHandler.getSessionFactory().openSession();
		String queryString = "from Payment where id = :id_payment";
		Query query = session.createQuery(queryString);
		query.setParameter("id_payment", i);
		Payment p = (Payment) query.uniqueResult();
		session.close();
		return p;
	}

	@Override
	public Long numberOfPayment() {
		Session session = databaseHandler.getSessionFactory().openSession();
		Long size = (Long) session.createQuery("select count(*) from Payment").uniqueResult();
		session.close();
		return size;
	}

	public DatabaseHandler getDatabaseHandler() {
		return databaseHandler;
	}

	public void setDatabaseHandler(DatabaseHandler databaseHandler) {
		this.databaseHandler = databaseHandler;
	}
	
}
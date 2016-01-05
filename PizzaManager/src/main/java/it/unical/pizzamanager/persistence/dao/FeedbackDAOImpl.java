package it.unical.pizzamanager.persistence.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import it.unical.pizzamanager.persistence.dto.Feedback;

public class FeedbackDAOImpl implements FeedbackDAO
{

private DatabaseHandler databaseHandler;
	
	FeedbackDAOImpl() {
		databaseHandler = null;
	}
	
	@Override
	public void delete(Feedback feedback) {
		databaseHandler.delete(feedback);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Feedback> getAllFeedbacks() {
		Session session = databaseHandler.getSessionFactory().openSession();
		List<Feedback> all = (List<Feedback>) session.createQuery("from Feedback").list();
		session.close();
		return all;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Feedback> getQualityMoreThan(Integer i) {
		Session session = databaseHandler.getSessionFactory().openSession();
		List<Feedback> all = (List<Feedback>) session.createQuery("from Feedback f where f.qualityRating >"+i).list();
		session.close();
		return all;
	}

	@Override
	public Feedback getFeedback(Integer i) {
		Session session = databaseHandler.getSessionFactory().openSession();
		String queryString = "from Feedback f where f.codice = :id_feedback";
		Query query = session.createQuery(queryString);
		query.setParameter("id_feedback", i);
		Feedback f = (Feedback) query.uniqueResult();
		session.close();
		return f;
	}

	@Override
	public void create(Feedback feedback) {
		databaseHandler.create(feedback);
	}

	@Override
	public Long numberOfFeedback() {
		Session session = databaseHandler.getSessionFactory().openSession();
		Long size = (Long) session.createQuery("select count(*) from Feedback").uniqueResult();
		session.close();
		return size;
	}

	@Override
	public void update(Feedback feedback) {
		databaseHandler.update(feedback);
		
	}
	
}
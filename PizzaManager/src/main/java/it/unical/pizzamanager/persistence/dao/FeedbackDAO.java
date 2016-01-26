package it.unical.pizzamanager.persistence.dao;

import java.util.List;

import it.unical.pizzamanager.persistence.entities.Feedback;

public interface FeedbackDAO { ///add other methods!!!
	public void delete(Feedback feedback);

	public List<Feedback> getAllFeedbacks();

	public List<Feedback> getQualityMoreThan(Integer i);

	public Feedback getFeedback(Integer i);

	public void create(Feedback feedback);

	public Long numberOfFeedback();

	public void update(Feedback feedback);
}

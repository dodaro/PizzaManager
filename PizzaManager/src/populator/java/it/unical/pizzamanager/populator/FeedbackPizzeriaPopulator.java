package it.unical.pizzamanager.populator;

import org.springframework.context.ApplicationContext;

import it.unical.pizzamanager.persistence.dao.FeedbackDAO;
import it.unical.pizzamanager.persistence.dao.PizzeriaDAO;
import it.unical.pizzamanager.persistence.dao.UserDAO;
import it.unical.pizzamanager.persistence.entities.FeedbackPizzeria;
import it.unical.pizzamanager.persistence.entities.Pizzeria;
import it.unical.pizzamanager.persistence.entities.User;

public class FeedbackPizzeriaPopulator extends Populator {

	public FeedbackPizzeriaPopulator(ApplicationContext context) {
		super(context);
	}

	@Override
	protected void populate() {
		createFeedbackPizzeria();
	}

	private void createFeedbackPizzeria() {
		FeedbackDAO feedbackDAO = (FeedbackDAO) context.getBean("feedbackDAO");
		PizzeriaDAO pizzeriaDAO = (PizzeriaDAO) context.getBean("pizzeriaDAO");
		Pizzeria pizzeria = pizzeriaDAO.get("mail11@mail.com");
		UserDAO userDAO = (UserDAO) context.getBean("userDAO");
		User user = userDAO.get("mail4@mail.com");
		for (int i = 1; i <= 10; i++) {
			FeedbackPizzeria f = new FeedbackPizzeria();
			f.setPizzeria(pizzeria);
			f.setComment("bella bella buona"+i);
			f.setFastnessRating(3);
			f.setHospitalityRating(3);
			f.setQualityRating(3);
			f.setUser(user);
			feedbackDAO.create(f);
			/*User u = new User("mail" + i + "@mail.com", "password" + i);
			u.setName("User" + i);
			userDAO.create(u);*/
		}
	}
}
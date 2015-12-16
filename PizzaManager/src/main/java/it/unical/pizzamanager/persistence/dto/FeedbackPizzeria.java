package it.unical.pizzamanager.persistence.dto;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("pizzeria")
public class FeedbackPizzeria extends Feedback {

	private static final long serialVersionUID = 3624821961920881430L;

	@ManyToOne
	@JoinColumn(name = "pizzeria")
	private Pizzeria pizzeria;

	public FeedbackPizzeria() {
		super();
		this.pizzeria = new Pizzeria();
	}

	public FeedbackPizzeria(User user, Integer qualityRating, Integer fastnessRating,
			Integer hospitalityRating, String text, Pizzeria pizzeria) {
		super(user, qualityRating, fastnessRating, hospitalityRating, text);
		this.pizzeria = pizzeria;
	}

	public Pizzeria getPizzeria() {
		return pizzeria;
	}

	public void setPizzeria(Pizzeria pizzeria) {
		this.pizzeria = pizzeria;
	}
}
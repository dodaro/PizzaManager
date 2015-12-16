package it.unical.pizzamanager.persistence.dto;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "feedbacks_pizza")
public class FeedbackPizza extends Feedback {

	private static final long serialVersionUID = -3588967946171011566L;

	@ManyToOne
	@JoinColumn(name = "pizza")
	private Pizza pizza;

	public FeedbackPizza() {
		super();
		this.pizza = new Pizza();
	}

	public FeedbackPizza(User user, Integer qualityRating, Integer fastnessRating,
			Integer hospitalityRating, String text, Pizza pizza) {
		super(user, qualityRating, fastnessRating, hospitalityRating, text);
		this.pizza = pizza;
	}

	public Pizza getPizza() {
		return pizza;
	}

	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}
}

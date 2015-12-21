package it.unical.pizzamanager.persistence.dto;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("pizza")
public class FeedbackPizza extends Feedback {

	private static final long serialVersionUID = -3588967946171011566L;

	@ManyToOne
	@JoinColumn(name = "pizza")
	private Pizza pizza;

	public FeedbackPizza() {
		super();
		this.pizza = new Pizza();
	}

	public Pizza getPizza() {
		return pizza;
	}

	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}
}
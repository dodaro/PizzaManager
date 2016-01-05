package it.unical.pizzamanager.persistence.dto;

import javax.persistence.Column;
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

	@Column(name = "fastness_rating", nullable = false)
	private Integer fastnessRating;

	@Column(name = "hospitality_rating", nullable = false)
	private Integer hospitalityRating;

	public FeedbackPizzeria() {
		super();
		this.pizzeria = new Pizzeria();
	}

	public Pizzeria getPizzeria() {
		return pizzeria;
	}

	public void setPizzeria(Pizzeria pizzeria) {
		this.pizzeria = pizzeria;
	}

	public Integer getFastnessRating() {
		return fastnessRating;
	}

	public void setFastnessRating(Integer fastnessRating) {
		this.fastnessRating = fastnessRating;
	}

	public Integer getHospitalityRating() {
		return hospitalityRating;
	}

	public void setHospitalityRating(Integer hospitalityRating) {
		this.hospitalityRating = hospitalityRating;
	}
}
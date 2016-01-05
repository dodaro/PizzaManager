package it.unical.pizzamanager.persistence.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import it.unical.pizzamanager.persistence.dao.DatabaseHandler;

@Entity
@Table(name = "pizza_ingredient")
public class RelationPizzaIngredient implements Serializable {

	private static final long serialVersionUID = 656149294073781552L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "pizza")
	private Pizza pizza;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "ingredient")
	private Ingredient ingredient;

	public RelationPizzaIngredient() {
		id = DatabaseHandler.NO_ID;
		pizza = null;
		ingredient = null;
	}

	public RelationPizzaIngredient(Pizza pizza, Ingredient ingredient) {
		id = DatabaseHandler.NO_ID;
		this.pizza = pizza;
		this.ingredient = ingredient;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@JsonIgnore
	public Pizza getPizza() {
		return pizza;
	}

	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}

	@JsonIgnore
	public Ingredient getIngredient() {
		return ingredient;
	}

	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}
}

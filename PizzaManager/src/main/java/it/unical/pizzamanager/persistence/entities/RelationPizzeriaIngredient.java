package it.unical.pizzamanager.persistence.entities;

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
@Table(name = "pizzeria_ingredient")
public class RelationPizzeriaIngredient implements Serializable {

	private static final long serialVersionUID = -5615124192655120058L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "ingredient")
	private Ingredient ingredient;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "pizzeria")
	private Pizzeria pizzeria;

	@Column(name = "price")
	private Double price;

	public RelationPizzeriaIngredient() {
		this.id = DatabaseHandler.NO_ID;
		this.ingredient = null;
		this.pizzeria = null;
		this.price = 0.0;
	}
	
	public RelationPizzeriaIngredient(Pizzeria pizzeria,Ingredient ingredient, Double price) {
		this.id = DatabaseHandler.NO_ID;
		this.ingredient = ingredient;
		this.pizzeria = pizzeria;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@JsonIgnore
	public Ingredient getIngredient() {
		return ingredient;
	}

	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}

	@JsonIgnore
	public Pizzeria getPizzeria() {
		return pizzeria;
	}

	public void setPizzeria(Pizzeria pizzeria) {
		this.pizzeria = pizzeria;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
}
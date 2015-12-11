package it.unical.pizzamanager.persistence.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import it.unical.pizzamanager.persistence.dao.DatabaseHandler;

@Entity
@Table(name = "pizzeria_ingredient_price")
@SequenceGenerator(name = "pizzeria_ingredient_priceGenerator", sequenceName = "pizzeria_ingredient_priceSequence", initialValue = 1)
public class RelationPizzeriaIngredient implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5615124192655120058L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pizzeria_ingredient_priceGenerator")
	private int id;

	@ManyToOne
	@JoinColumn(name = "ingredient")
	private Ingredient ingredient;

	@Column(name = "price")
	private int price;

	public RelationPizzeriaIngredient() {
		id = DatabaseHandler.NO_ID;
		ingredient = new Ingredient();
		price = 0;
	}

	public RelationPizzeriaIngredient(Ingredient ingredient, int price) {
		this.id = DatabaseHandler.NO_ID;
		this.ingredient = ingredient;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Ingredient getIngredient() {
		return ingredient;
	}

	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}
}

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
@Table(name = "pizza_ingredient")
@SequenceGenerator(name="pizza_IngredientGenerator",sequenceName="pizza_IngredientSequence",initialValue=1)
public class RelationPizzaIngredient implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 656149294073781552L;


	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="pizza_IngredientGenerator")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "pizza")
	private Pizza pizza;

	@ManyToOne
	@JoinColumn(name = "ingredient")
	private Ingredient ingredient;

	public RelationPizzaIngredient() {
		id=DatabaseHandler.NO_ID;
		pizza=new Pizza();
		ingredient=new Ingredient();
	}
	

	public RelationPizzaIngredient(Pizza pizza,Ingredient ingredient) {
		this.id=DatabaseHandler.NO_ID;
		this.pizza=pizza;
		this.ingredient=ingredient;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Pizza getPizza() {
		return pizza;
	}


	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}


	public Ingredient getIngredient() {
		return ingredient;
	}


	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}
	
}

package persistence;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pizzaIngredients")
public class PizzaIngredient implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 656149294073781552L;

	@Id
	@Column(name = "code")
	private int code;

	@ManyToOne
	@JoinColumn(name = "pizza")
	private Pizza pizza;

	@ManyToOne
	@JoinColumn(name = "ingredient")
	private Ingredient ingredient;

	public PizzaIngredient() {
		code=0;
		pizza=new Pizza();
		ingredient=new Ingredient();
	}
	

	public PizzaIngredient(int code,Pizza pizza,Ingredient ingredient) {
		this.code=code;
		this.pizza=pizza;
		this.ingredient=ingredient;
	}


	public int getCode() {
		return code;
	}


	public void setCode(int code) {
		this.code = code;
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

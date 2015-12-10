package it.unical.pizzamanager.persistence.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ingredientPriceLists")
public class IngredientPriceList implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5615124192655120058L;
	
	@Id
	@Column(name = "id")
	private int id;

	@ManyToOne
	@JoinColumn(name = "ingredient")
	private Ingredient ingredient;

	@Column(name = "price")
	private int price;

	
	public IngredientPriceList() {
		id=1;
		ingredient=new Ingredient();
		price=0;
	}
	public IngredientPriceList(int id, Ingredient ingredient,int price) {
		this.id=id;
		this.ingredient=ingredient;
		this.price=price;
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

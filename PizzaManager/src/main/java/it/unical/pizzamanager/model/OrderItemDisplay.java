package it.unical.pizzamanager.model;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class OrderItemDisplay {

	int id;

	String pizzeria;

	String itemName;

	int number;

	double cost;

	String imageItem;
	
	private String ingredients;

	public OrderItemDisplay() {
		id = 0;
		pizzeria = "";
		itemName = "";
		number = 1;
		cost = 0;
		imageItem = "";
		ingredients="";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPizzeria() {
		return pizzeria;
	}

	public void setPizzeria(String pizzeria) {
		this.pizzeria = pizzeria;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public double getCost() {
		return cost;
	}

	public String getCostLabel() {
		NumberFormat formatter = new DecimalFormat("#0.00");
		return formatter.format(cost);
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public String getImageItem() {
		return imageItem;
	}

	public void setImageItem(String imageItem) {
		this.imageItem = imageItem;
	}

	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

	public String getTotal() {
		NumberFormat formatter = new DecimalFormat("#0.00");
		return formatter.format(number * cost);

	}

}

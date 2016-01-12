package it.unical.pizzamanager.forms;

import it.unical.pizzamanager.persistence.dto.Pizza.PizzaSize;

public class PizzeriaPizzaForm {

	private static final String TIME_SEPARATOR = ":";

	private String action;

	private int pizzaId;
	private PizzaSize size;
	private String preparationTime;
	private boolean glutenFree;
	private double price;

	public PizzeriaPizzaForm() {
		this.action = "";
		this.pizzaId = -1;
		this.size = PizzaSize.NORMAL;
		this.preparationTime = "00:00";
		this.glutenFree = false;
		this.price = 0.0;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public int getPizzaId() {
		return pizzaId;
	}

	public void setPizzaId(int pizzaId) {
		this.pizzaId = pizzaId;
	}

	public PizzaSize getSize() {
		return size;
	}

	public void setSize(PizzaSize size) {
		this.size = size;
	}

	public String getPreparationTime() {
		return preparationTime;
	}

	public Integer getPreparationTimeInSeconds() {
		String[] tokens = preparationTime.split(TIME_SEPARATOR);
		int minutes = Integer.parseInt(tokens[0]);
		int seconds = Integer.parseInt(tokens[1]);

		return 60 * minutes + seconds;
	}

	public void setPreparationTime(String preparationTime) {
		this.preparationTime = preparationTime;
	}

	public boolean getGlutenFree() {
		return glutenFree;
	}

	public void setGlutenFree(boolean glutenFree) {
		this.glutenFree = glutenFree;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}

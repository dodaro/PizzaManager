package it.unical.pizzamanager.forms;

import it.unical.pizzamanager.persistence.dao.DatabaseHandler;
import it.unical.pizzamanager.persistence.entities.Pizza.PizzaSize;

public class PizzeriaPizzaForm {

	private static final String TIME_SEPARATOR = ":";

	private String action;

	private Integer id;
	private Integer pizzaId;
	private PizzaSize size;
	private String preparationTime;
	private Boolean glutenFree;
	private Double price;

	public PizzeriaPizzaForm() {
		this.action = "";
		this.id = DatabaseHandler.NO_ID;
		this.pizzaId = DatabaseHandler.NO_ID;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPizzaId() {
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

	public Boolean getGlutenFree() {
		return glutenFree;
	}

	public void setGlutenFree(Boolean glutenFree) {
		this.glutenFree = glutenFree;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
}
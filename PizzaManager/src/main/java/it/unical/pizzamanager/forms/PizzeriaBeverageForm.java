package it.unical.pizzamanager.forms;

import it.unical.pizzamanager.persistence.dao.DatabaseHandler;

public class PizzeriaBeverageForm {

	private String action;

	private Integer id;
	private Integer beverageId;
	private Double price;

	public PizzeriaBeverageForm() {
		this.action = "";
		this.id = DatabaseHandler.NO_ID;
		this.beverageId = DatabaseHandler.NO_ID;
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

	public Integer getBeverageId() {
		return beverageId;
	}

	public void setBeverageId(Integer beverageId) {
		this.beverageId = beverageId;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
}
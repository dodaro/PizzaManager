package it.unical.pizzamanager.model;

import java.util.ArrayList;
import java.util.Date;

public class CartBooking {

	private String pizzeria;

	private ArrayList<OrderItemDisplay> items;

	private Date date;

	private int number;

	public CartBooking() {
		this.number=0;
		this.pizzeria = "";
		this.items = new ArrayList<>();
		this.date = null;
	}

	public String getPizzeria() {
		return pizzeria;
	}

	public void setPizzeria(String pizzeria) {
		this.pizzeria = pizzeria;
	}

	public ArrayList<OrderItemDisplay> getItems() {
		return items;
	}

	public void setItems(ArrayList<OrderItemDisplay> items) {
		this.items = items;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

}

package it.unical.pizzamanager.models;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;

public class CartBooking {

	private String pizzeria;

	private ArrayList<OrderItemDisplay> items;

	private Date date;

	private String identifier;

	private double total;

	public CartBooking() {
		this.identifier = "";
		this.pizzeria = "";
		this.items = new ArrayList<>();
		this.date = null;
		this.total = 0;
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

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String performTotal() {
		for (OrderItemDisplay orderItemDisplay : items) {
			this.total += (orderItemDisplay.getCost() * orderItemDisplay.getNumber());
		}
		NumberFormat formatter = new DecimalFormat("#0.00");
		return formatter.format(total);

	}

}

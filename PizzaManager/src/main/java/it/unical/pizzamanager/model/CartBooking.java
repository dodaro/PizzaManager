package it.unical.pizzamanager.model;

import java.util.Date;
import java.util.ArrayList;

import it.unical.pizzamanager.persistence.dto.BeverageOrderItem;
import it.unical.pizzamanager.persistence.dto.OrderItem;
import it.unical.pizzamanager.persistence.dto.PizzaOrderItem;
import it.unical.pizzamanager.persistence.dto.Pizzeria;

public class CartBooking {

	private int number;

	Pizzeria pizzeria;

	ArrayList<OrderItem> items;

	Date date;

	public CartBooking(int n) {
		this.number = n;
		this.pizzeria = null;
		this.items = new ArrayList<>();
	}

	public Pizzeria getPizzeria() {
		return pizzeria;
	}

	public void setPizzeria(Pizzeria pizzeria) {
		this.pizzeria = pizzeria;
	}

	public ArrayList<OrderItem> getItems() {
		return items;
	}

	public void setItems(ArrayList<OrderItem> items) {
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

	public String itemName(Integer id) {
		String name="not found";
		for (OrderItem item : items) {
			if (id == item.getId()) {
				if (item instanceof PizzaOrderItem) {
					name = ((PizzaOrderItem) item).getPizzeria_pizza().getPizza().getName();
				} else if (item instanceof BeverageOrderItem) {
					name = ((BeverageOrderItem) item).getPizzeria_beverage().getBeverage().getName();
				}
				break;
			}
		}
		return name;
	}
	
}

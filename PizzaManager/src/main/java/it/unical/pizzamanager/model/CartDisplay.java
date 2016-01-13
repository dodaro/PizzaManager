package it.unical.pizzamanager.model;

import java.util.ArrayList;
import java.util.List;

public class CartDisplay {

	private List<OrderItemDisplay> items;

	public CartDisplay() {
		items = new ArrayList<>();
	}

	public List<OrderItemDisplay> getItems() {
		return items;
	}

	public void setItems(List<OrderItemDisplay> items) {
		this.items = items;
	}
}

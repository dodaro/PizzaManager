package it.unical.pizzamanager.persistence.dao;

import java.util.List;

import it.unical.pizzamanager.persistence.dto.OrderItem;

public interface OrderItemDAO {
	
	public void create(OrderItem orderItem);

	public void delete(OrderItem orderItem);

	public void update(OrderItem orderItem);

	public List<OrderItem> get();

}

package it.unical.pizzamanager.persistence.dao;

import java.util.List;

import it.unical.pizzamanager.persistence.dto.BeverageOrderItem;
import it.unical.pizzamanager.persistence.dto.OrderItem;
import it.unical.pizzamanager.persistence.dto.PizzaOrderItem;

public interface OrderItemDAO {

	public void create(OrderItem orderItem);

	public void delete(OrderItem orderItem);

	public void update(OrderItem orderItem);

	public List<PizzaOrderItem> getOrderPizza();

	public List<BeverageOrderItem> getOrderBeverage();
}

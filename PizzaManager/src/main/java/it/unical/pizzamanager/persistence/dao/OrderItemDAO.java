package it.unical.pizzamanager.persistence.dao;

import java.util.List;

import it.unical.pizzamanager.persistence.entities.BeverageOrderItem;
import it.unical.pizzamanager.persistence.entities.OrderItem;
import it.unical.pizzamanager.persistence.entities.Pizza;
import it.unical.pizzamanager.persistence.entities.PizzaOrderItem;

public interface OrderItemDAO {

	public void create(OrderItem orderItem);

	public void delete(OrderItem orderItem);

	public void update(OrderItem orderItem);

	public List<PizzaOrderItem> getOrderPizza();
	
	public Integer getNumberOfOrderPizza(String pizza);

	public List<BeverageOrderItem> getOrderBeverage();

	public OrderItem getItem(Integer id);
}

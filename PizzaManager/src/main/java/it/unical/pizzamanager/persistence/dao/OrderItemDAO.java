package it.unical.pizzamanager.persistence.dao;

import java.util.List;

import com.sun.glass.ui.MenuItem;
import it.unical.pizzamanager.persistence.dto.BeverageItem;
import it.unical.pizzamanager.persistence.dto.OrderItem;
import it.unical.pizzamanager.persistence.dto.PizzaItem;

public interface OrderItemDAO {

	public void create(OrderItem orderItem);

	public void delete(OrderItem orderItem);

	public void update(OrderItem orderItem);

	public List<PizzaItem> getOrderPizza();

	public List<BeverageItem> getOrderBeverage();

	public List<MenuItem> getOrderMenu();

}

package it.unical.pizzamanager.persistence.dao;

import java.util.List;

import org.hibernate.Session;

import it.unical.pizzamanager.persistence.dto.BeverageOrderItem;
import it.unical.pizzamanager.persistence.dto.MenuOrderItem;
import it.unical.pizzamanager.persistence.dto.OrderItem;
import it.unical.pizzamanager.persistence.dto.PizzaOrderItem;

public class OrderItemDAOImpl implements OrderItemDAO {

	private DatabaseHandler databaseHandler;

	public OrderItemDAOImpl() {
		databaseHandler = null;
	}

	@Override
	public void create(OrderItem orderItem) {
		databaseHandler.create(orderItem);

	}

	@Override
	public void delete(OrderItem orderItem) {
		databaseHandler.delete(orderItem);

	}

	@Override
	public void update(OrderItem orderItem) {
		databaseHandler.update(orderItem);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PizzaOrderItem> getOrderPizza() {
		Session session = databaseHandler.getSessionFactory().openSession();
		String queryString = "from PizzaItem";
		List<PizzaOrderItem> pizzaItems = session.createQuery(queryString).list();
		return pizzaItems;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BeverageOrderItem> getOrderBeverage() {
		Session session = databaseHandler.getSessionFactory().openSession();
		String queryString = "from BeverageItem";
		List<BeverageOrderItem> beverageItems = session.createQuery(queryString).list();
		return beverageItems;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MenuOrderItem> getOrderMenu() {
		Session session = databaseHandler.getSessionFactory().openSession();
		String queryString = "from MenuItem";
		List<MenuOrderItem> menuItems = session.createQuery(queryString).list();
		return menuItems;
	}

	public DatabaseHandler getDatabaseHandler() {
		return databaseHandler;
	}

	public void setDatabaseHandler(DatabaseHandler databaseHandler) {
		this.databaseHandler = databaseHandler;
	}

}

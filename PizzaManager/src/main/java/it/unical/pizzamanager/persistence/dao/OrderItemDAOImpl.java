package it.unical.pizzamanager.persistence.dao;

import java.util.List;

import org.hibernate.Session;

import com.sun.glass.ui.MenuItem;

import it.unical.pizzamanager.persistence.dto.BeverageItem;
import it.unical.pizzamanager.persistence.dto.OrderItem;
import it.unical.pizzamanager.persistence.dto.PizzaItem;

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
	public List<PizzaItem> getOrderPizza() {
		Session session=databaseHandler.getSessionFactory().openSession();
		String queryString = "from PizzaItem";
		List<PizzaItem> pizzaItems=session.createQuery(queryString).list();
		return pizzaItems;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BeverageItem> getOrderBeverage() {
		Session session=databaseHandler.getSessionFactory().openSession();
		String queryString = "from BeverageItem";
		List<BeverageItem> beverageItems=session.createQuery(queryString).list();
		return beverageItems;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MenuItem> getOrderMenu() {
		Session session=databaseHandler.getSessionFactory().openSession();
		String queryString = "from MenuItem";
		List<MenuItem> menuItems=session.createQuery(queryString).list();
		return menuItems;
	}
	
	public DatabaseHandler getDatabaseHandler() {
		return databaseHandler;
	}

	public void setDatabaseHandler(DatabaseHandler databaseHandler) {
		this.databaseHandler = databaseHandler;
	}

}

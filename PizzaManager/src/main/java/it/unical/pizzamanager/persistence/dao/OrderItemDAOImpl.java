package it.unical.pizzamanager.persistence.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import it.unical.pizzamanager.persistence.entities.BeverageOrderItem;
import it.unical.pizzamanager.persistence.entities.OrderItem;
import it.unical.pizzamanager.persistence.entities.Pizza;
import it.unical.pizzamanager.persistence.entities.PizzaOrderItem;

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
		session.close();
		return pizzaItems;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer getNumberOfOrderPizza(String name) {
		Session session = databaseHandler.getSessionFactory().openSession();
		Query query = session.createQuery("from Pizza where name = :name");
		query.setParameter("name", name);
		List<PizzaOrderItem> pizzaItems = (List<PizzaOrderItem>) query.list();
		int result = pizzaItems.size();
		session.close();
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<BeverageOrderItem> getOrderBeverage() {
		Session session = databaseHandler.getSessionFactory().openSession();
		String queryString = "from BeverageItem";
		List<BeverageOrderItem> beverageItems = session.createQuery(queryString).list();
		session.close();
		return beverageItems;
	}

	public DatabaseHandler getDatabaseHandler() {
		return databaseHandler;
	}

	public void setDatabaseHandler(DatabaseHandler databaseHandler) {
		this.databaseHandler = databaseHandler;
	}

	@Override
	public OrderItem getItem(Integer id) {
		Session session = databaseHandler.getSessionFactory().openSession();
		String queryString = "select * from order_items where id = :itemId";
		OrderItem item = (OrderItem) session.createSQLQuery(queryString).addEntity(OrderItem.class)
				.setParameter("itemId", id).uniqueResult();
		session.close();
		return item;
	}

}

package it.unical.pizzamanager.persistence.dao;

import java.util.List;

import org.hibernate.Session;

import it.unical.pizzamanager.persistence.dto.OrderItem;

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
	public List<OrderItem> get() {
		Session session = databaseHandler.getSessionFactory().openSession();
		List<OrderItem> orderItems = session.createSQLQuery("Select * from order_Items").addEntity(OrderItem.class)
				.list();
		session.close();
		return orderItems;
	}

	public DatabaseHandler getDatabaseHandler() {
		return databaseHandler;
	}

	public void setDatabaseHandler(DatabaseHandler databaseHandler) {
		this.databaseHandler = databaseHandler;
	}

}

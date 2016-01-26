package it.unical.pizzamanager.persistence.dao;

import org.hibernate.Query;
import org.hibernate.Session;

import it.unical.pizzamanager.persistence.entities.Cart;
import it.unical.pizzamanager.persistence.entities.OrderItem;
import it.unical.pizzamanager.persistence.entities.User;

public class CartDAOImpl implements CartDAO {

	private DatabaseHandler databaseHandler;

	CartDAOImpl() {
		databaseHandler = null;
	}

	@Override
	public void create(Cart cart) {
		databaseHandler.create(cart);
	}

	@Override
	public void update(Cart cart) {
		databaseHandler.update(cart);
	}

	public void delete(Cart cart) {
		databaseHandler.delete(cart);
	};

	@Override
	public Cart getCart() {
		Session session = databaseHandler.getSessionFactory().openSession();

		Cart cart = (Cart) session.createSQLQuery("SELECT * FROM carts").addEntity(Cart.class);

		session.close();
		return cart;
	}

	@Override
	public Cart getUserCart(User user) {
		Session session = databaseHandler.getSessionFactory().openSession();

		Cart cart = (Cart) session.createSQLQuery("SELECT * from carts where user = :u").addEntity(Cart.class)
				.setParameter("u", user).uniqueResult();
		cart.getOrderItems().size();
		session.close();
		return cart;
	}

	public void setDatabaseHandler(DatabaseHandler databaseHandler) {
		this.databaseHandler = databaseHandler;
	}

	public DatabaseHandler getDatabaseHandler() {
		return databaseHandler;
	}
}

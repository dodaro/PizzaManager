package it.unical.pizzamanager.persistence.dao;


import org.hibernate.Session;

import it.unical.pizzamanager.persistence.dto.Cart;

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
	
	public void setDatabaseHandler(DatabaseHandler databaseHandler) {
		this.databaseHandler = databaseHandler;
	}
	
	public DatabaseHandler getDatabaseHandler() {
		return databaseHandler;
	}
}

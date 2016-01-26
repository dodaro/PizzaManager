package it.unical.pizzamanager.persistence.dao;

import it.unical.pizzamanager.persistence.entities.Cart;
import it.unical.pizzamanager.persistence.entities.User;

public interface CartDAO { ///add other methods!!!
	public void delete(Cart cart);

	public Cart getCart();

	public void create(Cart cart);

	public void update(Cart cart);
	
	public Cart getUserCart(User user);
}

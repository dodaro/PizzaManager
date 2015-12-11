package it.unical.pizzamanager.persistence.dao;

import it.unical.pizzamanager.persistence.dto.Cart;

public interface CartDAO { ///add other methods!!!
	public void delete(Cart cart);

	public Cart getCart();

	public void create(Cart cart);

	public void update(Cart cart);
}

package it.unical.pizzamanager.persistence.dao;

import java.util.List;

import it.unical.pizzamanager.persistence.dto.Favourites;
import it.unical.pizzamanager.persistence.dto.Feedback;
import it.unical.pizzamanager.persistence.dto.Payment;
import it.unical.pizzamanager.persistence.dto.User;


public interface UserDAO {

	public void create(User user);
	
	public void delete(User user);
	
	public void update(User user);
	
	public int numberOfUsers();

	public List<User> getUsers();

	public List<Payment> getPayments(User user);

	public List<Feedback> getFeedbacks(User user);
	
	public List<Favourites> getFavourites(User user);
	
	public User getById(Integer id);

	public User getByUsername(String username);

}

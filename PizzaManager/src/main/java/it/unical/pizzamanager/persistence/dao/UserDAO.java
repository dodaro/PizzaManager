package it.unical.pizzamanager.persistence.dao;

import java.util.List;


import it.unical.pizzamanager.persistence.dto.Booking;

import it.unical.pizzamanager.persistence.dto.User;

public interface UserDAO {

	public void create(User user);

	public void delete(User user);

	public void update(User user);

	public User get(Integer id);

	public User get(String email);

	public User getByUsername(String username);

	public List<User> getAll();

	public Booking getBooking(int bookingId, Integer id);

}

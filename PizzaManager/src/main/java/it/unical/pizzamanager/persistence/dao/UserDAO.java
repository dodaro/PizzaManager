package it.unical.pizzamanager.persistence.dao;

import java.util.List;

import it.unical.pizzamanager.persistence.dto.User;

public interface UserDAO {

	public void create(User user);

	public void delete(User user);

	public void update(User user);

	public User get(Integer id);

	public User get(String username);

	public List<User> getAllUsers();
}

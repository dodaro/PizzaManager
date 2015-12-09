package it.unical.pizzamanager.persistence.dao;

import java.util.List;

import it.unical.pizzamanager.persistence.dto.User;

public interface UserDAO {

	public void create(User user);

	public List<User> get();
}

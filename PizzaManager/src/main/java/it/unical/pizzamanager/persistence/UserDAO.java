package it.unical.pizzamanager.persistence;

import java.util.List;

public interface UserDAO {

	public void create(User user);

	public List<User> get();
}

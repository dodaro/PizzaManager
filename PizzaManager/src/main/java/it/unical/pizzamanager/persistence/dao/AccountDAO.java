package it.unical.pizzamanager.persistence.dao;

import java.util.List;

import it.unical.pizzamanager.persistence.entities.Account;

public interface AccountDAO {
	public Account get(Integer id);

	public Account get(String email);

	public List<Account> getAll();
}

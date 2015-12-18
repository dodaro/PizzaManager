package it.unical.pizzamanager.persistence.dao;

import java.util.List;

import it.unical.pizzamanager.persistence.dto.Customer;

public interface CustomerDAO {

	public void create(Customer customer);

	public void delete(Customer customer);

	public void update(Customer customer);

	public Customer get(Integer id);

	public List<Customer> getAllCustomers();
}

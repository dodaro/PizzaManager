package it.unical.pizzamanager.persistence.dao;

import java.util.List;

import it.unical.pizzamanager.persistence.dto.Address;


public interface AddressDAO {

	public void create(Address address);

	public void delete(Address address);

	public void update(Address address);

	public Address get(Integer id);
	
	public List<Address> getAll();
}

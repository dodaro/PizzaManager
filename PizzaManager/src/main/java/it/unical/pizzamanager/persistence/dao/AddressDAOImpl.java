package it.unical.pizzamanager.persistence.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import it.unical.pizzamanager.persistence.dto.Address;

public class AddressDAOImpl implements AddressDAO{
	private DatabaseHandler databaseHandler;

	public AddressDAOImpl() {
		databaseHandler = null;
	}

	@Override
	public void create(Address address) {
		databaseHandler.create(address);
	}

	@Override
	public void delete(Address address) {
		databaseHandler.delete(address);
	}

	@Override
	public void update(Address address) {
		databaseHandler.update(address);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Address> getAll() {
		Session session = databaseHandler.getSessionFactory().openSession();
		List<Address> addresses = (List<Address>) session.createQuery("from Address").list();
		session.close();
		return addresses;
	}

	public DatabaseHandler getDatabaseHandler() {
		return databaseHandler;
	}

	public void setDatabaseHandler(DatabaseHandler databaseHandler) {
		this.databaseHandler = databaseHandler;
	}

	@Override
	public Address get(Integer id) {
		// TODO Auto-generated method stub
		Session session = databaseHandler.getSessionFactory().openSession();

		String queryString = "from Address where id = :id ";
		Query query = session.createQuery(queryString);
		query.setParameter("id", id);
		Address address = (Address) query.uniqueResult();
		session.close();
		return address;
	}
	
	@Override
	public Address get(String city, String street, Integer number) {
		// TODO Auto-generated method stub
		Session session = databaseHandler.getSessionFactory().openSession();

		String queryString = "from Address where city = :city  and street = :street and number = :number  ";
		Query query = session.createQuery(queryString);
		query.setParameter("city", city);
		query.setParameter("street", street);
		query.setParameter("number", number);
		Address address = (Address) query.uniqueResult();
		session.close();
		return address;
	}
}

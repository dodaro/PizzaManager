package it.unical.pizzamanager.persistence.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import it.unical.pizzamanager.persistence.dto.Location;
import it.unical.pizzamanager.persistence.dto.Pizzeria;
import it.unical.pizzamanager.persistence.dto.RelationPizzeriaPizza;
import it.unical.pizzamanager.utils.geo.BoundingRectangle;
import it.unical.pizzamanager.utils.geo.Geolocalization;

public class PizzeriaDAOImpl implements PizzeriaDAO {

	private DatabaseHandler databaseHandler;

	public PizzeriaDAOImpl() {
		databaseHandler = null;
	}

	@Override
	public void create(Pizzeria pizzeria) {
		databaseHandler.create(pizzeria);
	}

	@Override
	public void delete(Pizzeria pizzeria) {
		databaseHandler.delete(pizzeria);
	}

	@Override
	public void update(Pizzeria pizzeria) {
		databaseHandler.update(pizzeria);
	}

	@Override
	public Pizzeria get(Integer id) {
		Session session = databaseHandler.getSessionFactory().openSession();
		Query query = session.createQuery("from Pizzeria where id = :id");
		query.setParameter("id", id);

		Pizzeria pizzeria = (Pizzeria) query.uniqueResult();
		init(pizzeria);

		session.close();
		return pizzeria;
	}

	@Override
	public Pizzeria get(String email) {
		Session session = databaseHandler.getSessionFactory().openSession();
		Query query = session.createQuery("from Pizzeria where email = :email");
		query.setParameter("email", email);

		Pizzeria pizzeria = (Pizzeria) query.uniqueResult();
		init(pizzeria);

		session.close();
		return pizzeria;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pizzeria> getAll() {
		Session session = databaseHandler.getSessionFactory().openSession();
		List<Pizzeria> pizzerias = session.createQuery("from Pizzeria").list();

		for (Pizzeria pizzeria : pizzerias) {
			init(pizzeria);
		}

		session.close();
		return pizzerias;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pizzeria> getPizzeriasWithin(Location center, Double radius) {
		// Calculate BoundingRectangle;
		BoundingRectangle rectangle = Geolocalization.getBoundingRectangle(center, radius);
		
		Session session = databaseHandler.getSessionFactory().openSession();
		Query query = session.createQuery(
				"select pizzeria from Pizzeria as pizzeria where "
				+ "pizzeria.location.latitude <= :maxLatitude and "
				+ "pizzeria.location.latitude >= :minLatitude and "
				+ "pizzeria.location.longitude <= :maxLongitude and "
				+ "pizzeria.location.longitude >= :minLongitude");
		query.setParameter("minLatitude", rectangle.getMinLatitude());
		query.setParameter("maxLatitude", rectangle.getMaxLatitude());
		query.setParameter("minLongitude", rectangle.getMinLongitude());
		query.setParameter("maxLongitude", rectangle.getMaxLongitude());
		
		List<Pizzeria> pizzerias = (List<Pizzeria>) query.list();
		
		session.close();
		return pizzerias;
	}

	@Override
	public List<RelationPizzeriaPizza> getMenuPizze(Integer id) {
		// to write when remove init
		return null;
	}

	@Override
	public Pizzeria getByName(String name) {
		Session session = databaseHandler.getSessionFactory().openSession();
		Query query = session.createQuery("from Pizzeria where name = :name");
		query.setParameter("name", name);

		Pizzeria pizzeria = (Pizzeria) query.uniqueResult();

		session.close();
		return pizzeria;
	}

	private void init(Pizzeria pizzeria) {
		pizzeria.getBeveragesPriceList().size();
		pizzeria.getIngredientsPriceList().size();
		pizzeria.getPizzasPriceList().size();
		pizzeria.getBookings().size();
		pizzeria.getFeedbacks().size();
		pizzeria.getImages().size();

		pizzeria.getTables().size();
	}

	public DatabaseHandler getDatabaseHandler() {
		return databaseHandler;
	}

	public void setDatabaseHandler(DatabaseHandler databaseHandler) {
		this.databaseHandler = databaseHandler;
	}

}

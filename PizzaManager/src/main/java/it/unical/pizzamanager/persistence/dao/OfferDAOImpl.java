package it.unical.pizzamanager.persistence.dao;

import java.util.List;

import org.hibernate.Session;

import it.unical.pizzamanager.persistence.dto.Offer;

public class OfferDAOImpl implements OfferDAO {

	private DatabaseHandler databaseHandler;

	public OfferDAOImpl() {
		databaseHandler=null;
	}
	@Override
	public void create(Offer offer) {
		databaseHandler.create(offer);

	}

	@Override
	public void delete(Offer offer) {
		databaseHandler.delete(offer);

	}

	@Override
	public void update(Offer offer) {
		databaseHandler.update(offer);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Offer> get() {
		Session session = databaseHandler.getSessionFactory().openSession();
		List<Offer> offers = session.createSQLQuery("Select *from offers").addEntity(Offer.class).list();
		session.close();
		return offers;
	}
	public DatabaseHandler getDatabaseHandler() {
		return databaseHandler;
	}
	public void setDatabaseHandler(DatabaseHandler databaseHandler) {
		this.databaseHandler = databaseHandler;
	}

}

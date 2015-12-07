package persistence.DAO;

import java.util.List;

import org.hibernate.Session;

import persistence.Beverage;
import persistence.Offer;
import persistence.test.DBCUDHandler;

public class OfferDAOImpl implements OfferDAO {

	@Override
	public void create(Offer offer) {
		DBCUDHandler.create(offer);

	}

	@Override
	public void delete(Offer offer) {
		DBCUDHandler.delete(offer);

	}

	@Override
	public void update(Offer offer) {
		DBCUDHandler.update(offer);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Offer> get() {
		Session session = DBCUDHandler.getSession();
		List<Offer> offers = session.createSQLQuery("Select *from offers").addEntity(Offer.class).list();
		session.close();
		return offers;
	}

}

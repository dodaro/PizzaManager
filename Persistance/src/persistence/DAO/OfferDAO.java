package persistence.DAO;

import java.util.List;

import persistence.Offer;

public interface OfferDAO {

	
	public void create(Offer offer);

	public void delete(Offer offer);

	public void update(Offer offer);

	public List<Offer> get();
}

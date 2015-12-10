package it.unical.pizzamanager.persistence.dao;

import java.util.List;

import it.unical.pizzamanager.persistence.dto.Offer;

public interface OfferDAO {

	
	public void create(Offer offer);

	public void delete(Offer offer);

	public void update(Offer offer);

	public List<Offer> get();
}

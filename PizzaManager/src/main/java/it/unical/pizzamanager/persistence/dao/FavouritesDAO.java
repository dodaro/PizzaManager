package it.unical.pizzamanager.persistence.dao;

import java.util.List;

import it.unical.pizzamanager.persistence.dto.Favourites;

public interface FavouritesDAO { ///add other methods!!!
	public void delete(Favourites favourites);

	public List<Favourites> getAllFavourites();

	public Favourites getFavourites(Integer i);

	public void create(Favourites favourites);

	public Long numberOfFavourites();

	public void update(Favourites favourites);
}

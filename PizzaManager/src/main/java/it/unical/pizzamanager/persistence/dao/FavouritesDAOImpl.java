package it.unical.pizzamanager.persistence.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import it.unical.pizzamanager.persistence.dto.Favourites;

public class FavouritesDAOImpl implements FavouritesDAO
{

	private DatabaseHandler databaseHandler;
	
	FavouritesDAOImpl() {
		databaseHandler = null;
	}

	@Override
	public void create(Favourites favourites) {
		databaseHandler.create(favourites);
	}

	@Override
	public void update(Favourites favourites) {
		databaseHandler.update(favourites);
	}

	public void delete(Favourites favourites) {
		databaseHandler.delete(favourites);
	};
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Favourites> getAllFavourites() {
		Session session = databaseHandler.getSessionFactory().openSession();
		List<Favourites> all = (List<Favourites>) session.createSQLQuery("from Favourites").list();
		session.close();
		return all;
	}

	@Override
	public Favourites getFavourites(Integer i) {
		Session session = databaseHandler.getSessionFactory().openSession();
		String queryString = "from Favourites f where f.codice = :id_favourites";
		Query query = session.createQuery(queryString);
		query.setParameter("id_favourites", i);
		Favourites f = (Favourites) query.uniqueResult();
		session.close();	
		return f;
	}

	@Override
	public Long numberOfFavourites() {
		Session session = databaseHandler.getSessionFactory().openSession();
		Long size = (Long) session.createQuery("select count(*) from Favourites").uniqueResult();
		session.close();
		return size;		
	}

	public void setDatabaseHandler(DatabaseHandler databaseHandler) {
		this.databaseHandler = databaseHandler;
	}
	
	public DatabaseHandler getDatabaseHandler() {
		return databaseHandler;
	}
	
}
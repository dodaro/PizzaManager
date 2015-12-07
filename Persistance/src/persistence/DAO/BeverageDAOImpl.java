package persistence.DAO;

import java.util.List;

import org.hibernate.Session;

import persistence.Beverage;
import persistence.test.DBCUDHandler;

public class BeverageDAOImpl implements BeverageDAO {



	@Override
	public void create(Beverage beverage) {
		DBCUDHandler.create(beverage);
	}

	@Override
	public void delete(Beverage beverage) {
		DBCUDHandler.delete(beverage);
		
	}

	@Override
	public void update(Beverage beverage) {
		DBCUDHandler.update(beverage);
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Beverage> get() {
		Session session = DBCUDHandler.getSession();
		List<Beverage> bevereges = session.createSQLQuery("Select *from beverages").addEntity(Beverage.class).list();
		session.close();
		return bevereges;
	}
}

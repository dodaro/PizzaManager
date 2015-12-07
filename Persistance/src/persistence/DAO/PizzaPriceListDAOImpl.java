package persistence.DAO;

import java.util.List;

import org.hibernate.Session;

import persistence.Beverage;
import persistence.PizzaPriceList;
import persistence.test.DBCUDHandler;

public class PizzaPriceListDAOImpl implements PizzaPriceListDAO {

	@Override
	public void create(PizzaPriceList pizzaPriceList) {
		DBCUDHandler.create(pizzaPriceList);

	}

	@Override
	public void delete(PizzaPriceList pizzaPriceList) {
		DBCUDHandler.delete(pizzaPriceList);

	}

	@Override
	public void update(PizzaPriceList pizzaPriceList) {
		DBCUDHandler.update(pizzaPriceList);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PizzaPriceList> get() {
		Session session = DBCUDHandler.getSession();
		List<PizzaPriceList> pizzaPriceLists = session.createSQLQuery("Select *from pizzaPriceLists")
				.addEntity(PizzaPriceList.class).list();
		session.close();
		return pizzaPriceLists;
	}

}

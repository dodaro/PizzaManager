package persistence.DAO;

import java.util.List;

import org.hibernate.Session;

import persistence.Beverage;
import persistence.BeveragePriceList;
import persistence.test.DBCUDHandler;

public class BeveragePriceListDAOImpl implements BeveragePriceListDAO {

	@Override
	public void create(BeveragePriceList beveragePriceList) {
		DBCUDHandler.create(beveragePriceList);

	}

	@Override
	public void delete(BeveragePriceList beveragePriceList) {
		DBCUDHandler.delete(beveragePriceList);

	}

	@Override
	public void update(BeveragePriceList beveragePriceList) {
		DBCUDHandler.update(beveragePriceList);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BeveragePriceList> get() {
		Session session = DBCUDHandler.getSession();
		List<BeveragePriceList> beveragePriceLists = session.createSQLQuery("Select *from beveragePriceLists")
				.addEntity(BeveragePriceList.class).list();
		session.close();
		return beveragePriceLists;
	}

}

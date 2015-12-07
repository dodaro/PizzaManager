package persistence.DAO;

import java.util.List;

import org.hibernate.Session;

import persistence.MenuPriceList;
import persistence.test.DBCUDHandler;

public class MenuPriceListDAOImpl implements MenuPriceListDAO {

	@Override
	public void create(MenuPriceList menuPriceList) {
		DBCUDHandler.create(menuPriceList);

	}

	@Override
	public void delete(MenuPriceList menuPriceList) {
		DBCUDHandler.delete(menuPriceList);

	}

	@Override
	public void update(MenuPriceList menuPriceList) {
		DBCUDHandler.update(menuPriceList);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MenuPriceList> get() {
		Session session = DBCUDHandler.getSession();
		List<MenuPriceList> menuPriceLists = session.createSQLQuery("Select *from menuPriceLists")
				.addEntity(MenuPriceList.class).list();
		session.close();
		return menuPriceLists;
	}

}

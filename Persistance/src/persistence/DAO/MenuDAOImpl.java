package persistence.DAO;

import java.util.List;

import org.hibernate.Session;

import persistence.Menu;
import persistence.test.DBCUDHandler;

public class MenuDAOImpl implements MenuDAO {

	@Override
	public void create(Menu menu) {
		DBCUDHandler.create(menu);

	}

	@Override
	public void delete(Menu menu) {
		DBCUDHandler.delete(menu);

	}

	@Override
	public void update(Menu menu) {
		DBCUDHandler.update(menu);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Menu> get() {
		Session session = DBCUDHandler.getSession();
		List<Menu> menus = session.createSQLQuery("Select *from menus").addEntity(Menu.class).list();
		session.close();
		return menus;
	}

}

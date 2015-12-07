package persistence.DAO;

import java.util.List;

import org.hibernate.Session;

import persistence.Pizza;
import persistence.test.DBCUDHandler;

public class PizzaDAOImpl implements PizzaDAO {

	@Override
	public void create(Pizza pizza) {
		DBCUDHandler.create(pizza);
	}

	@Override
	public void delete(Pizza pizza) {
	DBCUDHandler.delete(pizza);

	}

	@Override
	public void update(Pizza pizza) {
		DBCUDHandler.update(pizza);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pizza> get() {
		Session session = DBCUDHandler.getSession();
		List<Pizza> pizze = session.createSQLQuery("Select *from pizze").addEntity(Pizza.class).list();
		session.close();
		return pizze;
	}

}

package persistence.DAO;

import java.util.List;

import org.hibernate.Session;

import persistence.PizzaIngredient;
import persistence.test.DBCUDHandler;

public class PizzaIngredientDAOImpl implements PizzaIngredientDAO {

	@Override
	public void create(PizzaIngredient pizzaIngredient) {
		DBCUDHandler.create(pizzaIngredient);

	}

	@Override
	public void delete(PizzaIngredient pizzaIngredient) {
		DBCUDHandler.delete(pizzaIngredient);

	}

	@Override
	public void update(PizzaIngredient pizzaIngredient) {
		DBCUDHandler.update(pizzaIngredient);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PizzaIngredient> get() {
		Session session = DBCUDHandler.getSession();
		List<PizzaIngredient> pizzaIngredients = session.createSQLQuery("Select *from pizzaIngredients")
				.addEntity(PizzaIngredient.class).list();
		session.close();
		return pizzaIngredients;
	}

}

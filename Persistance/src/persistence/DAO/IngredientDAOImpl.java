package persistence.DAO;

import java.util.List;

import org.hibernate.Session;

import persistence.Ingredient;
import persistence.test.DBCUDHandler;

public class IngredientDAOImpl implements IngredientDAO {

	@Override
	public void create(Ingredient ingredient) {
		DBCUDHandler.create(ingredient);

	}

	@Override
	public void delete(Ingredient ingredient) {
		DBCUDHandler.delete(ingredient);

	}

	@Override
	public void update(Ingredient ingredient) {
		DBCUDHandler.update(ingredient);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ingredient> get() {
		Session session = DBCUDHandler.getSession();
		List<Ingredient> ingredients = session.createSQLQuery("Select *from ingredients").addEntity(Ingredient.class)
				.list();
		session.close();
		return ingredients;
	}

}

package persistence.DAO;

import java.util.List;

import org.hibernate.Session;

import persistence.IngredientPriceList;
import persistence.test.DBCUDHandler;

public class IngredientPriceListDAOImpl implements IngredientPriceListDAO {

	@Override
	public void create(IngredientPriceList ingredientPriceList) {
		DBCUDHandler.create(ingredientPriceList);

	}

	@Override
	public void delete(IngredientPriceList ingredientPriceList) {
		DBCUDHandler.delete(ingredientPriceList);

	}

	@Override
	public void update(IngredientPriceList ingredientPriceList) {
		DBCUDHandler.update(ingredientPriceList);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<IngredientPriceList> get() {
		Session session = DBCUDHandler.getSession();
		List<IngredientPriceList> ingredientPriceLists = session.createSQLQuery("Select *from ingredientPriceLists")
				.addEntity(IngredientPriceList.class).list();
		session.close();
		return ingredientPriceLists;
	}

}

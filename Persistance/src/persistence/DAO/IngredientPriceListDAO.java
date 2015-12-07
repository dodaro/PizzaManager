package persistence.DAO;

import java.util.List;

import persistence.IngredientPriceList;

public interface IngredientPriceListDAO {

	
	public void create(IngredientPriceList ingredientPriceList);

	public void delete(IngredientPriceList ingredientPriceList);

	public void update(IngredientPriceList ingredientPriceList);

	public List<IngredientPriceList> get();
}

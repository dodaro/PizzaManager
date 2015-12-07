package persistence.DAO;

import java.util.List;

import persistence.Ingredient;

public interface IngredientDAO {

	public void create(Ingredient ingredient);

	public void delete(Ingredient ingredient);

	public void update(Ingredient ingredient);

	public List<Ingredient> get();
}

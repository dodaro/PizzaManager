package persistence.DAO;

import java.util.List;

import persistence.PizzaIngredient;

public interface PizzaIngredientDAO {

	public void create(PizzaIngredient pizzaIngredient);

	public void delete(PizzaIngredient pizzaIngredient);

	public void update(PizzaIngredient pizzaIngredient);

	public List<PizzaIngredient> get();
}

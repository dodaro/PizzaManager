package persistence.DAO;

import java.util.List;

import persistence.Pizza;

public interface PizzaDAO {

	public void create(Pizza pizza);

	public void delete(Pizza pizza);

	public void update(Pizza pizza);

	public List<Pizza> get();
}

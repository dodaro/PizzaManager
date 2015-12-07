package persistence.DAO;

import java.util.List;

import persistence.Beverage;

public interface BeverageDAO {

	public void create(Beverage beverage);

	public void delete(Beverage beverage);

	public void update(Beverage beverage);

	public List<Beverage> get();

}

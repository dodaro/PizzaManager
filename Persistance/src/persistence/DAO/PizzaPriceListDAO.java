package persistence.DAO;

import java.util.List;

import persistence.PizzaPriceList;

public interface PizzaPriceListDAO {

	public void create(PizzaPriceList pizzaPriceList);

	public void delete(PizzaPriceList pizzaPriceList);

	public void update(PizzaPriceList pizzaPriceList);

	public List<PizzaPriceList> get();
}

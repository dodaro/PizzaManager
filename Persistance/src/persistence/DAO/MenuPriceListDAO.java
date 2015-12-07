package persistence.DAO;

import java.util.List;

import persistence.MenuPriceList;

public interface MenuPriceListDAO {

	public void create(MenuPriceList menuPriceList);

	public void delete(MenuPriceList menuPriceList);

	public void update(MenuPriceList menuPriceList);

	public List<MenuPriceList> get();
}

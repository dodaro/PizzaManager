package persistence.DAO;

import java.util.List;

import persistence.BeveragePriceList;

public interface BeveragePriceListDAO {

	public void create(BeveragePriceList beveragePriceList);

	public void delete(BeveragePriceList beveragePriceList);

	public void update(BeveragePriceList beveragePriceList);

	public List<BeveragePriceList> get();
}

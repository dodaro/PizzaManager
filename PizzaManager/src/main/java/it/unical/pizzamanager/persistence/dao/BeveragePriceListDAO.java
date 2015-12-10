package it.unical.pizzamanager.persistence.dao;

import java.util.List;

import it.unical.pizzamanager.persistence.dto.BeveragePriceList;

public interface BeveragePriceListDAO {

	public void create(BeveragePriceList beveragePriceList);

	public void delete(BeveragePriceList beveragePriceList);

	public void update(BeveragePriceList beveragePriceList);

	public List<BeveragePriceList> get();
}

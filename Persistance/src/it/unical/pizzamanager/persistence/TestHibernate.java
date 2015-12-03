package it.unical.pizzamanager.persistence;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestHibernate {

	private BeverageDAO beverage;

	private List<Beverage> beverages;

	@BeforeClass
	public void initDB() {
		beverage=new BeverageDAO();
		beverages=new ArrayList<Beverage>();
		for (int i = 0; i < 10; i++) {
			Beverage b = new Beverage(i, "name" + i, "brand" + i);
			beverages.add(b);
			beverage.create(b);
		}

	}


	@Test
	public void createBeverege() {
		Beverage b = new Beverage(11, "shs", "bsss");
		beverage.create(b);
		assertEquals(new Integer(11), (Integer) beverage.get().size());
	}
}

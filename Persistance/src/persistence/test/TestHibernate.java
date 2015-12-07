package persistence.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import persistence.Beverage;
import persistence.DAO.BeverageDAO;
import persistence.DAO.BeverageDAOImpl;

public class TestHibernate {

	private static BeverageDAO beverage;

	private static List<Beverage> beverages;

	@BeforeClass
	public static void initDB() {
		beverage = new BeverageDAOImpl();
		beverages = new ArrayList<Beverage>();
		for (int i = 0; i < 10; i++) {
			Beverage b = new Beverage(i, "name" + i, "brand" + i, "glass", Beverage.SMALL_SIZE, "importata");
			beverages.add(b);
			beverage.create(b);
		}

	}

	@Test
	public void createBeverege() {
		Beverage b = new Beverage(11, "name" +11, "brand" + 11, "glass", Beverage.SMALL_SIZE, "importata");
		beverage.create(b);
		assertEquals(new Integer(11), (Integer) beverage.get().size());
	}
}

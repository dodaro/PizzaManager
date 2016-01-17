package it.unical.pizzamanager.tests;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import it.unical.pizzamanager.persistence.dao.PizzeriaDAO;
import it.unical.pizzamanager.persistence.dto.Location;
import it.unical.pizzamanager.persistence.dto.Pizzeria;
import it.unical.pizzamanager.utils.geo.Geolocalization;

@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class })
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:**/WEB-INF/spring/root-context.xml" })
public class LocationTests {

	@Autowired
	private ApplicationContext context;

	@Test
	public void distanceTest() {
		PizzeriaDAO pizzeriaDAO = (PizzeriaDAO) context.getBean("pizzeriaDAO");

		Location center = new Location(39.3496982, 16.240710699999998);

		for (int i = 11; i <= 20; i++) {
			Pizzeria pizzeria = pizzeriaDAO.get(i);
			System.out.println("Distance with pizzeria " + pizzeria.getName() + " is "
					+ Geolocalization.greatCircleDistance(center, pizzeria.getLocation()));
		}

		Double radius = 6.0;

		List<Pizzeria> pizzeriasNearby = pizzeriaDAO.getPizzeriasWithin(center, radius);
		for (Pizzeria p : pizzeriasNearby) {
			System.out.println(p.getName() + " is within " + radius + " km");
		}
	}
}
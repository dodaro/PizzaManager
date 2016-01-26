package it.unical.pizzamanager.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import it.unical.pizzamanager.persistence.dao.PizzeriaDAO;
import it.unical.pizzamanager.persistence.dao.UserDAO;
import it.unical.pizzamanager.persistence.entities.Pizzeria;
import it.unical.pizzamanager.persistence.entities.RelationPizzeriaPizza;

@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class })
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:**/WEB-INF/spring/root-context.xml" })
public class GenericTests {

	@Autowired
	private ApplicationContext context;

	@Test
	public void numberOfUsers() {
		UserDAO userDAO = (UserDAO) context.getBean("userDAO");
		assertEquals(10, userDAO.getAll().size());
	}

	@Test
	public void pizzeriasTest() {
		PizzeriaDAO pizzeriaDAO = (PizzeriaDAO) context.getBean("pizzeriaDAO");
		Pizzeria pizzeria = pizzeriaDAO.get(11);
		RelationPizzeriaPizza margheritaPrice = null;

		for (RelationPizzeriaPizza r : pizzeria.getPizzasPriceList()) {
			if (r.getPizza().getName().equals("Margherita")) {
				margheritaPrice = r;
			}
		}

		if (margheritaPrice != null) {
			System.out.println("Margherita price is " + margheritaPrice.getPrice());
		} else {
			System.out.println("This pizzeria has no Margherita.");
		}
	}
}
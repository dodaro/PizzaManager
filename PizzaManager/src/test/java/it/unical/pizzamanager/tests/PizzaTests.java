package it.unical.pizzamanager.tests;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import it.unical.pizzamanager.persistence.dao.PizzaDAO;
import it.unical.pizzamanager.persistence.dto.Ingredient.IngredientType;
import it.unical.pizzamanager.persistence.dto.Pizza;

@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class })
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:**/WEB-INF/spring/root-context.xml" })
public class PizzaTests {

	@Autowired
	private ApplicationContext context;

	@Test
	public void numberOfPizzas() {
		PizzaDAO pizzaDAO = (PizzaDAO) context.getBean("pizzaDAO");
		assertEquals(3, pizzaDAO.getAll().size());
	}

	@Test
	public void getMargherita() {
		PizzaDAO pizzaDAO = (PizzaDAO) context.getBean("pizzaDAO");
		assertEquals("Margherita", pizzaDAO.getByName("Margherita").get(0).getName());
	}

	@Test
	public void getPizzasWithMeat() {
		PizzaDAO pizzaDAO = (PizzaDAO) context.getBean("pizzaDAO");
		List<Pizza> pizzasWithMeat = pizzaDAO.getByIngredientType(IngredientType.MEAT);

		for (Pizza pizza : pizzasWithMeat) {
			System.out.println(pizza.getName());
		}

		assertEquals(2, pizzasWithMeat.size());
	}
}
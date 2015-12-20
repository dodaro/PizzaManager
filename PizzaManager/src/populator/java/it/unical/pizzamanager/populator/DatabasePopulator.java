package it.unical.pizzamanager.populator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DatabasePopulator {

	private ApplicationContext context;
	private List<Populator> populators;

	public DatabasePopulator() {
		context = new ClassPathXmlApplicationContext("file:**/WEB-INF/spring/root-context.xml");
		populators = new ArrayList<>();
		initPopulators();
	}

	private void initPopulators() {

		/*
		 * Add every new populator here. Be careful to put them in the right
		 * order.
		 */

		populators.add(new PersonPopulator(context));
		populators.add(new IngredientPopulator(context));
		populators.add(new PizzaPopulator(context));
		populators.add(new PizzeriaPopulator(context));
	}

	private void populateDatabase() {
		for (Populator populator : populators) {
			populator.populate();
		}
	}

	public static void main(String[] args) {
		new DatabasePopulator().populateDatabase();
	}
}

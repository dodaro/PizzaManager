package it.unical.pizzamanager.populator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DatabasePopulator {

	private ApplicationContext context;
	private List<Populator> populators;

	public DatabasePopulator() {
		context = new ClassPathXmlApplicationContext("file:**/WEB-INF/spring/root-context.xml");
		populators = new ArrayList<Populator>();
		initPopulators();
	}

	private void initPopulators() {

		/*
		 * Add every new populator here. Be careful to put them in the right order.
		 */

		populators.add(new UserPopulator(context));
		populators.add(new PizzeriaPopulator(context));
		populators.add(new IngredientPopulator(context));
		populators.add(new PizzaPopulator(context));
		populators.add(new BeveragePopulator(context));
		populators.add(new BookingPopulator(context));
		populators.add(new CartPopulator(context));
		populators.add(new FeedbackPizzeriaPopulator(context));
	}

	private void populateDatabase() {
		for (Populator populator : populators) {
			populator.populate();
		}
	}

	public static void main(String[] args) {
		/*
		 * Delete the existing database.
		 */
		File databaseFile = new File("database.mv.db");
		File traceFile = new File("database.trace.db");
		databaseFile.delete();
		traceFile.delete();

		System.out.println("Started populating.");
		new DatabasePopulator().populateDatabase();
		System.out.println("Done.");
		
		// Quick fix to avoid the automatic mail sender to keep the program running.
		System.exit(0);
	}
}

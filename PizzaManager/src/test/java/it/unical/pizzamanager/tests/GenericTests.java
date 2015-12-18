package it.unical.pizzamanager.tests;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import it.unical.pizzamanager.persistence.dao.CustomerDAO;
import it.unical.pizzamanager.persistence.dao.PizzaDAO;
import it.unical.pizzamanager.persistence.dao.UserDAO;
import it.unical.pizzamanager.persistence.dto.Customer;
import it.unical.pizzamanager.persistence.dto.Pizza;
import it.unical.pizzamanager.persistence.dto.Pizza.PizzaSize;
import it.unical.pizzamanager.persistence.dto.User;

@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class })
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:**/WEB-INF/spring/root-context.xml" })
public class GenericTests {

	@Autowired
	private ApplicationContext context;

	@Before
	public void init() {

		/*
		 * Attenzione! Questo metodo verrà chiamato prima dell'esecuzione di ogni singolo test, ciò
		 * vuol dire che gli User e Customer verranno creati (e poi eventualmente eliminati dal
		 * metodo delete()) prima di ogni test.
		 * 
		 * Ciò implica che, se si inseriscono ed eliminano utenti e customer prima e dopo ogni test,
		 * nel primo test i 10 User inseriti avranno id da 1 a 10, nel secondo test avranno id da 21
		 * a 30 (perché condividono gli stessi id di Customer) e via dicendo. Quindi state attenti
		 * quando fate query per id.
		 * 
		 * Oltre ad aggirare questo problema, ora c'è la necessità di capire per bene cosa viene
		 * effettivamente salvato sul database, quindi ripulire il database alla fine dei test non
		 * ha senso in quanto abbiamo interesse ad andare a vedere direttamente sul database
		 * (tramite la console di H2) cosa viene effettivamente creato o modificato dopo che i test
		 * vengono eseguiti.
		 * 
		 * Per cui, ho alterato un po' la procedura per popolare il database prima di ogni test. Il
		 * database non viene più ripulito alla fine di ogni test, e vengono creati nuovi utenti,
		 * customer, etc. solo se non ce ne sono già altri nel database.
		 * 
		 * Ricordate di eliminare il file del database prima dei test se volete ripartire da un
		 * database nuovo.
		 */

		UserDAO userDAO = (UserDAO) context.getBean("userDAO");
		if (userDAO.getAllUsers().isEmpty()) {
			createUsers(userDAO);
		}

		CustomerDAO customerDAO = (CustomerDAO) context.getBean("customerDAO");
		if (customerDAO.getAllCustomers().isEmpty()) {
			createCustomers(customerDAO);
		}

		PizzaDAO pizzaDAO = (PizzaDAO) context.getBean("pizzaDAO");
		if (pizzaDAO.getAllPizzas().isEmpty()) {
			createPizzas(pizzaDAO);
		}
	}

	/*
	 * Per il momento, non voglio che il database venga ripulito alla fine di ogni test, quindi
	 * commento l'annotazione.
	 */
	// @After
	public void delete() {
		CustomerDAO customerDAO = (CustomerDAO) context.getBean("customerDAO");
		UserDAO userDAO = (UserDAO) context.getBean("userDAO");

		List<User> users = userDAO.getAllUsers();
		List<Customer> customers = customerDAO.getAllCustomers();

		int size = users.size();

		for (int i = 0; i < size; i++) {
			userDAO.delete(users.get(i));
			customerDAO.delete(customers.get(i));
		}
	}

	@Test
	public void numberOfUsers() {
		UserDAO userDAO = (UserDAO) context.getBean("userDAO");
		assertEquals(10, userDAO.getAllUsers().size());
	}

	@Test
	public void numberOfCustomers() {
		CustomerDAO customerDAO = (CustomerDAO) context.getBean("customerDAO");
		assertEquals(10, customerDAO.getAllCustomers().size());
	}

	@Test
	public void numberOfPizzas() {
		PizzaDAO pizzaDAO = (PizzaDAO) context.getBean("pizzaDAO");
		assertEquals(2, pizzaDAO.getAllPizzas().size());
	}

	private void createUsers(UserDAO userDAO) {
		for (int i = 1; i <= 10; i++) {
			userDAO.create(new User("email" + i, "password" + i));
		}
	}

	private void createCustomers(CustomerDAO customerDAO) {
		/*
		 * Da 11 a 20 così, ad esempio, il Customer con id 13 avrà firstName "firstName13", poiché
		 * gli id sono condivisi tra User e Customer.
		 */
		for (int i = 11; i <= 20; i++) {
			customerDAO.create(new Customer("firstName" + i, "lastName" + i, "phoneNumber" + i));
		}
	}

	public void createPizzas(PizzaDAO pizzaDAO) {
		Pizza capricciosa = new Pizza("Capricciosa");
		Pizza margherita = new Pizza("Margherita", 3, false, "MargheritaDescription",
				PizzaSize.MAXI, false);
		pizzaDAO.create(capricciosa);
		pizzaDAO.create(margherita);
	}
}
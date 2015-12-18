package it.unical.pizzamanager.tests;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
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
import it.unical.pizzamanager.persistence.dao.UserDAO;
import it.unical.pizzamanager.persistence.dto.Customer;
import it.unical.pizzamanager.persistence.dto.User;

@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class })
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:**/WEB-INF/spring/root-context.xml" })
public class UserTests {

	@Autowired
	private ApplicationContext context;

	@Before
	public void init() {
		CustomerDAO customerDao = (CustomerDAO) context.getBean("customerDAO");
		UserDAO userDao = (UserDAO) context.getBean("userDAO");

		for (int i = 0; i < 2; i++) {
			userDao.create(new User("email" + i, "password" + i));
			customerDao.create(new Customer("first" + i, "last" + i, "phone" + i, 8));
		}
	}

	@After
	public void delete() {
		CustomerDAO customerDao = (CustomerDAO) context.getBean("customerDAO");
		UserDAO userDao = (UserDAO) context.getBean("userDAO");

		List<User> users = userDao.getAllUsers();
		List<Customer> customers = customerDao.getAllCustomers();

		int size = users.size();

		for (int i = 0; i < size; i++) {
			userDao.delete(users.get(i));
			customerDao.delete(customers.get(i));
		}
	}

	@Test
	public void testNumberOfCustomers() {
		CustomerDAO customerDao = (CustomerDAO) context.getBean("customerDAO");
		assertEquals(2, customerDao.getAllCustomers().size());
	}

	@Test
	public void testNumberOfUsers() {
		UserDAO userDao = (UserDAO) context.getBean("userDAO");
		assertEquals(2, userDao.getAllUsers().size());
	}
}
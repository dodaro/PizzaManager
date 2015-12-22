package it.unical.pizzamanager.persistence.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import it.unical.pizzamanager.persistence.dto.Ingredient.IngredientType;
import it.unical.pizzamanager.persistence.dto.Pizza;

public class PizzaDAOImpl implements PizzaDAO {

	private DatabaseHandler databaseHandler;

	public PizzaDAOImpl() {
		databaseHandler = null;
	}

	@Override
	public void create(Pizza pizza) {
		databaseHandler.create(pizza);
	}

	@Override
	public void delete(Pizza pizza) {
		databaseHandler.delete(pizza);

	}

	@Override
	public void update(Pizza pizza) {
		databaseHandler.update(pizza);
	}

	@Override
	public Pizza get(Integer id) {
		Session session = databaseHandler.getSessionFactory().openSession();
		Query query = session.createQuery("from Pizza where id = :id");
		query.setParameter("id", id);
		Pizza pizza = (Pizza) query.uniqueResult();
		session.close();
		return pizza;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pizza> getByName(String name) {
		Session session = databaseHandler.getSessionFactory().openSession();
		Query query = session.createQuery("from Pizza where name = :name");
		query.setParameter("name", name);
		List<Pizza> pizzas = (List<Pizza>) query.list();
		session.close();
		return pizzas;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pizza> getAll() {
		Session session = databaseHandler.getSessionFactory().openSession();
		List<Pizza> pizzas = (List<Pizza>) session.createQuery("from Pizza").list();
		if(pizzas!=null){
			for (Pizza pizza : pizzas) {
				if(pizza.getMenu() != null)
					System.out.println(pizza.getMenu().size());
				if(pizza.getOrderItems()  != null)
					System.out.println(pizza.getOrderItems().size());
				if(pizza.getPizzaIngredients()  != null)
					System.out.println(pizza.getPizzaIngredients().size());
				if(pizza.getPizzasPriceList()  != null)
					System.out.println(pizza.getPizzasPriceList().size());
			}
		}
		session.close();
		return pizzas;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pizza> getByIngredientType(IngredientType type) {
		Session session = databaseHandler.getSessionFactory().openSession();
		Query query = session.createQuery(
				"select pizza from Pizza as pizza " +
				"join pizza.pizzaIngredients as pizzaIngredients " +
				"where pizzaIngredients.ingredient.type = :type");
		query.setParameter("type", type);
		List<Pizza> pizzas = (List<Pizza>) query.list();
		session.close();
		return pizzas;
	}

	public DatabaseHandler getDatabaseHandler() {
		return databaseHandler;
	}

	public void setDatabaseHandler(DatabaseHandler databaseHandler) {
		this.databaseHandler = databaseHandler;
	}

}

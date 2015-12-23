package it.unical.pizzamanager.populator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;

import it.unical.pizzamanager.persistence.dao.BeverageDAO;
import it.unical.pizzamanager.persistence.dao.MenuDAO;
import it.unical.pizzamanager.persistence.dao.PizzaDAO;
import it.unical.pizzamanager.persistence.dao.PizzeriaDAO;
import it.unical.pizzamanager.persistence.dto.Beverage;
import it.unical.pizzamanager.persistence.dto.Menu;
import it.unical.pizzamanager.persistence.dto.Pizza;
import it.unical.pizzamanager.persistence.dto.Pizzeria;
import it.unical.pizzamanager.persistence.dto.RelationPizzeriaMenu;

public class MenuPopulator extends Populator {

	public MenuPopulator(ApplicationContext context) {
		super(context);
	}

	@Override
	protected void populate() {
		PizzeriaDAO pizzeriaDAO = (PizzeriaDAO) context.getBean("pizzeriaDAO");
		PizzaDAO pizzaDAO = (PizzaDAO) context.getBean("pizzaDAO");
		BeverageDAO beverageDAO = (BeverageDAO) context.getBean("beverageDAO");
		MenuDAO menuDAO = (MenuDAO) context.getBean("menuDAO");

		List<Pizza> pizzas = pizzaDAO.getAll();
		List<Beverage> beverages = beverageDAO.getAll();

		Menu menu0 = new Menu(pizzas.get(0), beverages.get(0));
		Menu menu1 = new Menu(pizzas.get(2), beverages.get(4));
		Menu menu2 = new Menu(pizzas.get(4), beverages.get(5));
		Menu menu3 = new Menu(pizzas.get(5), beverages.get(6));
		Menu menu4 = new Menu(pizzas.get(2), beverages.get(9));
		Menu menu5 = new Menu(pizzas.get(6), beverages.get(4));
		Menu menu6 = new Menu(pizzas.get(5), beverages.get(5));
		Menu menu7 = new Menu(pizzas.get(2), beverages.get(1));
		Menu menu8 = new Menu(pizzas.get(0), beverages.get(2));
		Menu menu9 = new Menu(pizzas.get(0), beverages.get(4));

		menuDAO.create(menu0);
		menuDAO.create(menu1);
		menuDAO.create(menu2);
		menuDAO.create(menu3);
		menuDAO.create(menu4);
		menuDAO.create(menu5);
		menuDAO.create(menu6);
		menuDAO.create(menu7);
		menuDAO.create(menu8);
		menuDAO.create(menu9);

		List<Pizzeria> pizzerias = pizzeriaDAO.getAll();

		// Pizzeria 0
		Pizzeria pizzeria0 = pizzerias.get(0);
		List<RelationPizzeriaMenu> priceList0 = new ArrayList<>();
		priceList0.add(new RelationPizzeriaMenu(pizzeria0, menu0, 5.50));
		priceList0.add(new RelationPizzeriaMenu(pizzeria0, menu1, 4.50));
		priceList0.add(new RelationPizzeriaMenu(pizzeria0, menu5, 3.50));

		// Pizzeria 1
		Pizzeria pizzeria1 = pizzerias.get(1);
		List<RelationPizzeriaMenu> priceList1 = new ArrayList<>();
		priceList1.add(new RelationPizzeriaMenu(pizzeria1, menu2, 4.00));
		priceList1.add(new RelationPizzeriaMenu(pizzeria1, menu4, 5.00));
		priceList1.add(new RelationPizzeriaMenu(pizzeria1, menu7, 5.50));

		// Pizzeria 2
		Pizzeria pizzeria2 = pizzerias.get(2);
		List<RelationPizzeriaMenu> priceList2 = new ArrayList<>();
		priceList2.add(new RelationPizzeriaMenu(pizzeria2, menu3, 4.00));
		priceList2.add(new RelationPizzeriaMenu(pizzeria2, menu5, 7.00));
		priceList2.add(new RelationPizzeriaMenu(pizzeria2, menu8, 6.00));
		priceList2.add(new RelationPizzeriaMenu(pizzeria2, menu9, 2.00));

		// Pizzeria 3
		Pizzeria pizzeria3 = pizzerias.get(3);
		List<RelationPizzeriaMenu> priceList3 = new ArrayList<>();
		priceList3.add(new RelationPizzeriaMenu(pizzeria3, menu0, 4.00));
		priceList3.add(new RelationPizzeriaMenu(pizzeria3, menu1, 6.00));
		priceList3.add(new RelationPizzeriaMenu(pizzeria3, menu5, 4.00));
		priceList3.add(new RelationPizzeriaMenu(pizzeria3, menu6, 5.00));

		// Pizzeria 4
		Pizzeria pizzeria4 = pizzerias.get(4);
		List<RelationPizzeriaMenu> priceList4 = new ArrayList<>();
		priceList4.add(new RelationPizzeriaMenu(pizzeria4, menu0, 4.00));
		priceList4.add(new RelationPizzeriaMenu(pizzeria4, menu1, 5.00));
		priceList4.add(new RelationPizzeriaMenu(pizzeria4, menu2, 3.00));
		priceList4.add(new RelationPizzeriaMenu(pizzeria4, menu6, 4.00));
		priceList4.add(new RelationPizzeriaMenu(pizzeria4, menu8, 5.50));

		pizzeria0.setMenusPriceList(priceList0);
		pizzeria1.setMenusPriceList(priceList1);
		pizzeria2.setMenusPriceList(priceList2);
		pizzeria3.setMenusPriceList(priceList3);
		pizzeria4.setMenusPriceList(priceList4);

		pizzeriaDAO.update(pizzeria0);
		pizzeriaDAO.update(pizzeria1);
		pizzeriaDAO.update(pizzeria2);
		pizzeriaDAO.update(pizzeria3);
		pizzeriaDAO.update(pizzeria4);
	}
}

package it.unical.pizzamanager.populator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;

import it.unical.pizzamanager.persistence.dao.BeverageDAO;
import it.unical.pizzamanager.persistence.dao.PizzeriaDAO;
import it.unical.pizzamanager.persistence.entities.Beverage;
import it.unical.pizzamanager.persistence.entities.Pizzeria;
import it.unical.pizzamanager.persistence.entities.RelationPizzeriaBeverage;
import it.unical.pizzamanager.persistence.entities.Beverage.BeverageContainer;
import it.unical.pizzamanager.persistence.entities.Beverage.BeverageSize;
import it.unical.pizzamanager.persistence.entities.Beverage.BeverageType;

public class BeveragePopulator extends Populator {

	public BeveragePopulator(ApplicationContext context) {
		super(context);
	}

	@Override
	protected void populate() {
		Beverage beverage0 = new Beverage("Sparkling Water", "Ferrarelle", BeverageContainer.BOTTLE,
				BeverageSize.LARGE, BeverageType.WATER);
		Beverage beverage1 = new Beverage("Coca Cola", "Coca Cola", BeverageContainer.CAN,
				BeverageSize.MEDIUM, BeverageType.SODA);
		Beverage beverage2 = new Beverage("Cirò Rosso", "Cirò", BeverageContainer.BOTTLE,
				BeverageSize.SMALL, BeverageType.WINE);
		Beverage beverage3 = new Beverage("Fanta", "Coca Cola", BeverageContainer.CAN,
				BeverageSize.LARGE, BeverageType.SODA);
		Beverage beverage4 = new Beverage("Still Water", "Levissima", BeverageContainer.BOTTLE,
				BeverageSize.MEDIUM, BeverageType.WATER);
		Beverage beverage5 = new Beverage("Peroni Bionda", "Peroni", BeverageContainer.DRAFT,
				BeverageSize.LARGE, BeverageType.BEER);
		Beverage beverage6 = new Beverage("Sparkling Water", "Lete", BeverageContainer.BOTTLE,
				BeverageSize.MEDIUM, BeverageType.WATER);
		Beverage beverage7 = new Beverage("Lemon Juice", "Schweppes", BeverageContainer.CAN,
				BeverageSize.LARGE, BeverageType.SODA);
		Beverage beverage8 = new Beverage("Heineken", "Heineken", BeverageContainer.BOTTLE,
				BeverageSize.LARGE, BeverageType.BEER);
		Beverage beverage9 = new Beverage("Coca Cola", "Coca Cola", BeverageContainer.DRAFT,
				BeverageSize.MEDIUM, BeverageType.SODA);

		BeverageDAO beverageDAO = (BeverageDAO) context.getBean("beverageDAO");
		beverageDAO.create(beverage0);
		beverageDAO.create(beverage1);
		beverageDAO.create(beverage2);
		beverageDAO.create(beverage3);
		beverageDAO.create(beverage4);
		beverageDAO.create(beverage5);
		beverageDAO.create(beverage6);
		beverageDAO.create(beverage7);
		beverageDAO.create(beverage8);
		beverageDAO.create(beverage9);

		// Add beverages to pizzerias.

		PizzeriaDAO pizzeriaDAO = (PizzeriaDAO) context.getBean("pizzeriaDAO");
		List<Pizzeria> pizzerias = pizzeriaDAO.getAll();

		// Pizzeria 0
		Pizzeria pizzeria0 = pizzerias.get(0);
		List<RelationPizzeriaBeverage> priceList0 = new ArrayList<>();
		priceList0.add(new RelationPizzeriaBeverage(pizzeria0, beverage0, 2.5));
		priceList0.add(new RelationPizzeriaBeverage(pizzeria0, beverage1, 1.5));
		priceList0.add(new RelationPizzeriaBeverage(pizzeria0, beverage4, 0.5));
		priceList0.add(new RelationPizzeriaBeverage(pizzeria0, beverage6, 1.0));
		priceList0.add(new RelationPizzeriaBeverage(pizzeria0, beverage7, 2.0));
		priceList0.add(new RelationPizzeriaBeverage(pizzeria0, beverage9, 2.0));

		// Pizzeria 1
		Pizzeria pizzeria1 = pizzerias.get(1);
		List<RelationPizzeriaBeverage> priceList1 = new ArrayList<>();
		priceList1.add(new RelationPizzeriaBeverage(pizzeria1, beverage1, 1.0));
		priceList1.add(new RelationPizzeriaBeverage(pizzeria1, beverage2, 0.5));
		priceList1.add(new RelationPizzeriaBeverage(pizzeria1, beverage3, 2.0));
		priceList1.add(new RelationPizzeriaBeverage(pizzeria1, beverage5, 3.0));
		priceList1.add(new RelationPizzeriaBeverage(pizzeria1, beverage6, 3.5));
		priceList1.add(new RelationPizzeriaBeverage(pizzeria1, beverage7, 2.0));
		priceList1.add(new RelationPizzeriaBeverage(pizzeria1, beverage8, 2.5));
		priceList1.add(new RelationPizzeriaBeverage(pizzeria1, beverage9, 3.0));

		// Pizzeria 2
		Pizzeria pizzeria2 = pizzerias.get(2);
		List<RelationPizzeriaBeverage> priceList2 = new ArrayList<>();
		priceList2.add(new RelationPizzeriaBeverage(pizzeria2, beverage0, 3.5));
		priceList2.add(new RelationPizzeriaBeverage(pizzeria2, beverage3, 4.0));
		priceList2.add(new RelationPizzeriaBeverage(pizzeria2, beverage4, 1.0));
		priceList2.add(new RelationPizzeriaBeverage(pizzeria2, beverage5, 0.5));
		priceList2.add(new RelationPizzeriaBeverage(pizzeria2, beverage7, 0.5));

		// Pizzeria 3
		Pizzeria pizzeria3 = pizzerias.get(3);
		List<RelationPizzeriaBeverage> priceList3 = new ArrayList<>();
		priceList3.add(new RelationPizzeriaBeverage(pizzeria3, beverage0, 1.0));
		priceList3.add(new RelationPizzeriaBeverage(pizzeria3, beverage2, 2.5));
		priceList3.add(new RelationPizzeriaBeverage(pizzeria3, beverage3, 3.5));
		priceList3.add(new RelationPizzeriaBeverage(pizzeria3, beverage4, 3.0));
		priceList3.add(new RelationPizzeriaBeverage(pizzeria3, beverage5, 4.0));
		priceList3.add(new RelationPizzeriaBeverage(pizzeria3, beverage6, 1.0));
		priceList3.add(new RelationPizzeriaBeverage(pizzeria3, beverage7, 4.0));
		priceList3.add(new RelationPizzeriaBeverage(pizzeria3, beverage9, 2.5));

		// Pizzeria 4
		Pizzeria pizzeria4 = pizzerias.get(4);
		List<RelationPizzeriaBeverage> priceList4 = new ArrayList<>();
		priceList4.add(new RelationPizzeriaBeverage(pizzeria4, beverage1, 1.0));
		priceList4.add(new RelationPizzeriaBeverage(pizzeria4, beverage3, 0.5));
		priceList4.add(new RelationPizzeriaBeverage(pizzeria4, beverage4, 2.5));
		priceList4.add(new RelationPizzeriaBeverage(pizzeria4, beverage5, 2.0));
		priceList4.add(new RelationPizzeriaBeverage(pizzeria4, beverage7, 2.0));
		priceList4.add(new RelationPizzeriaBeverage(pizzeria4, beverage8, 2.5));

		pizzeria0.setBeveragesPriceList(priceList0);
		pizzeria1.setBeveragesPriceList(priceList1);
		pizzeria2.setBeveragesPriceList(priceList2);
		pizzeria3.setBeveragesPriceList(priceList3);
		pizzeria4.setBeveragesPriceList(priceList4);

		pizzeriaDAO.update(pizzeria0);
		pizzeriaDAO.update(pizzeria1);
		pizzeriaDAO.update(pizzeria2);
		pizzeriaDAO.update(pizzeria3);
		pizzeriaDAO.update(pizzeria4);
	}
}

package it.unical.pizzamanager.utils;

import it.unical.pizzamanager.persistence.entities.BeverageOrderItem;
import it.unical.pizzamanager.persistence.entities.PizzaOrderItem;
import it.unical.pizzamanager.persistence.entities.Pizzeria;
import it.unical.pizzamanager.persistence.entities.RelationPizzaOrderItemIngredient;

public class OrderItemUtils {
	
	public static PizzaOrderItem setPizzaOrderCost(PizzaOrderItem pizzaOrder, Pizzeria pizzeria){
		
		Double pizzaBaseCost=pizzaOrder.getPizzeria_pizza().getPrice();
		Double ingredientAddedCost=0.0;
		Double ingredientRemovedCost=0.0;
		for (int i = 0; i < pizzaOrder.getPizzaOrderIngredients().size(); i++) {
			if(pizzaOrder.getPizzaOrderIngredients().get(i).getAdditive().equals(RelationPizzaOrderItemIngredient.ADDITION)){
				Integer idIngredient=pizzaOrder.getPizzaOrderIngredients().get(i).getIngredient().getId();
				for (int j = 0; j < pizzeria.getIngredientsPriceList().size(); j++) {
					if(pizzeria.getIngredientsPriceList().get(j).getIngredient().getId()==idIngredient){
						ingredientAddedCost+=pizzeria.getIngredientsPriceList().get(j).getPrice();
						break;
					}
				}
			}
			else if(pizzaOrder.getPizzaOrderIngredients().get(i).getAdditive().equals(RelationPizzaOrderItemIngredient.REMOVAL)){
				Integer idIngredient=pizzaOrder.getPizzaOrderIngredients().get(i).getIngredient().getId();
				for (int j = 0; j < pizzeria.getIngredientsPriceList().size(); j++) {
					if(pizzeria.getIngredientsPriceList().get(j).getIngredient().getId()==idIngredient){
						ingredientRemovedCost+=pizzeria.getIngredientsPriceList().get(j).getPrice();
						break;
					}
				}
			}
		}
		pizzaOrder.setCost((pizzaBaseCost+ingredientAddedCost-ingredientRemovedCost)*pizzaOrder.getNumber());
		return pizzaOrder;
	}
	
	public static BeverageOrderItem setBeverageOrderCost(BeverageOrderItem beverageOrder, Pizzeria pizzeria){
		beverageOrder.setCost(beverageOrder.getPizzeria_beverage().getPrice()*beverageOrder.getNumber());
		return beverageOrder;
	}
	
}

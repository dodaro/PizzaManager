package it.unical.pizzamanager.utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import it.unical.pizzamanager.persistence.entities.Pizza;

public class PizzaUtils {

	private Integer numberOccurrence;
	private Pizza pizza;
	
	public int getNumberOccurrence() {
		return numberOccurrence;
	}

	public void setNumberOccurrence(Integer numberOccurrence) {
		this.numberOccurrence = numberOccurrence;
	}

	public Pizza getPizza() {
		return pizza;
	}

	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}

	public PizzaUtils(Integer numberOccurrence, Pizza pizza) {
		super();
		this.numberOccurrence = numberOccurrence;
		this.pizza = pizza;
	}
	
		
}

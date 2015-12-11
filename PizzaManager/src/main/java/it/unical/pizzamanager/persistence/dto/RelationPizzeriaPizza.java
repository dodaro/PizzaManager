package it.unical.pizzamanager.persistence.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import it.unical.pizzamanager.persistence.dao.DatabaseHandler;

@Entity
@Table(name = "pizzeria_pizza_price")
@SequenceGenerator(name = "pizzeria_pizza_priceGenerator", sequenceName = "pizzeria_pizza_priceSequence", initialValue = 1)
public class RelationPizzeriaPizza implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6113816064968513550L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pizzeria_pizza_priceGenerator")
	private int id;

	@ManyToOne
	@JoinColumn(name = "pizza")
	private Pizza pizza;

	@Column(name = "price")
	private int price;

	public RelationPizzeriaPizza() {
		id = DatabaseHandler.NO_ID;
		pizza = new Pizza();
		price = 0;
	}

	public RelationPizzeriaPizza(Pizza pizza, int price) {
		this.id = DatabaseHandler.NO_ID;
		this.pizza = pizza;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Pizza getPizza() {
		return pizza;
	}

	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
}

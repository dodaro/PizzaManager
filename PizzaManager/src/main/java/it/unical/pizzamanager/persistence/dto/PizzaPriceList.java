package it.unical.pizzamanager.persistence.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pizzaPriceLists")
public class PizzaPriceList implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6113816064968513550L;

	@Id
	@Column(name = "id")
	private int id;

	@ManyToOne
	@JoinColumn(name = "pizza")
	private Pizza pizza;

	@Column(name = "price")
	private int price;

	public PizzaPriceList() {
		id=1;
		pizza=new Pizza();
		price=0;
	}
	public PizzaPriceList(int id, Pizza pizza,int price) {
		this.id=id;
		this.pizza=pizza;
		this.price=price;
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

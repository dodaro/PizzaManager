package it.unical.pizzamanager.persistence.dto;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("pizzaItem")
public class PizzaItem extends OrderItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5083273531033927404L;

	@ManyToOne
	@JoinColumn(name = "pizza")
	private Pizza pizza;

	@Column(name = "modified")
	private Boolean modified;
	
	
	public PizzaItem() {
		super();
		modified=false;
		pizza=new Pizza();
	}
	
	public PizzaItem(Pizza pizza,Boolean modified,Double cost) {
		super(cost);
		this.modified=modified;
		this.pizza=pizza;
	}

	public Boolean getModified() {
		return modified;
	}

	public void setModified(Boolean modified) {
		this.modified = modified;
	}

	public Pizza getPizza() {
		return pizza;
	}

	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}
	
}

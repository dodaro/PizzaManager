package it.unical.pizzamanager.persistence.dto;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("pizza")
public class PizzaOrderItem extends OrderItem {

	private static final long serialVersionUID = 8977364851663655249L;

	@ManyToOne
	@JoinColumn(name = "pizza")
	private Pizza pizza;

	@Column(name = "modified")
	private Boolean modified;

	public PizzaOrderItem() {
		super();
		pizza = new Pizza();
		modified = false;
	}

	//questo costruttore aggiunto da davide solo per fare alcune prove
	public PizzaOrderItem(Boolean modified, Double cost) {
		super(cost);
		this.modified = modified;
	}
	
	public PizzaOrderItem(Pizza pizza, Boolean modified, Double cost) {
		super(cost);
		this.pizza = pizza;
		this.modified = modified;
	}

	public Pizza getPizza() {
		return pizza;
	}
	
	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}

	public Boolean getModified() {
		return modified;
	}

	public void setModified(Boolean modified) {
		this.modified = modified;
	}
}
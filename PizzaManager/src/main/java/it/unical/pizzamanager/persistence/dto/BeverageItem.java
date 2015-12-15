package it.unical.pizzamanager.persistence.dto;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("beverageItem")
public class BeverageItem extends OrderItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3405750496555038143L;
	
	@ManyToOne
	@JoinColumn(name = "beverage")
	private Beverage beverage;
	
	public BeverageItem() {
		super();
		beverage=new Beverage();
	}
	
	public BeverageItem(Beverage beverage,Double cost) {
		super(cost);
		this.beverage=beverage;
	}

	public Beverage getBeverage() {
		return beverage;
	}

	public void setBeverage(Beverage beverage) {
		this.beverage = beverage;
	}
}

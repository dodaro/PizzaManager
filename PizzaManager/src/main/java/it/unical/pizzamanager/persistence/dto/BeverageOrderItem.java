package it.unical.pizzamanager.persistence.dto;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("beverage")
public class BeverageOrderItem extends OrderItem {

	private static final long serialVersionUID = 2386173235819801788L;

	public enum BeverageTemperature {
		ROOM, COLD, HOT
	}

	@ManyToOne
	@JoinColumn(name = "beverage")
	private Beverage beverage;

	@Column(name = "temperature")
	private BeverageTemperature temperature;

	public BeverageOrderItem() {
		super();
		beverage = new Beverage();
		temperature = BeverageTemperature.COLD;
	}

	public BeverageOrderItem(Beverage beverage, BeverageTemperature temperature, Double cost) {
		super(cost);
		this.beverage = beverage;
		this.temperature = temperature;
	}

	public Beverage getBeverage() {
		return beverage;
	}

	public void setBeverage(Beverage beverage) {
		this.beverage = beverage;
	}

	public BeverageTemperature getTemperature() {
		return temperature;
	}

	public void setTemperature(BeverageTemperature temperature) {
		this.temperature = temperature;
	}
}

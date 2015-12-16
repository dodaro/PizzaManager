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

	private static final String TEMPERATURE_ROOM = "room";
	private static final String TEMPERATURE_COLD = "cold";

	@ManyToOne
	@JoinColumn(name = "beverage")
	private Beverage beverage;

	@Column(name = "temperature")
	private String temperature;

	public BeverageOrderItem() {
		super();
		beverage = new Beverage();
		temperature = "";
	}

	public BeverageOrderItem(Beverage beverage, String temperature, Double cost) {
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

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
}

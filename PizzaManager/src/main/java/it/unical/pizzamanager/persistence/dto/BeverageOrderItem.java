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

	// come per la pizza
	@ManyToOne
	@JoinColumn(name = "pizzeria_beverage")
	private RelationPizzeriaBeverage pizzeria_beverage;

	@Column(name = "temperature")
	private BeverageTemperature temperature;

	public BeverageOrderItem() {
		super();
		pizzeria_beverage = new RelationPizzeriaBeverage();
		temperature = BeverageTemperature.COLD;
	}

	public RelationPizzeriaBeverage getPizzeria_beverage() {
		return pizzeria_beverage;
	}

	public void setPizzeria_beverage(RelationPizzeriaBeverage pizzeria_beverage) {
		this.pizzeria_beverage = pizzeria_beverage;
	}

	public BeverageTemperature getTemperature() {
		return temperature;
	}

	public void setTemperature(BeverageTemperature temperature) {
		this.temperature = temperature;
	}

	public String pizzeriaName() {
		return pizzeria_beverage.getPizzeria().getName();
	}

	public String beverageName() {
		return pizzeria_beverage.getBeverage().getName();
	}
}

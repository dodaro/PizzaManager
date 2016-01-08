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
	@JoinColumn(name = "beverage")
	private RelationPizzeriaBeverage beverage;

	@Column(name = "temperature")
	private BeverageTemperature temperature;

	public BeverageOrderItem() {
		super();
		beverage = new RelationPizzeriaBeverage();
		temperature = BeverageTemperature.COLD;
	}

	public RelationPizzeriaBeverage getBeverage() {
		return beverage;
	}

	public void setBeverage(RelationPizzeriaBeverage beverage) {
		this.beverage = beverage;
	}

	public BeverageTemperature getTemperature() {
		return temperature;
	}

	public void setTemperature(BeverageTemperature temperature) {
		this.temperature = temperature;
	}
}

package persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "beverage_order_items")
public class BeverageOrderItem extends OrderItem {

	private static final long serialVersionUID = 4086547954211294992L;

	public static final int TEMPERATURE_ROOM = 0;
	public static final int TEMPERATURE_COLD = 1;

	@ManyToOne
	@JoinColumn(name = "beverage")
	private Beverage beverage;

	@Column(name = "temperature")
	private Integer temperature;

	public BeverageOrderItem() {
		super();
		this.beverage = new Beverage();
		this.temperature = TEMPERATURE_ROOM;
	}

	public BeverageOrderItem(Order order, Beverage beverage, Integer temperature) {
		super(order);
		this.beverage = beverage;
		this.temperature = temperature;
	}

	public Beverage getBeverage() {
		return beverage;
	}

	public void setBeverage(Beverage beverage) {
		this.beverage = beverage;
	}

	public Integer getTemperature() {
		return temperature;
	}

	public void setTemperature(Integer temperature) {
		this.temperature = temperature;
	}
}
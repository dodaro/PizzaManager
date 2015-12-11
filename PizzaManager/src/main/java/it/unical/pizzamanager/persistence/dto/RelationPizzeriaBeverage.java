package it.unical.pizzamanager.persistence.dto;

import java.io.Serializable;

import javax.persistence.*;

import it.unical.pizzamanager.persistence.dao.DatabaseHandler;

@Entity
@Table(name = "pizzeria_beverage_price")
@SequenceGenerator(name="pizzeria_beverageGenerator",sequenceName="pizzeria_beverageSequence",initialValue=1)
public class RelationPizzeriaBeverage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7175661908750242983L;


	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="pizzeria_beverageGenerator")
	private int id;

	@ManyToOne
	@JoinColumn(name = "beverage")
	private Beverage beverage;

	@Column(name = "price")
	private int price;

	public RelationPizzeriaBeverage() {
		id=DatabaseHandler.NO_ID;
		beverage=null;
		price=0;
	}
	public RelationPizzeriaBeverage( Beverage beverage,int price) {
		this.id=DatabaseHandler.NO_ID;
		this.beverage=beverage;
		this.price=price;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Beverage getBeverage() {
		return beverage;
	}

	public void setBeverage(Beverage beverage) {
		this.beverage = beverage;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	
	
}

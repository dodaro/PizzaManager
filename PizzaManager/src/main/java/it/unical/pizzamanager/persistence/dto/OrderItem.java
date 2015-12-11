package it.unical.pizzamanager.persistence.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import it.unical.pizzamanager.persistence.dao.DatabaseHandler;

@Entity
@Table(name = "order_Items")
@SequenceGenerator(name = "order_ItemsGenerator", sequenceName = "order_ItemsSequence", initialValue = 1)
public class OrderItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2048189882587093175L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_ItemsGenerator")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "pizza")
	private Pizza pizza;
	
	@ManyToOne
	@JoinColumn(name = "menu")
	private Menu menu;
	
	@ManyToOne
	@JoinColumn(name = "beverage")
	private Beverage beverage;
	
	@Column(name="modified")
	private Boolean modified;
	
	@Column(name="cost")
	private Double cost;
	// instead of ha references add a boolean modified and the cost of item ordered so we can get cost of an order directly with a join 
	// without ask to table price, so ingredient cannot be ordered but a menu or a pizza can be modified cost is evalueted application side and stored in database at purchace time
	
	public OrderItem() {
		id=DatabaseHandler.NO_ID;
		pizza=new Pizza();
		menu=null;
		beverage=null;
		modified=false;
		cost=0.0;
	}

	public OrderItem(Pizza pizza,Menu menu,Beverage beverage,Boolean modified,Double cost){
		id=DatabaseHandler.NO_ID;
		this.pizza=pizza;
		this.beverage=beverage;
		this.menu=menu;
		this.modified=modified;
		this.cost=cost;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Pizza getPizza() {
		return pizza;
	}

	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public Beverage getBeverage() {
		return beverage;
	}

	public void setBeverage(Beverage beverage) {
		this.beverage = beverage;
	}

	public Boolean getModified() {
		return modified;
	}

	public void setModified(Boolean modified) {
		this.modified = modified;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}
}

package it.unical.pizzamanager.persistence.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import it.unical.pizzamanager.persistence.dao.DatabaseHandler;

@Entity
@Table(name = "pizzeria_beverage")
public class RelationPizzeriaBeverage implements Serializable {

	private static final long serialVersionUID = -7175661908750242983L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "pizzeria")
	private Pizzeria pizzeria;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "beverage")
	private Beverage beverage;

	@Column(name = "price")
	private Double price;

	/**
	 * OrderItems which contain a beverage.
	 */
	@OneToMany(mappedBy = "beverage", fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<BeverageOrderItem> orderItems;
	
	public RelationPizzeriaBeverage() {
		this.id = DatabaseHandler.NO_ID;
		this.pizzeria = null;
		this.beverage = null;
		this.price = 0.0;
		this.orderItems=new ArrayList<>();
	}

	public RelationPizzeriaBeverage(Pizzeria pizzeria, Beverage beverage, Double price) {
		this.id = DatabaseHandler.NO_ID;
		this.pizzeria = pizzeria;
		this.beverage = beverage;
		this.price = price;
		this.orderItems=new ArrayList<>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@JsonIgnore
	public Beverage getBeverage() {
		return beverage;
	}

	public void setBeverage(Beverage beverage) {
		this.beverage = beverage;
	}

	@JsonIgnore
	public Pizzeria getPizzeria() {
		return pizzeria;
	}

	public void setPizzeria(Pizzeria pizzeria) {
		this.pizzeria = pizzeria;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public List<BeverageOrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<BeverageOrderItem> orderItems) {
		this.orderItems = orderItems;
	}
}
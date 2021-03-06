package it.unical.pizzamanager.persistence.entities;

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
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import it.unical.pizzamanager.persistence.dao.DatabaseHandler;
import it.unical.pizzamanager.serializers.PizzeriaBeverageSerializer;

@JsonSerialize(using = PizzeriaBeverageSerializer.class)
@Entity
@Table(name = "pizzeria_beverage")
public class RelationPizzeriaBeverage implements Serializable {

	private static final long serialVersionUID = -7175661908750242983L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "pizzeria")
	private Pizzeria pizzeria;

	@ManyToOne
	@JoinColumn(name = "beverage")
	private Beverage beverage;

	@Column(name = "price")
	private Double price;

	@Column(name = "available")
	private Boolean available;

	/**
	 * OrderItems which contain a beverage.
	 */
	@OneToMany(mappedBy = "pizzeria_beverage", fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<BeverageOrderItem> orderItems;

	public RelationPizzeriaBeverage() {
		this.id = DatabaseHandler.NO_ID;
		this.pizzeria = null;
		this.beverage = null;
		this.price = 0.0;
		this.orderItems = new ArrayList<>();
		this.available = true;
	}

	public RelationPizzeriaBeverage(Pizzeria pizzeria, Beverage beverage, Double price) {
		this.id = DatabaseHandler.NO_ID;
		this.pizzeria = pizzeria;
		this.beverage = beverage;
		this.price = price;
		this.orderItems = new ArrayList<>();
		this.available = true;
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
	
	public Boolean getAvailable() {
		return available;
	}
	
	public void setAvailable(Boolean available) {
		this.available = available;
	}
}
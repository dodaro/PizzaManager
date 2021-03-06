package it.unical.pizzamanager.persistence.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
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
import it.unical.pizzamanager.persistence.entities.Pizza.PizzaSize;
import it.unical.pizzamanager.serializers.PizzeriaPizzaSerializer;

@JsonSerialize(using = PizzeriaPizzaSerializer.class)
@Entity
@Table(name = "pizzeria_pizza")
public class RelationPizzeriaPizza implements Serializable {

	private static final long serialVersionUID = -6113816064968513550L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne
	@JoinColumn(name = "pizzeria")
	private Pizzeria pizzeria;

	@ManyToOne
	@JoinColumn(name = "pizza")
	private Pizza pizza;

	@Column(name = "price")
	private Double price;

	@Column(name = "size")
	private PizzaSize pizzaSize;

	/**
	 * Preparation time in seconds.
	 */
	@Column(name = "preparation_time")
	private Integer preparationTime;

	@Column(name = "gluten_free", nullable = false)
	private Boolean glutenFree;

	@Column(name = "available", nullable = false)
	private Boolean available;

	@ElementCollection
	@CollectionTable(name = "images", joinColumns = @JoinColumn(name = "pizza_images") )
	private List<Image> images;

	@OneToMany(mappedBy = "pizzeria_pizza", fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<PizzaOrderItem> orderItems;

	public RelationPizzeriaPizza() {
		this.id = DatabaseHandler.NO_ID;
		this.pizzeria = null;
		this.pizza = null;
		this.price = 0.0;
		this.pizzaSize = PizzaSize.NORMAL;
		this.preparationTime = 0;
		this.glutenFree = false;
		this.images = new ArrayList<Image>();
		this.orderItems = new ArrayList<>();
		this.available = true;
	}

	public RelationPizzeriaPizza(Pizzeria pizzeria, Pizza pizza, Double price, PizzaSize pizzaSize,
			Integer preparationTime, Boolean glutenFree) {
		this.id = DatabaseHandler.NO_ID;
		this.pizzeria = pizzeria;
		this.pizza = pizza;
		this.price = price;
		this.pizzaSize = pizzaSize;
		this.preparationTime = preparationTime;
		this.glutenFree = glutenFree;
		this.images = new ArrayList<Image>();
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
	public Pizzeria getPizzeria() {
		return pizzeria;
	}

	public void setPizzeria(Pizzeria pizzeria) {
		this.pizzeria = pizzeria;
	}

	@JsonIgnore
	public Pizza getPizza() {
		return pizza;
	}

	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public PizzaSize getPizzaSize() {
		return pizzaSize;
	}

	public void setPizzaSize(PizzaSize pizzaSize) {
		this.pizzaSize = pizzaSize;
	}

	public Integer getPreparationTime() {
		return preparationTime;
	}

	public String getPreparationTimeString() {
		Integer minutes = preparationTime / 60;
		Integer seconds = preparationTime % 60;

		String minutesString = Integer.toString(minutes);
		String secondsString = Integer.toString(seconds);

		if (minutes < 9) {
			minutesString = "0" + minutesString;
		}

		if (seconds < 9) {
			secondsString = "0" + secondsString;
		}

		return minutesString + ":" + secondsString;
	}

	public void setPreparationTime(Integer preparationTime) {
		this.preparationTime = preparationTime;
	}

	public Boolean getGlutenFree() {
		return glutenFree;
	}

	public void setGlutenFree(Boolean glutenFree) {
		this.glutenFree = glutenFree;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public List<PizzaOrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<PizzaOrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	
	public Boolean getAvailable() {
		return available;
	}
	
	public void setAvailable(Boolean available) {
		this.available = available;
	}
}
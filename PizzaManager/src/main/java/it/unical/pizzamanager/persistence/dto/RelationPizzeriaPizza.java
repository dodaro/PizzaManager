package it.unical.pizzamanager.persistence.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import it.unical.pizzamanager.persistence.dao.DatabaseHandler;
import it.unical.pizzamanager.persistence.dto.Pizza.PizzaSize;

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

	@Column(name = "preparation_time")
	private Double preparationTime;

	@Column(name = "gluten_free", nullable = false)
	private Boolean glutenFree;

	@ElementCollection
	@CollectionTable(name = "images", joinColumns = @JoinColumn(name = "pizza_images") )
	private List<Image> images;

	public RelationPizzeriaPizza() {
		this.id = DatabaseHandler.NO_ID;
		this.pizzeria = null;
		this.pizza = null;
		this.price = 0.0;
		this.pizzaSize = PizzaSize.NORMAL;
		this.preparationTime = 0.0;
		this.glutenFree = false;
		this.images = new ArrayList<Image>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Pizzeria getPizzeria() {
		return pizzeria;
	}

	public void setPizzeria(Pizzeria pizzeria) {
		this.pizzeria = pizzeria;
	}

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

	public Double getPreparationTime() {
		return preparationTime;
	}

	public void setPreparationTime(Double preparationTime) {
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
}
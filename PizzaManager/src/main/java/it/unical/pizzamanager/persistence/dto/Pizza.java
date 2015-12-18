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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import it.unical.pizzamanager.persistence.dao.DatabaseHandler;

@Entity
@Table(name = "pizzas")
@SequenceGenerator(name = "pizzasGenerator", sequenceName = "pizzas_sequence", initialValue = 1)
public class Pizza implements Serializable {

	private static final long serialVersionUID = 6079186121128866192L;

	public enum PizzaSize {
		NORMAL, MAXI
	}

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pizzasGenerator")
	private Integer id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "preparation_time", nullable = false)
	private Integer preparationTime;

	@Column(name = "gluten_free")
	private boolean glutenFree;

	@Column(name = "description")
	private String description;

	@Column(name = "size", nullable = false)
	private PizzaSize size;

	@Column(name = "special", nullable = false)
	private boolean special;

	@OneToMany(mappedBy = "pizza", fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<RelationPizzaIngredient> pizzaIngredients;

	@OneToMany(mappedBy = "pizza", fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<RelationPizzeriaPizza> pizzasPriceList;

	@OneToMany(mappedBy = "pizza", fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Menu> menu;

	@OneToMany(mappedBy = "pizza", fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<PizzaOrderItem> orderItems;

	public Pizza() {
		this.id = DatabaseHandler.NO_ID;
		this.name = "";
		this.preparationTime = 0;
		this.glutenFree = false;
		this.description = "";
		this.size = PizzaSize.NORMAL;
		this.special = false;
		this.pizzaIngredients = new ArrayList<>();
		this.menu = new ArrayList<>();
		this.pizzasPriceList = new ArrayList<>();
		this.orderItems = new ArrayList<>();
	}

	public Pizza(String name) {
		this.id = DatabaseHandler.NO_ID;
		this.name = name;
		this.preparationTime = 0;
		this.glutenFree = false;
		this.description = "";
		this.size = PizzaSize.NORMAL;
		this.special = false;
		this.pizzaIngredients = new ArrayList<>();
		this.menu = new ArrayList<>();
		this.pizzasPriceList = new ArrayList<>();
		this.orderItems = new ArrayList<>();
	}

	public Pizza(String name, Integer preparationTime, Boolean glutenFree, String description,
			PizzaSize size, Boolean special) {
		this.id = DatabaseHandler.NO_ID;
		this.name = name;
		this.preparationTime = preparationTime;
		this.glutenFree = glutenFree;
		this.description = description;
		this.size = size;
		this.special = special;
		this.pizzaIngredients = new ArrayList<>();
		this.menu = new ArrayList<>();
		this.pizzasPriceList = new ArrayList<>();
		this.orderItems = new ArrayList<>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPreparationTime() {
		return preparationTime;
	}

	public void setPreparationTime(Integer preparationTime) {
		this.preparationTime = preparationTime;
	}

	public boolean getGlutenFree() {
		return glutenFree;
	}

	public void setGlutenFree(Boolean glutenFree) {
		this.glutenFree = glutenFree;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public PizzaSize getSize() {
		return size;
	}

	public void setSize(PizzaSize size) {
		this.size = size;
	}

	public List<RelationPizzaIngredient> getPizzaIngredients() {
		return pizzaIngredients;
	}

	public void setPizzaIngredient(List<RelationPizzaIngredient> pizzaIngredients) {
		this.pizzaIngredients = pizzaIngredients;
	}

	public List<Menu> getMenu() {
		return menu;
	}

	public void setMenu(List<Menu> menu) {
		this.menu = menu;
	}

	public List<RelationPizzeriaPizza> getPizzasPriceList() {
		return pizzasPriceList;
	}

	public void setPizzasPriceList(List<RelationPizzeriaPizza> pizzaPriceList) {
		this.pizzasPriceList = pizzaPriceList;
	}

	public List<PizzaOrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<PizzaOrderItem> orderItems) {
		this.orderItems = orderItems;
	}

}

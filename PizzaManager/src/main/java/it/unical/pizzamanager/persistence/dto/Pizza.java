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
@SequenceGenerator(name="pizzasGenerator",sequenceName="pizzasSequence",initialValue=1)
public class Pizza implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6079186121128866192L;

	public static final String MAXI = "MAXI";
	public static final String NORMAL = "NORMAL";


	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="pizzasGenerator")
	private Integer id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "preparationTime", nullable = false)
	private int preparationTime;

	@Column(name = "celiac")
	private boolean celiac;

	@Column(name = "description")
	private String description;

	@Column(name = "size", nullable = false)
	private String size;

	@Column(name = "special", nullable = false)
	private boolean special;

	@OneToMany(mappedBy = "pizza", fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<RelationPizzaIngredient> pizzaIngredient;

	@OneToMany(mappedBy = "pizza", fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<RelationPizzeriaPizza> pizzaPriceList;

	@OneToMany(mappedBy = "pizza", fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Menu> menu;
	
	@OneToMany(mappedBy = "pizza", fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<OrderItem> orderItems;

	public Pizza() {
		id=DatabaseHandler.NO_ID;
		name = "";
		preparationTime = 0;
		celiac = false;
		description = "";
		size = Pizza.MAXI;
		special = false;
		pizzaIngredient = new ArrayList<>();
		menu = new ArrayList<>();
		pizzaPriceList = new ArrayList<>();
		orderItems=new ArrayList<>();
	}

	public Pizza(int code, String name, int preparationTime, boolean celiac, String description, String size,
			Boolean special) {
		this.id = DatabaseHandler.NO_ID;
		this.preparationTime = preparationTime;
		this.celiac = celiac;
		this.description = description;
		this.size = size;
		this.special = special;
		this.pizzaIngredient = new ArrayList<>();
		this.menu = new ArrayList<>();
		this.pizzaPriceList = new ArrayList<>();
		this.orderItems=new ArrayList<>();
	}

	public Pizza(int code, String name, int preparationTime, boolean celiac, String description, String size,
			Boolean special, ArrayList<RelationPizzaIngredient> pizzaIngredient, ArrayList<Menu> menu,
			ArrayList<RelationPizzeriaPizza> pizzaPriceList,ArrayList<OrderItem> orderItems) {
		this.id = DatabaseHandler.NO_ID;
		this.preparationTime = preparationTime;
		this.celiac = celiac;
		this.description = description;
		this.size = size;
		this.special = special;
		this.pizzaIngredient = pizzaIngredient;
		this.menu = menu;
		this.pizzaPriceList = pizzaPriceList;
		this.orderItems=orderItems;
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

	public void setPreparationTime(int preparationTime) {
		this.preparationTime = preparationTime;
	}

	public boolean isCeliac() {
		return celiac;
	}

	public void setCeliac(boolean celiac) {
		this.celiac = celiac;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public List<RelationPizzaIngredient> getPizzaIngredient() {
		return pizzaIngredient;
	}

	public void setPizzaIngredient(List<RelationPizzaIngredient> pizzaIngredient) {
		this.pizzaIngredient = pizzaIngredient;
	}

	public List<Menu> getMenu() {
		return menu;
	}

	public void setMenu(List<Menu> menu) {
		this.menu = menu;
	}

	public List<RelationPizzeriaPizza> getPizzaPriceList() {
		return pizzaPriceList;
	}

	public void setPizzaPriceList(List<RelationPizzeriaPizza> pizzaPriceList) {
		this.pizzaPriceList = pizzaPriceList;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

}

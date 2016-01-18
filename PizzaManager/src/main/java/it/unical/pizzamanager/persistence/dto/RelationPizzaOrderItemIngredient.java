package it.unical.pizzamanager.persistence.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import it.unical.pizzamanager.persistence.dao.DatabaseHandler;

@Entity
@Table(name="pizzaOrderItem_ingredient")
public class RelationPizzaOrderItemIngredient {

	public final static String ADDITION="addition";
	public final static String REMOVAL="removal";
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	// ho messo una stringa ma potremmo usare anche un boolean
	@Column (name="additive")
	private String additive;
	
	@ManyToOne
	@JoinColumn(name = "ingredient")
	private Ingredient ingredient;
	
	@Column(name="cost")
	private Double cost;
	
	@ManyToOne
	@JoinColumn(name="pizzaOrderItem")
	private PizzaOrderItem pizzaOrderItem;
	
	public RelationPizzaOrderItemIngredient() {
		id=DatabaseHandler.NO_ID;
		cost=0.0;
		additive=ADDITION;
		ingredient=null;
		pizzaOrderItem=null;
		
	}
	
	public RelationPizzaOrderItemIngredient(String additive,Ingredient ingredient,PizzaOrderItem pizzaOrderItem) {
		this.id=DatabaseHandler.NO_ID;
		this.additive=additive;
		this.ingredient=ingredient;
		this.pizzaOrderItem=pizzaOrderItem;
	}

	public PizzaOrderItem getPizzaOrderItem() {
		return pizzaOrderItem;
	}

	public void setPizzaOrderItem(PizzaOrderItem pizzaOrderItem) {
		this.pizzaOrderItem = pizzaOrderItem;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Ingredient getIngredient() {
		return ingredient;
	}

	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}

	public String getAdditive() {
		return additive;
	}

	public void setAdditive(String additive) {
		this.additive = additive;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}
	
	
}

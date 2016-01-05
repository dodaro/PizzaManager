package it.unical.pizzamanager.persistence.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@DiscriminatorValue("pizza")
public class PizzaOrderItem extends OrderItem {

	private static final long serialVersionUID = 8977364851663655249L;

	@ManyToOne
	@JoinColumn(name = "pizza")
	private Pizza pizza;

	@Column(name = "modified")
	private Boolean modified;
	
	@OneToMany(mappedBy = "pizzaOrderItem", fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<RelationPizzaOrderItemIngredient> pizzaOrderIngredients;

	public PizzaOrderItem() {
		super();
		this.pizza = null;
		this.modified = false;
		this.pizzaOrderIngredients=new ArrayList<>();
	}

	public Pizza getPizza() {
		return pizza;
	}

	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}

	public Boolean getModified() {
		return modified;
	}

	public void setModified(Boolean modified) {
		this.modified = modified;
	}

	public List<RelationPizzaOrderItemIngredient> getPizzaOrderIngredients() {
		return pizzaOrderIngredients;
	}

	public void setPizzaOrderIngredients(List<RelationPizzaOrderItemIngredient> pizzaOrderIngredients) {
		this.pizzaOrderIngredients = pizzaOrderIngredients;
	}
}
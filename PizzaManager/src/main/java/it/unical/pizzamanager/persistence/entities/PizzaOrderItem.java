package it.unical.pizzamanager.persistence.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@DiscriminatorValue("pizza")
public class PizzaOrderItem extends OrderItem {

	private static final long serialVersionUID = 8977364851663655249L;

	// mantengo il nome dell'attributo cmq sia un oggetto RelationPizzeriaPizza
	// � una pizza con il prezzo e la pizzeria dove viene prodotta
	@ManyToOne
	@JoinColumn(name = "pizzeria_pizza")
	private RelationPizzeriaPizza pizzeria_pizza;

	@Column(name = "modified")
	private Boolean modified;

	@OneToMany(mappedBy = "pizzaOrderItem", fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@Cascade(value = CascadeType.SAVE_UPDATE)
	private List<RelationPizzaOrderItemIngredient> pizzaOrderIngredients;

	public PizzaOrderItem() {
		super();
		this.pizzeria_pizza = null;
		this.modified = false;
		this.pizzaOrderIngredients = new ArrayList<>();
	}
	
	public RelationPizzeriaPizza getPizzeria_pizza() {
		return pizzeria_pizza;
	}

	public void setPizzeria_pizza(RelationPizzeriaPizza pizzeria_pizza) {
		this.pizzeria_pizza = pizzeria_pizza;
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
	
	public String pizzaName(){
		return pizzeria_pizza.getPizza().getName();
	}
	public String pizzeriaName(){
		return pizzeria_pizza.getPizzeria().getName();
	}

}
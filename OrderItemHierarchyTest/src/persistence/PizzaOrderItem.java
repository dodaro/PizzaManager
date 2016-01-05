package persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pizza_order_items")
public class PizzaOrderItem extends OrderItem {

	private static final long serialVersionUID = 7426154904393532134L;

	@ManyToOne
	@JoinColumn(name = "pizza")
	private Pizza pizza;

	@Column(name = "modified")
	private Boolean modified;

	public PizzaOrderItem() {
		super();
		this.pizza = new Pizza();
		this.modified = false;
	}

	public PizzaOrderItem(Order order, Pizza pizza, Boolean modified) {
		super(order);
		this.pizza = pizza;
		this.modified = modified;
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
}

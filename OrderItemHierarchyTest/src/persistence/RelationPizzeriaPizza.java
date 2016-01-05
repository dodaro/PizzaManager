package persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "relation_pizzeria_pizza")
public class RelationPizzeriaPizza {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "pizzeria")
	private Pizzeria pizzeria;

	@ManyToOne
	@JoinColumn(name = "pizza")
	private Pizza pizza;

	@Column(name = "price")
	private Double price;

	public RelationPizzeriaPizza(Pizzeria pizzeria, Pizza pizza, Double price) {
		this.id = -1;
		this.pizzeria = pizzeria;
		this.pizza = pizza;
		this.price = price;
	}

	public RelationPizzeriaPizza() {
		this.id = -1;
		this.pizzeria = new Pizzeria();
		this.pizza = new Pizza();
		this.price = 0.0;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	@Override
	public String toString() {
		return "Pizza " + pizza.getName() + " costs " + price + " in pizzeria " + pizzeria.getName();
	}
}

package it.unical.pizzamanager.persistence.dto;

import java.io.Serializable;

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
@Table(name = "pizzeria_menu")
public class RelationPizzeriaMenu implements Serializable {

	private static final long serialVersionUID = -3132509529862670681L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "pizzeria")
	private Pizzeria pizzeria;

	@ManyToOne
	@JoinColumn(name = "menu")
	private Menu menu;

	@Column(name = "price")
	private Double price;

	public RelationPizzeriaMenu() {
		this.id = DatabaseHandler.NO_ID;
		this.pizzeria = null;
		this.menu = null;
		this.price = 0.0;
	}

	public RelationPizzeriaMenu(Pizzeria pizzeria, Menu menu, Double price) {
		this.id = DatabaseHandler.NO_ID;
		this.pizzeria = pizzeria;
		this.menu = menu;
		this.price = price;
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

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
}

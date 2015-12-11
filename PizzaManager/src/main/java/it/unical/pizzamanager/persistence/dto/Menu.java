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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import it.unical.pizzamanager.persistence.dao.DatabaseHandler;

@Entity
@Table(name = "menus")
@SequenceGenerator(name = "menusGenerator", sequenceName = "menusSequence", initialValue = 1)
public class Menu implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4303650276870261381L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "menusGenerator")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "beverage")
	private Beverage beverage;

	@ManyToOne
	@JoinColumn(name = "pizza")
	private Pizza pizza;

	@OneToMany(mappedBy = "menu", fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<RelationPizzeriaMenu> menuPriceList;

	@OneToMany(mappedBy = "menu", fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<OrderItem> orderItems;

	public Menu() {
		id = DatabaseHandler.NO_ID;
		beverage = new Beverage();
		pizza = new Pizza();
		menuPriceList = new ArrayList<>();
		orderItems = new ArrayList<>();
	}

	public Menu(Beverage beverage, Pizza pizza) {
		this.id = DatabaseHandler.NO_ID;
		this.beverage = beverage;
		this.pizza = pizza;
		this.menuPriceList = new ArrayList<>();
		this.orderItems = new ArrayList<>();
	}

	public Menu(Beverage beverage, Pizza pizza, ArrayList<RelationPizzeriaMenu> menuPriceList,
			ArrayList<OrderItem> orderItems) {
		this.id = DatabaseHandler.NO_ID;
		this.beverage = beverage;
		this.pizza = pizza;
		this.menuPriceList = menuPriceList;
		this.orderItems = orderItems;

	}

	public Beverage getBeverage() {
		return beverage;
	}

	public void setBeverage(Beverage beverage) {
		this.beverage = beverage;
	}

	public Pizza getPizza() {
		return pizza;
	}

	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}

	public List<RelationPizzeriaMenu> getMenuPriceList() {
		return menuPriceList;
	}

	public void setMenuPriceList(List<RelationPizzeriaMenu> menuPriceList) {
		this.menuPriceList = menuPriceList;
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

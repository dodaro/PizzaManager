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
@Table(name = "beverages")
@SequenceGenerator(name = "beveragesGenerator", sequenceName = "beverages_sequence", initialValue = 1)
public class Beverage implements Serializable {

	private static final long serialVersionUID = 3542955027496737446L;

	public enum BeverageSize {
		SMALL, MEDIUM, LARGE
	}

	public enum BeverageContainer {
		BOTTLE, CAN, GLASS
	}

	public enum BeverageType {
		BEER, WINE, WATER, SODA
	}

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "beveragesGenerator")
	private Integer id;

	@Column(name = "name", nullable = false, length = 255)
	private String name;

	@Column(name = "brand", nullable = false, length = 255)
	private String brand;

	@Column(name = "container", nullable = false)
	private BeverageContainer container;

	@Column(name = "size", nullable = false)
	private BeverageSize size;

	@Column(name = "type", nullable = false)
	private BeverageType type;

	/**
	 * How much the beverage costs in each pizzeria which sells it.
	 * Coincidentally, list of the pizzerias who sell this beverage.
	 */
	@OneToMany(mappedBy = "beverage", fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<RelationPizzeriaBeverage> beveragePriceList;

	/**
	 * Menus to which the beverage belongs.
	 */
	@OneToMany(mappedBy = "beverage", fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Menu> menu;

	/**
	 * OrderItems which contain a beverage.
	 */
	@OneToMany(mappedBy = "beverage", fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<BeverageOrderItem> orderItems;

	public Beverage() {
		id = DatabaseHandler.NO_ID;
		name = "";
		brand = "";
		container = BeverageContainer.BOTTLE;
		size = BeverageSize.MEDIUM;
		type = BeverageType.WATER;
		beveragePriceList = new ArrayList<RelationPizzeriaBeverage>();
		menu = new ArrayList<>();
		orderItems = new ArrayList<>();
	}

	public Beverage(String name, String brand, BeverageContainer container, BeverageSize size,
			BeverageType type) {
		this.id = DatabaseHandler.NO_ID;
		this.name = name;
		this.brand = brand;
		this.container = container;
		this.size = size;
		this.type = type;
		this.beveragePriceList = new ArrayList<>();
		this.menu = new ArrayList<>();
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

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public BeverageContainer getContainer() {
		return container;
	}

	public void setContainer(BeverageContainer container) {
		this.container = container;
	}

	public BeverageSize getSize() {
		return size;
	}

	public void setSize(BeverageSize size) {
		this.size = size;
	}

	public BeverageType getType() {
		return type;
	}

	public void setType(BeverageType type) {
		this.type = type;
	}

	public List<RelationPizzeriaBeverage> getBeveragePriceList() {
		return beveragePriceList;
	}

	public void setBeveragePriceList(List<RelationPizzeriaBeverage> beveragePriceList) {
		this.beveragePriceList = beveragePriceList;
	}

	public List<Menu> getMenu() {
		return menu;
	}

	public void setMenu(List<Menu> menu) {
		this.menu = menu;
	}

	public List<BeverageOrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<BeverageOrderItem> orderItems) {
		this.orderItems = orderItems;
	}
}
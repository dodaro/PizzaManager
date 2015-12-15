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
@SequenceGenerator(name = "beveragesGenerator", sequenceName = "beveragesSequence", initialValue = 1)
public class Beverage implements Serializable {

	/**
	 * 
	 */
	public static final String MEDIUM_SIZE = "MEDIUM";
	public static final String SMALL_SIZE = "SMALL";

	// add sequence on entity , modify name with _ change type to obj change
	// serial id
	private static final long serialVersionUID = -4456395642459391534L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "beveragesGenerator")
	private Integer id;

	@Column(name = "name", nullable = false, length = 255)
	private String name;

	@Column(name = "brand", nullable = false, length = 255)
	private String brand;

	@Column(name = "containerType", nullable = false, length = 255)
	private String containerType;

	@Column(name = "size", nullable = false, length = 255)
	private String size;

	@Column(name = "category", nullable = false, length = 255)
	private String category;

	@OneToMany(mappedBy = "beverage", fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<RelationPizzeriaBeverage> beveragePriceList;

	@OneToMany(mappedBy = "beverage", fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Menu> menu;

	@OneToMany(mappedBy = "beverage", fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<BeverageItem> orderItems;

	public Beverage() {
		id = DatabaseHandler.NO_ID;
		name = "";
		brand = "";
		containerType = "";
		size = "";
		category = "";
		beveragePriceList = new ArrayList<RelationPizzeriaBeverage>();
		menu = new ArrayList<>();
		orderItems = new ArrayList<>();
	}

	public Beverage(String name, String brand, String containerType, String size, String category) {
		this.id = DatabaseHandler.NO_ID;
		this.name = name;
		this.brand = brand;
		this.containerType = containerType;
		this.size = size;
		this.category = category;
		this.beveragePriceList = new ArrayList<>();
		this.menu = new ArrayList<>();
		this.orderItems = new ArrayList<>();
	}

	public Beverage(String name, String brand, String containerType, String size, String category,
			ArrayList<RelationPizzeriaBeverage> beveragePriceList, ArrayList<Menu> menu,
			ArrayList<BeverageItem> orderItems) {
		this.id = DatabaseHandler.NO_ID;
		this.name = name;
		this.brand = brand;
		this.containerType = containerType;
		this.size = size;
		this.category = category;
		this.beveragePriceList = beveragePriceList;
		this.menu = menu;
		this.orderItems=orderItems;
	}

	public String getContainerType() {
		return containerType;
	}

	public void setContainerType(String containerType) {
		this.containerType = containerType;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<BeverageItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<BeverageItem> orderItems) {
		this.orderItems = orderItems;
	}

}

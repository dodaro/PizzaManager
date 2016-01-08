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

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import it.unical.pizzamanager.persistence.dao.DatabaseHandler;

@Entity
@Table(name = "beverages")
@SequenceGenerator(name = "beveragesGenerator", sequenceName = "beverages_sequence", initialValue = 1)
public class Beverage implements Serializable {

	private static final long serialVersionUID = 3542955027496737446L;

	public enum BeverageSize {
		SMALL, MEDIUM, LARGE, NOT_SPECIFIED
	}

	public enum BeverageContainer {
		BOTTLE, CAN, DRAFT /* Alla spina */, NOT_SPECIFIED
	}

	public enum BeverageType {
		BEER, WINE, WATER, SODA, NOT_SPECIFIED
	}

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "beveragesGenerator")
	private Integer id;

	@Column(name = "name", length = 255)
	private String name;

	@Column(name = "brand", length = 255)
	private String brand;

	@Column(name = "container")
	private BeverageContainer container;

	@Column(name = "size")
	private BeverageSize size;

	@Column(name = "type")
	private BeverageType type;

	/**
	 * How much the beverage costs in each pizzeria which sells it. Coincidentally, list of the
	 * pizzerias who sell this beverage.
	 */
	
	//TODO: convertire EAGER in LAZY e sistemare il dao, non toccate il jsonIgnore
		//vado di fretta per testare le jsp --> by David
	
	@OneToMany(mappedBy = "beverage", fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@Cascade(value = CascadeType.SAVE_UPDATE)
	private List<RelationPizzeriaBeverage> beveragePriceList;

	//rimosse beverageorderitems

	public Beverage() {
		this.id = DatabaseHandler.NO_ID;
		this.name = "";
		this.brand = "";
		this.container = BeverageContainer.NOT_SPECIFIED;
		this.size = BeverageSize.NOT_SPECIFIED;
		this.type = BeverageType.NOT_SPECIFIED;
		this.beveragePriceList = new ArrayList<>();
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
}
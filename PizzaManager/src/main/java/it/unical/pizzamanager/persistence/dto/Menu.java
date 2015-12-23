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
@SequenceGenerator(name = "menusGenerator", sequenceName = "menus_sequence", initialValue = 1)
public class Menu implements Serializable {

	private static final long serialVersionUID = -305460523112063746L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "menusGenerator")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "pizza")
	private Pizza pizza;

	@ManyToOne
	@JoinColumn(name = "beverage")
	private Beverage beverage;

	@OneToMany(mappedBy = "menu", fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<RelationPizzeriaMenu> menuPriceList;

	@OneToMany(mappedBy = "menu", fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<MenuOrderItem> menuOrderItems;

	public Menu() {
		this.id = DatabaseHandler.NO_ID;
		this.pizza = null;
		this.beverage = null;
		this.menuPriceList = new ArrayList<>();
		this.menuOrderItems = new ArrayList<>();
	}

	public Menu(Pizza pizza, Beverage beverage) {
		this.id = DatabaseHandler.NO_ID;
		this.pizza = pizza;
		this.beverage = beverage;
		this.menuPriceList = new ArrayList<>();
		this.menuOrderItems = new ArrayList<>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public List<MenuOrderItem> getMenuOrderItems() {
		return menuOrderItems;
	}

	public void setMenuOrderItems(List<MenuOrderItem> menuOrderItems) {
		this.menuOrderItems = menuOrderItems;
	}
}
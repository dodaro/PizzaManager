package it.unical.pizzamanager.persistence.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="menus")
public class Menu implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4303650276870261381L;

	@Id
	@Column(name = "code")
	private int code;
	
	@ManyToOne
	@JoinColumn(name = "beverage")
	private Beverage beverage;
	
	@ManyToOne
	@JoinColumn(name = "pizza")
	private Pizza pizza;
	
	@OneToMany(mappedBy = "menu", fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<MenuPriceList> menuPriceList;
	
	public Menu() {
		code=0;
		beverage=new Beverage();
		pizza=new Pizza();
		menuPriceList=new ArrayList<>();
	}
	
	public Menu(int code,Beverage beverage,Pizza pizza){
		this.code=code;
		this.beverage=beverage;
		this.pizza=pizza;
		menuPriceList=new ArrayList<>();
	}

	public Menu(int code,Beverage beverage,Pizza pizza,ArrayList<MenuPriceList> menuPriceList){
		this.code=code;
		this.beverage=beverage;
		this.pizza=pizza;
		this.menuPriceList=menuPriceList;
		
	}
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
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

	public List<MenuPriceList> getMenuPriceList() {
		return menuPriceList;
	}

	public void setMenuPriceList(List<MenuPriceList> menuPriceList) {
		this.menuPriceList = menuPriceList;
	}
}

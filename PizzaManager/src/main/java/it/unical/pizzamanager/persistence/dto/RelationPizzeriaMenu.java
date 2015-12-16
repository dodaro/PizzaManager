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
@Table(name = "pizzeria_menu_price")
@SequenceGenerator(name="pizzeria_menu_priceGenerator",sequenceName="pizzeria_menu_priceSequence",initialValue=1)
public class RelationPizzeriaMenu implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3132509529862670681L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="pizzeria_menu_priceGenerator")
	private int id;

	@ManyToOne
	@JoinColumn(name = "menu")
	private Menu menu;

	@ManyToOne
	@JoinColumn(name = "pizzeria")
	private Pizzeria pizzeria;
	
	@Column(name = "price")
	private int price;

	@OneToMany(mappedBy = "menuPriceList", fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Offer> offers;

	public RelationPizzeriaMenu() {
		id = DatabaseHandler.NO_ID;
		menu = new Menu();
		price = 0;
		pizzeria=new Pizzeria();
		offers = new ArrayList<>();
	}

	public RelationPizzeriaMenu(Menu menu, int price, Pizzeria pizzeria) {
		this.id = DatabaseHandler.NO_ID;
		this.menu = menu;
		this.price = price;
		this.offers = new ArrayList<>();
		this.pizzeria=pizzeria;
	}

	public RelationPizzeriaMenu(Menu menu, int price, ArrayList<Offer> offers, Pizzeria pizzeria) {
		this.id = DatabaseHandler.NO_ID;
		this.menu = menu;
		this.price = price;
		this.offers = offers;
		this.pizzeria=pizzeria;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public List<Offer> getOffers() {
		return offers;
	}

	public void setOffers(List<Offer> offers) {
		this.offers = offers;
	}

	public Pizzeria getPizzeria() {
		return pizzeria;
	}

	public void setPizzeria(Pizzeria pizzeria) {
		this.pizzeria = pizzeria;
	}
}

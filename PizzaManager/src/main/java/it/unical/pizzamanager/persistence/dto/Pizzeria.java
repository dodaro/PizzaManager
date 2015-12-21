package it.unical.pizzamanager.persistence.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "pizzerias")
public class Pizzeria extends Account {

	private static final long serialVersionUID = -5327142814488664848L;

	@Column(name = "name")
	private String name;

	@Column(name = "phoneNumber")
	private String phoneNumber;

	@OneToMany(mappedBy = "pizzeria", fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<RelationPizzeriaBeverage> beveragesPriceList;

	@OneToMany(mappedBy = "pizzeria", fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<RelationPizzeriaPizza> pizzasPriceList;

	@OneToMany(mappedBy = "pizzeria", fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<RelationPizzeriaMenu> menusPriceList;

	@OneToMany(mappedBy = "pizzeria", fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<RelationPizzeriaIngredient> ingredientsPriceList;

	@OneToMany(mappedBy = "pizzeria", fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<FeedbackPizzeria> feedbacks;

	@OneToMany(mappedBy = "pizzeria", fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<PizzeriaTable> tables;

	@OneToMany(mappedBy = "pizzeria", fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Booking> bookings;

	@ElementCollection
	@CollectionTable(name = "images", joinColumns = @JoinColumn(name = "pizzeria_id") )
	private List<Image> images;

	public Pizzeria() {
		super();
		this.name = "";
		this.phoneNumber = "";
		this.beveragesPriceList = new ArrayList<>();
		this.pizzasPriceList = new ArrayList<>();
		this.menusPriceList = new ArrayList<>();
		this.ingredientsPriceList = new ArrayList<>();
		this.feedbacks = new ArrayList<>();
		this.tables = new ArrayList<>();
		this.bookings = new ArrayList<>();
		this.images = new ArrayList<>();
	}

	public Pizzeria(String email, String password, String name, String phoneNumber) {
		super(email, password);
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.beveragesPriceList = new ArrayList<>();
		this.pizzasPriceList = new ArrayList<>();
		this.menusPriceList = new ArrayList<>();
		this.ingredientsPriceList = new ArrayList<>();
		this.feedbacks = new ArrayList<>();
		this.tables = new ArrayList<>();
		this.bookings = new ArrayList<>();
		this.images = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<RelationPizzeriaBeverage> getBeveragesPriceList() {
		return beveragesPriceList;
	}

	public void setBeveragesPriceList(List<RelationPizzeriaBeverage> beveragesPriceList) {
		this.beveragesPriceList = beveragesPriceList;
	}

	public List<RelationPizzeriaPizza> getPizzasPriceList() {
		return pizzasPriceList;
	}

	public void setPizzasPriceList(List<RelationPizzeriaPizza> pizzasPriceList) {
		this.pizzasPriceList = pizzasPriceList;
	}

	public List<RelationPizzeriaMenu> getMenusPriceList() {
		return menusPriceList;
	}

	public void setMenusPriceList(List<RelationPizzeriaMenu> menusPriceList) {
		this.menusPriceList = menusPriceList;
	}

	public List<RelationPizzeriaIngredient> getIngredientsPriceList() {
		return ingredientsPriceList;
	}

	public void setIngredientsPriceList(List<RelationPizzeriaIngredient> ingredientsPriceList) {
		this.ingredientsPriceList = ingredientsPriceList;
	}

	public List<FeedbackPizzeria> getFeedbacks() {
		return feedbacks;
	}

	public void setFeedbacks(List<FeedbackPizzeria> feedbacks) {
		this.feedbacks = feedbacks;
	}

	public List<PizzeriaTable> getTables() {
		return tables;
	}

	public void setTables(List<PizzeriaTable> tables) {
		this.tables = tables;
	}

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}
}

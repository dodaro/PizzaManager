package it.unical.pizzamanager.persistence.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import it.unical.pizzamanager.persistence.dao.DatabaseHandler;

@Entity
@Table(name = "pizzerias")
@SequenceGenerator(name = "pizzeriasGenerator", sequenceName = "pizzerias_sequence", initialValue = 1)
public class Pizzeria implements Serializable {

	private static final long serialVersionUID = -5327142814488664848L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pizzeriasGenerator")
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "email")
	private String email;

	@Column(name = "description")
	private String description;

	@OneToOne
	@JoinColumn(name = "address")
	@Cascade(value = CascadeType.SAVE_UPDATE)
	private Address address;

	@Column(name = "phoneNumber")
	private String phoneNumber;

	@OneToOne
	@JoinColumn(name = "user")
	private User user;

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
	private List<TablePizzeria> tables;

	@OneToMany(mappedBy = "pizzeria", fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Booking> bookings;

	@ElementCollection
	@CollectionTable(name = "images", joinColumns = @JoinColumn(name = "pizzeria_id") )
	private List<Image> images;

	public Pizzeria() {
		id = DatabaseHandler.NO_ID;
		name = "";
		email = "";
		description = "";
		phoneNumber = "";
		address = new Address();
		user = new User();
		beveragesPriceList = new ArrayList<>();
		pizzasPriceList = new ArrayList<>();
		menusPriceList = new ArrayList<>();
		ingredientsPriceList = new ArrayList<>();
		feedbacks = new ArrayList<>();
		tables = new ArrayList<>();
		bookings = new ArrayList<>();
		images = new ArrayList<>();
	}

	public Pizzeria(String name, String email, String description, Address address,
			String phoneNumber, User user) {
		this.id = DatabaseHandler.NO_ID;
		this.name = name;
		this.email = email;
		this.description = description;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.user = user;
		this.beveragesPriceList = new ArrayList<>();
		this.pizzasPriceList = new ArrayList<>();
		this.menusPriceList = new ArrayList<>();
		this.ingredientsPriceList = new ArrayList<>();
		this.feedbacks = new ArrayList<>();
		this.tables = new ArrayList<>();
		this.bookings = new ArrayList<>();
		this.images = new ArrayList<>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public List<TablePizzeria> getTables() {
		return tables;
	}

	public void setTables(List<TablePizzeria> tables) {
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

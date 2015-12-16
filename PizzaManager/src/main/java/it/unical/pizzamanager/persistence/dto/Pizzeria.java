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

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import it.unical.pizzamanager.persistence.dao.DatabaseHandler;

@Entity
@Table(name = "pizzerias")
@SequenceGenerator(name = "pizzeriaGenerator", sequenceName = "pizzeria_sequence", initialValue = 1)
public class Pizzeria implements Serializable {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pizzeriaGenerator")
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "email")
	private String email;

	@Column(name = "description")
	private String description;

	@ElementCollection
	@CollectionTable(name = "addresses", joinColumns = @JoinColumn(name = "pizzeria") )
	private Address address;

	@Column(name = "phoneNumber")
	private String phoneNumber;

	@OneToOne
	@JoinColumn(name = "user")
	private User user;

	@OneToMany(mappedBy = "pizzeria", fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<RelationPizzeriaBeverage> beveragePriceLists;

	@OneToMany(mappedBy = "pizzeria", fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<RelationPizzeriaPizza> pizzaPriceLists;

	@OneToMany(mappedBy = "pizzeria", fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<RelationPizzeriaMenu> menuPriceLists;

	@OneToMany(mappedBy = "pizzeria", fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<RelationPizzeriaIngredient> ingredientPriceLists;

	@OneToMany(mappedBy = "pizzeria", fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<FeedbackPizzeria> feedbacks;
	
	@OneToMany(mappedBy = "pizzeria", fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<TablePizzeria> tables;

	// table;

	public Pizzeria() {
		id = DatabaseHandler.NO_ID;
		name = "";
		email = "";
		description = "";
		phoneNumber = "";
		address = new Address();
		user = new User();
		beveragePriceLists = new ArrayList<>();
		pizzaPriceLists = new ArrayList<>();
		menuPriceLists = new ArrayList<>();
		ingredientPriceLists = new ArrayList<>();
		feedbacks = new ArrayList<>();
		tables=new ArrayList<>();

	}

	public Pizzeria(String name, String email, String description, String phoneNumber,User user) {
		this.id = DatabaseHandler.NO_ID;
		this.name = name;
		this.email = email;
		this.description = description;
		this.phoneNumber = phoneNumber;
		this.address = new Address();
		this.user = user;
		this.beveragePriceLists = new ArrayList<>();
		this.pizzaPriceLists = new ArrayList<>();
		this.menuPriceLists = new ArrayList<>();
		this.ingredientPriceLists = new ArrayList<>();
		this.feedbacks = new ArrayList<>();
		this.tables=new ArrayList<>();
	}

	public Pizzeria(String name, String email, String description, String phoneNumber, Address address, User user,
			List<RelationPizzeriaMenu> menuPriceLists, List<RelationPizzeriaBeverage> beveragePriceLists,
			List<RelationPizzeriaPizza> pizzaPriceLists, List<RelationPizzeriaIngredient> ingredientPriceLists,
			List<FeedbackPizzeria> feedbacks, List<TablePizzeria> tables) {
		this.id = DatabaseHandler.NO_ID;
		this.name = name;
		this.email = email;
		this.description = description;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.user = user;
		this.beveragePriceLists = beveragePriceLists;
		this.pizzaPriceLists = pizzaPriceLists;
		this.menuPriceLists = menuPriceLists;
		this.ingredientPriceLists = ingredientPriceLists;
		this.feedbacks = feedbacks;
		this.tables=tables;
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

	public List<RelationPizzeriaBeverage> getBeveragePriceLists() {
		return beveragePriceLists;
	}

	public void setBeveragePriceLists(List<RelationPizzeriaBeverage> beveragePriceLists) {
		this.beveragePriceLists = beveragePriceLists;
	}

	public List<RelationPizzeriaPizza> getPizzaPriceLists() {
		return pizzaPriceLists;
	}

	public void setPizzaPriceLists(List<RelationPizzeriaPizza> pizzaPriceLists) {
		this.pizzaPriceLists = pizzaPriceLists;
	}

	public List<RelationPizzeriaMenu> getMenuPriceLists() {
		return menuPriceLists;
	}

	public void setMenuPriceLists(List<RelationPizzeriaMenu> menuPriceLists) {
		this.menuPriceLists = menuPriceLists;
	}

	public List<RelationPizzeriaIngredient> getIngredientPriceLists() {
		return ingredientPriceLists;
	}

	public void setIngredientPriceLists(List<RelationPizzeriaIngredient> ingredientPriceLists) {
		this.ingredientPriceLists = ingredientPriceLists;
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

}

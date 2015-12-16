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

@Entity
@Table(name = "users")
@SequenceGenerator(name = "usersGenerator", sequenceName = "users_sequence", initialValue = 1)
public class User implements Serializable {

	private static final long serialVersionUID = -6053598225539038240L;

	private static final int NO_ID = -1;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usersGenerator")
	@Column(name = "id")
	private Integer id;

	@Column(name = "email", length = 255, unique = true, nullable = false)
	private String email;

	@Column(name = "password", length = 255, unique = true, nullable = false)
	private String password;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@ElementCollection
	@CollectionTable(name = "addresses", joinColumns = @JoinColumn(name = "user") )
	@Column(name = "address")
	private Address address;

	@Column(name = "phone_number")
	private String phoneNumber;

	@Column(name = "last_location", length = 255)
	private String lastLocation;

	@OneToOne(mappedBy = "user", fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Cart cart;

	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Payment> payments;

	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Feedback> feedbacks;

	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Favourites> favourites;

	public User() {
		this.id = NO_ID;
		this.email = "";
		this.password = "";
		this.firstName = "";
		this.lastName = "";
		this.address = new Address();
		this.phoneNumber = "";
		this.lastLocation = "";
		this.cart = new Cart();
		this.payments = new ArrayList<>();
		this.feedbacks = new ArrayList<>();
		this.favourites = new ArrayList<>();
	}

	public User(String email, String password) {
		this.id = NO_ID;
		this.email = email;
		this.password = password;
		this.firstName = "";
		this.lastName = "";
		this.address = new Address();
		this.phoneNumber = "";
		this.lastLocation = "";
		this.cart = new Cart();
		this.payments = new ArrayList<>();
		this.feedbacks = new ArrayList<>();
		this.favourites = new ArrayList<>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public String getLastLocation() {
		return lastLocation;
	}

	public void setLastLocation(String lastLocation) {
		this.lastLocation = lastLocation;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public List<Payment> getPayments() {
		return payments;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}

	public List<Feedback> getFeedbacks() {
		return feedbacks;
	}

	public void setFeedbacks(List<Feedback> feedbacks) {
		this.feedbacks = feedbacks;
	}

	public List<Favourites> getFavourites() {
		return favourites;
	}

	public void setFavourites(List<Favourites> favourites) {
		this.favourites = favourites;
	}
}
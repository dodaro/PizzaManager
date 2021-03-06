package it.unical.pizzamanager.persistence.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "users")
public class User extends Account {

	private static final long serialVersionUID = -6053598225539038240L;

	@Column(name = "name", length = 255, unique = true)
	private String name;

	@Column(name = "firstName", length = 255)
	private String firstName;

	@Column(name = "lastName", length = 255)
	private String lastName;

	@OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Cart cart;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Booking> bookings;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Feedback> feedbacks;

	public User() {
		super();
		this.name = "";
		this.firstName = "";
		this.lastName = "";
		this.cart = new Cart();
		this.bookings = new ArrayList<Booking>();
		this.feedbacks = new ArrayList<Feedback>();
	}

	public User(String email, String password, String salt) {
		super(email, password, salt);
	}

	public User(String email, String password, String salt, String username, String firstName, String lastName) {
		super(email, password, salt);

		this.name = username;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	public List<Feedback> getFeedbacks() {
		return feedbacks;
	}

	public void setFeedbacks(List<Feedback> feedbacks) {
		this.feedbacks = feedbacks;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
}
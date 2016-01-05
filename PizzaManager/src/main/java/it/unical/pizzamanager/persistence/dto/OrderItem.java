package it.unical.pizzamanager.persistence.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import it.unical.pizzamanager.persistence.dao.DatabaseHandler;

@Entity
@Table(name = "order_items")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "order_type", discriminatorType = DiscriminatorType.STRING)
@SequenceGenerator(name = "orderItemsGenerator", sequenceName = "order_items_sequence", initialValue = 1)
public abstract class OrderItem implements Serializable {

	private static final long serialVersionUID = -8807690064904416275L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderItemsGenerator")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "booking")
	private Booking booking;

	@ManyToOne
	@JoinColumn(name = "cart")
	private Cart cart;

	@Column(name = "cost")
	private Double cost;
	// instead of ha references add a boolean modified and the cost of item ordered so we can get
	// cost of an order directly with a join
	// without ask to table price, so ingredient cannot be ordered but a menu or a pizza can be
	// modified cost is evalueted application side and stored in database at purchace time

	public OrderItem() {
		this.id = DatabaseHandler.NO_ID;
		this.booking = null;
		this.cart = null;
		this.cost = 0.0;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}
}
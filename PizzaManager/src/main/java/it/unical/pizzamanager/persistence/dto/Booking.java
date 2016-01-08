package it.unical.pizzamanager.persistence.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import it.unical.pizzamanager.persistence.dao.DatabaseHandler;
import it.unical.pizzamanager.serializers.BookingSerializer;

@JsonSerialize(using = BookingSerializer.class)
@Entity
@Table(name = "bookings")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@SequenceGenerator(name = "bookingsSequence", sequenceName = "bookings_sequence", allocationSize = 1)
public abstract class Booking implements Serializable {

	private static final long serialVersionUID = 7661901592703829148L;

	public static final int PRIORITY_DEFAULT = 0;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bookingsSequence")
	@Column(name = "id")
	private Integer id;

	@Temporal(TemporalType.DATE)
	@Column(name = "date", nullable = false)
	private Date date;

	/*
	 * FIXME - Non abbiamo realmente bisogno di un altro attributo time: l'ora è già inclusa in un
	 * oggetto Date.
	 */
	@Temporal(TemporalType.TIME)
	@Column(name = "time", nullable = false)
	private Date time;

	@Column(name = "confirmed", nullable = false)
	private Boolean confirmed;

	/*
	 * finchè una prenotazione non è stata confermata il campo priority deve avere un valore di
	 * default. nel momento in cui però una prenotazione viene effettuata bisogna calcolare la
	 * priorità in base all'orario ed alla modalità di prenotazione.
	 */
	@Column(name = "priority", nullable = false)
	private Integer priority;

	@ManyToOne
	@JoinColumn(name = "user")
	private User user;

	@ManyToOne
	@JoinColumn(name = "pizzeria")
	private Pizzeria pizzeria;

	// il pagamento può essere null nel momento in cui non è stato effettuato
	// (relazione 0_to_1)
	@OneToOne
	@JoinColumn(name = "payment")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Payment payment;

	@OneToMany(mappedBy = "booking", fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<OrderItem> orderItems;

	// TODO preparationTime and bill vengono ricavate tramite procedure o
	// funzioni al momento in cui
	// ci servono

	public Booking() {
		this.id = DatabaseHandler.NO_ID;
		this.date = new Date();
		this.time = new Date();
		this.confirmed = false;
		this.priority = PRIORITY_DEFAULT;
		this.user = null;
		this.pizzeria = null;
		this.payment = null;
		this.orderItems = new ArrayList<OrderItem>();
	}

	public Booking(Date date, Date time, Boolean confirmed, Integer priority) {
		this.id = DatabaseHandler.NO_ID;
		this.date = date;
		this.time = time;
		this.confirmed = confirmed;
		this.priority = priority;
		this.user = null;
		this.pizzeria = null;
		this.payment = null;
		this.orderItems = new ArrayList<OrderItem>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Boolean getConfirmed() {
		return confirmed;
	}

	public void setConfirmed(Boolean confirmed) {
		this.confirmed = confirmed;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Pizzeria getPizzeria() {
		return pizzeria;
	}

	public void setPizzeria(Pizzeria pizzeria) {
		this.pizzeria = pizzeria;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
}

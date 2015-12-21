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

import it.unical.pizzamanager.persistence.dao.DatabaseHandler;

@Entity
@Table(name = "booking")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@SequenceGenerator(name = "bookingSequence", sequenceName = "booking_sequence", allocationSize = 1)
public abstract class Booking implements Serializable {

	private static final long serialVersionUID = 7661901592703829148L;

	private static final int PRIORITY_DEFAULT = 0;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bookingSequence")
	@Column(name = "id")
	private Integer id;

	@Temporal(TemporalType.DATE)
	@Column(name = "date", nullable = false)
	private Date date;

	/*
	 * FIXME - Non abbiamo realmente bisogno di un altro attributo time: l'ora è
	 * già inclusa in un oggetto Date.
	 */
	@Temporal(TemporalType.TIME)
	@Column(name = "time", nullable = false)
	private Date time;

	@Column(name = "confirmed", nullable = false)
	private Boolean confirmed;

	// colui che effettua la prenotazione, e può essere una persona(che telefona
	// e prenota) o un
	// utente
	@ManyToOne
	@JoinColumn(name = "person", nullable = true)
	private Person person;

	// il pagamento può essere null nel momento in cui non è stato effettuato
	// (relazione 0_to_1)
	@OneToOne(optional = true)
	@JoinColumn(name = "payment")
	private Payment payment;

	/*
	 * finchè una prenotazione non è stata confermata il campo priority deve
	 * avere un valore di default. nel momento in cui però una prenotazione
	 * viene effettuata bisogna calcolare la priorità in base all'orario ed alla
	 * modalità di prenotazione.
	 */
	@Column(name = "priority", nullable = false)
	private Integer priority;

	@OneToMany(mappedBy = "booking", fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<OrderItem> orderItems;

	@ManyToOne
	@JoinColumn(name = "pizzeria", nullable = true)
	private Pizzeria pizzeria;

	// TODO preparationTime and bill vengono ricavate tramite procedure o
	// funzioni al momento in cui
	// ci servono

	public Booking() {
		this.id = DatabaseHandler.NO_ID;
		this.date = new Date();
		this.time = new Date();
		this.confirmed = false;
		this.person = new Customer();
		this.payment = new Payment();
		this.priority = PRIORITY_DEFAULT;
		this.orderItems = new ArrayList<OrderItem>();
		this.pizzeria = new Pizzeria();
	}

	//costruttore utile ai fini di un esempietto
	public Booking(Integer priority) {
		this.id = DatabaseHandler.NO_ID;
		this.date = new Date();
		this.time = new Date();
		this.confirmed = true;
		this.person = null;
		this.payment = null;
		this.priority = priority;
		this.orderItems = null;
		this.pizzeria = null;

	}
	
	
	public Booking(Date date, Date time, Boolean confirmed, Person person, Payment payment,
			Integer priority, ArrayList<OrderItem> orderItems, Pizzeria pizzeria) {
		this.id = DatabaseHandler.NO_ID;
		this.date = date;
		this.time = time;
		this.confirmed = confirmed;
		this.person = person;
		this.payment = payment;
		this.priority = priority;
		this.orderItems = orderItems;
		this.pizzeria = pizzeria;
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

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public Pizzeria getPizzeria() {
		return pizzeria;
	}

	public void setPizzeria(Pizzeria pizzeria) {
		this.pizzeria = pizzeria;
	}
	
	
}

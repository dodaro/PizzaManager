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
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import it.unical.pizzamanager.persistence.dao.DatabaseHandler;

@Entity
@Table(name = "pizzeria_tables")
public class PizzeriaTable implements Serializable {

	private static final long serialVersionUID = 2946713211562844890L;

	private static final Integer NO_NUMBER = -1;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	// FIXME - la coppia pizzeria-numero dovrebbe essere unique..come facciamo?????
	// controlliamo lato client?
	@Column(name = "number", nullable = false)
	private Integer number;

	// show the actual table sits number
	@Column(name = "seats", nullable = false)
	private Integer seats;

	@Column(name = "min_seats", nullable = false)
	private Integer minSeats;

	@Column(name = "max_seats", nullable = false)
	private Integer maxSeats;

	@Column(name = "available", nullable = false)
	private Boolean available;

	@OneToMany(mappedBy = "pizzeriaTable", fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<RelationBookingTablePizzeriaTable> bookings;

	@ManyToOne
	@JoinColumn(name = "pizzeria")
	private Pizzeria pizzeria;

	public PizzeriaTable() {
		this.id = DatabaseHandler.NO_ID;
		this.number = NO_NUMBER;
		this.seats = 0;
		this.minSeats = 0;
		this.maxSeats = 0;
		this.available = true;
		this.bookings = new ArrayList<RelationBookingTablePizzeriaTable>();
		this.pizzeria = null;
	}

	public PizzeriaTable(Integer number, Integer minSeats, Integer maxSeats, Pizzeria pizzeria) {
		this.id = DatabaseHandler.NO_ID;
		this.number = number;
		this.seats = 0;
		this.minSeats = minSeats;
		this.maxSeats = maxSeats;
		this.available = true;
		this.bookings = new ArrayList<>();
		this.pizzeria = pizzeria;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getSeats() {
		return seats;
	}

	public void setSeats(Integer seats) {
		this.seats = seats;
	}

	public Integer getMinSeats() {
		return minSeats;
	}

	public void setMinSeats(Integer minSeats) {
		this.minSeats = minSeats;
	}

	public Integer getMaxSeats() {
		return maxSeats;
	}

	public void setMaxSeats(Integer maxSeats) {
		this.maxSeats = maxSeats;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public List<RelationBookingTablePizzeriaTable> getBookings() {
		return bookings;
	}

	public void setBookings(List<RelationBookingTablePizzeriaTable> bookings) {
		this.bookings = bookings;
	}

	public Pizzeria getPizzeria() {
		return pizzeria;
	}

	public void setPizzeria(Pizzeria pizzeria) {
		this.pizzeria = pizzeria;
	}
}
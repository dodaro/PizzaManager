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

	// la coppia pizzeria-numero dovrebbe essere unique..come facciamo?????
	// controlliamo lato client?
	@Column(name = "number", nullable = false)
	private Integer number;

	// show the actual table sits number
	@Column(name = "sits", nullable = false)
	private Integer sits;

	@Column(name = "min_sits", nullable = false)
	private Integer minSits;

	@Column(name = "max_sits", nullable = false)
	private Integer maxSits;

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
		this.sits = 0;
		this.minSits = 0;
		this.maxSits = 0;
		this.available = true;
		this.bookings = new ArrayList<RelationBookingTablePizzeriaTable>();
		this.pizzeria = null;
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

	public Integer getSits() {
		return sits;
	}

	public void setSits(Integer sits) {
		this.sits = sits;
	}

	public Integer getMinSits() {
		return minSits;
	}

	public void setMinSits(Integer minSits) {
		this.minSits = minSits;
	}

	public Integer getMaxSits() {
		return maxSits;
	}

	public void setMaxSits(Integer maxSits) {
		this.maxSits = maxSits;
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

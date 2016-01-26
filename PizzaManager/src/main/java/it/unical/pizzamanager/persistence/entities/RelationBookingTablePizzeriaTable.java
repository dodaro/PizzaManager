package it.unical.pizzamanager.persistence.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import it.unical.pizzamanager.persistence.dao.DatabaseHandler;

@Entity
@Table(name = "bookingtable_pizzeriatable")
public class RelationBookingTablePizzeriaTable implements Serializable {

	private static final long serialVersionUID = -3428771534570201572L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "booking")
	private BookingPizzeriaTable booking;

	@ManyToOne
	@JoinColumn(name = "pizzeriaTable")
	private PizzeriaTable pizzeriaTable;

	public RelationBookingTablePizzeriaTable() {
		this.id = DatabaseHandler.NO_ID;
		this.pizzeriaTable = null;
		this.booking = null;
	}
	
	public RelationBookingTablePizzeriaTable(PizzeriaTable pizzeriaTable,BookingPizzeriaTable booking) {
		this.id = DatabaseHandler.NO_ID;
		this.pizzeriaTable = pizzeriaTable;
		this.booking = booking;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@JsonIgnore
	public BookingPizzeriaTable getBooking() {
		return booking;
	}

	public void setBooking(BookingPizzeriaTable booking) {
		this.booking = booking;
	}

	@JsonIgnore
	public PizzeriaTable getPizzeriaTable() {
		return pizzeriaTable;
	}

	public void setPizzeriaTable(PizzeriaTable table) {
		this.pizzeriaTable = table;
	}
}

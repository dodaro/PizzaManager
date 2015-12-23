package it.unical.pizzamanager.persistence.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
		this.pizzeriaTable = new PizzeriaTable();
		this.booking = new BookingPizzeriaTable();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BookingPizzeriaTable getBooking() {
		return booking;
	}

	public void setBooking(BookingPizzeriaTable booking) {
		this.booking = booking;
	}

	public PizzeriaTable getPizzeriaTable() {
		return pizzeriaTable;
	}

	public void setPizzeriaTable(PizzeriaTable table) {
		this.pizzeriaTable = table;
	}
}

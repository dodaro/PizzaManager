package it.unical.pizzamanager.persistence.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@DiscriminatorValue("booking_table")
public class BookingPizzeriaTable extends Booking {

	private static final long serialVersionUID = -1611802959380912382L;

	@OneToMany(mappedBy = "booking", fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<RelationBookingTablePizzeriaTable> tableBooking;

	public BookingPizzeriaTable() {
		super();
		this.tableBooking = new ArrayList<RelationBookingTablePizzeriaTable>();
	}

	public List<RelationBookingTablePizzeriaTable> getTableBooking() {
		return tableBooking;
	}

	public void setTableBooking(List<RelationBookingTablePizzeriaTable> tableBooking) {
		this.tableBooking = tableBooking;
	}

	// FUNZIONE: dammi tutti i tavoli relativi ad una prenotazione di tavolo
	// "tb"
	// facendo il getRelationTableBookingTablePizzeria avrò già solo la lista di
	// tutti i tavoli relativi alla prenotazione

}

package it.unical.pizzamanager.persistence.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class RelationTableBookingTablePizzeria implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3428771534570201572L;
	private static final int NO_ID = -1;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "booking")
	private BookingTablePizzeria booking;
	
	@ManyToOne
	@JoinColumn(name = "tablePizzeria")
	private TablePizzeria table;
	
	public RelationTableBookingTablePizzeria() {
		// TODO Auto-generated constructor stub
		this.id=NO_ID;
		this.table=new TablePizzeria();
		this.booking=new BookingTablePizzeria();
		
	}

	public RelationTableBookingTablePizzeria(TablePizzeria table, BookingTablePizzeria booking) {
		
		this.id=NO_ID;
		this.table=table;
		this.booking=booking;		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BookingTablePizzeria getBooking() {
		return booking;
	}

	public void setBooking(BookingTablePizzeria booking) {
		this.booking = booking;
	}

	public TablePizzeria getTable() {
		return table;
	}

	public void setTable(TablePizzeria table) {
		this.table = table;
	}
	
	
}

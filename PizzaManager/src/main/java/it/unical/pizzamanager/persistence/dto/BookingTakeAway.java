package it.unical.pizzamanager.persistence.dto;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("booking_takeAway") 
public class BookingTakeAway extends Booking {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2403428020672529065L;

	public BookingTakeAway() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	public BookingTakeAway(Date date, Date time, Boolean confirmed,Person person, Payment payment,Integer priority, ArrayList<RelationTableBookingTablePizzeria> tablebooking/*, ArrayList<OrderItems> orderItems, Pizzeria pizzeria*/) {
		// TODO Auto-generated constructor stub
		super(date,time,confirmed,person, payment,priorit/*, orderItems, pizzeria*/);
	}

}

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
		super();
	}

	public BookingTakeAway(Date date, Date time, Boolean confirmed, Person person, Payment payment, Integer priority,
			ArrayList<RelationTableBookingTablePizzeria> tablebooking, ArrayList<OrderItem> orderItems,
			Pizzeria pizzeria) {
		super(date, time, confirmed, person, payment, priority, orderItems, pizzeria);
	}

}

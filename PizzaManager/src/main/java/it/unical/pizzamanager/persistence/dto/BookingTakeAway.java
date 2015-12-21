package it.unical.pizzamanager.persistence.dto;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("booking_takeaway")
public class BookingTakeAway extends Booking {

	private static final long serialVersionUID = 2403428020672529065L;

	public BookingTakeAway() {
		super();
	}
}

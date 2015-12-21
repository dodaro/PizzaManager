package it.unical.pizzamanager.populator;

import org.springframework.context.ApplicationContext;

import it.unical.pizzamanager.persistence.dto.Booking;
import it.unical.pizzamanager.persistence.dto.BookingTakeAway;

public class BookingPopulator extends Populator {

	public BookingPopulator(ApplicationContext context) {
		super(context);
	}

	@Override
	protected void populate() {
		Booking booking = new BookingTakeAway();
	}

}

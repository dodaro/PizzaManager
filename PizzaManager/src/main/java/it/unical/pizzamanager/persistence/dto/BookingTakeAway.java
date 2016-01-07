package it.unical.pizzamanager.persistence.dto;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import it.unical.pizzamanager.serializers.BookingTakeAwaySerializer;

@JsonSerialize(using = BookingTakeAwaySerializer.class)
@Entity
@DiscriminatorValue("booking_takeaway")
public class BookingTakeAway extends Booking {

	private static final long serialVersionUID = 2403428020672529065L;

	public BookingTakeAway() {
		super();
	}

	public BookingTakeAway(Date date, Date time, Boolean confirmed, Integer priority) {
		super(date, time, confirmed, priority);
	}
}

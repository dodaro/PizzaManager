package it.unical.pizzamanager.model;

import java.util.Date;

public class PizzeriaCartBooking {

	String pizzeria;
	Date date;
	private String bookingType;

	public PizzeriaCartBooking() {
		this.pizzeria = "";
		this.date = null;
		this.bookingType = "";
	}

	public String getPizzeria() {
		return pizzeria;
	}

	public void setPizzeria(String pizzeria) {
		this.pizzeria = pizzeria;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getBookingType() {
		return bookingType;
	}

	public void setBookingType(String bookingType) {
		this.bookingType = bookingType;
	}

}

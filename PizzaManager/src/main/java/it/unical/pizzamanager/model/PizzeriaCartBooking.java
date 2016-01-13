package it.unical.pizzamanager.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PizzeriaCartBooking {
	
	String pizzeria;
	List<Integer> items;
	Date data;
	private String bookingType;
	
	public PizzeriaCartBooking() {
		this.pizzeria="";
		this.items=new ArrayList<>();
		this.data=null;
		this.bookingType="";
	}

	public String getPizzeria() {
		return pizzeria;
	}

	public void setPizzeria(String pizzeria) {
		this.pizzeria = pizzeria;
	}

	public List<Integer> getItems() {
		return items;
	}

	public void setItems(List<Integer> items) {
		this.items = items;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getBookingType() {
		return bookingType;
	}

	public void setBookingType(String bookingType) {
		this.bookingType = bookingType;
	}

}

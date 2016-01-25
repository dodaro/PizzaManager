package it.unical.pizzamanager.model;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookingUserDisplay {

	private int id;

	private String bookingType;

	private String pizzeria;

	private Date date;

	private List<OrderItemDisplay> items;

	private boolean actived;

	private Double bill;

	private String preparationTime;
	
	private String completationTime;

	private String identifier;
	
	private String token;

	private boolean payed;
	
	public BookingUserDisplay() {
		this.id = 0;
		this.bookingType = "";
		this.pizzeria = "";
		this.date = null;
		this.items = new ArrayList<>();
		this.actived = false;
		this.bill = 0.0;
		this.preparationTime = "";
		this.identifier = "";
		this.token="";
		this.completationTime="";
		this.payed=false;

	}

	public String getBookingType() {
		return bookingType;
	}

	public void setBookingType(String bookingType) {
		this.bookingType = bookingType;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<OrderItemDisplay> getItems() {
		return items;
	}

	public void setItems(List<OrderItemDisplay> items) {
		this.items = items;
	}

	public boolean isActived() {
		return actived;
	}

	public void setActived(boolean actived) {
		this.actived = actived;
	}

	public Double getBill() {
		return bill;
	}

	public void setBill(Double bill) {
		this.bill = bill;
	}

	public String getPreparationTime() {
		return preparationTime;
	}

	public void setPreparationTime(String preparationTime) {
		this.preparationTime = preparationTime;
	}

	public String getPizzeria() {
		return pizzeria;
	}

	public void setPizzeria(String pizzeria) {
		this.pizzeria = pizzeria;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getBillLabel() {
		NumberFormat formatter = new DecimalFormat("#0.00");
		return formatter.format(bill);

	}

	public String getCompletationTime() {
		return completationTime;
	}

	public void setCompletationTime(String completationTime) {
		this.completationTime = completationTime;
	}

	public boolean isPayed() {
		return payed;
	}

	public void setPayed(boolean payed) {
		this.payed = payed;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	

}

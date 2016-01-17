package it.unical.pizzamanager.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookingUserDispay {

	private String bookingType;
	
	private Date date;
	
	private UserDisplay user;
	
	private List<OrderItemDisplay> items;
	
	private boolean actived;
	
	private Double bill;
	
	private Double preparationTime;
	
	
	public BookingUserDispay() {
		this.bookingType="";
		this.date=null;
		this.user=null;
		this.items=new ArrayList<>();
		this.actived=false;
		this.bill=0.0;
		this.preparationTime=0.0;
		
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


	public UserDisplay getUser() {
		return user;
	}


	public void setUser(UserDisplay user) {
		this.user = user;
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


	public Double getPreparationTime() {
		return preparationTime;
	}


	public void setPreparationTime(Double preparationTime) {
		this.preparationTime = preparationTime;
	}
	
	
}

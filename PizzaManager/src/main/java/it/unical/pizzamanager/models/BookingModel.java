package it.unical.pizzamanager.models;

import java.util.Date;
import java.util.List;

public class BookingModel {
	private Integer id;
	private String type;//TODO DEVE DIVENTARE UN ENUM
	private String date;
	private String time;
	private Boolean payment; //TODO deve diventare un boolean
	private List<BeverageModel> beverages;
	private List<PizzaModel> pizzas;
	
	
	
	public BookingModel() {

	}

	public BookingModel(Integer id,String type, String date, String time, Boolean payment, List<BeverageModel> beverages, List<PizzaModel> pizzas) {

		this.id=id;
		this.type=type;
		this.date=date;
		this.time=time;
		this.payment=payment;
		this.beverages=beverages;
		this.pizzas=pizzas;
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Boolean getPayment() {
		return payment;
	}

	public void setPayment(Boolean payment) {
		this.payment = payment;
	}

	public List<BeverageModel> getBeverages() {
		return beverages;
	}

	public void setBeverages(List<BeverageModel> beverages) {
		this.beverages = beverages;
	}

	public List<PizzaModel> getPizzas() {
		return pizzas;
	}

	public void setPizzas(List<PizzaModel> pizzas) {
		this.pizzas = pizzas;
	}

	
	
}

package it.unical.pizzamanager.models;

import java.util.List;

public class BookingModel {
	private Integer id;
	private String type;//TODO DEVE DIVENTARE UN ENUM
	private String date;
	private String time;
	private Boolean payment; //TODO deve diventare un boolean
	private List<BeverageModel> beverages;
	private List<PizzaModel> pizzas;
	private String user;
	private Double bill;
	private String underTheNameOf;
	private AddressModel address;//id dell'address
	private List<Integer> tables;//lista di id dei tavoli
	private Boolean confirmed;
	
	
	
	
	public BookingModel() {

	}

	public BookingModel(Integer id,String type, String date, String time, Boolean payment,Boolean confirmed, List<BeverageModel> beverages, List<PizzaModel> pizzas, String user, Double bill) {

		this.id=id;
		this.type=type;
		this.date=date;
		this.time=time;
		this.payment=payment;
		this.beverages=beverages;
		this.pizzas=pizzas;
		this.user=user;
		this.bill=bill;
		//TODO  FIX
		this.underTheNameOf="";
		this.address=null;
		this.tables=null;
		this.confirmed=confirmed;
		
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

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Double getBill() {
		return bill;
	}

	public void setBill(Double bill) {
		this.bill = bill;
	}

	public String getUnderTheNameOf() {
		return underTheNameOf;
	}

	public void setUnderTheNameOf(String underTheNameOf) {
		this.underTheNameOf = underTheNameOf;
	}

	public AddressModel getAddress() {
		return address;
	}

	public void setAddress(AddressModel address) {
		this.address = address;
	}

	public List<Integer> getTables() {
		return tables;
	}

	public void setTables(List<Integer> tables) {
		this.tables = tables;
	}

	public Boolean getConfirmed() {
		return confirmed;
	}

	public void setConfirmed(Boolean confirmed) {
		this.confirmed = confirmed;
	}


	
}

package it.unical.pizzamanager.models;

public class BeverageModel {
	private Integer id;
	private String number;
	
	public BeverageModel() {
		id=-1;//TODO static string default
		number="";
	}
	
	public BeverageModel(Integer id,String number) {
		this.id=-1;
		this.number=number;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	
}

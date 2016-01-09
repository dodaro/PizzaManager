package it.unical.pizzamanager.forms;

public class PizzeriaTableForm {

	private String action;

	private int id;
	private int number;
	private int minSeats;
	private int maxSeats;

	public PizzeriaTableForm() {
		this.action = "";
		this.id = -1;
		this.number = -1;
		this.minSeats = -1;
		this.maxSeats = -1;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getMinSeats() {
		return minSeats;
	}

	public void setMinSeats(int minSeats) {
		this.minSeats = minSeats;
	}

	public int getMaxSeats() {
		return maxSeats;
	}

	public void setMaxSeats(int maxSeats) {
		this.maxSeats = maxSeats;
	}

}

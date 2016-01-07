package it.unical.pizzamanager.model;

public class PizzeriaTableForm {

	private String action;

	private int number;
	private int minSeats;
	private int maxSeats;

	public PizzeriaTableForm() {
		this.action = "";
		this.number = -1;
		this.minSeats = -1;
		this.maxSeats = -1;
	}

	public PizzeriaTableForm(String action, int number, int minSeats, int maxSeats) {
		this.action = action;
		this.number = number;
		this.minSeats = minSeats;
		this.maxSeats = maxSeats;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
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

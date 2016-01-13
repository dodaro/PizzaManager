package it.unical.pizzamanager.model;

import java.util.ArrayList;
import java.util.List;

public class ItemToBook {

	private List<Integer> ids;
	private List<Integer> numbers;

	public ItemToBook() {
		this.ids = new ArrayList<>();
		this.numbers = new ArrayList<>();
	}

	public List<Integer> getNumbers() {
		return numbers;
	}

	public void setNumbers(List<Integer> numbers) {
		this.numbers = numbers;
	}

	public List<Integer> getIds() {
		return ids;
	}

	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}
}

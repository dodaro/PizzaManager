package it.unical.pizzamanager.models;

public class IngredientModel {

	private Integer id;
	private String name;
	
	public IngredientModel() {
		id=-1;
		name="";
	}
	
	public IngredientModel(Integer id, String name) {
		this.id=id;
		this.name=name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}

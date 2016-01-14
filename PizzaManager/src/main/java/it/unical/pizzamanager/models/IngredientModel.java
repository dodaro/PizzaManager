package it.unical.pizzamanager.models;

public class IngredientModel {

	private Integer id;
	private String name;
	private String text;
	
	public IngredientModel() {
		id=-1;
		name="";
		text="";
	}
	
	public IngredientModel(Integer id, String name,String text) {
		this.id=id;
		this.name=name;
		this.text=text;
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

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	
}

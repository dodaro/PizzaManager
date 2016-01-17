package it.unical.pizzamanager.models;

import java.util.List;

public class PizzaModel {

	private String name;
	private Integer number;
	private String size;
	private String glutenFree;
	List<IngredientModel> ingredientsAdded;
	List<IngredientModel> ingredientsRemoved;
	List<IngredientModel> ingredientsBase;
	private Double priceEach;
	
	public PizzaModel() {
		this.name="";
		this.number=-1;//TODO aggiungere default static
		this.size="";
		this.glutenFree="";
		this.ingredientsAdded=null;
		this.ingredientsBase=null;
		this.ingredientsRemoved=null;
		this.priceEach=-1.0;
	}
	public PizzaModel(String name, Integer number, String size, String glutenFree, List<IngredientModel> ingredientsAdded, List<IngredientModel> ingredientsBase, List<IngredientModel> ingredientsRemoved, Double priceEach) {
		this.name="";
		this.number=-1;//TODO aggiungere default static
		this.size="";
		this.glutenFree="";
		this.ingredientsAdded=ingredientsAdded;
		this.ingredientsBase=ingredientsBase;
		this.ingredientsRemoved=ingredientsRemoved;
		this.priceEach=priceEach;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getGlutenFree() {
		return glutenFree;
	}

	public void setGluten(String glutenFree) {
		this.glutenFree = glutenFree;
	}

	public List<IngredientModel> getIngredientsAdded() {
		return ingredientsAdded;
	}

	public void setIngredientsAdded(List<IngredientModel> ingredientsAdded) {
		this.ingredientsAdded = ingredientsAdded;
	}

	public List<IngredientModel> getIngredientsRemoved() {
		return ingredientsRemoved;
	}

	public void setIngredientsRemoved(List<IngredientModel> ingredientsRemoved) {
		this.ingredientsRemoved = ingredientsRemoved;
	}

	public List<IngredientModel> getIngredientsBase() {
		return ingredientsBase;
	}

	public void setIngredientsBase(List<IngredientModel> ingredientsBase) {
		this.ingredientsBase = ingredientsBase;
	}
	public Double getPriceEach() {
		return priceEach;
	}
	public void setPriceEach(Double priceEach) {
		this.priceEach = priceEach;
	}
	
	
}

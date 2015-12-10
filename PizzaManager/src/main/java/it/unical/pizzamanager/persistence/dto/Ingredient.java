package it.unical.pizzamanager.persistence.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="ingredients")
public class Ingredient implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7811955001213258947L;
	
	public static final String VEGETABLE_TYPE="Vegetable";
	public static final String MEAT_TYPE="Meat";
	public static final String FISH_TYPE="Fish";
	
	@Id
	@Column(name = "code")
	private int code;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name="type")
	private String type;
	
	@OneToMany(mappedBy = "ingredient", fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<PizzaIngredient> pizzaIngredient;

	
	public Ingredient() {
		code=0;
		name="";
		type=VEGETABLE_TYPE;
		pizzaIngredient=new ArrayList<>();
	}
	
	public Ingredient(int code,String name, String type){
		this.code=code;
		this.name=name;
		this.type=type;
		pizzaIngredient=new ArrayList<>();
	}

	public Ingredient(int code,String name, String type,List<PizzaIngredient> pizzaIngredient){
		this.code=code;
		this.name=name;
		this.type=type;
		this.pizzaIngredient=pizzaIngredient;
	}
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<PizzaIngredient> getPizzaIngredient() {
		return pizzaIngredient;
	}

	public void setPizzaIngredient(List<PizzaIngredient> pizzaIngredient) {
		this.pizzaIngredient = pizzaIngredient;
	}
}

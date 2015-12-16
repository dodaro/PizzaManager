package it.unical.pizzamanager.persistence.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import it.unical.pizzamanager.persistence.dao.DatabaseHandler;

@Entity
@Table(name = "ingredients")
@SequenceGenerator(name = "ingredientsGenerator", sequenceName = "ingredients_sequence", initialValue = 1)
public class Ingredient implements Serializable {

	private static final long serialVersionUID = 2041182307938465963L;

	public static final String TYPE_VEGETABLE = "vegetable";
	public static final String TYPE_MEAT = "meat";
	public static final String TYPE_FISH = "fish";

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ingredientsGenerator")
	private Integer id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "type")
	private String type;

	@OneToMany(mappedBy = "ingredient", fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<RelationPizzaIngredient> pizzaIngredient;

	/*
	 * It seems to be no need for a RelationPizzeriaIngredient list, since it's unlikely we want to
	 * pull from an ingredient all pizzerias using that ingredient.
	 */

	public Ingredient() {
		id = DatabaseHandler.NO_ID;
		name = "";
		type = TYPE_VEGETABLE;
		pizzaIngredient = new ArrayList<>();
	}

	public Ingredient(String name, String type) {
		this.id = DatabaseHandler.NO_ID;
		this.name = name;
		this.type = type;
		pizzaIngredient = new ArrayList<>();
	}

	public Ingredient(String name, String type, List<RelationPizzaIngredient> pizzaIngredient) {
		this.id = DatabaseHandler.NO_ID;
		this.name = name;
		this.type = type;
		this.pizzaIngredient = pizzaIngredient;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<RelationPizzaIngredient> getPizzaIngredient() {
		return pizzaIngredient;
	}

	public void setPizzaIngredient(List<RelationPizzaIngredient> pizzaIngredient) {
		this.pizzaIngredient = pizzaIngredient;
	}
}

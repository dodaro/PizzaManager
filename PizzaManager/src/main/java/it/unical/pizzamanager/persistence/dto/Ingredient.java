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

import com.fasterxml.jackson.annotation.JsonIgnore;

import it.unical.pizzamanager.persistence.dao.DatabaseHandler;

@Entity
@Table(name = "ingredients")
@SequenceGenerator(name = "ingredientsGenerator", sequenceName = "ingredients_sequence", initialValue = 1)
public class Ingredient implements Serializable {

	private static final long serialVersionUID = 2041182307938465963L;

	public enum IngredientType {
		VEGETABLE, MEAT, FISH, CHEESE, NOT_SPECIFIED
	}

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ingredientsGenerator")
	private Integer id;

	@Column(name = "name", unique = true, nullable = false)
	private String name;

	@Column(name = "type", nullable = false)
	private IngredientType type;

	@JsonIgnore
	@OneToMany(mappedBy = "ingredient", fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<RelationPizzaIngredient> ingredientOf;

	/*
	 * It seems to be no need for a RelationPizzeriaIngredient list, since it's unlikely we want to
	 * pull from an ingredient all pizzerias using that ingredient.
	 */

	public Ingredient() {
		id = DatabaseHandler.NO_ID;
		name = "";
		type = IngredientType.NOT_SPECIFIED;
		ingredientOf = new ArrayList<RelationPizzaIngredient>();
	}

	public Ingredient(String name, IngredientType type) {
		this.id = DatabaseHandler.NO_ID;
		this.name = name;
		this.type = type;
		ingredientOf = new ArrayList<RelationPizzaIngredient>();
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

	public IngredientType getType() {
		return type;
	}

	public void setType(IngredientType type) {
		this.type = type;
	}

	@JsonIgnore
	public List<RelationPizzaIngredient> getIngredientOf() {
		return ingredientOf;
	}

	@JsonIgnore
	public void setIngredientOf(List<RelationPizzaIngredient> pizzaIngredient) {
		this.ingredientOf = pizzaIngredient;
	}
}

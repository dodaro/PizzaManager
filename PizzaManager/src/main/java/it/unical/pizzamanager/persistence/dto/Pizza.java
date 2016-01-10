package it.unical.pizzamanager.persistence.dto;

import java.io.Serializable;
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

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import it.unical.pizzamanager.persistence.dao.DatabaseHandler;

@Entity
@Table(name = "pizzas")
@SequenceGenerator(name = "pizzasGenerator", sequenceName = "pizzas_sequence", initialValue = 1)
public class Pizza implements Serializable {

	private static final long serialVersionUID = 6079186121128866192L;

	public enum PizzaSize {
		SMALL("Small"), NORMAL("Normal"), MAXI("Maxi");

		private String string;

		PizzaSize(String string) {
			this.string = string;
		}

		public String getString() {
			return string;
		}
	}

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pizzasGenerator")
	private Integer id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "special", nullable = false)
	private boolean special;

	// TODO: convertire in lazy e sistemare il dao
	// vado di fretta per testare le jsp --> by David

	@OneToMany(mappedBy = "pizza", fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@Cascade(value = CascadeType.SAVE_UPDATE)
	private List<RelationPizzaIngredient> pizzaIngredients;

	@OneToMany(mappedBy = "pizza", fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<RelationPizzeriaPizza> pizzasPriceList;

	// IL PROBLEMA PER I JSON SONO LE RELAZIONI molti a molti
	// IL CONTROLLER VA IN LOOP NON APPENA PROVA A MAPPARE LA RELAZIONE molti a molti DI UN OGGETTO
	// inoltre la versione di jackson deve essere 2.4.3
	// nella 2.5 non funziona il jsonignore

	/*
	 * RISOLTO: il problema è sull'elemento riflessivo della relazione molti a molti, ovvero in
	 * questo caso sulla relazione RelationPizzaIngredient ho dovuto ignorare la colonna stessa
	 * Pizza perchè altrimenti va in loop su se stesso
	 */

	// rimossi gli order items

	public Pizza() {
		this.id = DatabaseHandler.NO_ID;
		this.name = "";
		this.description = "";
		this.special = false;
		this.pizzaIngredients = null;
		this.pizzasPriceList = null;
	}

	public Pizza(String name, String description, Boolean special) {
		this.id = DatabaseHandler.NO_ID;
		this.name = name;
		this.description = description;
		this.special = special;
		this.pizzaIngredients = null;
		this.pizzasPriceList = null;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isSpecial() {
		return special;
	}

	public void setSpecial(boolean special) {
		this.special = special;
	}

	public List<RelationPizzaIngredient> getPizzaIngredients() {
		return pizzaIngredients;
	}

	public void setPizzaIngredients(List<RelationPizzaIngredient> pizzaIngredients) {
		this.pizzaIngredients = pizzaIngredients;
	}

	public List<RelationPizzeriaPizza> getPizzasPriceList() {
		return pizzasPriceList;
	}

	public void setPizzasPriceList(List<RelationPizzeriaPizza> pizzasPriceList) {
		this.pizzasPriceList = pizzasPriceList;
	}
}
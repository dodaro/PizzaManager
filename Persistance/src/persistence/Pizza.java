package persistence;

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
@Table(name = "pizze")
public class Pizza implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6079186121128866192L;

	public static final String MAXI = "MAXI";
	public static final String NORMAL = "NORMAL";

	@Id
	@Column(name = "code")
	private int code;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "preparationTime", nullable = false)
	private int preparationTime;

	@Column(name = "celiac")
	private boolean celiac;

	@Column(name = "description")
	private String description;

	@Column(name = "size", nullable = false)
	private String size;

	@Column(name = "special", nullable = false)
	private boolean special;

	@OneToMany(mappedBy = "pizza", fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<PizzaIngredient> pizzaIngredient;

	@OneToMany(mappedBy = "pizza", fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<PizzaPriceList> pizzaPriceList;

	@OneToMany(mappedBy = "pizza", fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Menu> menu;

	public Pizza() {
		code = 0;
		name = "";
		preparationTime = 0;
		celiac = false;
		description = "";
		size = Pizza.MAXI;
		special = false;
		pizzaIngredient = new ArrayList<>();
		menu = new ArrayList<>();
		pizzaPriceList = new ArrayList<>();
	}

	public Pizza(int code, String name, int preparationTime, boolean celiac, String description, String size,
			Boolean special) {
		this.code = code;
		this.preparationTime = preparationTime;
		this.celiac = celiac;
		this.description = description;
		this.size = size;
		this.special = special;
		this.pizzaIngredient = new ArrayList<>();
		this.menu = new ArrayList<>();
		this.pizzaPriceList = new ArrayList<>();
	}

	public Pizza(int code, String name, int preparationTime, boolean celiac, String description, String size,
			Boolean special, ArrayList<PizzaIngredient> pizzaIngredient, ArrayList<Menu> menu,
			ArrayList<PizzaPriceList> pizzaPriceList) {
		this.code = code;
		this.preparationTime = preparationTime;
		this.celiac = celiac;
		this.description = description;
		this.size = size;
		this.special = special;
		this.pizzaIngredient = pizzaIngredient;
		this.menu = menu;
		this.pizzaPriceList = pizzaPriceList;
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

	public int getPreparationTime() {
		return preparationTime;
	}

	public void setPreparationTime(int preparationTime) {
		this.preparationTime = preparationTime;
	}

	public boolean isCeliac() {
		return celiac;
	}

	public void setCeliac(boolean celiac) {
		this.celiac = celiac;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public List<PizzaIngredient> getPizzaIngredient() {
		return pizzaIngredient;
	}

	public void setPizzaIngredient(List<PizzaIngredient> pizzaIngredient) {
		this.pizzaIngredient = pizzaIngredient;
	}

	public List<Menu> getMenu() {
		return menu;
	}

	public void setMenu(List<Menu> menu) {
		this.menu = menu;
	}

	public List<PizzaPriceList> getPizzaPriceList() {
		return pizzaPriceList;
	}

	public void setPizzaPriceList(List<PizzaPriceList> pizzaPriceList) {
		this.pizzaPriceList = pizzaPriceList;
	}

}

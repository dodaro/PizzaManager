package persistence;

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

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "pizzerias")
@SequenceGenerator(name = "pizzeriasGenerator", sequenceName = "pizzerias_sequence", initialValue = 1)
public class Pizzeria {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pizzeriasGenerator")
	@Column(name = "id")
	private Integer id;

	@Column(name = "name")
	private String name;

	@OneToMany(mappedBy = "pizzeria", fetch = FetchType.EAGER)
	@Cascade(value = CascadeType.SAVE_UPDATE)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<RelationPizzeriaPizza> pizzasPrices;

	public Pizzeria() {
		this.id = -1;
		this.name = "";
		this.pizzasPrices = new ArrayList<>();
	}

	public Pizzeria(String name) {
		this.id = -1;
		this.name = name;
		this.pizzasPrices = new ArrayList<>();
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

	public List<RelationPizzeriaPizza> getPizzasPrices() {
		return pizzasPrices;
	}

	public void setPizzasPrices(List<RelationPizzeriaPizza> pizzasPrices) {
		this.pizzasPrices = pizzasPrices;
	}

	public void addPizza(Pizza pizza, Double price) {
		pizzasPrices.add(new RelationPizzeriaPizza(this, pizza, price));
	}
}

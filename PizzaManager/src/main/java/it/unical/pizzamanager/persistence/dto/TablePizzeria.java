package it.unical.pizzamanager.persistence.dto;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "tables_pizzeria")
public class TablePizzeria implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2946713211562844890L;
	private static final int NO_ID = -1;

	@Id
	@SequenceGenerator(name = "TablePizzeriaSequence", sequenceName = "table_seq", allocationSize = 1)
	// corrispective sql --> CREATE SEQUENCE my_seq START WITH 1 INCREMENT BY 1;
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TablePizzeriaSequence")
	@Column(name = "id")
	private Integer id;

	// la coppia pizzeria-numero dovrebbe essere unique..come facciamo?????
	// controlliamo lato client?
	@Column(name = "number", nullable = false)
	private Integer number;

	// show the actual table sits number
	@Column(name = "sits", nullable = false)
	private Integer sits;

	@Column(name = "max_sits", nullable = false)
	private Integer maxSits;

	@Column(name = "min_sits", nullable = false)
	private Integer minSits;

	// Boolean should be the BIT in sql
	@Column(name = "available", nullable = false)
	private Boolean available;

	@OneToMany(mappedBy = "tablePizzeria", fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<RelationTableBookingTablePizzeria> tableBooking;

	@ManyToOne
	@JoinColumn(name = "pizzeria")
	private Pizzeria pizzeria;

	public TablePizzeria() {

		this.id = NO_ID;
		this.number = 0;
		this.sits = 0;
		this.maxSits = 0;
		this.minSits = 0;
		this.available = true;
		this.pizzeria = new Pizzeria();

	}

	public TablePizzeria(Integer number, Integer sits, Integer maxSits, Integer minSits, Boolean available,
			Pizzeria pizzeria) {

		this.id = NO_ID;
		this.number = number;
		this.sits = sits;
		this.maxSits = maxSits;
		this.minSits = minSits;
		this.available = available;
		this.pizzeria = pizzeria;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getSits() {
		return sits;
	}

	public void setSits(Integer sits) {
		this.sits = sits;
	}

	public Integer getMaxSits() {
		return maxSits;
	}

	public void setMaxSits(Integer maxSits) {
		this.maxSits = maxSits;
	}

	public Integer getMinSits() {
		return minSits;
	}

	public void setMinSits(Integer minSits) {
		this.minSits = minSits;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public List<RelationTableBookingTablePizzeria> getTableBooking() {
		return tableBooking;
	}

	public void setTableBooking(List<RelationTableBookingTablePizzeria> tableBooking) {
		this.tableBooking = tableBooking;
	}

	public Pizzeria getPizzeria() {
		return pizzeria;
	}

	public void setPizzeria(Pizzeria pizzeria) {
		this.pizzeria = pizzeria;
	}

	@Override
	public String toString() {
		return "Table " + id + ": sits = " + this.sits + ", number = " + this.number + ", maxSits = " + this.maxSits
				+ ", minSits = " + this.minSits + ", sits = " + this.sits;
	}

	// TODO chiedere a dodaro la solita questione ed eventualmente aggiungere i
	// seguenti metodi
	// Le seguenti table_utility vengono ricavate a partire da getTableBooking:

	// FUNZIONE: dammi tutte le prenotazioni del tavolo 't' in una certa data
	// 'd'

	// FUNZIONE: dammi tutte le prenotazioni del tavolo 't' in una certa data
	// 'd' e ad una certa ora 'h'

	// FUNZIONE: dammi tutte le prenotazioni del tavolo 't' ad una certa ora 'h'

}

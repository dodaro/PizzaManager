package it.unical.pizzamanager.persistence.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import it.unical.pizzamanager.persistence.dao.DatabaseHandler;

@Entity
@Table(name = "offers")
@SequenceGenerator(name = "offersGenerator", sequenceName = "offers_sequence", initialValue = 1)
public class Offer implements Serializable {

	private static final long serialVersionUID = 415331303785623656L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "offersGenerator")
	private Integer id;

	@Column(name = "discount", nullable = false)
	private Double discount;

	@Temporal(TemporalType.DATE)
	@Column(name = "begin_date", nullable = false)
	private Date beginDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "end_date", nullable = false)
	private Date endDate;

	@ManyToOne
	@JoinColumn(name = "menu")
	private RelationPizzeriaMenu menuPriceList;

	public Offer() {
		id = DatabaseHandler.NO_ID;
		discount = 0.0;
		beginDate = new Date();
		endDate = new Date();
		menuPriceList = new RelationPizzeriaMenu();
	}

	public Offer(Double discount, Date beginDate, Date endDate,
			RelationPizzeriaMenu menuPriceList) {
		this.id = DatabaseHandler.NO_ID;
		this.discount = discount;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.menuPriceList = menuPriceList;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public RelationPizzeriaMenu getMenuPriceList() {
		return menuPriceList;
	}

	public void setMenuPriceList(RelationPizzeriaMenu menuPriceList) {
		this.menuPriceList = menuPriceList;
	}
}

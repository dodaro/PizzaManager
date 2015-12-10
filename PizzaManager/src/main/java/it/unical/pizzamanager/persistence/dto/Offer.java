package it.unical.pizzamanager.persistence.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "offers")
public class Offer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 415331303785623656L;

	@Id
	@Column(name = "code")
	private int code;

	@Column(name = "discount", nullable = false)
	private int discount;

	@Temporal(TemporalType.DATE)
	@Column(name = "begin_date", nullable = false)
	private Date beginDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "end_date", nullable = false)
	private Date endDate;

	@ManyToOne
	@JoinColumn(name = "menuPriceList")
	private MenuPriceList menuPriceList;

	public Offer() {
		code = 0;
		discount = 0;
		beginDate = null;
		endDate = null;
		menuPriceList = new MenuPriceList();
	}

	public Offer(int code, int discount, Date beginDate, Date endDate, MenuPriceList menuPriceList) {
		this.code = code;
		this.discount = discount;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.menuPriceList = menuPriceList;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
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

	public MenuPriceList getMenuPriceList() {
		return menuPriceList;
	}

	public void setMenuPriceList(MenuPriceList menuPriceList) {
		this.menuPriceList = menuPriceList;
	}

}

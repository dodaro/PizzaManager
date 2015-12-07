package persistence;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "beveragePriceLists")
public class BeveragePriceList implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7175661908750242983L;

	@Id
	@Column(name = "id")
	private int id;

	@ManyToOne
	@JoinColumn(name = "beverage")
	private Beverage beverage;

	@Column(name = "price")
	private int price;

	public BeveragePriceList() {
		id=1;
		beverage=null;
		price=0;
	}
	public BeveragePriceList(int id, Beverage beverage,int price) {
		this.id=id;
		this.beverage=beverage;
		this.price=price;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Beverage getBeverage() {
		return beverage;
	}

	public void setBeverage(Beverage beverage) {
		this.beverage = beverage;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	
	
}

package persistence;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "menuPriceLists")
public class MenuPriceList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3132509529862670681L;

	@Id
	@Column(name = "id")
	private int id;

	@ManyToOne
	@JoinColumn(name = "menu")
	private Menu menu;

	@Column(name = "price")
	private int price;

	@OneToMany(mappedBy = "menuPriceList", fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Offer> offers;

	public MenuPriceList() {
		id = 0;
		menu = new Menu();
		price = 0;
		offers = new ArrayList<>();
	}

	public MenuPriceList(int id, Menu menu, int price) {
		this.id = id;
		this.menu = menu;
		this.price = price;
		this.offers = new ArrayList<>();
	}

	public MenuPriceList(int id, Menu menu, int price, ArrayList<Offer> offers) {
		this.id = id;
		this.menu = menu;
		this.price = price;
		this.offers = offers;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public List<Offer> getOffers() {
		return offers;
	}

	public void setOffers(List<Offer> offers) {
		this.offers = offers;
	}
}

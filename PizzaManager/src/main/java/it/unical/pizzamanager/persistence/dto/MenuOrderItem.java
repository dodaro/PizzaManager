package it.unical.pizzamanager.persistence.dto;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("menu")
public class MenuOrderItem extends OrderItem {

	private static final long serialVersionUID = 3680503653087012041L;

	@ManyToOne
	@JoinColumn(name = "menu")
	private Menu menu;

	public MenuOrderItem() {
		super();
		menu = null;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}
}
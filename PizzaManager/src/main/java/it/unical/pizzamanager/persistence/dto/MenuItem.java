package it.unical.pizzamanager.persistence.dto;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("menuItem")
public class MenuItem extends OrderItem {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3680503653087012041L;
	
	@ManyToOne
	@JoinColumn(name = "menu")
	private Menu menu;
	
	public MenuItem() {
		super();
		menu=new Menu();
	}
	
	public MenuItem(Menu menu,Double cost) {
		super(cost);
		this.menu=menu;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}
}

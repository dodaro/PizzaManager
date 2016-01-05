package it.unical.pizzamanager.populator;

import org.springframework.context.ApplicationContext;

public abstract class Populator {

	protected ApplicationContext context;

	public Populator(ApplicationContext context) {
		this.context = context;
	}

	protected abstract void populate();
}

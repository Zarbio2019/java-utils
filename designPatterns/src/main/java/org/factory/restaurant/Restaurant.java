package org.factory.restaurant;

public abstract class Restaurant {
	
	public Burger orderBurger() {
		Burger burger = createBurger();
		burger.prepare();
		return burger;
	}
	
	// Factory Method
	public abstract Burger createBurger();	
}

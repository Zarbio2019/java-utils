package org.creational.factory.restaurant;

public class VeggieBurgerRestaurant extends Restaurant {
	
	@Override
	public Burger createBurger() {
		return new VeggieBurger();
	}
}

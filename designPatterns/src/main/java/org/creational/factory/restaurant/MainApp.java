package org.creational.factory.restaurant;

/**
 * using Factory Method Pattern, completely
 * 
 * creator-subclasses decide which class to instantiate.
 *
 * factory-method-pattern-sample4-uml-class-diagram.png
 *
 * 1. Interface
 * Burger.java
 * 
 * 2. Concrete classes implementing the same interface
 * BeefBurger.java
 * VeggieBurger.java
 * 
 * 3. Factory to generate object of concrete class based on given information
 * Restaurant.java
 * 
 * 4. Use the Factory to get object of concrete class by passing an information such as type.
 * MainApp.java
 */
public class MainApp {
	
	public static void main(String[] args) {
		
		Restaurant beefResto = new BeefBurgerRestaurant();
		Burger beefBurger = beefResto.orderBurger();
		
		Restaurant veggieResto = new VeggieBurgerRestaurant();
		Burger veggieBurger = veggieResto.orderBurger();
	}
}

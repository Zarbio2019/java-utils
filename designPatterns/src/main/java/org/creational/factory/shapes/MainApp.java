package org.creational.factory.shapes;

/**
 * using Factory Method Pattern (better example)
 * https://www.tutorialspoint.com/design_pattern/factory_pattern.htm
 * 
 * We're going to create a Shape interface and concrete classes implementing the Shape interface. A factory class ShapeFactory is defined as a next step.
 *
 * FactoryPatternDemo, our demo class will use ShapeFactory to get a Shape object. It will pass information (CIRCLE / RECTANGLE / SQUARE) to ShapeFactory to get the type of object it needs.
 *
 * design.png
 *
 * 1. Interface
 * Shape.java
 * 
 * 2. Concrete classes implementing the same interface
 * Rectangle.java
 * Square.java
 * Circle.java
 * 
 * 3. Factory to generate object of concrete class based on given information
 * ShapeFactory.java
 * 
 * 4. Use the Factory to get object of concrete class by passing an information such as type.
 * MainApp.java
 */
public class MainApp {
	public static void main(String[] args) {
		ShapeFactory shapeFactory = new ShapeFactory();
		
		// get an object of Circle and call its draw method.
		Shape shape1 = shapeFactory.getShape("CIRCLE");
		// call draw method of Circle
		shape1.draw();
		
		// get an object of Rectangle and call its draw method.
		Shape shape2 = shapeFactory.getShape("RECTANGLE");
		// call draw method of Rectangle
		shape2.draw();
		
		// get an object of Square and call its draw method.
		Shape shape3 = shapeFactory.getShape("SQUARE");
		// call draw method of square
		shape3.draw();
		
		/* output:
		Inside Circle::draw() method.
		Inside Rectangle::draw() method.
		Inside Square::draw() method.
		*/
	}
}

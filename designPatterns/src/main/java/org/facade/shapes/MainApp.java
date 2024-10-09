package org.facade.shapes;

/**
 * https://www.tutorialspoint.com/design_pattern/facade_pattern.htm
 * 
 * We are going to create a Shape interface and concrete classes implementing the Shape interface.
 * A facade class ShapeMaker is defined as a next step.
 *
 * ShapeMaker class uses the concrete classes to delegate user calls to these classes.
 * FacadePatternDemo, our demo class, will use ShapeMaker class to show the results.
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
 * 3. Facade class
 * ShapeMaker.java
 * 
 * 4. Use the facade to draw various types of shapes.
 * MainApp.java
 */
public class MainApp {
	public static void main(String[] args) {
		
		ShapeMaker shapeMaker = new ShapeMaker();
		
		shapeMaker.drawCircle();
		shapeMaker.drawRectangle();
		shapeMaker.drawSquare();
		
		/* output:
		Circle::draw()
		Rectangle::draw()
		Square::draw()
		*/
	}
}

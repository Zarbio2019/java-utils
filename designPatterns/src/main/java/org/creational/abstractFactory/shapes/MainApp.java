package org.abstractFactory.shapes;

/**
 * https://www.tutorialspoint.com/design_pattern/abstract_factory_pattern.htm
 *
 * We are going to create a Shape interface and a concrete class implementing it.
 * We create an abstract factory class AbstractFactory as next step. Factory class ShapeFactory is defined,
 * which extends AbstractFactory. A factory creator/generator class FactoryProducer is created.
 *
 * AbstractFactoryPatternDemo, our demo class uses FactoryProducer to get a AbstractFactory object.
 * It will pass information (CIRCLE / RECTANGLE / SQUARE for Shape) to AbstractFactory to get the type of object it needs.
 *
 * design.png
 *
 * 1. Interface for Shapes
 * Shape.java
 * 
 * 2. Concrete classes implementing the same interface
 * Rectangle.java
 * Square.java
 * RoundedRectangle.java
 * RoundedSquare.java
 * 
 * 3. Abstract class to get factories for ShapeFactory and RoundedShapeFactory Objects
 * AbstractFactory.java
 * 
 * 4. Factory classes extending AbstractFactory to generate object of concrete class based on given information.
 * ShapeFactory.java
 * RoundedShapeFactory.java
 * 
 * 5. Factory generator/producer class to get factories by passing an information such as Shape.
 * FactoryProducer.java
 * 
 * 6. Use the FactoryProducer to get AbstractFactory in order to get factories of concrete classes by passing an information such as type.
 * Use the Factory to get object of concrete class by passing an information such as type.
 * AbstractFactoryPatternDemo.java = MainApp.java
 */
public class MainApp {
	public static void main(String[] args) {
		
		// get Shape Factory
		AbstractFactory shapeFactory = FactoryProducer.getFactory(false);

		// get an object of Shape Rectangle
		Shape shape1 = shapeFactory.getShape("RECTANGLE");
		// call draw method of Shape Rectangle
		shape1.draw();
		
		// get an object of Shape Square
		Shape shape2 = shapeFactory.getShape("SQUARE");
		// call draw method of Shape Square
		shape2.draw();
		
		// get Shape Factory
		AbstractFactory shapeFactory1 = FactoryProducer.getFactory(true);
		
		// get an object of Shape Rectangle
		Shape shape3 = shapeFactory1.getShape("RECTANGLE");
		// call draw method of Shape Rectangle
		shape3.draw();
		
		// get an object of Shape Square
		Shape shape4 = shapeFactory1.getShape("SQUARE");
		// call draw method of Shape Square
		shape4.draw();
		
		/* output:
		Inside Rectangle::draw() method.
		Inside Square::draw() method.
		Inside RoundedRectangle::draw() method.
		Inside RoundedSquare::draw() method.
		*/
	}
}

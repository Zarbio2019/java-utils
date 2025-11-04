package org.abstractFactory.shapes;

public class ShapeFactory extends AbstractFactory {
	
	// use getShape method to get object of type Shape
	public Shape getShape(String shapeType) {
		
		if(shapeType.equalsIgnoreCase("RECTANGLE")) {
			return new Rectangle();
		} else if(shapeType.equalsIgnoreCase("SQUARE")) {
			return new Square();
		}
		
		return null;
	}
}

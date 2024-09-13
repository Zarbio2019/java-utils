package org.abstractFactory.shapes;

public class RoundedShapeFactory extends AbstractFactory {
	
	// use getShape method to get object of type Shape
	public Shape getShape(String shapeType) {
		
		if(shapeType.equalsIgnoreCase("RECTANGLE")) {
			return new RoundedRectangle();
		} else if(shapeType.equalsIgnoreCase("SQUARE")) {
			return new RoundedSquare();
		}
		
		return null;
	}
}

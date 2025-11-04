package org.classes;

import org.classes.Outer.Inner;

/* refs:
https://www.youtube.com/watch?v=52frlN8webg
 */
public class Classes {
	public static void main(String[] args) {

		/* Inner class */
		
        Outer outer = new Outer();
        Outer.Inner inner = outer.new Inner();
        inner.display();  // Output: Outer's x value: 10
        
        /***************************************/
        
		//Features f = new Features(); // abstract class cannot be instantiated
		
		Dog dog = new Dog();
	    dog.makeSound();  // Output: Woof!
	    dog.breed = "pitbull";
	    System.out.println("dog.breed = " + dog.breed);
	    dog.poop();
	    dog.makeSound();
	    
	    // Animal animal = new Animal(); // interface cannot instantiate
	    Animal animal = new Dog();
	    animal.makeSound();

	    dog.iniPet();
	    dog.iniAnimal(); // from superclass Dog
	    animal.iniAnimal(); // from superclass Dog
	    
	    Feature feature = new Dog();
	    feature.breed = "chihuahua";
	}
}

// Outer class
class Outer {
	private int x = 10;

    // Inner class
    class Inner {
        public void display() {
            System.out.println("Outer's x value: " + x);
        }
    }
}

/*
Interface

- characteristics:
list of abstracts methods that any class can implement them.
without implementation.
cannot be instantiated.
cannot contain a constructor.
not be marked as final.
by default: public, abstract.

- used:
to define a contract
*/
interface Animal {
	/*
	Interface variables:
	
	- characteristics:
	by default: public, static and final.
	 */
	int age = 0;
	
	/* 
	Interface methods:
	
	- characteristics:
	by default: abstract and public.
	default method must has a body, only in an interface, not in abstract class.
	default method doesn't be static, final, or abstract.
	 */
	default void iniAnimal() { // default for implementation
		System.out.println("Animal.iniAnimal()");
	}
	
	void makeSound();
	
	void poop();
}

// Interface
interface Mammal extends Animal {
}

//interface Pet implements Animal // COMPILER ERROR
interface Pet extends Animal {
	void iniPet();
}

interface ExampleInterface1 {
	//By default - public abstract. No other modifier allowed
	void method1();//method1 is public and abstract
	//private void method6();//COMPILER ERROR!

	//This method, uncommented, would have given COMPILER ERROR!
	//in Java 7. Allowed from Java 8.
	default void method5() {
		System.out .println("Method5");
	}
}

/*
Concrete class:
Superclass
Is the first non-abstract subclass that extends an abstract class and is required to implement
all inherited abstract methods.
*/
//class Dog extends Features, Mammal { // Java don't allow multiple inheritance with classes
class Dog extends Feature implements Mammal, Pet {

	//@Override // optional
	public void iniAnimal() {
		System.out.println("Dog.iniAnimal()");
	}

	@Override
	public void poop() {
		System.out.println("Dog poop");
	}
	
	@Override
	public void makeSound() {
		System.out.println("Dog makeSound");
	}

	@Override
	public void iniPet() {
		System.out.println("iniPet -> Dog");
	}
}

/*
Abstract class:
help to write other classes.

- characteristics:
cannot be instantiated.
may not be marked as private or final.
the first concrete class that extends an abstract class must provide an implementation for all of the inherited abstract methods.

- used:
to define some common behavior for all sub-classes.
to list methods with and without implementation.
*/
abstract class Feature {
	String breed;
	
	/*
	Abstract method:
	
	- characteristics:
	only be defined in abstract classes.
	not be declared private or final.
	no body, the body is provide by the subclass (concrete class, inherited from).
	 */
	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}
	
	public void bark() {
		System.out.println("Bark!");
	}
	
	public void makeSound() {
		System.out.println("Features: Woof!");
	}

	// abstract methods has no implementation
	abstract void poop();
}

abstract class ExtraFeatures extends Feature {
	
	public void makeSound() {
		System.out.println("Extra Features: Woof!");
	}
	
	abstract void extra();
}

/***************************************/

/**
* With Serializable

public class ClassName implements Serializable {
	private static final long serialVersionUID = 7654064882800343638L;
}

* Serialization of Class

public class NameClassDTO extends AbstractDTO {

    private static final long serialVersionUID = 1L;
}
*/

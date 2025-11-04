package org.creational.singleton;

/* Singleton Pattern */

/* sample 1: Lazy Loading
No Thread-safe
multi-threading problem: nothing in this code prevents two threads from accessing this piece of code at the same time and return with two different instances of this same Singleton class.

The Singleton Pattern Explained and Implemented in Java | Creational Design Patterns | Geekific
https://www.youtube.com/watch?v=tSZn4wkBIu8&list=PLlsmxlJgn1HJpa28yHzkBmUY-Ty71ZUGc&index=2 
*/
class Singleton1 {
	private static Singleton1 instance;
	private String data;
	
	private Singleton1(String data) {
		this.data = data;
	}
	
	public static Singleton1 getInstance(String data) {
		if(instance == null) {
			instance = new Singleton1(data);
		}
		return instance;
	}
}

/* sample 2: Lazy Loading (better)
Solve multi-threading problem.
Adapted to handle multiple threads.

The Singleton Pattern Explained and Implemented in Java | Creational Design Patterns | Geekific
https://www.youtube.com/watch?v=tSZn4wkBIu8&list=PLlsmxlJgn1HJpa28yHzkBmUY-Ty71ZUGc&index=2 
*/
class Singleton2 {
	// volatile: everytime we access this variable we need to read it directly from the main memory.
	private static volatile Singleton2 instance;
	private String data;
	
	private Singleton2(String data) {
		this.data = data;
	}
	
	public static Singleton2 getInstance(String data) {
		
		// the usage of such a local variable can improve the method overall performance by as much as 40%
		Singleton2 result = instance;
		
		// double checked locking idiom
		// skipping synchronization when retrieving an already-created instance
		if(result == null) {
								
			// but now, even if the instance was created, every thread has to wait before returning it.
			synchronized (Singleton2.class) {
			
				result = instance;
				
				// ensure 2 threads are no initializing the instance at the same time
				if(result == null) {
					instance = result = new Singleton2(data);
				}
			}
		}
		
		return instance;
	}
}

/* sample 3: Eager Loading
We're going to create a SingleObject class. SingleObject class have its constructor as private and have a static instance of itself.

SingleObject class provides a static method to get its static instance to outside world. SingletonPatternDemo, our demo class will use SingleObject class to get a SingleObject object.

singleton-pattern-sample3.png
*/
// 1. Singleton Class: SingleObject.java
class SingleObject {

   //create an object of SingleObject
   private static SingleObject instance = new SingleObject();

   //make the constructor private so that this class cannot be
   //instantiated
   private SingleObject(){}

   //Get the only object available
   public static SingleObject getInstance(){
      return instance;
   }

   public void showMessage(){
      System.out.println("Hello World!");
   }
}

// 2. Get the only object from the singleton class.
// SingletonPatternDemo.java
class SingletonPatternDemo {
   public static void main(String[] args) {

      //illegal construct
      //Compile Time Error: The constructor SingleObject() is not visible
      //SingleObject object = new SingleObject();

      //Get the only object available
      SingleObject object = SingleObject.getInstance();

      //show the message
      object.showMessage();
   }
}
/* output:
Hello World!
*/
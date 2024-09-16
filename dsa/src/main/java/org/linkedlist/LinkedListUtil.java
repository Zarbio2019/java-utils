package org.linkedlist;

import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;

public class LinkedListUtil {
	
	public static void main(String[] args) {
	
		System.out.println("LinkedListUtil");
		
		String tmp;
		int in;
		
		/* Create a Linked list */
		/*
		public class LinkedList<E>
		extends AbstractSequentialList<E>
		implements List<E>, Deque<E>, Cloneable, Serializable 

		import java.util.LinkedList;

		class Main {
			public static void main(String[] args) {
				LinkedList<String> names = new LinkedList<String>();
			}
		}
		*/
		
		/* methods */
		// https://www.w3schools.com/java/java_linkedlist.asp
		// import java.util.LinkedList;

		LinkedList<String> cars = new LinkedList<String>();
		cars.add("Volvo");
		cars.add("BMW");
		cars.add("Ford");
		cars.add("Mazda");
		System.out.println(cars); // [Volvo, BMW, Ford, Mazda]
		cars.size(); // 4

		// Linked Lists are indexed like JavaScript arrays
		cars.add(1, "Apple"); // add in index 1

		cars.addFirst("Mazda");
		cars.addLast("Mazda");

		cars.remove("BMW"); // remove first occurrence of a specified element
		cars.remove(1); // remove at index 1
		cars.removeFirst();
		cars.removeLast();

		cars.get(0);
		cars.getFirst();
		cars.getLast();
		cars.set(0, "BMW"); // update at index 0

		List<String> list = new ArrayList<>();
		list.add("SD"); // [SD]
		list.add(0, "NY"); // [NY,SD]
		list.set(1, "FL"); // [NY,FL]
		list.remove("NY"); // [FL]
		list.remove(0); // []
		
		/* iteration */
		for (int i = 0; i < cars.size(); i++) {
		  System.out.println(cars.get(i));
		}

		// foreach:
		for (String str : cars) {
			System.out.println(str);
		}
		
		System.out.println("cars.forEach");
		cars.forEach(str -> System.out.println(str)); // using lambda
		cars.forEach(System.out::println); // using operator to reference
	}
}

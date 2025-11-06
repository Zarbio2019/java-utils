/*
 * Implementation of Set Data Structure
 * https://www.geeksforgeeks.org/introduction-to-set-data-structure-and-algorithm-tutorials/?ref=lbp
 */

package org;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SetUtil {
	
	public static void main(String[] args) {

		/* Set */
		// Set.class: interface
		// package java.util
		// extends Collection
		
		/* methods */
		/*
		size
		add
		addAll
		remove
		removeAll
		clear
		contains
		containsAll
		max
		min
		size
		isEmpty
		iterator
		toArray
		equals
		 */
        /*
        Time Complexity:
        	Insertion: O(Log(N)), N = number of elements in the set.
        	Searching: O(Log(N)), N = number of elements in the set.
        	Deletion: O(Log(N)), N = number of elements in the set.
        	Accessing the minimum/maximum element: O(1).
        	Size of the set: O(1).
        */
		
		Set<Integer> set = new HashSet<>();
		boolean b1 = set.add(66); // true
		
		// Creating an object of Set and
		// declaring object of type String
		Set<Integer> hs = new HashSet<Integer>();

		// Custom input elements
		hs.add(10);
		hs.add(5);
		hs.add(12);
		hs.add(4);

		// Print the Set object elements
		System.out.println("Set is " + hs); // [4, 5, 10, 12]

		// Declaring a string
		int check = 10;

		// Check if the above string exists in
		// the SortedSet or not
		// using contains() method
		System.out.println("Contains " + check + " "
						+ hs.contains(check)); // true

		// Printing elements of HashSet object
		System.out.println(hs); // [4, 5, 10, 12]

		// Removing custom element
		// using remove() method
		hs.remove(5);

		// Printing Set elements after removing an element
		// and printing updated Set elements
		System.out.println("After removing element " + hs); // [4, 10, 12]

		// finding maximum element
		Object obj = Collections.max(hs);
		System.out.println("Maximum Element = " + obj); // 12

		// finding minimum element
		Object obj2 = Collections.min(hs);
		System.out.println("Maximum Element = " + obj2); // 4

		// Displaying the size of the Set
		System.out.println("The size of the set is: "
						+ hs.size()); // 3
						
		// Creating an iterator
        Iterator value = hs.iterator();
  
        // Displaying the values after iterating through the
        // iterator
        System.out.println("The iterator values are: ");
        while (value.hasNext()) {
            System.out.println(value.next());
        }
        /*
        4
        10
        12
        */
        
		/***************************************/
		
		/* HashSet */
		// https://www.w3schools.com/java/java_hashset.asp
		// HashSet.class: class
		// package java.util
		// extends AbstractSet<E>
	    // implements Set<E>, Cloneable, java.io.Serializable
	    // is built from HashMap
		
		/* methods */
		/*
		size
		isEmpty
		contains
		add
		remove
		clear: remove all items
		clone
		toArray
		 */
		
		HashSet<String> cars = new HashSet<String>();

		cars.add("Volvo");
		cars.remove("Volvo");
		cars.clear();
		cars.size();

		HashSet<Integer> numbers = new HashSet<Integer>();
		numbers.add(4);

		// check if an item exists
		cars.contains("Mazda"); // true

		/* loop */
		for (String i : cars) {
			System.out.println(i);
		}
	}
}

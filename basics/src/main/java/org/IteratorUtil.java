// https://www.w3schools.com/java/java_iterator.asp

package org;

import java.util.ArrayList;
import java.util.Iterator;

public class IteratorUtil {
	
	public static void main(String[] args) {
		
		// Iterate around an ArrayList using Iterator
		
		// Make a collection
		ArrayList<String> cars = new ArrayList<String>();
		cars.add("Volvo");
		cars.add("BMW");
		cars.add("Ford");
		cars.add("Mazda");

		// Get the iterator
		Iterator<String> it = cars.iterator();

		// Get First item
		it.next(); // Volvo

		// Loop through a collection
		while(it.hasNext()) {
			System.out.println(it.next());
		}
		/* output:
		Volvo
		BMW
		Ford
		Mazda
		 */
		
		/***************************************/
		
		// Removing Items from a Collection
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		numbers.add(12);
		numbers.add(8);
		numbers.add(2);
		numbers.add(23);

		Iterator<Integer> it2 = numbers.iterator();

		while(it2.hasNext()) {
			Integer i = it2.next();
			
			if(i < 10) { // remove numbers less than 10 from a collection
				it2.remove();
			}
		}
		System.out.println(numbers); // [12, 23]
		// Note: Trying to remove items using a for loop or a for-each loop would not work correctly because the collection is changing size at the same time that the code is trying to loop.
	}
}

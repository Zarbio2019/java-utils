package org.arrays;

import java.util.*;
import java.util.stream.Collectors;

public class ArrayUtil {

	public static final String COMMA = ",";

	public static void main(String[] args) {
		
		System.out.println("ArrayUtil");

		String tmp;
		int in;
		
		/* Built-in Array */
		// Object.class, package java.lang
		
		int [] matrix; // declare, not allocate memory
		int [] matrix2 = new int[5]; // declare and allocate memory
		int matrix3 [] = new int[5];
		int [] numbers = new int[] {42, 55, 99};
		int[] numbers4 = {1, 2, 3};
		
		// indexes with no value are null
		int[] intArray8 = new int[14];
		intArray8[3] = 5;
		intArray8[4] = 3;
		intArray8[13] = 14;
		//intArray8[14] = 15; // java.lang.ArrayIndexOutOfBoundsException
		
		String[] cars = {"Volvo", "BMW", "Ford", "Mazda"};
		tmp = cars[0]; // Volvo
		in = cars.length; // 4
		
		// loop:
		for (int i = 0; i < cars.length; i++) {
		  System.out.println(cars[i]);
		}

		for (String i : cars) {
		  System.out.println(i);
		}

		/* final in array:
		final array means that the array variable which is actually
		a reference to an object, cannot be changed to refer to anything else,
		but the members of the array can be modified.
		
		Arrays are objects and object variables are always references in Java.
		 
		final int[] arr = { 1, 2, 3, 4, 5 };
		arr[4] = 1; // ok: { 1, 2, 3, 4, 1 }
				
		a final class, which means it can't be instantiated or changed.
		Only the methods of this class can be used by the class name itself:
		
		final Test t = new Test();
		t.p = 30; // ok

		final GFG t1 = new GFG();
		GFG t2 = new GFG();
		t1 = t2; // Compiler Error: cannot assign a value to final variable t1
		*/
		
		/* methods */
		// https://www.geeksforgeeks.org/array-class-in-java/	
		
		/* direct superclass of arrays is a class Object:
		
		matrix.getClass().getSuperclass() // class java.lang.Object

		All the members are inherited from class Object; the only method of
		Object that is not inherited is its clone method.

		The public method clone() overrides the clone method in class Object
		and throws no checked exceptions.
		*/
		
		// equals:
		String [] bugs = {"cricket", "beetle", "laybug"};
		String [] alias = bugs;
		
		System.out.println(bugs.equals(alias)); // true

		// clone:
		// 1. deep copy: copy values, create new object.
		// used for single-dimensional array
		int intArray[] = { 1, 2, 3 };
		int cloneArray[] = intArray.clone();

		boolean b = (intArray == cloneArray); // false
		b = (intArray[1] == cloneArray[1]); // true
		
		// 2. shallow copy: copy values, create new object, but subarrays share same reference.
		// used for multi-dimensional array
		int intArray2[][] = { { 1, 2, 3 }, { 4, 5 } };
		int cloneArray2[][] = intArray2.clone();

		b = (intArray2 == cloneArray2); // false
		b = (intArray2[1] == cloneArray2[1]); // true

		// copyOfRange
		String[] data = { "1", "2", "3", "4"};
		String[] arrStr = Arrays.copyOfRange(data, 1, data.length);
		System.out.println("arrStr: " + Arrays.toString(arrStr));

		// hashCode:
		System.out.println("intArray2.hashCode(): " + intArray2.hashCode()); // 1995616381
		
		// toString:
		System.out.println("intArray2.toString(): " + intArray2.toString()); // [[I@76f2b07d
		
		// others:
		// notify()
		// notifyAll()
		// wait()
		
		/***************************************/
		
		/* Multi-Dimensional Arrays */
		// arrays of arrays

		int[][] vars1; // 2D array
		int vars2 [][]; // 2D array
		int[] vars3[]; // 2D array
		int[] vars4 [], space [][]; // 2D and 3D arrays
		String [][] rectangle = new String[3][2]; // 2D array
		int[][][] intArray3 = new int[10][20][10]; // 3D array
		int[][][] arr = { { { 1, 2 }, { 3, 4 } },
						  { { 5, 6 }, { 7, 8 } } }; // 3D array
		/* output:
		arr[0][0][0] = 1
		arr[0][0][1] = 2
		arr[0][1][0] = 3
		arr[0][1][1] = 4
		arr[1][0][0] = 5
		arr[1][0][1] = 6
		arr[1][1][0] = 7
		arr[1][1][1] = 8
		*/
		
		int[][] differentSize = {{1, 4}, {3}, {9,8,7}}; // jagged array
		int arr_name[][] = new int[][] { new int[] {10, 20, 30 ,40},
		                                 new int[] {50, 60, 70, 80, 90, 100},
		                                 new int[] {110, 120}
		                               }; // jagged array
					   
	    // Asymmetric array:
		int [][] args1 = new int[4][];
		args1[0] = new int[5];

		int[][] myNumbers = { {1, 2, 3, 4}, {5, 6, 7} };
		in = myNumbers[1][2]; // 7

		// loop:
		for (int i = 0; i < myNumbers.length; ++i) {
			for(int j = 0; j < myNumbers[i].length; ++j) {
				System.out.println(myNumbers[i][j]);
			}
		}

		int [][] matrix4 = new int[4][5];
		for(int[] fila :matrix4){
			for(int z: fila){
				System.out.println(z + " ");
			}
		}
		
		/***************************************/
			
		/* Arrays.class */
		// package java.util

		/* methods */
		/*
		sort
		parallelSort
		mergeSort
		swap // swap 2 values
		binarySearch
		equals
		fill // assign values to fill
		copyOf
		copyOfRange
		asList // convert Array to List
			// List<Integer> values = Arrays.asList(numbers);
		hashCode
		toString
		stream
		compare // compare 2 arrays
		*/
		
		/* Arrays.class/ArrayItr.class */
		// package java.util
		
		/* methods */
		/*
		size
		toArray
		get
		set
		indexOf
		contains
		forEach
		replaceAll
		sort
		iterator
		*/
		
		/* Arrays.class/ArrayList.class */
		// package java.util
		
		/* methods */
		/*
		hasNext
		next
		*/
		
		/* Sort */
		// import java.util.* // import whole package including Arrays
		// import java.util.Arrays; // import just Arrays

		// numeric order
		int[] numbers2 = { 6, 9, 1 };
		Arrays.sort(numbers);
		for (int i = 0; i < numbers.length; i++)
			System.out.print (numbers[i] + " "); // 1 6 9

		// alphabetic order
		// Numbers sort before letters and uppercase sorts before lowercase
		String[] strings = { "10", "9", "100" };
		Arrays.sort(strings);
		
		for (String string : strings)
			System.out.print(string + " "); // 10 100 9

		/* Search */
		// The array must be sorted.

		int[] numbers3 = {2,4,6,8};
		System.out.println(Arrays.binarySearch(numbers, 4)); // 1
		System.out.println(Arrays.binarySearch(numbers, 3)); // -2
			
		/***************************************/
		
		/* ArrayList.class */
		// package java.util
		
		// https://www.w3schools.com/java/java_arraylist.asp
		
		// import java.util.* // import whole package including ArrayList
		// import java.util.ArrayList; // import just ArrayList

		ArrayList list2 = new ArrayList(10);

		List<String> list6 = new ArrayList<>();
		// ArrayList<String> list7 = new List<>(); // DOES NOT COMPILE

		ArrayList<String> cars2 = new ArrayList<String>();

		ArrayList<Integer> myNumbers2 = new ArrayList<Integer>();
		
		// ArrayList<int> myNumbers = new ArrayList<int>(); // invalid
		ArrayList<Integer> myNumbers3 = new ArrayList<Integer>(); // valid
		
		/* methods */
		/*
		size
		isEmpty
		contains
		indexOf
		lastIndexOf
		clone
		toArray // convert List to Array
		elementAt
		get
		set
		add
		remove
		equals
		hashCode
		clear // remove all elements to null
		addAll
		removeAll
		forEach
		sort
		*/
		
		myNumbers2.add(10);
		cars2.add("Volvo"); // add item
		cars2.add(0, "Volvo");
		cars2.get(0); // get element by index
		cars2.set(0, "Opel"); // change an item
		cars2.remove(0); // remove item
		cars2.clear(); // remove all elements

		cars2.isEmpty(); // false
		cars2.size(); // get size

		cars2.contains("robin"); // false

		// Adding all elements from list (list1b) to another list (list1)
		List<String> list1 = new ArrayList<>();
		list1.add("Apple");
		list1.add("Banana");
		list1.add("Orange");

		List<String> list1b = new ArrayList<>();
		list1b.add("Apple");
		list1b.add("Pineapple");

		list1.addAll(list1b);
		System.out.println("list1 after addAll: " + list1); // [Apple, Banana, Orange, Apple, Pineapple]

		// convert List to Object[] array
		Object[] objectArray = list2.toArray(); // List.class
		
		// convert array to List
		String[] array = { "hawk", "robin" };
		List<String> list3 = Arrays.asList(array); // [hawk, robin], Arrays.class
		List<String> list4 = Arrays.asList("one", "two");
		List<String> list5 = Arrays.asList("1,2,3,4".split(COMMA));

		list3.equals(list4); // false

		// convert List to Set
		Set<String> set = new HashSet<>(list4);
		System.out.println("set: " + set);

		// loop:
		/* Limitations of for-each loop:
		https://www.geeksforgeeks.org/for-each-loop-in-java/
			not appropriate when you want to modify the array
			do not keep track of index
			only iterates forward over the array in single steps
			cannot process two decision making statements
			performance overhead over simple iteration
		 */
		for (int i = 0; i < cars2.size(); i++) {
			System.out.println(cars2.get(i));
		}

		for (String i : cars) {
			System.out.println(i);
		}

		for (int i : myNumbers2) {
		  System.out.println(i);
		}

		for (String i : list4) {
			System.out.println(i);
		}

		/* Sort alphabetically */
		// import java.util.ArrayList;
		// import java.util.Collections;

		ArrayList<String> cars3 = new ArrayList<String>();
		cars3.add("Volvo");
		cars3.add("BMW");
		cars3.add("Ford");
		cars3.add("Mazda");
		
		Collections.sort(cars3);  // Sort cars
		
		for (String i : cars) {
		  System.out.println(i);
		}
		/* output:
		BMW
		Ford
		Mazda
		Volvo
		 */
		
		/* Sort numerically */
		// import java.util.ArrayList;
		// import java.util.Collections;
		
		ArrayList<Integer> myNumbers4 = new ArrayList<Integer>();
		myNumbers4.add(33);
		myNumbers4.add(15);
		myNumbers4.add(20);
		myNumbers4.add(34);
		myNumbers4.add(8);
		myNumbers4.add(12);

		Collections.sort(myNumbers4);

		for (int i : myNumbers4) {
		  System.out.println(i);
		}
		/* output:
		8
		12
		15
		20
		33
		34
		 */
		
		/* ArrayList.class/Itr.class */
		// package java.util
		
		/* methods */
		/*
		hasNext
		next
		remove
		*/
		
		/***************************************/

		/* Conversion */

		// Convert List<Integer> to Array[int]:
		List<Integer> intList = new ArrayList<>();
		int listSize = intList.size();
		int[] intArray4 = new int[listSize];

		for(int i=0; i<listSize; i++)
			intArray4[i] = intList.get(i);

		// Convert Array[int] to List<Integer>:
		int[] intArray5 = {1, 2, 3, 4, 5};

		List<Integer> integerList = Arrays.stream(intArray5)
										  .boxed()
										  .collect(Collectors.toList());
										  
		// Convert List<Integer> to Array[Integer]:
		List<Integer> intList2 = new ArrayList<>();
		intList2.add(1);
		intList2.add(2);

		int listSize2 = intList2.size();
		Integer[] intArray6 = intList2.toArray(new Integer[0]); // List.class
		
		// Convert List<Integer> to Object[]
		Object[] objectArray2 = intList2.toArray(); // List.class
		
		
		// Convert Array[Integer] to List<Integer>:
		Integer[] intArray7 = { 1, 2 };
		List<Integer> intList3 = Arrays.asList(intArray7); // Arrays.class

		/***************************************/

		/* Collection.java */
		// package java.util

		/***************************************/

		/* Print */
		// 1D array:
		int array1d[] = { 1, 2, 3 };
		System.out.println(array1d); // [I@6db3f829
		System.out.println(array1d.toString()); // [I@76b0bfab
		System.out.println(Arrays.toString(array1d)); // [1, 2, 3]
		
		// 2D array:
		int[][] array2d = { { 1, 2, 3 }, { 4, 5, 6 } };
		System.out.println(array2d); // [[I@34c4973
		System.out.println(Arrays.toString(array2d)); // [[I@52feb982, [I@7a765367]
		System.out.println(Arrays.deepToString(array2d)); // [[1, 2, 3], [4, 5, 6]]

		// array2d[0] is a 1D array
		System.out.println(array2d[0]);// [I@52feb982
		System.out.println(Arrays.toString(array2d[0]));// [1, 2, 3]

		// List:
		System.out.println(intList);
		System.out.println(intList.toString());
		
		/***************************************/
		
		/* Vector */
		// public class Vector<E> extends AbstractList<E> implements List<E>, RandomAccess, Cloneable, Serializable
		// package java.util

        System.out.println("\nVector");

        // Size of the Vector
        int n = 5;

        // Declaring the Vector with
        // initial size n
        Vector<Integer> v = new Vector<Integer>(n);

        // Appending new elements at
        // the end of the vector
        for (int i = 1; i <= n; i++)
            v.add(i);

        // Printing elements
        System.out.println(v);

        // Remove element at index 3
        v.remove(3);

        // Displaying the vector
        // after deletion
        System.out.println(v);

        // iterating over vector elements
        // using for loop
        for (int i = 0; i < v.size(); i++)
            // Printing elements one by one
            System.out.print(v.get(i) + " ");
        /* output:
        [1, 2, 3, 4, 5]
		[1, 2, 3, 5]
		1 2 3 5
         */
	}
}

package org;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SyntaxUtil {
	
	public static void main(String[] args) {

		System.out.print("SyntaxUtil");
		
		// String[] args, String args[], String... args;

		/************************************************/

		/* Pass-by-Value vs. Pass-by-Reference */
		/*
		Pass-by-Value: when the parameter of a function doesn’t change its value. Used in Java.
		Pass-by-Reference: when the parameter of a function changes its value. Don't used in Java. Only used in StringBuilder.
		*/

		/*
		Java is strictly pass-by-value, it can be misleading when dealing with object references:

		1. For Primitive Types (like int, char, double, boolean, String (immutable object type)):
		   When you pass a primitive type to a method, Java passes a copy of the value, means any changes
		   made to the parameter inside the method do not affect the original variable.
		*/
		String str = "2";
		passByValueForPrimitivesTypes(str);
		System.out.println("passByValueForPrimitivesTypes: " + str); // str doesn't change

		/*
		2. For Object Reference (like Client, ArrayList, etc):
		   When you pass an object to a method, Java passes a copy of the reference (a memory address pointing to the object).
		   Changes to the object's state via this reference will affect the original object.
		   If you resign the reference itself inside the method, it does not affect the original reference.
	    */
		// passing the reference of the object.
		// any changes you make to the object inside the method will reflect on the original object.
		Client client = new Client("1", "Allie", "qa");
		passByValueForObject(client);
		System.out.println("passByValueForObject: " + client); // client change

		/************************************************/

		/* Print */

		// text:
		System.out.print("Hello World! ");

		// number:
		System.out.println(2 * 5); // 10

		System.out.println(); // add break line

		/************************************************/

		/* Comments */

		// single-line comment

		/* Multi
		 * line comment
		 */

		/**
		* Javadoc multiple-line comment
		* @author Jeanne and Scott
		*/

		/************************************************/

		/* Variables */

		// Types:
		String a = "b";
		int i = 0;
		float f = 19.99f;
		double d = 19.99d; // or 19.99
		char c = 'a';
		boolean bo = true; // or false
		long l = 15000000000L;

		// Declaration:
		int x = 5;

		int x2 = 5, y = 6, z = 50;

		int x3, y3, z3;
		x3 = y3 = z3 = 50;

		Integer myInt = 100; 
		String myString = myInt.toString();
		System.out.println(myString.length()); // 3
		
		/* Wrapper Classes */

		// Converting Wrapper Class types to primitive types
		/*
		booleanValue()
		byteValue()
		shortValue()
		intValue()
		longValue()
		floatValue()
		doubleValue()
		charValue()
		 */
		Integer wrapperInt = new Integer(100);
		
        int primitiveInt = wrapperInt.intValue();

        // Converting String to primitive types
		/*
		parseBoolean
		parseByte
		parseShort
		parseInt
		parseLong
		parseFloat
		parseDouble
		 */
        boolean b1 = Boolean.parseBoolean("true");
        
        // Converting String to wrapper class
        /*
        valueOf
         */
        Boolean b2 = Boolean.valueOf("TRUE");

		/***************************************/
		
		/* Conditions */
		/*
		// If, Else:

		if (condition1) {
		  // code
		} else if (condition2) {
		  // code
		} else {
		  // code
		}

		// Short Hand If...Else:
		// variable = (condition) ? expressionTrue : expressionFalse;
		int time = 20;
		String result = (time < 18) ? "Good day." : "Good evening.";

		// Switch:
		switch(expression) {
		  case x:
		    // code
		    break;
		  case y:
		    // code
		    break;
		  default:
		    // code
		}

		// Switch enhanced:
		String sRet = switch(expression) {
		  case "x" -> {
			  // code...
			  yield "value1";
		  }
		  case "y" -> "value2";
		  case "z" -> "value3";
		  default -> "Unknown";
		}
		 */
		
		/***************************************/
		
		/* Loops */
		
		// Do/While:
		/*
		while (condition) {
		  // code
		}

		do {
		  // code
		}
		while (condition);

		// For:
		for (int i = 0; i < 5; i++) {
		  System.out.println(i);
		}
		
			// Creating an Infinite Loop
		for(;;) {
			System.out.println("Hello World");
		}

			// Adding Multiple Terms to the for Statement
		int x = 0;
		for(long y = 0, z = 4; x < 5 && y < 10; x++, y++) {
			System.out.print(y + " ");
		}
		System.out.print(x);

			// Redeclaring a Variable in the Initialization Block
		int x = 0;
		for(long y = 0, x = 4; x < 5 && y < 10; x++, y++) { // DOES NOT COMPILE
			System.out.print(x + " ");
		}

		int x = 0;
		long y = 10;
		for(y = 0, x = 4; x < 5 && y < 10; x++, y++) {
			System.out.print(x + " ");
		}

			// Using Incompatible Data Types in the Initialization Block
		for(long y = 0, int x = 4; x < 5 && y<10; x++, y++) { // DOES NOT COMPILE
			System.out.print(x + " ");
		}

		// For-Each:
		String[] cars = {"Volvo", "BMW", "Ford", "Mazda"};
		for (String i : cars) {
		  System.out.println(i);
		}

		// Break/Continue:
		// https://www.w3schools.com/java/java_break.asp
		*/
		
		/***************************************/
		
		/* Methods */
		/*
		// Method Name:
		public void 2walk() { } // DOES NOT COMPILE
		public walk3 void() { } // DOES NOT COMPILE
		public void() { } // DOES NOT COMPILE

		// Method Overloading: same name with different parameters

		static int plusMethodInt(int x, int y) {
			return x + y;
		}

		static double plusMethodDouble(double x, double y) {
			return x + y;
		}
		*/
		
		/***************************************/
		
		/* Scope */
		// https://www.w3schools.com/java/java_scope.asp

		/***************************************/

		/* Enums */
		// https://www.w3schools.com/java/java_enums.asp
		/*
		enum Level {
		  LOW,
		  MEDIUM,
		  HIGH
		}
		*/
		
		/***************************************/

		/* Exceptions */
		// https://www.w3schools.com/java/java_try_catch.asp
		/*
		try {
		  // Block of code to try
		}
		catch(Exception e) {
		  // Block of code to handle errors
		}
		finally {
		  // execute code after try...catch, regardless of the result
		}

		// Create a custom error:
		ArithmeticException
		FileNotFoundException
		ArrayIndexOutOfBoundsException
		SecurityException

		// ex:
		throw new ArithmeticException("Access denied - You must be at least 18 years old.");
		*/
		
		/***************************************/

		/* Regular Expressions */
		// https://www.w3schools.com/java/java_regex.asp
		// to perform all types of text search and text replace operations
		// package java.util.regex
		
		// Classes:
		/*
		Pattern.class: Defines a pattern (to be used in a search)
			package java.util.regex
			
		Matcher Class: Used to search for the pattern
			package java.util.regex
		
		PatternSyntaxException.class: Indicates syntax error in a regular expression pattern
			package java.util.regex
		 */
		 
		// Find out if there are any occurrences of the word "w3schools" in a sentence:
		// import java.util.regex.Matcher;
		// import java.util.regex.Pattern;
		Pattern pattern = Pattern.compile("w3schools", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher("Visit W3Schools!");
		
		boolean matchFound = matcher.find();

		if(matchFound) {
			System.out.println("Match found");
		} else {
			System.out.println("Match not found");
		}
		// output: Match found
		
		/***************************************/

		/* Varargs */
		/*
		public static void main(String[] args)
		public static void main(String args[])
		public static void main(String... args) // varargs

		public void walk4(int... start, int... nums) { } // DOES NOT COMPILE
		*/
	}

	/**************************************************/

	private static void passByValueForPrimitivesTypes(String str) {
		str = "1111";
	}

	private static void passByValueForObject(Client client) {
		client.setId("1111");
	}
}

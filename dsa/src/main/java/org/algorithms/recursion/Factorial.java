package org.algorithms.recursion;

public class Factorial {
	
	public static long factorial(int number){
		
		// base case - factorial of 0 or 1 is 1
		if(number <=1){
			return 1;
		}
		
		return number*factorial(number - 1);
	}
}

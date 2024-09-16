/*
 * Fibonacci
 * 
 * Approach: Recursive
 * 
 * f(n) = f(n-1) + f(n-2)
 * 1,1,2,3,5,8,13,21,34,55,89,144,377,610,987,1597,...
 * f0 = 0
 * f1 = 1
 * f2 = 1
 * f3 = 2
 * f4 = 3
 * f5 = 5
 * f6 = 8
 */

package org.algorithms.recursion;

public class Fibonacci {
	
    /**
     * Time Complexity: O(2^N), exponential
     *  n = input of Fibonacci number
     *  Each call to the fib() function makes two recursive calls and so on, grows exponentially with the input value of n.
     * Auxiliary Space: O(N), recursion. Can improve using Iteration or Memoization-based approach.
     */
	public static int fib(int n){
		if(n <= 1)
			return n;
		return fib(n-1) + fib(n-2);
	}
	
	public static void main(String args[]) {

		int n = 10; // Define the number of Fibonacci numbers to generate
		System.out.println("Fibonacci Series up to " + n + " terms:");
		
		for(int i=1; i<n; i++) {
			System.out.print(fib(i) + " ");
		}
	}
}

/*
 * Fibonacci
 * https://www.geeksforgeeks.org/introduction-to-dynamic-programming-data-structures-and-algorithm-tutorials/#characteristics-of-dynamic-programming-algorithm
 * 
 * Approach: Dynamic Programming (tabulation: use iteration, better)
 * The current state of any fibonacci number depend only on prev two number.
 * So we did not need to store the whole table of size n but instead of that we can only store the prev two values.
 * So this way we can optimize the Space Complexity in the above code O(n) to O(1).
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

public class Fibonacci3 {
	
    /**
     * Time Complexity: O(N), Lineal
     *  n = input of Fibonacci number
     * Auxiliary Space: O(1), don't create array
     */
	public static int fib(int n){
		
		int prevPrev, prev, curr;
		
		// Storing the independent values
		prevPrev = 0;
		prev = 1;
		curr = 1;
		
		// Using the bottom-up approach
		for(int i=2; i<=n; i++) {
			curr = prev + prevPrev;
			prevPrev = prev;
			prev = curr;
		}
		
		// Returning the final answer
		return curr;
	}
	
	public static void main(String args[]) {

		int n = 10; // Define the number of Fibonacci numbers to generate
		System.out.println("Fibonacci Series up to " + n + " terms:");

		for(int i=1; i<n; i++) {
			System.out.print(fib(i) + " ");
		}
	}
}

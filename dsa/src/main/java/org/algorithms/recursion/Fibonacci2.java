/*
 * Fibonacci
 * https://www.geeksforgeeks.org/introduction-to-dynamic-programming-data-structures-and-algorithm-tutorials/#characteristics-of-dynamic-programming-algorithm
 * 
 * Approach: Dynamic Programming (tabulation: use iteration)
 * Use an array called table to store the results of subproblems,
 * and we use iteration to compute the results.
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

public class Fibonacci2 {
	
    /**
     * Time Complexity: O(N), Lineal
     *  n = input of Fibonacci number
     * Auxiliary Space: O(N), create array
     */
	public static int fib(int n){
		
		int f[] = new int[n+1];
		
		// Storing the independent values in the array
		f[0] = 0;
		f[1] = 1;
		
		// Using the bottom-up approach
		for(int i=2; i<=n; i++) {
			f[i] = f[i-1] + f[i-2];
		}
		
		// Returning the final index
		return f[n];
	}
	
	public static void main(String args[]) {

		int n = 10; // Define the number of Fibonacci numbers to generate
		System.out.println("Fibonacci Series up to " + n + " terms:");
		
		for(int i=1; i<n; i++) {
			System.out.print(fib(i) + " ");
		}
	}
}

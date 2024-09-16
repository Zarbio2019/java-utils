/*
 * Fibonacci
 * https://www.geeksforgeeks.org/introduction-to-dynamic-programming-data-structures-and-algorithm-tutorials/#characteristics-of-dynamic-programming-algorithm
 * 
 * Approach: Dynamic Programming (memoization: use recursion)
 * Use a dictionary object called cache to store the results of function calls,
 * and we use recursion to compute the results.
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

import java.util.HashMap;
import java.util.Map;

public class Fibonacci4 {
	
    /**
     * Time Complexity: O(N), use memoization to avoid redundant calculations.
     *  n = input of Fibonacci number
     *  With memoization, each Fibonacci number is calculated only once.
     * Auxiliary Space: O(N), recursion. Can improve using Iteration or Memoization-based approach.
     */
	public static int fib(int n, Map<Integer, Integer> cache) {
		
		if(cache.containsKey(n)) {
			return cache.get(n);
		}
		
		int result;
		
		if(n <= 1)
			return n;
		result = fib(n-1, cache) + fib(n-2, cache);
		
		cache.put(n, result);
		
		return result;
	}
	
	public static void main(String args[]) {

		int n = 10; // Define the number of Fibonacci numbers to generate
		Map<Integer, Integer> cache = new HashMap<>();		

		System.out.println("Fibonacci Series up to " + n + " terms:");

		for(int i=1; i<n; i++) {
			System.out.print(fib(i, cache) + " ");
		}
	}
}

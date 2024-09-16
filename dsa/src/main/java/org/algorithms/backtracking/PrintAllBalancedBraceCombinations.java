/*
 * Print balanced brace combinations
 * https://www.educative.io/all-possible-braces
 */

package org.algorithms.backtracking;

import java.util.ArrayList;

public class PrintAllBalancedBraceCombinations {
	
    /**
     * Approach: recursion
     * 
     * Time Complexity: O(2^n). Exponential, for every index there can be two options �{� or �}�.
     * Auxiliary Space: O(n)
     */
	static ArrayList<ArrayList<Character>> printAllBraces(int n) {
	    ArrayList<ArrayList<Character>> result = new ArrayList<ArrayList<Character>>();
	    ArrayList<Character> output = new ArrayList<Character>();
	    printAllBacesRec(n, 0, 0, output, result);
	    return result;
	}
	
	static void printAllBacesRec(int n,
							     int leftCount,
	                             int rightCount, ArrayList<Character> output,
	                             ArrayList<ArrayList<Character>> result) {
	
		if (leftCount >= n && rightCount >=n) {
			result.add((ArrayList)output.clone()); // return the result
	    }
	
	    if (leftCount < n) {
	    	output.add('{');
	    	printAllBacesRec(n, leftCount + 1, rightCount, output, result);
	    	output.remove(output.size() - 1);
	    }
	
	    if (rightCount < leftCount) {
	    	output.add('}');
	    	printAllBacesRec(n, leftCount, rightCount + 1, output, result);
	    	output.remove(output.size() - 1);
	    }
	}
	
	static void print(ArrayList<ArrayList<Character>> arr) {
		for (int i = 0; i < arr.size(); i++) {
			System.out.println(arr.get(i).toString());
		}
	}
	
	public static void main(String[] args) {
		ArrayList<ArrayList<Character>> result = new ArrayList<>();
	    result =  printAllBraces(3);
	    print(result);
	    /* output:
	    [{, {, {, }, }, }]
		[{, {, }, {, }, }]
		[{, {, }, }, {, }]
		[{, }, {, {, }, }]
		[{, }, {, }, {, }]
	     */
	}
}

/*
 * Print balanced brace combinations
 * https://www.educative.io/all-possible-braces
 * https://www.geeksforgeeks.org/print-all-combinations-of-balanced-parentheses/ (better)
 * https://practice.geeksforgeeks.org/problems/generate-all-possible-parentheses/1 (explain)
 */

package org.algorithms.backtracking;

import java.util.ArrayList;
import java.util.List;

public class PrintAllBalancedBraceCombinations2 {

    /**
     * Approach: Back Tracking, recursion
     * 
     * Time Complexity: O(2^n). Exponential, for every index there can be two options �{� or �}�.
     * Auxiliary Space: O(n). The space complexity can be reduced to O(n) if global variable or static variable is used to store the output string.
     */
	// function which generates all possible n pairs of
    // balanced parentheses.
    // open : count of the number of open parentheses used
    // in generating the current string s. close : count of
    // the number of closed parentheses used in generating
    // the current string s. s : currently generated string/
    // ans : a vector of strings to store all the valid
    // parentheses.
	public static List<String> printAllBraces(int n)
	{
        // vector ans is created to store all the possible
        // valid combinations of the parentheses.
		List<String> ans = new ArrayList<>();
 
        // initially we are passing the counts of open and
        // close as 0, and the string s as an empty string.
        printAllBraces(n, 0, 0, "", ans);
        
        return ans;
	}
	
    public static void printAllBraces(int n, int open, int close, String s, List<String> ans)
    {
        // if the count of both open and close parentheses
        // reaches n, it means we have generated a valid
        // parentheses. So, we add the currently generated
        // string s to the final ans and return.
        if (open == n && close == n) {
            ans.add(s);
            return;
        }
 
        // At any index i in the generation of the string s,
        // we can put an open parentheses only if its count
        // until that time is less than n.
        if (open < n) {
        	printAllBraces(n, open + 1, close, s + "{", ans);
        }
 
        // At any index i in the generation of the string s,
        // we can put a closed parentheses only if its count
        // until that time is less than the count of open
        // parentheses.
        if (close < open) {
        	printAllBraces(n, open, close + 1, s + "}", ans);
        }
    }
    
	public static void main(String[] args) {
		
		int n = 3;
		 
		List<String> list = printAllBraces(n);
		
        // Now, here we print out all the combinations.
        for (String s : list) {
        	System.out.println(s);
        }
	    /* output:
		{{{}}}
		{{}{}}
		{{}}{}
		{}{{}}
		{}{}{}
	     */
	}
}

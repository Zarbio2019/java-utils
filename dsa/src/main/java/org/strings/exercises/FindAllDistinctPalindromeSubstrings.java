/*
 * find all distinct palindromic sub-strings of a given string
 * https://www.geeksforgeeks.org/find-number-distinct-palindromic-sub-strings-given-string/
 * 
 * Approach: Iterative
 */

package org.strings.exercises;

public class FindAllDistinctPalindromeSubstrings {

    /**
     * Time Complexity: O(N^2), is polynomial
     * Auxiliary Space: O(1).
     */

    public static void main(String args[])
    {
    	String str = "aabbbaa";
        int count = 0; //findAllPalindromeSubstrings(str);
        System.out.println("Total palindrome substrings: " + count);
        /* output:
		aa
		bb
		bbb
		abbba
		aabbbaa
		bb
		aa
		Total palindrome substrings: 7
        */
    }
}
/*
notes:
*/
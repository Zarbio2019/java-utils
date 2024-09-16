/*
 * count all palindrome sub-strings in a string
 * https://www.geeksforgeeks.org/count-palindrome-sub-strings-string/ 
 * https://www.geeksforgeeks.org/count-palindrome-sub-strings-string-set-2/
 * 
 * Approach: Iterative
 * 
 * Step:
 * Take each character of string and start expanding to the right.
 * Compare with its reverse while is true, if is false go to the next character.
 */

package org.strings.exercises;

public class CountPalindromeSubstrings2 {

	/**
	 * Time Complexity: O(N^2)
	 * Auxiliary Space: O(N). Use StringBuffer.
	 */
	public static int countPalindromes(String s){
        String temp = "";
        StringBuffer stf;
        int count = 0;
        
        // Iterate the loop twice
        for (int i = 0; i < s.length(); i++) {
        	
            for (int j = i + 1; j <= s.length(); j++) {
                // Get each substring
                temp = s.substring(i, j);
                 
                // If length is greater than or equal to two
                // Check for palindrome    
                if (temp.length() >= 2) {
                	
                    // Use StringBuffer class to reverse the string
                    stf = new StringBuffer(temp);
                    stf.reverse();
                    
                    // Compare substring with reverse of substring
                    if (stf.toString().compareTo(temp) == 0)
                        count++;
                }
            }
        }
        // return the count
        return count;  
	}

    public static void main(String args[]) {
        String s = "abbaeae";
        System.out.println(countPalindromes(s));
        /* output:
        4
        */
    }
}
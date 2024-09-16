/*
 * Longest Palindrome in a String
 * Accept repetitive strings.
 * 
 * https://practice.geeksforgeeks.org/problems/longest-palindrome-in-a-string3411/1?page=1&company[]=Google&category[]=Strings&sortBy=submissions
 * https://www.geeksforgeeks.org/longest-palindromic-substring-using-dynamic-programming-2/
 */

package org.strings.exercises;

public class LongestPalindromeString {

	// variables to store and update
	static int maxLength;
    static String res;
    
    /**
     * takes a string prints the LPS and returns its length
     * 
     * Time Complexity: O(N^2), is polynomial
     * Auxiliary Space: O(1).
     */
    static int longestPalSubstr(String str)
    {
        res = "";
        maxLength = 1;
        
        // for every index in the string check palindromes
        // starting from that index
        for (int i = 0; i < str.length(); i++) {
            
        	// check for odd length palindromes
            cSubUtil(str, i, i);
            
            // check for even length palindromes
            cSubUtil(str, i, i + 1);
        }
        
        System.out.println("Longest palindrome substring is: " + res);
        return maxLength;
    }
    
    // get the longest palindrome
    // starting and expanding out from given center indices
    static void cSubUtil(String s, int l, int r)
    {
        // check if the indices lie in the range of string
        // and also if it is palindrome
        while (l >= 0 && r < s.length()
               && s.charAt(l) == s.charAt(r)) {
            // expand the boundary
            l--;
            r++;
        }
        
        // if it's length is greater than maxLength update
        // maxLength and res
        if (r - l - 1 >= maxLength) {
            res = s.substring(l + 1, r);
            maxLength = r - l - 1;
        }
        return;
    }
    
    public static void main(String args[])
    {
    	String str = "forgeeksskeegfor";
        System.out.println("Length is: " + longestPalSubstr(str));
        /* output:
		Longest palindrome substring is: geeksskeeg
		Length is: 10
        */
    }
}

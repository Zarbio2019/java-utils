/*
 * Longest Common Prefix in an Array
 * https://practice.geeksforgeeks.org/problems/longest-common-prefix-in-an-array5129/1?page=1&company[]=Google&category[]=Strings&sortBy=submissions
 * https://www.geeksforgeeks.org/longest-common-prefix-using-word-by-word-matching/
 * https://www.geeksforgeeks.org/longest-common-prefix-using-character-by-character-matching/
 * 
 * Approach: using Character by Character Matching.
 */

package org.strings.exercises;

public class LongestCommonPrefix {
    
	/**
	 * find the string having the minimum length and returns that length
	 */
    static int findMinLength(String arr[], int n)
    {
        int min = arr[0].length();
 
        for (int i = 1; i < n; i++)
        {
            if (arr[i].length() < min)
            {
                min = arr[i].length();
            }
        }
 
        return (min);
    }
    
    /** 
     * returns the longest common prefix from the array of strings
     * 
     * Time Complexity: O(N*M), iterating through all the characters of all the strings.
     *   N = number of strings
     *   M = length of the smallest string
     *   
     * Auxiliary Space: O(M), store the longest prefix string
     */
	static String commonPrefix(String arr[], int n)
    {
        int minlen = findMinLength(arr, n);
 
        String result = ""; // Our resultant string
        char current; // The current character
 
        // i = length of the smallest string
        // j = number of strings
        for (int i = 0; i < minlen; i++)
        {
            // Current character (must be same
            // in all strings to be a part of
            // result)
            current = arr[0].charAt(i);
 
            for (int j = 1; j < n; j++)
            {
                if (arr[j].charAt(i) != current)
                {
                    return result;
                }
            }
 
            // Append to result
            result += (current);
        }
 
        return result;
    }
    
    public static void main(String args[])
    {
    	// Test 1:
    	String arr[] = {"geeksforgeeks", "geeks", "geek", "geezer"};
        int n = arr.length;
 
        String ans = commonPrefix(arr, n);
 
        if (ans.length() > 0) {
            System.out.println("The longest common prefix is " + ans);
        } else {
            System.out.println("There is no common prefix");
        }
        /* output:
		The longest common prefix is gee
        */
        
        /****************************************/
        
        // Test 2:
    	String arr2[] = {"hello", "world"};
        int n2 = arr2.length;
 
        String ans2 = commonPrefix(arr2, n2);
 
        if (ans2.length() > 0) {
            System.out.println("The longest common prefix is "
                    + ans2);
        } else {
            System.out.println("There is no common prefix");
        }
        /* output:
		The longest common prefix is gee
        */
    }
}

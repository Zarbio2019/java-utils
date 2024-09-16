/*
 * Longest Common Prefix in an Array
 */

package org.strings.exercises;

public class LongestCommonPrefix2 {
    
    /** 
     * returns the longest common prefix from the array of strings
     *  1. Start assuming the first string is the longest common prefix.
     *  2. Compare this prefix with each subsequent string in the array,
     *  updating the prefix by trimming characters that don't match.
     *  
     * Time Complexity: O(N*M), iterating through all the characters of all the strings.
     *   N = number of strings in array
     *   M = length of the longest string in the array
     *   
     * Auxiliary Space: O(1), operates in-place, does not require additional space proportional to the input.
     */
    public static String longestCommonPrefix(String[] strs) {
    	
        if (strs == null || strs.length == 0) {
            return "";
        }
        
        String prefix = strs[0];
        
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) {
                    return "";
                }
            }
        }
        
        return prefix;
    }
    
    public static void main(String args[])
    {
    	// Test 1:
    	String arr[] = {"geeksforgeeks", "geeks", "geek", "geezer"};
 
        String ans = longestCommonPrefix(arr);
 
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
 
        String ans2 = longestCommonPrefix(arr2);
 
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

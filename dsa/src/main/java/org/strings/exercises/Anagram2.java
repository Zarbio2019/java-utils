/*
 * Two strings are Anagram.
 * https://www.geeksforgeeks.org/check-whether-two-strings-are-anagram-of-each-other/
 * 
 * Approach: use a character count array.
 */

package org.strings.exercises;

public class Anagram2 {
    
	static int NO_OF_CHARS = 256;
	
    /**
     * Time Complexity: O(N), N length of the input strings
     * Auxiliary Space: O(256), O(1) for constant space
     */
	public static boolean areAnagrams(String str1, String str2) {
		
        // Check if the strings are of different lengths
        if (str1.length() != str2.length()) {
            return false;
        }

        // Create an array to store character counts
        int n = str1.length();
        int[] charCount = new int[NO_OF_CHARS];
        char[] arrStr1 = str1.toCharArray();
        char[] arrStr2 = str2.toCharArray();
        
        // Increment/Decrement counts for characters in the strings
        for (int i=0; i<n; i++) {        	
        	charCount[arrStr1[i]]++;
        	charCount[arrStr2[i]]--;  
        }

        // If the strings are anagrams, all counts in the array should be 0
        for (int count : charCount) {
            if (count != 0)
                return false;
        }

        return true;
    }
    
    public static void main(String args[])
    {
    	String str1 = "listen";
        String str2 = "silent";

        if (areAnagrams(str1, str2)) {
            System.out.println("The strings are anagrams.");
        } else {
            System.out.println("The strings are not anagrams.");
        }
        /* output:
		The strings are anagrams.
        */
    }
}

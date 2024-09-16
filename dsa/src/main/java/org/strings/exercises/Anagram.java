/*
 * Two strings are Anagram.
 * Check whether two Strings are anagram of each other
 * 
 * https://www.geeksforgeeks.org/check-whether-two-strings-are-anagram-of-each-other/
 */

package org.strings.exercises;

import java.util.Arrays;

public class Anagram {
    
    /**
     * Time Complexity: O((N*log(N)), for sorting
     * Auxiliary Space: O(1), constant space
     */
    static boolean areAnagram(String a, String b)
    {
        // Sort the characters in both strings
        char[] arrA = a.toCharArray();
        char[] arrB = b.toCharArray();
        Arrays.sort(arrA);
        Arrays.sort(arrB);
 
        // Compare the sorted strings
        return Arrays.equals(arrA, arrB);
    }
    
    public static void main(String args[])
    {
        String a = "geeksforgeeks";
        String b = "forgeeksgeeks";
 
        if (areAnagram(a, b))
            System.out.println("YES, They are Anagram");
        else
            System.out.println("NO, They are not Anagram");
        /* output:
		YES, They are Anagram
        */
    }
}

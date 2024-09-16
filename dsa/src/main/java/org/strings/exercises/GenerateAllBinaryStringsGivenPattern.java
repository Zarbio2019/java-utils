/*
 * Generate all binary strings from given pattern
 * https://www.geeksforgeeks.org/generate-all-binary-strings-from-given-pattern/?ref=lbp
 * 
 * Approach: using Recursion.
 */

package org.strings.exercises;

public class GenerateAllBinaryStringsGivenPattern {
    
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
     * recursive function to generate all binary strings
     * formed by replacing each wildcard character by 0 or 1
     * 
     * Time Complexity: O(2^N), N length of th given string and there are 2 possibilities.
     * Auxiliary Space: O(N^2), as a copy of the string is created in every recursive call.
     */
    public static void print(char str[], int index)
    {
        if (index == str.length)
        {
            System.out.println(str);
            return;
        }
 
        if (str[index] == '?')
        {
            // replace '?' by '0' and recurse
            str[index] = '0';
            print(str, index + 1);
             
            // replace '?' by '1' and recurse
            str[index] = '1';
            print(str, index + 1);
             
            // NOTE: Need to backtrack as string
            // is passed by reference to the
            // function
            str[index] = '?';
        }
        else
            print(str, index + 1);
    }
    
    public static void main(String args[])
    {
    	String input = "1??0?101";
        char[] str = input.toCharArray();
        print(str, 0);
        /* output:
		10000101
		10001101
		10100101
		10101101
		11000101
		11001101
		11100101
		11101101
        */
    }
}

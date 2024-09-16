/*
 * count all palindrome sub-strings in a string
 * 
 * Accept repetitive strings.
 *  
 * https://www.geeksforgeeks.org/count-palindrome-sub-strings-string/ 
 * https://www.geeksforgeeks.org/count-palindrome-sub-strings-string-set-2/
 * 
 * Approach: Iterative
 */

package org.strings.exercises;

public class CountPalindromeSubstrings {

	/**
	 * Time Complexity: O(N^2), polynomial, worst case when all characters are same e.g: �aaaaaaaa�
	 * Auxiliary Space: O(1).
	 */
	public static int countPalindromes(String s){
		int count = 0;
		
		for(int i=0; i<s.length(); i++) {
	    //Taking every node as a center and expanding it from there to check if it is a palindrome or not.
	    //This will make sure that the length of the string to be checked > 2.
			count += expandFromCenter(s, i-1, i+1) +  expandFromCenter(s, i , i+1);
		}
	       
		return count;   
	}
 
	public static int expandFromCenter(String s, int left, int right){
		int count = 0;
		
		//As we expand from the center and find if the character matches, we increase the count.
		while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
			count++;
		    left--;
		    right++;
		}
		
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
/*
notes:

string = abbaeae
pos    = 0123456

i=0, pos = 0 -> a
	compare sides: (pos=-1)? = (pos=1)a		-> false
	
	compare to rigth: (pos=0)a = (pos=1)b	-> false

i=1, pos = 1 -> a
	compare sides: (pos=0)a = (pos=2)b		-> false
	
	compare to right: (pos=1)b = (pos=2)b	-> true
	compare to right: (pos=0)a = (pos=3)a	-> true

...
*/
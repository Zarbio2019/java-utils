/*
 * Find All non-single letter Palindrome Substrings.
 * Accept repetitive strings.
 * 
 * Approach: Iterative
 * 
 * https://www.educative.io/find-all-palindrome-substrings
 * 
 * For each letter in the input string, start expanding to the left and right,
 * while checking for even and odd length palindromes. Move to the next letter if we know a palindrome doesn�t exist.
 * We expand one character to the left and right and compare them.
 * If both of them are equal, we print out the palindrome substring.
 */

package org.strings.exercises;

public class FindAllPalindromeSubstrings {

    /**
     * Time Complexity: O(N^2), is polynomial
     * Auxiliary Space: O(1).
     */
	public static int findAllPalindromeSubstrings(String input) {
		int count = 0;
	    
		for(int i = 0 ; i < input.length(); ++i) {
			System.out.println("i=" + i);
			count+= findPalindromesInSubString(input, i-1, i+1);
			count+= findPalindromesInSubString(input, i, i+1);
		}

	    return count;
	}
	  
	public static int findPalindromesInSubString(String input, int j, int k) {
    
		int count = 0;
		
		for (; j >= 0 && k < input.length(); --j, ++k) {
			if (input.charAt(j) != input.charAt(k)) {      
				break;
			} 
      
			System.out.println(input.substring(j, k+1));
			count++;
		}
		
		return count;
	}

    public static void main(String args[])
    {
    	String str = "aabbbaa";
        int count = findAllPalindromeSubstrings(str);
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

string = aabbbaa
pos    = 0123456

i=0, pos = 0 -> a
	compare sides: (pos=-1)? = (pos=1)a		-> false -> break
	
	compare to rigth: (pos=0)a = (pos=1)a	-> true: aa

i=1, pos = 1 -> a
	compare sides: (pos=0)a = (pos=2)b		-> false -> break
	
	compare to right: (pos=1)a = (pos=2)b	-> false -> break

i=2, pos = 2 -> b
	compare sides: (pos=1)a = (pos=3)b		-> false -> break
	
	compare to right: (pos=2)b = (pos=3)b	-> true -> bb
	compare to right: (pos=1)a = (pos=4)b	-> false -> break
	
i=3, pos = 3 -> b
	compare sides: (pos=2)b = (pos=4)b	-> true: bbb
	compare sides: (pos=1)a = (pos=5)a	-> true: abbba
	compare sides: (pos=0)a = (pos=6)a	-> true: aabbbaa
	
	compare to right: (pos=3)b = (pos=4)b	-> true: bb
	compare to right: (pos=2)b = (pos=5)a	-> false -> break
	
i=4, pos = 4 -> b
	compare sides: (pos=3)b = (pos=5)a		-> false -> break
	
	compare to right: (pos=4)b = (pos=5)a	-> false -> break
*/
/*
 * String Segmentation
 * https://www.educative.io/string-segmentation 
 * 
 * Approach: Recursive
 */
package org.strings.exercises;

import java.util.HashSet;
import java.util.Set;

public class StringSegmentation {

	/**
	 * Runtime Complexity: O(2^N), exponential, recursion, each character position can be 2 possibilities: split (substring) or not.
	 * Memory Complexity: O(N^2), is polynomial, include auxiliary space and space, depends on the numbers of words
	 * and the size of the dictionary. 
	 */
	public static boolean canSegmentString(String s, Set<String> dictionary) {
		for (int i = 1; i <= s.length(); ++i) {
			String first = s.substring(0, i);
		
			if (dictionary.contains(first)) { // Dictionary Lookup: constant time O(1) for hash set
				String second = s.substring(i);

		        if (second == null || second.length() == 0 || dictionary.contains(second) || canSegmentString(second, dictionary)) {
		        	return true;
		        }
			}
		}
	    
		return false;
	}
	
    public static void main(String args[])
    {
    	Set<String> dictionary = new HashSet<String>();
        String s = new String();
        s = "hellonownow";

        dictionary.add("hello");
        dictionary.add("hell");
        dictionary.add("on");
        dictionary.add("now");
                
        if (canSegmentString(s, dictionary)) {
        	System.out.println("String Can be Segmented");
        } else {
        	System.out.println("String Can NOT be Segmented");
        }
        /* output:
		String Can be Segmented
        */
    }
}

/*
 * String Segmentation
 * https://www.youtube.com/watch?v=hLQYQ4zj0qg
 * https://www.geeksforgeeks.org/word-break-problem-dp-32/
 * 
 * Approach: Dynamic Programming
 * Dynamic programming allows us to break down the problem into smaller subproblems and solve each subproblem only once,
 * storing its result for future reference.
 *  
 * Steps:
 * 1. We convert the word dictionary into a HashSet for efficient lookup.
 * 2. We create a boolean array dp[] of size n+1, where n is the length of the input string s.
 * 3. We initialize dp[0] as true, indicating that an empty string can be segmented.
 * 4. We iterate through all positions i in the string and check if any prefix of the string ending at position i can be segmented into words from the dictionary.
 * 5. To determine if a prefix ending at position i can be segmented, we check if dp[j] is true (where j < i) and if the substring from j to i-1 is present in the dictionary.
 * 6. Finally, we return dp[n], where n is the length of the input string, which indicates whether the entire string can be segmented into words from the dictionary.
 */
package org.strings.exercises;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StringSegmentation4 {

    /**
     * returns true if the word can be segmented into parts such
     * that each part is contained in dictionary
     * 
     * Time Complexity: O(N^2), polynomial
     *   O(n*n) if n>s
     *   O(n*s) if s>n
     *   where s is the length of the largest string in the dictionary and
     *   n is the length of the given string.
     * Auxiliary Space: O(N), create array dp[]
     */
    public static boolean canSegmentString(String s, List<String> dictionary) {
    	
        Set<String> wordSet = new HashSet<>(dictionary);
        int n = s.length();
        
        // create a dp table to store results of subproblems
        // value of dp[i] will be true if string s can be segmented
        // into dictionary words from 0 to i.
    	boolean[] dp = new boolean[n + 1];
    	
    	dp[0] = true; // Empty string is always in the dictionary
    	
    	for(int i=1; i <= n; i++) {
    		for(int j=0; j < i; j++) {
    			if(dp[j] && wordSet.contains(s.substring(j, i))) {
    				dp[i] = true;
    				break; // Break the inner loop is found
    			}
    		}
    	}
    	
    	return dp[n];
    }
    
    public static void main(String args[])
    {
    	// test 1: 
        List<String> wordDict = Arrays.asList("leet", "code");
    	String s = "leetcode";

        System.out.println(canSegmentString(s, wordDict)); // output: true
    
        // test 2:
        List<String> wordDict2 = Arrays.asList("apple", "pen", "applepen", "pine", "pine");
        
        String s1 = "pineapplepenapple";
        System.out.println(canSegmentString(s1, wordDict2)); // output: true

        String s2 = "catsandog";
        System.out.println(canSegmentString(s2, wordDict2)); // output: false
        
    	// test 3:
    	String s3 = "code";
        List<String> wordDict3 = Arrays.asList("c", "od", "e", "x");
         
        System.out.println(canSegmentString(s3, wordDict3)); // output: true
    }
}
/*
notes:

string = "code"
dict = "c", "od", "e", "x"

dp[0] = empty				->true

dp[1] = c					->true
	dp[0] & c				= true
	
dp[2] = co					->false
	dp[0](empty) & co		= false
	dp[1](c) & o			= false

dp[3] = cod					->true
	dp[0](empty) & cod		= false	
	dp[1](c) & od			= true -> break
	dp[2](co) & d			= not analyzed
	
dp[4] = code				->true
	dp[0](empty) & code		= false
	dp[1](c) & ode			= false
	dp[2](co) & de			= false
	dp[3](cod) & e			= true -> break
 */

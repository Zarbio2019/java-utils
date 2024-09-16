/*
 * String Segmentation
 * https://www.youtube.com/watch?v=hLQYQ4zj0qg
 * 
 * Approach: Memoization (caching intermediate results)
 * To improve the performance, to avoid redundant calculations and optimize the Runtime Complexity.
 * You can see that you may be computing the same substring multiple times, even if it doesn�t exist in the dictionary.
 * This redundancy can be fixed by memoization, where you remember which substrings have already been solved.
 * To achieve memoization, you can store the second string in a new set each time. This will reduce both time and memory complexities.
 */
package org.strings.exercises;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class StringSegmentation3 {

	// set to hold dictionary values
    private static Set<String> dictionary = new HashSet<>();

    /**
     * returns true if the word can be segmented into parts such
     * that each part is contained in dictionary
     * 
     * Time Complexity: O(2^N), exponential
     * Auxiliary Space: O(N^2), because recursion.
     */
    public static boolean canSegmentString(String s, Set<String> dictionary) {
        // Memoization map to store intermediate results
        Map<String, Boolean> memo = new HashMap<>();
        return canSegmentStringHelper(s, dictionary, memo);
    }

    private static boolean canSegmentStringHelper(String s, Set<String> dictionary, Map<String, Boolean> memo) {
        // If the result for this substring is already cached, return it
        if (memo.containsKey(s)) {
            return memo.get(s);
        }

        for (int i = 1; i <= s.length(); ++i) {
            String first = s.substring(0, i);

            if (dictionary.contains(first)) {
                String second = s.substring(i);

                if (second.isEmpty() || dictionary.contains(second) || canSegmentStringHelper(second, dictionary, memo)) {
                    // Cache the result for the current substring
                    memo.put(s, true);
                    return true;
                }
            }
        }

        // Cache the result for the current substring
        memo.put(s, false);
        return false;
    }
    
    public static void main(String args[])
    {
        Set<String> dictionary = new HashSet<>();
        dictionary.add("apple");
        dictionary.add("pen");
        dictionary.add("applepen");
        dictionary.add("pine");
        dictionary.add("pineapple");

        String s1 = "pineapplepenapple";
        String s2 = "catsandog";

        System.out.println(canSegmentString(s1, dictionary)); // output: true
        System.out.println(canSegmentString(s2, dictionary)); // output: false
    }
}

/* leetcode 1. Two Sum
 * https://leetcode.com/problems/two-sum/
 * return the indexes.
 *
 * Approach: It uses a HashMap to store the complement of each element and quickly look up whether the
 * complement exists in the array. If the complement is found, we return the indices of the two numbers.
 * Otherwise, if no solution is found, we return an empty array.
 */

package org.arrays.exercises;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SumOfTwoValuesLeetcode1 {

	/**
	 * Time Complexity: O(n), n=size of the array.
     * Auxiliary Space:
	 *   worst case: O(n), where there are no two numbers that add up to the target, the HashMap will store all the n elements from the nums array.
	 */
    static int[] twoSum(int[] nums, int target) {
        
    	int n = nums.length;
    	
        // Create a HashMap to store the complement of each element in the array
        Map<Integer, Integer> complementMap = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int complement = target - nums[i];
            
            // If the complement exists in the HashMap, we found the solution
            if (complementMap.containsKey(complement)) {
                return new int[] { complementMap.get(complement), i };
            }
            
            // Otherwise, add the current number and its index to the HashMap
            complementMap.put(nums[i], i);
		}
		
		// Return an empty array if no solution is found
        return new int[0];
	}
	
	public static void main(String args[])
 	{	
 		int A[] = {2,7,11,15};
 		int[] result = twoSum(A, 9);
 		System.out.println(Arrays.toString(result));
 		/*
 		output:
 		[0, 1]
 		*/
 	}
}

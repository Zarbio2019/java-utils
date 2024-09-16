/*
 * Sum of two values
 * https://www.educative.io/blog/google-coding-interview-questions?eid=5082902844932096
 *
 * Approach: use HashMapfor complements.
 * 
 * Given an array of integers and a value, determine if there are any two integers in the array
 * whose sum is equal to the given value. Return true if the sum exists and return false if it does not.
 */

package org.arrays.exercises;

import java.util.HashMap;
import java.util.Map;

class SumOfTwoValues {
	
	/**
	 * Time Complexity: O(n), n=size of the array
     * Auxiliary Space:
	 *   worst case: O(n), where there are no two numbers that add up to the target, the HashMap will store all n elements in the nums array.
	 */
    static boolean twoSum(int[] nums, int target) {
    	
    	// Create a HashMap to store the complement of each element in the array
        Map<Integer, Integer> complementMap = new HashMap<>();
        
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            
            if (complementMap.containsKey(complement))
                return true;
            
            complementMap.put(nums[i], i);
        }
        
        return false;
    }
	
	public static void main(String args[])
 	{
 		int A[] = {2,7,11,15};
 		System.out.println(twoSum(A, 9));
 		/*
 		output:
 		true
 		*/
 	}
}

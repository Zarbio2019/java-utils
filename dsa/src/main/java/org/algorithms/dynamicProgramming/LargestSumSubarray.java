/*
 * Largest Sum Subarray
 * https://www.educative.io/largest-sum-subarray
 * 
 * Approach: Kadane�s Algorithm
 */
package org.algorithms.dynamicProgramming;

public class LargestSumSubarray {
    
    /**
     * Time Complexity: O(N)
     * Auxiliary Space: O(1)
     */
    static int findMaxSumSubArray(int[] A) {
    	
    	if (A.length < 1) {
    		return 0;
    	}

        int currMax = A[0];
        int globalMax = A[0];
        
        for (int i = 1; i < A.length; ++i) {
        	if (currMax < 0) {
        		currMax = A[i];
        	} else {
        		currMax += A[i];
        	}

        	if (globalMax < currMax) {
        		globalMax = currMax;
        	}
        }

        return globalMax;
    }
    
    public static void main(String args[])
    {
    	int[] v = new int[]{-4, 2, -5, 1, 2, 3, 6, -5, 1};
        System.out.println("Sum of largest subarray: " + findMaxSumSubArray(v));
        /* output:
		Sum of largest subarray: 12
        */
    }
}

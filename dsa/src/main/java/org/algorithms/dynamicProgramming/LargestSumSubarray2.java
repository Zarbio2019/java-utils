/*
 * Largest Sum Contiguous Subarray
 * https://www.geeksforgeeks.org/largest-sum-contiguous-subarray/
 * 
 * Approach: Kadane�s Algorithm
 */
package org.algorithms.dynamicProgramming;

public class LargestSumSubarray2 {
    
    /**
     * Time Complexity: O(N)
     * Auxiliary Space: O(1)
     */
    static int findMaxSumSubArray(int[] a) {
    	
    	int size = a.length;
        int max_so_far = Integer.MIN_VALUE, max_ending_here = 0;
        int start = 0, end = 0, s = 0; // for printing
 
        for (int i = 0; i < size; i++) {
            max_ending_here += a[i];
            
            if (max_so_far < max_ending_here) {
                max_so_far = max_ending_here; // set the max value
                start = s;
                end = i;
            }
            
            if (max_ending_here < 0) { // negative sum is discarded by assigning max = 0
                max_ending_here = 0;
                s = i + 1;
            }
        }
        
        //System.out.println("Maximum contiguous sum is " + max_so_far);
		System.out.println("Starting index " + start);
		System.out.println("Ending index " + end);

        return max_so_far;
    }
    
    public static void main(String args[])
    {
    	int[] a = { -2, -3, 4, -1, -2, 1, 5, -3 };
        System.out.println("Maximum contiguous sum is " + findMaxSumSubArray(a)); 
        /* output:
		Starting index 2
		Ending index 6
		Maximum contiguous sum is 7
        */
    }
}

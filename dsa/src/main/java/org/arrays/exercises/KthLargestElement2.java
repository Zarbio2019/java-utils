/*
 * kth largest element in an array
 * https://www.scaler.com/topics/kth-largest-element/
 * 
 * Approach: Using Standard Template Library
 */

package org.arrays.exercises;

import java.util.Arrays;
import java.util.Collections;

public class KthLargestElement2 {

	/**
	 * Time Complexity: O(N*Log(N)), worst case, because built-in-sorting methods use hybrid sort (Quick sort, Merge sort, Insertion sort and Heap sort).
	 * Auxiliary Space: O(1)
	 */
	public static int KthLargestElement(Integer arr[], int n, int k) {
		Arrays.sort(arr, Collections.reverseOrder()); // sort array in reverse order
	    return arr[k - 1]; // return kth largest element
	}

	public static void main(String[] args) {
		Integer arr[] = {2, 1, 4, 6, 3, 9, 7};
		int n = arr.length;
		int k = 2;
		int x = KthLargestElement(arr, n, k);
	    System.out.print("Kth largest element is " + x);
	    /* output:
	    Kth largest element is 7
	    */
	}
}

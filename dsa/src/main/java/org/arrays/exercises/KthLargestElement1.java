/*
 * kth largest element in an array
 * https://www.scaler.com/topics/kth-largest-element/
 *
 * Approach: Sorting
 */

package org.arrays.exercises;

import java.util.Arrays;

public class KthLargestElement1 {

	/**
	 * Time Complexity: O(N Log(N)), worst case, because built-in-sorting methods use hybrid sort (Quick sort, Merge sort, Insertion sort and Heap sort).
	 * Auxiliary Space: O(1)
	 */
	public static int KthLargestElement(int arr[], int n, int k) {
		Arrays.sort(arr); // sorting the array		
	    return arr[n - k]; // return kth largest element
	}

	public static void main(String[] args) {
		int arr[] = {2, 1, 4, 6, 3, 9, 7};
		int n = arr.length;
		int k = 2;
		int x = KthLargestElement(arr, n, k);
	    System.out.print("Kth largest element is " + x);
		/* output:
		Kth largest element is 7
		*/
	}
}

/*
 * kth largest element in an array
 * https://www.scaler.com/topics/kth-largest-element/
 *
 * Approach: Using Max Heap (better)
 *
 * Note: A Max Heap is a data structure that has the property that its topmost element is always the maximum element of the heap.
 *
 * Steps:
 * 1. Create a max-heap
 * 2. Run a loop from i=0 to i=n-1
 * 3. Push all the elements of the array into the heap
 * 4. Run a loop from i=0 to i=k-2 and each time pop an element from the heap.
 * 5. Return the topmost element of the heap
 */

package org.arrays.exercises;

import java.util.Collections;
import java.util.PriorityQueue;

public class KthLargestElement4 {
	
    /**
     * Time Complexity: O(N Log(N)), worst case when k = n
	 * pushing k elements of the array into the heap: O(Log(K))
	 * then, for performing n operations by running a loop from i=0 to i=n-1: O(N)*O(Log(K)) = O(N*Log(K)).
     *
	 * Auxiliary Space: O(N), using heap variable to store elements.
     */
	public static int KthLargestElement(int arr[], int n, int k) {
		
		PriorityQueue<Integer> q = new PriorityQueue<Integer>(Collections.reverseOrder()); // creating max-heap

		int i;
		
		for(i=0; i<n; i++) { // push every element into the heap
			q.add(arr[i]); 
		}

		for(i=0; i<k-1;i++) {
			q.remove(q.peek()); // pop k-1 elements from heap
		}
		
		return q.peek();
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

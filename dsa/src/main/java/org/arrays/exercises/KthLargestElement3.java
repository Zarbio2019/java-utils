/*
 * kth largest element in an array
 * https://www.scaler.com/topics/kth-largest-element/
 * 
 * Approach: Using Min Heap
 *
 * Note: A Min Heap is a data structure that has the property that its topmost element is always the minimum element of the heap.
 *
 * Steps:
 * 1. Create a min-heap
 * 2. Run a loop from i=0 to i=n-1
 * 3. Push each element into the min-heap
 * 4. If at any point the size of the min-heap becomes greater than k, pop one element from the min-heap (this is because we have to only put k elements in the heap).
 * 5. After the loop is over, return the topmost element of the heap, because this is the kth largest element of the array.
 */

package org.arrays.exercises;

import java.util.PriorityQueue;

public class KthLargestElement3 {

	/**
	 * Time Complexity: O(N Log(N)), worst case when k = n
	 * pushing k elements of the array into the heap: O(Log(K))
	 * then, for performing n operations by running a loop from i=0 to i=n-1: O(N)*O(Log(K)) = O(N*Log(K)).
     *
	 * Auxiliary Space: O(N), using heap variable to store elements.	    
	 */
	public static int KthLargestElement(int arr[], int n, int k) {
		
		PriorityQueue<Integer> q = new PriorityQueue<Integer>(); // creating min-heap
		
		for(int i=0; i<n; i++) {
			q.add(arr[i]); // push every element into the heap
			if(q.size() > k) q.remove(q.peek()); // if the size of the heap becomes greater than k, pop the element
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

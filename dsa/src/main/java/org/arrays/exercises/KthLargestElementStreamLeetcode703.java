/*
 * Kth largest element in a stream
 * https://leetcode.com/problems/kth-largest-element-in-a-stream/
 * https://leetcode.com/problems/kth-largest-element-in-a-stream/editorial/
 *
 * Approach: using a Min-Heap (better)
 *
 * Steps:
 * 1. In the constructor, create a min heap using the elements from nums. Then, pop from the heap until heap.length == k.
 *
 * 2. For every call to add():
 * First, push val into heap.
 * Next, check if heap.length > k. If so, pop from the heap.
 * Finally, return the smallest value from the heap, which we can get in O(1)O(1)O(1) time.
 */

package org.arrays.exercises;

import java.util.PriorityQueue;

class KthLargestElementStreamLeetcode703 {
  
	private static int k;
    private PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    
    /**
     * Initialize the stream
     * 
     * Time Complexity: O(N * Log(K)) = O(N*Log(K) + M*Log(K)),
     *   K = # elements of Min Heap (Kth largest elements),
     *   N = # elements of arrays
     *   M = # calls add()
     * Auxiliary Space: O(K)
     */
	public KthLargestElementStreamLeetcode703(int k, int[] nums) {
        this.k = k;

        for (int num: nums) {
        	add(num);
        }
    }
	
	// appends the value to the stream and returns the Kth largest element in the stream
    public int add(int val) {
    	minHeap.offer(val); // insert head
    	
        if (minHeap.size() > k) // heap only contain the Kth largest elements
        	minHeap.poll(); // request and remove head

        return minHeap.peek(); // return head
    }

	public static void main(String[] args) {
		 
		// Test 1:
		System.out.println("Test 1");

		int arr1[] = { 4, 5, 8, 2 };
		int k = 3; 
		
		KthLargestElementStreamLeetcode703 kthLargest = new KthLargestElementStreamLeetcode703(k, arr1);
		
		System.out.println(kthLargest.add(3)); // return 4, stream = 2, 3, 4, 5, 8
		System.out.println(kthLargest.add(5)); // return 5, stream = 2, 3, 4, 5, 5, 8
		System.out.println(kthLargest.add(10)); // return 5, stream = 2, 3, 4, 5, 5, 8, 10
		System.out.println(kthLargest.add(9)); // return 8, stream = 2, 3, 4, 5, 5, 8, 9, 10
		System.out.println(kthLargest.add(4)); // return 8, stream = 2, 3, 4, 4, 5, 5, 8, 9, 10
	    /*
	    input: arr[] = { 4, 5, 8, 2 }
	    output:
		4
		5
		5
		8
		8
		*/
		
		/***************************************/
		
		// Test 2:
		System.out.println("Test 2");
		
		int arr2[] = { 4, 1, 3, 12, 7, 14 };
		k = 3;
		KthLargestElementStreamLeetcode703 kthLargest2 = new KthLargestElementStreamLeetcode703(k, arr2);
		System.out.println(kthLargest2.add(6)); // return 7
		System.out.println(kthLargest2.add(13)); // return 12
		System.out.println(kthLargest2.add(4)); // return 12
	}
}

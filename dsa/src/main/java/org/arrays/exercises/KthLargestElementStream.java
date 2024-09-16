/*
 * Kth largest element in a stream
 * https://www.geeksforgeeks.org/kth-largest-element-in-a-stream/
 * https://www.geeksforgeeks.org/problems/kth-largest-element-in-a-stream2220/1
 *
 * Approach: using a Min-Heap (better)
 *
 * Steps:
 * 1. Min Heap to store K largest elements of the stream.
 *    The Kth largest elements is always at the root and can be found in O(1) time.
 * 2. Compare the new element with the root of the heap. If a new element is
 *    smaller, then ignore it. Otherwise, replace the root with a new element and
 *    call heapify (create) for the root of the modified heap.
 *
 * heapify = O(log N), create a heap from an array.
 * Binary Heap/Min Heap = is a complete binary tree.
 *   Uses:
 *     Priority Queue: O(log N) time. Elements with higher priority are retrieved before elements with lower priority.
 * 	 
 */
package org.arrays.exercises;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class KthLargestElementStream {

	/**
	 * using min heap DS
     * how data are stored in min Heap DS
     *      1
     *    2   3
     * if k==3 , then top element of heap
     * itself the kth largest element
     *
	 * Time Complexity: O(N * Log(K)), K = # elements of Min Heap, N = # elements of arrays
	 * Auxiliary Space: O(K)
	 */
	static List<Integer> getAllKthNumber(int arr[], int k)
	{
		PriorityQueue<Integer> min = new PriorityQueue<>(); // creating min-heap

        // list to store kth largest number
        List<Integer> list = new ArrayList<>();
 
        // one by one adding values to the min heap
        for (int val : arr) {
 
            // if the heap size is less than k , we add to
            // the heap
            if (min.size() < k)
                min.add(val);
 
            /*
            otherwise,
            first we  compare the current value with the
            min heap TOP value
 
            if TOP val > current element, no need to
            remove TOP, because it will be the largest kth
            element anyhow
 
            else we need to update the kth largest element
            by removing the top lowest element
            */
 
            else {
                if (val > min.peek()) {
                    min.poll(); // delete top (min)
                    min.add(val);
                }
            }
 
            // if heap size >=k we add
            // kth largest element
            // otherwise -1
 
            if (min.size() >= k)
                list.add(min.peek()); // peek = get top (min)
            else
                list.add(-1);
        }
        return list;
	}
    
	// return Array
	static int[] getAllKthNumberArray(int arr[], int k)
	{
		List<Integer> intList = getAllKthNumber(arr, k);
		int listSize = intList.size();
		
		int[] intArray = new int[listSize];
				
		for(int i=0; i<listSize; i++)
			intArray[i] = intList.get(i);
		
		return intArray;
	}
	
	// Drive Code
	public static void main(String[] args) {
		
		System.out.println("Test 1");

		int k = 3;
		int arr[] = { 10, 20, 11, 70, 50, 40, 100, 5 };
 
		// Function call: return List
		List<Integer> res = getAllKthNumber(arr, k);
		
		for (int x : res)
			System.out.println("Kth largest element is " + x);
		/*
		input: arr[] = { 10, 20, 11, 70, 50, 40, 100, 5 }
		output:
		Kth largest element is -1
		Kth largest element is -1
		Kth largest element is 10
		Kth largest element is 11
		Kth largest element is 20
		Kth largest element is 40
		Kth largest element is 50
		Kth largest element is 50
		*/
		
		// return Array
		int[] resArray = getAllKthNumberArray(arr, k);
		System.out.println(Arrays.toString(resArray));
		/* output
		[-1, -1, 10, 11, 20, 40, 50, 50]
		 */
	
		/*
		input: arr[] = { 1, 2, 3, 4, 5, 6 }
		output:
		Kth largest element is -1
		Kth largest element is -1
		Kth largest element is 1
		Kth largest element is 2
		Kth largest element is 3
		Kth largest element is 4
		 */
		
		/***************************************/
		
		System.out.println("Test 2");
		
		int k2 = 2, arr2[] = { 2, 5, 1, 7, 9 };
		int[] resArray2 = getAllKthNumberArray(arr2, k2);
		System.out.println(Arrays.toString(resArray2));
		/* output:
		[-1, 2, 2, 5, 7]
		 */
		
		/***************************************/

		System.out.println("Test 3");

		int k3 = 3, arr3[] = { 3, 5, 4, 2, 9 };
		int[] resArray3 = getAllKthNumberArray(arr3, k3);
		System.out.println(Arrays.toString(resArray3));
		/* output:
		[-1, -1, 3, 3, 4]
		 */
	}
}
/* notes:
int k2 = 2, arr2[] = { 2, 5, 1, 7, 9 };
                 pos = 0  1  2  3  4

for arr[0]=2
	2th largest element is in { , 2} = doesn't exist = -1

for arr[1]=5
	2th largest element is in {2, 5} = 2

for arr[2]=1
	2th largest element is in {2, 5, 1} = 2
	
for arr[3]=7
	2th largest element is in {2, 5, 1, 7} = 5
	
for arr[4]=9
	2th largest element is in {2, 5, 1, 7, 9} = 7
	
output = {-1, 2, 2, 5, 7}
*/

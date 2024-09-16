/*
 * Find Low/High Index
 * https://www.educative.io/find-low-high-index
 */

package org.algorithms.sorting;

import java.util.Arrays;
import java.util.List;

public class FindLowHighIndex {
	  
	/**
	 * Time Complexity: O(Log(N)), because Binary Search, is logarithmic.
	 * Memory Complexity: O(1), no extra storage is being used.
	 */
	static int findLowIndex (List<Integer> arr, int key) {
		int low = 0;
	    int high = arr.size() - 1;
	    int mid = high / 2;
	  
	    while (low <= high) {
	
	    	int mid_elem = arr.get(mid);
	
	    	if (mid_elem < key) {
	    		low = mid + 1;
	    	}
	    	else {
	    		high = mid - 1;
	    	}
	  
	    	mid = low + (high - low) / 2;
	    }
	
	    if (low < arr.size() && arr.get(low) == key) {
	    	return low;
	    }
	
	    return -1;
	}
	
	/**
	 * Time Complexity: O(Log(N)), because Binary Search, is logarithmic.
	 * Memory Complexity: O(1), no extra storage is being used.
	 */
	static int findHighIndex(List<Integer> arr, int key) {
	    int low = 0;
	    int high = arr.size() - 1;
	    int mid = high / 2;

	    while (low <= high) {
  
	    	int mid_elem = arr.get(mid);
  
	    	if (mid_elem <= key) {
	    		low = mid + 1;
	    	}
	    	else {
	    		high = mid - 1;
	    	}

	    	mid = low + (high - low) / 2;
	    }
    
	    if(high == -1){
	    	return high;
	    }
    
	    if (high < arr.size() && arr.get(high) == key) {
	    	return high;
	    }

	    return -1;
	}
	
	public static void main(String[] args) {
		
		System.out.println("Test 1");
		
		List<Integer> array = Arrays.asList(1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 6, 6, 6, 6, 6, 6);
		int key = 5;
		int low = findLowIndex(array, key);
		int high = findHighIndex(array, key);
		
		System.out.println("Low Index of " + key + ": " + low);
		System.out.println("High Index of " + key + ": " + high);
		/* output:
		Low Index of 5: 15
		High Index of 5: 17
		*/
		
		/**************************************************/
		
		System.out.println("Test 2");
		
		key = -2;
		low = findLowIndex(array, key);
		high = findHighIndex(array, key);
		
		System.out.println("Low Index of " + key + ": " + low);
		System.out.println("High Index of " + key + ": " + high);
		/* output:
		Low Index of -2: -1
		High Index of -2: -1
		 */
	}
}

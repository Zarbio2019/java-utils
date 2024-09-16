/*
 * binary search in sorted array
 * https://www.geeksforgeeks.org/binary-search/
 * 
 * Approach: Iterative
 * Here we use a while loop to continue the process of comparing the key and splitting the search space in two halves.
 */

package org.arrays.exercises;

class BinarySearchInSortedArray {

	/**
	 * Returns index of x if it is present in arr[].
	 * 
	 * Time Complexity: O(log(N))
     * Auxiliary Space: O(1)
	 */
	int binarySearch(int arr[], int x)
	{
		int l = 0, r = arr.length - 1;
		while (l <= r) {
			int m = l + (r - l) / 2;

			// Check if x is present at mid
			if (arr[m] == x)
				return m;

			// If x greater, ignore left half
			if (arr[m] < x)
				l = m + 1;

			// If x is smaller, ignore right half
			else
				r = m - 1;
		}

		// If we reach here, then element was
		// not present
		return -1;
	}

	public static void main(String args[])
	{
		BinarySearchInSortedArray ob = new BinarySearchInSortedArray();
		int arr[] = { 2, 3, 4, 10, 40 };
		int x = 10;
		int result = ob.binarySearch(arr, x);
		if (result == -1)
			System.out.println("Element is not present in array");
		else
			System.out.println("Element is present at index " + result);
		/* output:
		Element is present at index 3
		*/
	}
}

/*
 * binary search in sorted array
 * https://www.geeksforgeeks.org/binary-search/
 * 
 * Approach: Recursive
 * Create a recursive function and compare the mid of the search space with the key. And based on the result either return the index where the key is found or call the recursive function for the next search space.
 */

package org.arrays.exercises;

class BinarySearchInSortedArray2 {

	/**
	 * Returns index of x if it is present in arr[l..r], else return -1
	 * 
     * Time Complexity: 
	 *   Best Case: O(1)
	 *   Average Case: O(log N)
	 *   Worst Case: O(log N)
     * Auxiliary Space: O(1), If the recursive call stack is considered then the auxiliary space will be O(logN).
	 */
	int binarySearch(int arr[], int l, int r, int x)
	{
		if (r >= l) {
			int mid = l + (r - l) / 2;

			// If the element is present at the
			// middle itself
			if (arr[mid] == x)
				return mid;

			// If element is smaller than mid, then
			// it can only be present in left subarray
			if (arr[mid] > x)
				return binarySearch(arr, l, mid - 1, x);

			// Else the element can only be present
			// in right subarray
			return binarySearch(arr, mid + 1, r, x);
		}

		// We reach here when element is not present
		// in array
		return -1;
	}

	// Driver code
	public static void main(String args[])
	{
		BinarySearchInSortedArray2 ob = new BinarySearchInSortedArray2();
		int arr[] = { 2, 3, 4, 10, 40 };
		int n = arr.length;
		int x = 10;
		int result = ob.binarySearch(arr, 0, n - 1, x);
		if (result == -1)
			System.out.println(
				"Element is not present in array");
		else
			System.out.println(
				"Element is present at index " + result);
		/*
		output:
		Element is present at index 3
		*/
	}
}

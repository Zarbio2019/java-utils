/*
 * Search: Program for Nth node from the end of a Linked List
 * https://www.geeksforgeeks.org/nth-node-from-the-end-of-a-linked-list/?ref=lbp
 *
 * Approach: Iterative
 *
 * Steps:
 * 1. Calculate the length of the Linked List. Let the length be len. 
 * 2. Print the (len – n + 1)th node from the beginning of the Linked List.
 */

package org.linkedlist.exercises;

class LinkedList {
	Node head; // head of the list

	/* Linked List node */
	class Node {
		int data;
		Node next;
		Node(int d)
		{
			data = d;
			next = null;
		}
	}

	/* Function to get the Nth node from the last of a
	linked list */
	
	/**
     * Time Complexity: O(N), where N is the size of the linked list
	 * Auxiliary Space: O(1)
	 */
	void printNthFromLast(int N)
	{
		int len = 0;
		Node temp = head;

		// 1) count the number of nodes in Linked List
		while (temp != null) {
			temp = temp.next;
			len++;
		}

		// check if value of N is not more than length of
		// the linked list
		if (len < N)
			return;

		temp = head;

		// 2) get the (len-N+1)th node from the beginning
		for (int i = 1; i < len - N + 1; i++)
			temp = temp.next;

		System.out.println(temp.data);
	}

	/* Inserts a new Node at front of the list. */
	public void push(int new_data)
	{
		/* 1 & 2: Allocate the Node &
				Put in the data*/
		Node new_node = new Node(new_data);

		/* 3. Make next of new Node as head */
		new_node.next = head;

		/* 4. Move the head to point to new Node */
		head = new_node;
	}

	// Driver's code
	public static void main(String[] args)
	{
		LinkedList llist = new LinkedList();
		llist.push(20);
		llist.push(4);
		llist.push(15);
		llist.push(35);
		
		// Function call
		llist.printNthFromLast(4);
		/* output:
		35
		*/
	}
}

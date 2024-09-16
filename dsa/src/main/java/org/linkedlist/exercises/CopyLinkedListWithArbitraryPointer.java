/*
 * Copy Linked List with Arbitrary Pointer (with next and Random Pointer)
 * https://www.geeksforgeeks.org/a-linked-list-with-next-and-arbit-pointer/
 * 
 * Approach: Using Extra Space
 */
package org.linkedlist.exercises;

import java.util.HashMap;

public class CopyLinkedListWithArbitraryPointer {
	
	static class Node {
		int val;
		Node next;
		Node arbit;
		 
		Node(int x)
		{
			this.val = x;
		    this.next = null;
		    this.arbit = null;
		}
	}
    
	/**
	 * Time Complexity: O(N) 
     * Auxiliary Space: O(N) 
	 */
	static Node clone(Node head)
	{
		// Map to store the mapping of
		// old nodes with new ones
		HashMap<Node, Node> mp = new HashMap<>();
		Node temp, nhead;
		 
		// Duplicate of the first node
		temp = head; // old nodes
		nhead = new Node(temp.val); // new node
		mp.put(temp, nhead);
		 
		// Loop to create duplicates of nodes
		// with only next pointer
		while (temp.next != null) {
			nhead.next = new Node(temp.next.val); // new node
			temp = temp.next; // traverse till last node
			nhead = nhead.next;
			mp.put(temp, nhead); // map: old nodes, new nodes
		}
		
		temp = head;
		 
		// Loop to clone the arbit pointers
		while (temp != null) {
			mp.get(temp).arbit = mp.get(temp.arbit); // new node.arbit = .old node.arbit
			temp = temp.next;
		}
		 
		// Return the head of the clone
		return mp.get(head);
	}
    
    static void printList(Node head)
	{				
		System.out.print(head.val + "(" + head.arbit.val + ")");
		head = head.next;
	    
	    while (head != null) {
	    	System.out.print(" -> " + head.val + "(" + head.arbit.val + ")");
	    	head = head.next;
	    }
	    System.out.println();
	}
	
	public static void main(String[] args)
	{
		// Creating a linked list with random pointer		
	    Node head = new Node(1);
	    head.next = new Node(2);
	    head.next.next = new Node(3);
	    head.next.next.next = new Node(4);
	    head.next.next.next.next = new Node(5);
	    head.arbit = head.next.next; // 1->3
	    head.next.arbit = head; // 2->1
	    head.next.next.arbit = head.next.next.next.next; // 3->5
	    head.next.next.next.arbit = head.next.next; // 4->3
	    head.next.next.next.next.arbit = head.next; //5->2
	 
		// Print the original list
		System.out.println("The original linked list:");
		printList(head);
		 
	    Node sol = clone(head);
	    
	    // Print cloned list
		System.out.println("The cloned linked list:");
		printList(sol);
		
		// Add Node to simulate change
		head.next.next.next.next.next = new Node(6);
		head.next.next.next.next.next.arbit = new Node(7);
		
		System.out.println("The original linked list, after change:");
		printList(head);
		
		System.out.println("The cloned linked list, after change:");
		printList(sol);
		/* output:
		The original linked list:
		1(3) -> 2(1) -> 3(5) -> 4(3) -> 5(2)
		The cloned linked list:
		1(3) -> 2(1) -> 3(5) -> 4(3) -> 5(2)
		The original linked list, after change:
		1(3) -> 2(1) -> 3(5) -> 4(3) -> 5(2) -> 6(7)
		The cloned linked list, after change:
		1(3) -> 2(1) -> 3(5) -> 4(3) -> 5(2)
		 */    
	  }
}
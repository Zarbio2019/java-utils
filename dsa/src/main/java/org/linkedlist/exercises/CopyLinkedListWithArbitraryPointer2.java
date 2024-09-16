/*
 * Copy Linked List with Arbitrary Pointer (with next and Random Pointer)
 * https://www.geeksforgeeks.org/clone-linked-list-next-arbit-pointer-set-2/
 *
 * Approach: using Hashing
 */
package org.linkedlist.exercises;

import java.util.HashMap;
import java.util.Map;

public class CopyLinkedListWithArbitraryPointer2 {
	
	Node head; //Linked list head reference
	
	public CopyLinkedListWithArbitraryPointer2() {
		head = null;
	}
	
    public CopyLinkedListWithArbitraryPointer2(Node head) {
        this.head = head;
    }
    
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
    
	// at node at front/head
	public void push(int data) {
		Node new_node = new Node(data);
		new_node.next = this.head; // make next of new Node as head
		this.head = new_node; // move the head to point to new Node
	}
	
	/**
	 * Time Complexity: O(N) 
     * Auxiliary Space: O(N)
	 */
    public CopyLinkedListWithArbitraryPointer2 clone()
    {
        // Initialize two references, one with original list's head.
        Node origCurr = this.head, cloneCurr = null;
  
        // Hash map which contains node to node mapping of
        // original and clone linked list.
        Map<Node, Node> map = new HashMap<Node, Node>();
  
        // Traverse the original list and make a copy of that
        // in the clone linked list.
        while (origCurr != null)
        {
            cloneCurr = new Node(origCurr.val);
            map.put(origCurr, cloneCurr);
            origCurr = origCurr.next;
        }
  
        // Adjusting the original list reference again.
        origCurr = this.head;
  
        // Traversal of original list again to adjust the next
        // and random references of clone list using hash map.
        while (origCurr != null)
        {
            cloneCurr = map.get(origCurr);
            cloneCurr.next = map.get(origCurr.next);
            cloneCurr.arbit = map.get(origCurr.arbit);
            origCurr = origCurr.next;
        }
  
        // return the head reference of the clone list.
        return new CopyLinkedListWithArbitraryPointer2(map.get(this.head));
    }
    	  
    // print Linked List
    void printList()
	{				
    	Node node = head;
    	
		System.out.print(node.val + "(" + node.arbit.val + ")");
		node = node.next;
	    
	    while (node != null) {
	    	System.out.print(" -> " + node.val + "(" + node.arbit.val + ")");
	    	node = node.next;
	    }
	    System.out.println();
	}
    	
	public static void main(String[] args)
	{
		CopyLinkedListWithArbitraryPointer2 llist = new CopyLinkedListWithArbitraryPointer2();
		llist.push(5);
		llist.push(4);
		llist.push(3);
		llist.push(2);
		llist.push(1);
		
		// Setting up arbitrary/random references
		llist.head.arbit = llist.head.next.next; // 1->3
		llist.head.next.arbit = llist.head; // 2->1
		llist.head.next.next.arbit = llist.head.next.next.next.next; // 3->5
		llist.head.next.next.next.arbit = llist.head.next.next; // 4->3
		llist.head.next.next.next.next.arbit = llist.head.next; //5->2

		// Print the original list
		System.out.println("The original linked list:");
		llist.printList();
		 
		// Print cloned list
		System.out.println("The cloned linked list:");
		CopyLinkedListWithArbitraryPointer2 llist2 = llist.clone();
		llist2.printList();
		
		// Add Node to simulate change
		llist.head.next.next.next.next.next = new Node(6);
		llist.head.next.next.next.next.next.arbit = new Node(7);

		System.out.println("The original linked list, after change:");
		llist.printList();
		
		System.out.println("The cloned linked list, after change:");
		llist2.printList();
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
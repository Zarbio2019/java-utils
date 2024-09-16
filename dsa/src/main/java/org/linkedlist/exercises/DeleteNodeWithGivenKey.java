/*
 * Deletes the node with a given key
 * https://www.educative.io/delete-node-with-given-key
 */

package org.linkedlist.exercises;

import org.linkedlist.singleLinkedList.SinglyLinkedList;

public class DeleteNodeWithGivenKey {

	public static void main(String[] args)
	{
		// Approach: Iterative
		SinglyLinkedList sllist = new SinglyLinkedList();
		// 15->14->12->10
		sllist.push(10);
		sllist.push(12);
		sllist.push(14);
		sllist.push(15);
		sllist.printList(); // 15 14 12 10
	
		// delete node with value 20, but it's not present
		sllist.deleteNodeByValueIterative(20);
		sllist.printList(); // 15 14 12 10
	
		// delete node with value 10, it's present
		sllist.deleteNodeByValueIterative(10);
		sllist.printList(); // 15 14 12 
	
		// delete node with value 14, it's present
		sllist.deleteNodeByValueIterative(14);
		sllist.printList(); // 15 12
		/* output:
		15 14 12 10 
		Element not present in the list
		15 14 12 10 
		15 14 12 
		15 12
		*/
		
		/***************************************/
		
		// Approach: Recursive
		SinglyLinkedList sllist2 = new SinglyLinkedList();
		// 15->14->12->10
		sllist2.push(10);
		sllist2.push(12);
		sllist2.push(14);
		sllist2.push(15);
		sllist2.printList();
	
		// delete node with value 20, but it's not present
		sllist2.deleteNodeByValueRecursive(20);
		sllist2.printList();
	
		// delete node with value 10, it's present
		sllist2.deleteNodeByValueRecursive(10);
		sllist2.printList();
	
		// delete node with value 14, it's present
		sllist2.deleteNodeByValueRecursive(14);
		sllist2.printList();
		/* output:
		15 14 12 10 
		Element not present in the list
		15 14 12 10 
		15 14 12 
		15 12
		*/
	}
}

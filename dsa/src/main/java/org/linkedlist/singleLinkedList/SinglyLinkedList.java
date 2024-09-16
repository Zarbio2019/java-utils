/**
 * create Singly Linked List
 * https://www.geeksforgeeks.org/applications-advantages-and-disadvantages-of-linked-list/?ref=lbp
 */

package org.linkedlist.singleLinkedList;

public class SinglyLinkedList {
	
	static Node head; // head of list

	static class Node {
		int data;
		Node next;

		Node(int d) {
			data = d;
			next = null;
		}
	}
	
	public SinglyLinkedList() {
		head = null;
	}

	/**
	 * prints content of double linked list
	 * @param node
	 */
	public void printList()
	{
		Node node = head;
		
		while (node != null) {
			System.out.print(node.data + " ");
			node = node.next;
		}
		System.out.println("");
	}

	/**
	 * returns length (count of nodes) of a linked list.
	 * https://www.geeksforgeeks.org/find-length-of-a-linked-list-iterative-and-recursive/?ref=lbp
     * Approach: Iterative
     * Steps:
     * 1. Initialize count as 0 
     * 2. Initialize a node pointer, current = head.
     * 3. Do following while current is not NULL
     *    current = current -> next
     *    Increment count by 1.
     * 4. Return count 
     *
     * Time Complexity: O(N), where N is the size of the linked list.
     * Auxiliary Space: O(1), as constant extra space is used.
	 */
	public int length()
	{
		Node temp = head;
		int count = 0;
		while (temp != null) {
			count++;
			temp = temp.next;
		}
		return count;
	}
	
	/**
	 * returns length (count of nodes) of a linked list.
     * Approach: Recursive
     * Steps:
     * 1. If the head is NULL, return 0.
     * 2. Otherwise, return 1 + getCount(head->next)
     * 
     * Time Complexity: O(N), traversing the linked list only once.
     * Auxiliary Space: O(N), extra space is used in the recursion call stack.
	 */
	public int lengthRec(Node node)
	{
		// Base case
		if (node == null)
			return 0;

		// Count is this node plus rest of the list
		return 1 + lengthRec(node.next);
	}

	// Wrapper over lengthRec()
	public int lengthRecursive() { return lengthRec(head); }
	
	/**
	 * returns length (count of nodes) of a linked list.
     * Approach: Recursive
     * is the tail-recursive method: optimizes the recursion by using the current count as an accumulator, avoiding the need for additional stack frames for each recursive call.
     * This helps reduce the risk of stack overflow for long linked lists, to handle recursive operations that require keeping track of a changing state.
     * 
     * Time Complexity: O(N), traversing the linked list only once.
     * Auxiliary Space: O(N), in worst case the depth of recursion call stack will be N.
	 */
	public int lengthRec(Node node, int count)
	{
		// Base case
		if (node == null)
			return count;

		// Move to the next node and increase count
		return lengthRec(node.next, 1 + count);
	}

	// Wrapper over lengthRec()
	public int lengthTailRecursive() { return lengthRec(head, 0); }
	
	/**
	 * Search element, return true if exists, else returns false.
	 * https://www.geeksforgeeks.org/search-an-element-in-a-linked-list-iterative-and-recursive/?ref=lbp
	 * 
	 * Approach: Iterative
	 * 
	 * Steps:
	 * 1. Initialize a node pointer, current = head.
	 * 2. Do following while current is not NULL
	 * - If the current value (i.e., current->key) is equal to the key being searched return true.
	 * - Otherwise, move to the next node (current = current->next).
	 * 3. If the key is not found, return false 
	 *
	 *
	 * Time Complexity: O(N), where N is the number of nodes.
     * Auxiliary Space: O(1)
     *
	 * @param head
	 * @param x value to search
	 * @return
	 */
	public boolean search(Node head, int x)
	{
		Node current = head; // Initialize current
		
		while (current != null) {
			if (current.data == x)
				return true; // data found
			current = current.next;
		}
		return false; // data not found
	}
	
	/**
	 * Search element, return true if exists, else returns false.
	 * https://www.geeksforgeeks.org/search-an-element-in-a-linked-list-iterative-and-recursive/?ref=lbp
	 * 
	 * Approach: Recursive
     *
	 * - Steps:
	 * 1. If the head is NULL, return false.
	 * 2. If the head's key is the same as X, return true;
	 * 3. Else recursively search in the next node. 
	 * 
	 * Time Complexity: O(N), where N is the number of nodes.
     * Auxiliary Space: O(N), stack space used by recursive calls.
	 */
	public boolean searchRecursive(Node head, int x)
	{
		// Base case
		if (head == null)
			return false;

		// If key is present in current node, return true
		if (head.data == x)
			return true;

		// Recur for remaining list
		return search(head.next, x);
	}
	
	/**
     * Search element by index.
     * https://www.geeksforgeeks.org/write-a-function-to-get-nth-node-in-a-linked-list/?ref=lbp
     * 
     * Approach: Iterative
     *
     * Steps:
     * 1. Initialize count = 0
     * 2. Loop through the link list
     * 	a. if the count is equal to the passed index then return the current node
     * 	b. Increment count
     * 	c. change current to point to next of the current.
     *
     * Time Complexity: O(N), where N is the number of nodes.
     * Auxiliary Space: O(1), space created for variables.
	 * 
	 * @param index to search
	 * @return
	 */
	public int searchByIndex(int index)
	{
		Node current = head;
		int count = 0; /* index of Node we are
						currently looking at */
		while (current != null)
		{
			if (count == index)
				return current.data;
			count++;
			current = current.next;
		}

		/* if we get to this line, the caller was asking
		for a non-existent element so we assert fail */
		assert (false);
		return 0;
	}
	
	/**
     * Search element by index.
     * 
     * Approach: Recursive (Tail-recursion)
     *
     * Steps:
     * 1. Initialize count = 0
     * 2. if count==n
     *      return node->data
     * 3. else
     *      return searchByIndexRec(node->next,n-1)
     *
     * Time Complexity: O(N)
	 * Auxiliary Space: O(N), due to recursive calls (worst-case).
	 * 
	 * @param index to search
	 * @return
	 */
	public int searchByIndexRec(Node head, int n)
	{
		int count = 0;
		
		if (head == null) // edge case - if head is null
			return -1;
		
		// if count equal to n, return node.data
		if (count == n)
			return head.data;

		// recursively decrease n and increase
		// head to next pointer
		return searchByIndexRec(head.next, n - 1);
	}
	
	public int searchByIndexRecursive(int n)
	{
		return searchByIndexRec(head, n);
	}

	/**
	 * Inserts a new Node at front of the list.
	 * https://www.geeksforgeeks.org/insert-a-node-at-front-beginning-of-a-linked-list/
	 * 
	 * Time Complexity: O(1), with the pointer to the head, we can directly attach a node and change the pointer, does a constant amount of work.
     * Auxiliary Space: O(1)
	 * @param data
	 */
	public void push(int data) {
		Node new_node = new Node(data);
		new_node.next = head; // Make next of new Node as head
		head = new_node; // Move the head to point to new Node
	}
	
	/** 
	 * Insert a Node after a given Node in Linked List
     * https://www.geeksforgeeks.org/insert-a-node-after-a-given-node-in-linked-list/
     *
     * Steps:
     * 1. Check if the given node exists or not. 
     * If it do not exists, 
     *   terminate the process.
     * If the given node exists,
     *   Make the element to be inserted as a new node
     *   Change the next pointer of given node to the new node
     *   Now shift the original next pointer of given node to the next pointer of new node
     *
     * e.g.:
	 * LinkedList: node1->node2->node3
	 * pushAfter(node1, data)
	 * previous node: node1
	 * node to insert: node2
	 * result: node1->node2->node
	 *
     * Time Complexity: O(1), since prev_node is already given as argument in a method, no need to iterate over list to find prev_node.
     * Auxiliary Space: O(1), since using constant space to modify pointers.
	 *
	 * @param prev_node given node
	 * @param new_data
	 */
	void pushAfter(Node prev_node, int new_data)
	{
		// 1. check if the given prev_node is NULL
		if (prev_node == null) {
			System.out.println("The given previous node cannot be NULL");
			return;
		}

		// 2. allocate new node
		Node new_node = new Node(new_data);

		// 3. Make next of new node as next of prev_node
		new_node.next = prev_node.next;

		// 4. move the next of prev_node as new_node
		prev_node.next = new_node;
	}
	
	/**
	 * Insert (append) a Node at the end of the Linked List
	 * https://www.geeksforgeeks.org/insert-node-at-the-end-of-a-linked-list/
	 * 
	 * Steps:
	 * Go to the last node of the Linked List.
	 * Change the next pointer of last node from NULL to the new node.
	 * Make the next pointer of new node as NULL to show the end of Linked List.
	 * 
	 * Time Complexity: O(N), where N is the number of nodes, does traverse from head to end.
	 *   This method can also be optimized to work in O(1) by keeping an extra pointer to the tail of the linked list.
     * Auxiliary Space: O(1)
	 * 
	 * @param new_data
	 */
	void append(int new_data)
	{
		Node new_node = new Node(new_data);

		// If the Linked List is empty, then make the new node as head
		if (head == null) {
			head = new_node;
			return;
		}
		
		// This new node is going to be the last node, so make next of it as null
		new_node.next = null;
	
		// Else traverse till the last node
		Node last = head;
		while (last.next != null) {
			last = last.next;
		}

		// Change the next of last node
		last.next = new_node;
	}
	
	/**
	 * Deletes the node with a given key, first occurrence
	 * https://www.educative.io/delete-node-with-given-key
	 * 
	 * Approach: Iterative
	 * 
	 * Time Complexity: O(N)
     * Auxiliary Space: O(1)
	 * 
	 * @param val given key
	 * @return
	 */
	public Node deleteNodeByValueIterative(int val) {
		return deleteNodeByValueIterative(head, val);
	}
	
	public Node deleteNodeByValueIterative(Node head, int val) {
	    Node prev = null; // previous node
	    Node current = head; // actual node

	    while (current != null) {
	    	
	    	if (current.data == val) {  
	    		
	    		if(current == head) { // found at front
	    			head = head.next;
	    			current = head;
	    		}
	    		else { // found the value
	    			prev.next = current.next; // delete node, move reference 
	    			current = current.next; // assign next node
	    		}
	    	}
	    	else {
	    		prev = current;
	    		current = current.next; // go next node
	    	}
	    }

	    return head;
	}
	
	/**
	 * Deletes the node with a given key, first occurrence
	 * https://www.geeksforgeeks.org/deletion-in-linked-list/
	 * 
	 * Approach: Recursive
	 * 
	 * Time Complexity: O(N)
     * Auxiliary Space: O(N), due to recursion call stack
	 * 
	 * @param head
	 * @param val given key
	 * @return
	 */
	public int deleteNodeByValueRecursive(int val) {
		return deleteNodeByValueRecursive(head, val);
	}
	
	public int deleteNodeByValueRecursive(Node head, int val) {
		// Check if list is empty or we
		// reach at the end of the list.
		if (head == null) {
			System.out.println("Element not present in the list");
			return -1;
		}
		
		// If current node is the node to be deleted
		if (head.data == val) {
			// If it's start of the node head
			// node points to second node
			if (head.next != null) {
				head.data = head.next.data;
				head.next = head.next.next;
				return 1;
			} else
				return 0;
		}
		
		if (deleteNodeByValueRecursive(head.next, val) == 0) {
			head.next = null;
			return 1;
		}
		
		return -1;
	}
		
	/**
	 * Delete a Node at a given position in Linked List
     * https://www.geeksforgeeks.org/delete-a-linked-list-node-at-a-given-position/
     *
     * Steps:
     * 1. Input: A pointer to the head node of the linked list and the value to be deleted.
     * 2. If the linked list is empty, return NULL.
     * 3. If the node to be deleted is the head node, set the head node to the next node and delete the original head node.
     * 4. Otherwise, traverse the linked list from the head node until the node to be deleted is found.
     * 5. If the node to be deleted is not found, return NULL.
     * 6. Otherwise, set the previous node's next pointer to the node after the node to be deleted.
     * 7. Delete the node to be deleted.
     * 8. Return the head node of the linked list.
     *
	 * Time Complexity:
	 *   Best Case: O(1) if given position is 1. 
	 *   Average & Worst Case: O(N), where N is the length, because the traverse to find the node to be deleted.
     *
     * Auxiliary Space: O(1), as we are only using a constant amount of extra space for temporary variables and pointers,
     * regardless of the size of the linked list. Specifically, we only need to allocate memory for a few temporary pointers (e.g., current, temp)
     * and the new head pointer if the original head is deleted. The memory for the deleted node(s) is also freed as soon as it is deleted.
     *
	 * @param position
	 */
	void deleteNode(int position)
	{
		// If linked list is empty
		if (head == null)
			return;

		// Store head node
		Node temp = head;

		// If head needs to be removed
		if (position == 0) {
			head = temp.next; // Change head
			return;
		}

		// Find previous node of the node to be deleted
		for (int i = 0; temp != null && i < position - 1; i++)
			temp = temp.next;

		// If position is more than number of nodes
		if (temp == null || temp.next == null)
			return;

		// Node temp->next is the node to be deleted
		// Store pointer to the next of node to be deleted
		Node next = temp.next.next;

		temp.next = next; // Unlink the deleted node from list
	}
	
	/**
	 * Delete a Node at the end of Linked List
     * https://www.geeksforgeeks.org/delete-nth-node-from-the-end-of-the-given-linked-list/
     * 
     * Time Complexity: O(N), where N is the number of nodes.
	 * Auxiliary Space: O(1)
	 * @param key
	 */
	void deleteNodeAtEnd(int position)
	{
		// First pointer will point to
		// the head of the linked list
		Node first = head;

		// Second pointer will point to the
		// Nth node from the beginning
		Node second = head;
		for (int i = 0; i < position; i++) {

			// If count of nodes in the given
			// linked list is <= N
			if (second.next == null) {

				// If count = N i.e. delete the head node
				if (i == position - 1)
					head = head.next;
				return;
			}
			second = second.next;
		}

		// Increment both the pointers by one until
		// second pointer reaches the end
		while (second.next != null) {
			first = first.next;
			second = second.next;
		}

		// First must be pointing to the
		// Nth node from the end by now
		// So, delete the node first is pointing to
		first.next = first.next.next;
	}

	/**
	 * Deletes the entire linked list
	 * https://www.geeksforgeeks.org/write-a-function-to-delete-a-linked-list/?ref=lbp
	 * 
     * Steps:
     * head = null
	 * 
	 * Time Complexity: O(1)
	 * Auxiliary Space: O(1)
	 */
	void deleteList() { head = null; }
	
	/**
	 * Reverse a Linked List
	 * https://www.geeksforgeeks.org/reverse-a-linked-list/?ref=lbp
     *
	 * Approach: Iterative
     *
	 * Steps:
	 * 1. Initialize three pointers prev as NULL, curr as head, and next as NULL.
	 * 2. Iterate through the linked list. In a loop, do the following:
	 * Before changing the next of curr, store the next node 
	 *    next = curr -> next
	 * Now update the next pointer of curr to the prev 
	 *    curr -> next = prev 
	 * Update prev as curr and curr as next 
	 *    prev = curr 
	 *    curr = next
	 *
	 * e.g.:
	 * node = (head) 1 -> 2 -> 3 -> NULL (tail)
     * reverse = (tail) NULL <- 1 <- 2 <- 3 (head)
	 *
     * NULL			1 ->		2 ->		3 -> 		NULL
     * (prev,next)	(curr)
	 *
	 * NULL <- 		1  			2			3			NULL
	 * 		    	(prev)		(next,curr)
	 *
	 * NULL <- 		1 <- 		2			3			NULL
	 *							(prev)		(next,curr)
	 *
	 * NULL <- 		1 <- 		2 <-		3			NULL
	 *				 						(prev)		(next, curr)
	 *
	 * return (prev)
	 *
	 * Time Complexity: O(N), traversing over the linked list of size N.
     * Auxiliary Space: O(1)
	 */
	Node reverse(Node node)
	{
		Node prev = null;
		Node current = node;
		Node next = null;
		
		while (current != null) {
			next = current.next;
			current.next = prev;
			prev = current;
			current = next;
		}
		node = prev;
		return node;
	}
		
	/**
	 * Drive code
	 * @param args
	 */
	public static void main(String[] args)
	{
		// length()
		SinglyLinkedList sllist = new SinglyLinkedList(); // Start with the empty list
		sllist.push(1);
		sllist.push(3);
		sllist.push(1);
		sllist.push(2);
		sllist.push(1);

		// Function call
		System.out.println("Count of nodes is " + sllist.length());
		/* output:
		count of nodes is 5
		*/
		
		/***************************************/
		
		// lengthRecursive()
		System.out.println("Count of nodes is " + sllist.lengthRecursive());
		/* output:
		count of nodes is 5
		*/
		
		/***************************************/
		
		// lengthTailRecursive()
		System.out.println("Count of nodes is " + sllist.lengthTailRecursive());
		/* output:
		count of nodes is 5
		*/
		
		/***************************************/
				
		// search()
		SinglyLinkedList sllist2 = new SinglyLinkedList(); // Start with the empty list
		int x = 21; // value to search

		// 14->21->11->30->10
		sllist2.push(10);
		sllist2.push(30);
		sllist2.push(11);
		sllist2.push(21);
		sllist2.push(14);

		if (sllist2.search(sllist2.head, x))
			System.out.println("Yes");
		else
			System.out.println("No");
		/* output:
		Yes
		*/
		
		/***************************************/

		// searchRecursive()
		if (sllist2.searchRecursive(sllist2.head, x))
			System.out.println("Yes");
		else
			System.out.println("No");
		/* output:
		Yes
		*/

		/***************************************/
		
		// searchByIndex()
		SinglyLinkedList sllist3 = new SinglyLinkedList(); // Start with the empty list

		// 1->12->1->4->1
		sllist3.push(1);
		sllist3.push(4);
		sllist3.push(1);
		sllist3.push(12);
		sllist3.push(1);

		System.out.println("Element at index 3 is " + sllist3.searchByIndex(3));
		/* output:
		Element at index 3 is 4
		*/
		
		/***************************************/
		
		// searchByIndexRecursive()
		System.out.println("Element at index 3 is " + sllist3.searchByIndexRecursive(3));
		/* output:
		Element at index 3 is 4
		*/		
		
		/***************************************/
		
		// reverse()
		SinglyLinkedList sllist4 = new SinglyLinkedList(); // Start with the empty list
		// 85->15->4->20
		sllist4.head = new Node(85);
		sllist4.head.next = new Node(15);
		sllist4.head.next.next = new Node(4);
		sllist4.head.next.next.next = new Node(20);
		/* or:
		sllist4.push(20);
		sllist4.push(4);
		sllist4.push(15);
		sllist4.push(85);
		*/
		
		System.out.println("Given linked list");
		sllist4.printList();
		
		head = sllist4.reverse(head);
		
		System.out.println("");
		
		System.out.println("Reversed linked list ");
		sllist4.printList();
		/* output:
		Given linked list
		85 15 4 20 
		Reversed linked list 
		20 4 15 85 
		 */
		
		/***************************************/

		// pushAfter()
		SinglyLinkedList sllist5 = new SinglyLinkedList();
		sllist5.push(6);
		sllist5.push(5);
		sllist5.push(4);
		sllist5.push(3);
		sllist5.push(2);

		System.out.println("Created Linked list is: ");
		sllist5.printList();

		sllist5.pushAfter(sllist5.head, 1);

		System.out.println("After inserting 1 after 2: ");
		sllist5.printList();
		/* output:
		Created Linked list is:  2 3 4 5 6
		After inserting 1 after 2:  2 1 3 4 5 6
		*/
		
		/***************************************/
		
		// append()
		SinglyLinkedList sllist6 = new SinglyLinkedList();
		sllist6.push(6);
		sllist6.push(5);
		sllist6.push(4);
		sllist6.push(3);
		sllist6.push(2);

		System.out.print("Created Linked list is: ");
		sllist6.printList();

		// Insert 1 at the end
		sllist6.append(1);

		System.out.println("After inserting 1 at the end: ");
		sllist6.printList();
		/* output:
		Created Linked list is:  2 3 4 5 6
		After inserting 1 at the end:  2 3 4 5 6 1
		*/
		
		/***************************************/
		
		// deleteList()
		SinglyLinkedList sllist7 = new SinglyLinkedList();
		sllist7.push(10);
		sllist7.push(12);
		sllist7.push(14);
		sllist7.push(15);
		
		System.out.println("Deleting the list");
		sllist7.deleteList();

		System.out.println("Linked list deleted");
		/* output:
		Deleting linked list
		Linked list deleted
		*/
		
		/***************************************/
		
		// deleteNode()
		SinglyLinkedList sllist9 = new SinglyLinkedList();
		sllist9.push(7);
		sllist9.push(1);
		sllist9.push(3);
		sllist9.push(2);
		sllist9.push(8);

		System.out.println("\nCreated Linked list is: ");
		sllist9.printList();

		sllist9.deleteNode(4); // Delete node at position 4

		System.out.println("\nLinked List after Deletion at position 4: ");
		sllist9.printList();
		/* output:
		Created Linked List: 8 2 3 1 7 
		Linked List after Deletion at position 4: 8 2 3 1 
		*/
		
		/***************************************/
		
		// deleteNodeAtEnd()
		SinglyLinkedList sllist10 = new SinglyLinkedList();
		sllist10.push(7);
		sllist10.push(1);
		sllist10.push(3);
		sllist10.push(2);

		System.out.println("\nCreated Linked list is:");
		sllist10.printList();

		int N = 1;
		sllist10.deleteNodeAtEnd(N);

		System.out.println("\nLinked List after Deletion is:");
		sllist10.printList();
		/* output:
		Created Linked list is:
		2 3 1 7 
		Linked List after Deletion is:
		2 3 1
		*/	
	}
}
/* Notes:

// class linked list having Generic data-type <T>
public class SinglyLinkedList<T> {
	
    public Node head; // head node of the linked list
    public int size;      // size of the list
    
    // Node inner class for SLL
    public class Node<T>{
        public T data; // Data to store (could be int, string, Object etc)
        public Node next; // Pointer to next node in list
    }

    // constructor
    public SinglyLinkedList() {
    	head = null;
        size = 0;
    }
}
*/

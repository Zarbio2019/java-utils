/*
 * create Doubly Linked List
 * https://www.geeksforgeeks.org/applications-advantages-and-disadvantages-of-linked-list/?ref=lbp
 */

package org.linkedlist.doublyLinkedList;

class Node {
	public int data;
	public Node next;
	public Node prev;
	
	public Node(int d) {
		data = d;
		next = null;
		prev = null;
	}
}

class DoublyLinkedList {
	public Node head;
	
	public DoublyLinkedList() {
		head = null;
	}
	
	public void addNode(int data) {
		Node newNode = new Node(data);
		newNode.next = head;
		if (head != null) {
			head.prev = newNode;
		}
		head = newNode;
	}
}

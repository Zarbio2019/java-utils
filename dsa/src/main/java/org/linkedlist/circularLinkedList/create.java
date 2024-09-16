/*
 * create Doubly Linked List
 * https://www.geeksforgeeks.org/applications-advantages-and-disadvantages-of-linked-list/?ref=lbp
 */

package org.linkedlist.circularLinkedList;

class Node {
	public int data;
	public Node next;

	public Node(int d) {
		data = d;
		next = null;
	}
}

class CircularLinkedList {
	public Node head;

	public CircularLinkedList() {
		head = null;
	}

	public void add_node(int data) {
		Node new_node = new Node(data);
		if (head == null) {
			head = new_node;
			new_node.next = head;
			return;
		}
		Node current = head;
		while (current.next != head) {
			current = current.next;
		}
		current.next = new_node;
		new_node.next = head;
	}
}

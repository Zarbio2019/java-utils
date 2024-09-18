/*
 * Implement LRU Cache
 */
package org.lruCache;

import java.util.HashMap;
import java.util.Map;

// Node: represents each cache entry with a key-value pair and pointers to the previous and next nodes.
class Node {
	int key;
	int value;
	Node prev;
	Node next;
	
	Node(int key, int value) {
		this.key = key;
		this.value = value;
	}
}

// DoublyLinkedList: manages the ordering of nodes.
// add, remove, move nodes to the front, and pop the tail node.
class DoublyLinkedList {
	private Node head;
	private Node tail;
	
	DoublyLinkedList() {
		head = new Node(0, 0);
		tail = new Node(0, 0);
		head.next = tail;
		tail.prev = head;
	}
	
	// add to the head/front
	public void addNode(Node node) {
		node.next = head.next;
		node.prev = head;
		head.next.prev = node;
		head.next = node;
	}
	
	// remove to the head/front
	public void removeNode(Node node) {
		Node prev = node.prev;
		Node next = node.next;
		prev.next = next;
		next.prev = prev;
	}
	
	// move nodes to the head/front
	public void moveToFront(Node node) {
		removeNode(node);
		addNode(node);
	}
	
	// pop node from tail
	public Node popTail() {
		Node res = tail.prev;
		removeNode(res);
		return res;
	}
}


// LRUCache: manages the cache operations and maintains the size constraints.
// HashMap to store nodes for O(1) access.
public class LRUCache {
	
	private HashMap<Integer, Node> cache;
	private DoublyLinkedList dll;
	private int capacity;
	private int size;
	
	public LRUCache(int capacity) {
		this.capacity = capacity;
		this.size = 0;
		cache = new HashMap<>();
		dll = new DoublyLinkedList();
	}
	
	// retrieves value associated with a key and moves the node to the front to mark it as recently used.
	// Time complexity: O(1)
	public int get(int key) {
		Node node = cache.get(key);
		if(node == null) {
			return -1;
		}
		
		dll.moveToFront(node); // head/front is the most recently used
		return node.value;
	}
	
	// adds a new key-value pair to the cache.
	// If the key already exists, it updates the value and moves the node to the front.
	// If adding a new node exceeds the capacity, it removes the least recently used node from both the doubly linked list and the hash map.
	// Time complexity: O(1)
	public void put(int key, int value) {
		Node node = cache.get(key);
		
		if(node == null) { // if value not exist
			Node newNode = new Node(key, value); // create new Node
			cache.put(null, newNode);
			dll.addNode(newNode); // add to the head/front
			size++;
			
			if(size > capacity) {
				Node tail = dll.popTail();
				cache.remove(tail.key);
				size--;
			}
		} else {
			node.value = value;
			dll.moveToFront(node);
		}
	}
	
    public void printCache() {
        System.out.println("Current cache contents:");
        for (Map.Entry<Integer, Node> entry : cache.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue().value);
        }
    }
    
	public static void main(String[] args){
	
		LRUCache cache = new LRUCache(2);
		cache.put(1, 1);
		cache.put(2, 2);
 		/*  
			       MRU	    LRU		   
 		  front - (1,1) -> (2,2) - head
		*/
		cache.get(1); // returns 1
		cache.put(3, 3); // evicts key 2
		cache.get(2); // returns -1 (not found)
		cache.put(4, 4); // evicts key 1
		cache.get(1); // returns -1 (not found)
		cache.get(3); // returns 3
		cache.get(4); // returns 4
		
		cache.printCache();
	  }
}

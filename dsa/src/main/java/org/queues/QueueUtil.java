// https://www.geeksforgeeks.org/queue-interface-java/?ref=lbp

package org.queues;

import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.LinkedList;

public class QueueUtil {
	
	public static void main(String[] args) {
	
		System.out.println("QueueUtil");
		
		/***************************************/

		/* Queue */
		
		// (back,rear,enqueue) <-> (front,dequeue)
		
		/* methods */
		// Queue interface
		// package java.util
		// extends Collection
		/*
		add(element): add to the rear (back). If the queue is full, it throws an exception.
			O(log(N)) time
			In PriorityQueue, elements are stored based on the priority order which is ascending by default. 
			
		offer(element): add to the rear (back). If the queue is full, it returns false.

		remove(): remove and returns element at the front (head). If the queue is empty, it throws and exception.

		poll(): remove and returns element at the front (head). If the queue is empty, it returns null.
			O(log(N)) time
			
		element(): returns element at the front (head). If the queue is empty, it throws and exception.

		peek(): returns element at the front (head). If the queue is empty, it returns null.
		*/

		Queue<Integer> queue = new ArrayDeque<>();
		System.out.println(queue.offer(10)); // true
		System.out.println(queue.offer(4)); // true
		// (back,rear,enqueue) 4 10 (front,dequeue)
		System.out.println(queue.peek()); // 10 (front)
		System.out.println(queue.poll()); // 10
		System.out.println(queue.poll()); // 4
		System.out.println(queue.peek()); // null

		/***************************************/

		/* ArrayDeque (Double-ended queue) */
		
		// (back,rear,enqueue) <-> (front,dequeue)

		/* methods */
		// ArrayDeque interface
		// package java.util
		// extends AbstractCollection<E>
        // implements Deque<E>, Cloneable, Serializable
		/*
		add(element): add at the end (front). If the queue is full, it throws an exception
		
		offer(element): add to the end (front). If the queue is full, it returns false.
		
		push(element): add at the front.
		
		remove(): remove and returns element at the head (back, last element). If the queue is empty, it throws and exception.

		poll(): remove and returns element at the head (back, last element). If the queue is empty, it returns null.

		pop(): remove and returns element at the back (last element). If the queue is empty, it throws and exception.
		
		element(): returns element at the head (back, last element). If the queue is empty, it throws and exception.

		peek(): returns element at the head (back, last element). If the queue is empty, it returns null.
		 */
			
		// used as Stack
		ArrayDeque<Integer> stack = new ArrayDeque<>();
		stack.push(10);
		stack.push(4);
		stack.offer(5);
		stack.add(3);
		// (back,rear,enqueue) 4 10 5 3 (front,dequeue)
		System.out.println(stack.peek()); // 4 (last element)
		System.out.println(stack.poll()); // 4 (last element)
		System.out.println(stack.poll()); // 10 (last element)
		System.out.println(stack.peek()); // 5
		System.out.println(stack.poll()); // 3 (last element)
		System.out.println(stack.peek()); // null
		
		// others methods:
		// Collection Methods
		/*
		size
		isEmpty
		iterator
		forEach
		remove
		removeAll
		contains
		clear
		toArray
		clone
		 */
		
		/***************************************/

		/* iterate */
		Queue<String> pq = new PriorityQueue<>();
		pq.add("Geeks");
		pq.add("For");
		pq.add("Geeks");

		Iterator iterator = pq.iterator();

		while (iterator.hasNext()) {
			System.out.print(iterator.next() + " ");
		}
		/* output:
		For Geeks Geeks
		*/
		
		/***************************************/
		
		/* Queue with LinkedList */
		// import java.util.LinkedList;
		// import java.util.Queue;
		
		Queue<String> queue2 = new LinkedList<>();

		// add elements to the queue
		queue2.add("apple");
		queue2.add("banana");
		queue2.add("cherry");
		// (first element) [apple, banana, cherry] (last element)
		
		// print the queue
		System.out.println("Queue: " + queue2); // [apple, banana, cherry]

		// remove the element at the front of the queue
		String front = queue2.remove();
		System.out.println("Removed element: " + front); // apple

		// print the updated queue
		System.out.println("Queue after removal: " + queue2); // [banana, cherry]

		// add another element to the queue
		queue2.add("date");
		System.out.println("Queue after add: " + queue2); // [banana, cherry, date]

		// peek at the element at the front of the queue
		String peeked = queue2.peek();
		System.out.println("Peeked element: " + peeked);  // banana

		// print the updated queue
		System.out.println("Queue after peek: " + queue2); // [banana, cherry, date]
		/* output:
		Queue: [apple, banana, cherry]
		Removed element: apple
		Queue after removal: [banana, cherry]
		Peeked element: banana
		Queue after peek: [banana, cherry, date]
		*/
		
		/***************************************/
		
		/* Queue with LinkedList */
		Queue<Integer> queue3 = new LinkedList<Integer>();

		// Adding items to the ll
		// using add()
		queue3.add(10);
		queue3.add(20);
		queue3.add(15);
		// (first element) [10 20 15] (last element)
		
		// print the queue
		System.out.println("queue3: " + queue3); // [10 20 15]
		
		// Printing the top element of
		// the LinkedList
		System.out.println(queue3.peek()); // 10

		// Printing the top element and removing it
		// from the LinkedList container
		System.out.println(queue3.poll()); // 10

		// Printing the top element again
		System.out.println(queue3.peek()); // 20
		/* output: 
		10
		10
		20
		*/
		
		/***************************************/

		/* Queue with PriorityQueue */
		// import java.util.*;

		Queue<Integer> pQueue = new PriorityQueue<Integer>();

		// Adding items to the pQueue
		// using add()
		pQueue.add(10);
		pQueue.add(20);
		pQueue.add(15);
		// (low priority) 10 20 15 (high priority)
		
		// print the queue
		System.out.println("pQueue: " + pQueue); // [10 20 15]
		
		// Printing the top element of
		// the PriorityQueue
		System.out.println(pQueue.peek()); // 10

		// Printing the top element and removing it
		// from the PriorityQueue container
		System.out.println(pQueue.poll()); // 10

		// Printing the top element again
		System.out.println(pQueue.peek()); // 15
		/* output: 
		10
		10
		15
		*/
		
		/***************************************/

		/* Queue with PriorityBlockingQueue */
		
		// import java.util.concurrent.PriorityBlockingQueue;
		// import java.util.*;
		
		// Creating empty priority
		// blocking queue
		Queue<Integer> pbq = new PriorityBlockingQueue<Integer>();

		// Adding items to the pbq
		// using add()
		pbq.add(10);
		pbq.add(20);
		pbq.add(15);
		// (low priority) 10 20 15 (high priority)
		
		// print the queue
		System.out.println("pbq: " + pbq); // [10 20 15]
		
		// Printing the top element of
		// the PriorityBlockingQueue
		System.out.println(pbq.peek()); // 10

		// Printing the top element and
		// removing it from the
		// PriorityBlockingQueue
		System.out.println(pbq.poll()); // 10

		// Printing the top element again
		System.out.println(pbq.peek()); // 15
		/* output: 
		10
		10
		15
		*/
	}
}

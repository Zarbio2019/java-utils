/* 
 * linked-list implementation of queue
 * https://www.geeksforgeeks.org/queue-linked-list-implementation/?ref=lbp
 */

// A linked list (LL) node to store a queue entry
package org.queues.singleQueue;

// A class to represent a queue
// The queue, front stores the front node of LL and rear
// stores the last node of LL
class SingleQueueWithLinkedList {
	
	class QNode {
		int key;
		QNode next;

		// constructor to create a new linked list node
		public QNode(int key)
		{
			this.key = key;
			this.next = null;
		}
	}
	
	QNode front, rear;

	public SingleQueueWithLinkedList() { this.front = this.rear = null; }

	// Method to add an key to the queue.
	void enqueue(int key)
	{
		// Create a new LL node
		QNode temp = new QNode(key);

		// If queue is empty, then new node is front and
		// rear both
		if (this.rear == null) {
			this.front = this.rear = temp;
			return;
		}

		// Add the new node at the end of queue and change
		// rear
		this.rear.next = temp;
		this.rear = temp;
	}

	// Method to remove an key from queue.
	void dequeue()
	{
		// If queue is empty, return NULL.
		if (this.front == null)
			return;

		// Store previous front and move front one node
		// ahead
		QNode temp = this.front;
		this.front = this.front.next;

		// If front becomes NULL, then change rear also as NULL
		if (this.front == null)
			this.rear = null;
	}
	
	// Driver code
	public static void main(String[] args)
	{
		SingleQueueWithLinkedList q = new SingleQueueWithLinkedList();
		q.enqueue(10);
		q.enqueue(20);
		q.dequeue();
		q.dequeue();
		q.enqueue(30);
		q.enqueue(40);
		q.enqueue(50);
		q.dequeue();
		System.out.println("Queue Front : " + ((q.front != null) ? (q.front).key : -1));
		System.out.println("Queue Rear : " + ((q.rear != null) ? (q.rear).key : -1));
	}
	/* output:

	Queue Front : 40
	Queue Rear : 50

	Time Complexity:
		Enqueue(insertion) and Deque(deletion): O(1), it only changes pointers.

	Space Complexity:
		Enqueue(insertion) and Deque(deletion): O(1) as constant extra space is required.
	*/
}

/* 
 * Implementation of queue
 * https://www.geeksforgeeks.org/introduction-and-array-implementation-of-queue/?ref=lbp
 */

package org.queues.singleQueue;

public class SingleQueueWithArray {
	
	int front, rear, size;
	int capacity;
	int array[];

	public SingleQueueWithArray(int capacity)
	{
		this.capacity = capacity;
		front = this.size = 0;
		rear = capacity - 1;
		array = new int[this.capacity];
	}

	// Queue is full when size becomes
	// equal to the capacity
	boolean isFull(SingleQueueWithArray queue)
	{
		return (queue.size == queue.capacity);
	}

	// Queue is empty when size is 0
	boolean isEmpty(SingleQueueWithArray queue)
	{
		return (queue.size == 0);
	}

	// Method to add an item to the queue.
	// It changes rear and size
	void enqueue(int item)
	{
		if (isFull(this))
			return;
		this.rear = (this.rear + 1)
					% this.capacity;
		this.array[this.rear] = item;
		this.size = this.size + 1;
		System.out.println(item	+ " enqueued to queue");
	}

	// Method to remove an item from queue.
	// It changes front and size
	int dequeue()
	{
		if (isEmpty(this))
			return Integer.MIN_VALUE;

		int item = this.array[this.front];
		this.front = (this.front + 1)
					% this.capacity;
		this.size = this.size - 1;
		return item;
	}

	// Method to get front of queue
	int front()
	{
		if (isEmpty(this))
			return Integer.MIN_VALUE;

		return this.array[this.front];
	}

	// Method to get rear of queue
	int rear()
	{
		if (isEmpty(this))
			return Integer.MIN_VALUE;

		return this.array[this.rear];
	}
	
	// Driver class
	public static void main(String[] args)
	{
		SingleQueueWithArray queue = new SingleQueueWithArray(1000);

		queue.enqueue(10);
		queue.enqueue(20);
		queue.enqueue(30);
		queue.enqueue(40);
		// (rear) -> 40 30 20 10 (front)
		
		System.out.println(queue.dequeue() + " dequeued from queue\n"); // 10 (front)

		System.out.println("Front item is " + queue.front()); // 20 (front)

		System.out.println("Rear item is " + queue.rear()); // 40 (rear)
		/* output:

		10 enqueued to queue
		20 enqueued to queue
		30 enqueued to queue
		40 enqueued to queue
		10 dequeued from queue
		Front item is 20
		Rear item is 40

		Time Complexity:
			Enqueue(insertion): O(1)
			Deque(deletion front): O(1)
			Front(get front): O(1)
			Rear(get rear): O(1)
			IsFull(check queue is full or not): O(1)
			IsEmpty(check queue is empty or not): O(1)

		Space Complexity: O(N), where N is the size of the array for storing elements.
		*/
	}
}

// https://www.geeksforgeeks.org/stack-class-in-java/?ref=lbp

package org.stacks;

import java.util.Stack;

public class StackUtil {

	// Pushing element on the top of the stack
	static void stack_push(Stack<Integer> stack)
	{
		System.out.println("Push Operation:");

		for(int i = 0; i < 5; i++)
		{
			stack.push(i);
		}
	}
	
	// Popping element from the top of the stack
	static void stack_pop(Stack<Integer> stack)
	{
		System.out.println("Pop Operation:");

		for(int i = 0; i < 5; i++)
		{
			Integer y = (Integer) stack.pop();
			System.out.println(y);
		}
	}

	// Displaying element on the top of the stack
	static void stack_peek(Stack<Integer> stack)
	{
		Integer element = (Integer) stack.peek();
		System.out.println("Element on stack top: " + element);
	}
	
	// Searching element in the stack
	static void stack_search(Stack<Integer> stack, int element)
	{
		Integer pos = (Integer) stack.search(element);

		if(pos == -1)
			System.out.println("Element not found");
		else
			System.out.println("Element is found at position: " + pos);
	}
	
	public static void main(String[] args) {
	
		System.out.println("StackUtil");
		
		/* methods */
		// package java.util
		// push, pop, peek, search in-built functions.
		
		Stack<String> stack = new Stack<String>();
		stack.push("4"); // add
		stack.push("Geeks"); // [4, Geeks]
		stack.peek(); // Geeks, get top item
		stack.pop(); // Geeks, delete top item and return it
		stack.isEmpty(); // true, false
		stack.search("4"); // 1 (position)
		
		/***************************************/
		
		/* Samples */
		Stack<Integer> stack2 = new Stack<Integer>();
		
		stack_push(stack2); // stack2 = [0, 1, 2, 3, 4]
		stack_pop(stack2);
		stack_push(stack2); // stack2 = [0, 1, 2, 3, 4]
		stack_peek(stack2);
		stack_search(stack2, 2); // Element is found at position: 3
		stack_search(stack2, 6);
		/* output:
		Pop Operation:
		4
		3
		2
		1
		0
		Element on stack top: 4
		Element is found at position: 3
		Element not found
		*/
	}
}

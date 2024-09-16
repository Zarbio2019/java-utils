/*
 * Parenthesis Checker
 * https://www.geeksforgeeks.org/check-for-balanced-parentheses-in-an-expression/
 * https://practice.geeksforgeeks.org/problems/parenthesis-checker2744/1?page=1&company[]=Google&category[]=Strings&sortBy=submissions
 */

package org.strings.exercises;

import java.util.ArrayDeque;
import java.util.Deque;

public class ParenthesisChecker {

    /**
     * Check if brackets are balanced
     * 
     * Approach: without Stack
     * 
     * Time Complexity: O(N), traverse the string of size N.
     * Auxiliary Space: O(1).
     */
    static boolean areBracketsBalancedWithoutStack(String s)
    {
        int i = -1;
        char[] stack = new char[s.length()];
        
        for (char ch : s.toCharArray()) {
            if (ch == '(' || ch == '{' || ch == '[')
                stack[++i] = ch;
            else {
                if (i >= 0
                    && ((stack[i] == '(' && ch == ')')
                        || (stack[i] == '{' && ch == '}')
                        || (stack[i] == '[' && ch == ']')))
                    i--;
                else
                    return false;
            }
        }
        
        return i == -1;
    }
    
    /**
     * function to check if brackets are balanced
     * 
     * Approach: Stack
     *  1. Put all the opening brackets in the stack.
     *  2. If hit a closing bracket, compare it with the pop of the stack with same nature (e.g.:{})
     *  3. If stack is empty means are balanced, else are not balanced.
     * 
     * Time Complexity: O(N), traverse the string of size N.
     * Auxiliary Space: O(N), for the stack.
     */
    static boolean areBracketsBalanced(String expr)
    {
        // Using ArrayDeque is faster than using Stack class
        Deque<Character> stack = new ArrayDeque<Character>();

        // Traversing the Expression
        for (int i = 0; i < expr.length(); i++) {
        	char x = expr.charAt(i);

            if (x == '(' || x == '[' || x == '{') {
                // Push the element in the stack
                stack.push(x);
                continue;
            }

            // If current character is not opening bracket,
            // then it must be closing,
            // so stack cannot be empty at this point.
            if (stack.isEmpty())
                return false;
            
            char check = stack.pop();
            
            switch (x) {
	            case ')':
	                if (check == '{' || check == '[')
	                    return false;
	                break;
	
	            case '}':
	                if (check == '(' || check == '[')
	                    return false;
	                break;
	
	            case ']':
	                if (check == '(' || check == '{')
	                    return false;
	                break;
            }
        }

        // Check Empty Stack
        return (stack.isEmpty());
    }

	// Driver code
    public static void main(String args[])
    {
        String expr1 = "([{}])";

        // Function call
        if (areBracketsBalancedWithoutStack(expr1))
            System.out.println("Balanced ");
        else
            System.out.println("Not Balanced ");
        /* output:
		Balanced
        */
        
        /***************************************/
        
        String expr2 = "([{}])";
        // [(]) : not balanced, 1 an 4 brackets are not balanced because there is a closing ']'
        // Function call
        if (areBracketsBalanced(expr2))
            System.out.println("Balanced ");
        else
            System.out.println("Not Balanced ");
        /* output:
		Balanced
        */
    }
}

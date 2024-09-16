/*
 * Is the Number Valid
 * https://www.educative.io/is-the-number-valid
 */

package org.algorithms.stateMachine;

public class IsNumberValid {
	  
	enum STATE { START, INTEGER, DECIMAL, UNKNOWN, AFTER_DECIMAL};
	 
    /**
     * Time Complexity: O(N)
     * Auxiliary Space: O(1)
     */
	static STATE get_next_state(STATE current_state, char ch) {
		
		switch(current_state) {
			case START:
			case INTEGER:
		        if (ch == '.') {
		        	return STATE.DECIMAL;
		        } else if (ch >= '0' && ch <= '9') {
		        	return STATE.INTEGER;
		        } else {
		        	return STATE.UNKNOWN;
		        }
			case DECIMAL:
		        if (ch >= '0' && ch <= '9') {
		        	return STATE.AFTER_DECIMAL;
		        } else {
		        	return STATE.UNKNOWN;
		        }
			case AFTER_DECIMAL: 
				if (ch >= '0' && ch <= '9') {
		       		return STATE.AFTER_DECIMAL;
				} else {
		    	   return STATE.UNKNOWN;
				}
		}
	    
		return STATE.UNKNOWN;
	}

	static boolean is_number_valid(String s) {
		if (s.isEmpty()) {
			return true;
		}
	    
		int i = 0;
	  
		// We are not looking at the sign (+ or -) at the start
		// of a number in the state machine
		if (s.charAt(i) == '+' || s.charAt(i) == '-') {
			++i;
		}
	  
	    STATE current_state = STATE.START;

	    while (i < s.length()) {
	    	current_state = get_next_state(current_state, s.charAt(i));
	    
	    	if (current_state == STATE.UNKNOWN) {
	    		return false;
	    	}

	    	i = i + 1;
	    }
	    
	    if (current_state == STATE.DECIMAL)
	    	return false;
	    
	    return true;
	}
	
	static void test(String s) {
		boolean is_valid = is_number_valid(s);
	  
	    if (is_valid) {
	    	System.out.println(s + " is valid.");
	    } else {
	    	System.out.println(s + " is not valid.");
	    }
	}
	
	public static void main(String[] args) {
		test("4.325");
	    test("4.325a");
	    test("x4.325");
	    test("4.32.5");
	    test("4325");
	    test("1.");
	    test("1.1.");
	    test("1.1.1");
	    test("1.1.1.");
	    test("+1.1.");
	    test("+1.1");
	    test("-1.1.");
	    test("-1.1");
	    test("");
	    /* output:
	    4.325 is valid.
		4.325a is not valid.
		x4.325 is not valid.
		4.32.5 is not valid.
		4325 is valid.
		1. is not valid.
		1.1. is not valid.
		1.1.1 is not valid.
		1.1.1. is not valid.
		+1.1. is not valid.
		+1.1 is valid.
		-1.1. is not valid.
		-1.1 is valid.
		 is valid.
	     */
	}
}

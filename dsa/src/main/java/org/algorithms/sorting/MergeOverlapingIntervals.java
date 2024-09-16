/*
 * Merge Overlapping Intervals
 * https://www.educative.io/merge-overlapping-intervals
 */

package org.algorithms.sorting;

import java.util.ArrayList;

public class MergeOverlapingIntervals {
	
	static class Pair {
	    public int first;
	    public int second;
	    
	    public Pair(int x, int y){
	    	this.first = x;
	    	this.second = y; 
	    }
	}
	
	/**
	 * Time Complexity: O(N), because is linear
	 * Memory Complexity: O(N), because is linear
	 */
	static ArrayList<Pair> mergeIntervals(ArrayList<Pair> v) {
	  
		if(v == null || v.size() == 0) {
			return null;
	    }

	    ArrayList<Pair> result = new ArrayList<Pair>();

	    result.add(new Pair(v.get(0).first, v.get(0).second));

	    for(int i = 1 ; i < v.size(); i++) {
	    	int x1 = v.get(i).first; // of next interval
	    	int y1 = v.get(i).second; // of next interval
	    	int x2 = result.get(result.size() - 1).first;
	    	int y2 = result.get(result.size() - 1).second;

	    	if(y2 >= x1) {
	    		result.get(result.size() - 1).second = Math.max(y1, y2);
	    	} else {
	    		result.add(new Pair(x1, y1)); // add new interval
	    	}
		}

	    return result;
	}
	
	public static void main(String[] args) {
		
		ArrayList<Pair> v = new ArrayList<Pair>();

	    v.add(new Pair(1, 5));
	    v.add(new Pair(3, 7));
	    v.add(new Pair(4, 6));
	    v.add(new Pair(6, 8));
	    v.add(new Pair(10, 12));
	    v.add(new Pair(11, 15));

	    ArrayList<Pair> result = mergeIntervals(v);

	    for(int i=0; i<result.size(); i++){
	    	System.out.print(String.format("[%d, %d] ", result.get(i).first, result.get(i).second));
	    }
		/* output:
		[1, 8] [10, 15] 
		 */
	}
}
/*
 notes:
   0     1     2     3      4       5
(1,5) (3,7) (4,6) (6,8) (10,12) (11,15)

1-----5 : i=0
   3----7 : i=1, result = (1,7)
    4--6 : i=2
	   6--8 : i=3, result = (1,8)
	        10---12: i=4
			   11----15 : i=5
			   
Intervals: (1,8) (10,15)

result = (1,5)

Iterations:
		x1	y1	x2	y2	result
i = 1	3	7	1	5	(1,7)
i = 2	4	6	1	7	(1,7)
i = 3	6	8	1	7	(1,8)
i = 4  10  12   1 	8	(10,12)
i = 5  11  15  10  12   (10,15)
*/

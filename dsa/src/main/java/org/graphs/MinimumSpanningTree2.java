/*
 * Minimum Spanning Tree
 * https://www.geeksforgeeks.org/prims-algorithm-using-priority_queue-stl/
 * 
 * Approach: Prim's Algorithm using Adjacency List with Priority Queue
 */
package org.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

//iPair ==>  Integer Pair
class iPair {
	 int first, second;
	 
	 public iPair(int first, int second)
	 {
	     this.first = first;
	     this.second = second;
	 }
}

//This class represents a directed graph using
//adjacency list representation
class Graph2 {
	 int V; // No. of vertices
	
	 // In a weighted graph, we need to store vertex
	 // and weight pair for every edge
	 List<List<iPair> > adj;
	
	 public Graph2(int V)
	 {
	     this.V = V;
	     adj = new ArrayList<>();
	     for (int i = 0; i < V; i++) {
	         adj.add(new ArrayList<>());
	     }
	 }
	
	 // function to add an edge to graph
	 void addEdge(int u, int v, int w)
	 {
	     adj.get(u).add(new iPair(v, w));
	     adj.get(v).add(new iPair(u, w));
	 }
	
	/**
	 * Find the MST of a graph using Prim's Algorithm
	 * 
	 * Approach: using Prim's Algorithm with Adjacency List and Priority Queue
	 * 
	 * Time Complexity: O(E Log V), E = # edges, V = # vertices
	 * Auxiliary Space: O(V), to store vertices V and edges E using Priority Queue
	 */
	 void primMST()
	 {
	     // Create a priority queue to store vertices that
	     // are being primMST. This is weird syntax in Java.
	     // Refer below link for details of this syntax
	     // https://www.geeksforgeeks.org/implement-min-heap-using-stl/
	     PriorityQueue<iPair> pq = new PriorityQueue<>(
	         V, new Comparator<iPair>() {
	             public int compare(iPair a, iPair b)
	             {
	                 return a.second - b.second;
	             }
	         });
	
	     int src = 0; // Taking vertex 0 as source
	
	     // Create a vector for keys and initialize all
	     // keys as infinite (INF)
	     int INF = Integer.MAX_VALUE;
	     int[] key = new int[V];
	     Arrays.fill(key, INF);
	
	     // To store parent array which in turn store MST
	     int[] parent = new int[V];
	     Arrays.fill(parent, -1);
	
	     // To keep track of vertices included in MST
	     boolean[] inMST = new boolean[V];
	
	     // Insert source itself in priority queue and
	     // initialize its key as 0.
	     pq.offer(new iPair(0, src));
	     key[src] = 0;
	
	     /* Looping till priority queue becomes empty */
	     while (!pq.isEmpty()) {
	         // The first vertex in pair is the minimum key
	         // vertex, extract it from priority queue.
	         // vertex label is stored in second of pair (it
	         // has to be done this way to keep the vertices
	         // sorted key (key must be first item
	         // in pair)
	         int u = pq.peek().second;
	         pq.poll();
	
	         // Different key values for same vertex may
	         // exist in the priority queue. The one with the
	         // least key value is always processed first.
	         // Therefore, ignore the rest.
	         if (inMST[u]) {
	             continue;
	         }
	
	         inMST[u] = true; // Include vertex in MST
	
	         // 'i' is used to get all adjacent vertices of a
	         // vertex
	         for (iPair i : adj.get(u)) {
	             // Get vertex label and weight of current
	             // adjacent of u.
	             int v = i.first;
	             int weight = i.second;
	
	             //  If v is not in MST and weight of (u,v)
	             //  is smaller
	             // than current key of v
	             if (!inMST[v] && key[v] > weight) {
	                 // Updating key of v
	                 key[v] = weight;
	                 pq.offer(new iPair(key[v], v));
	                 parent[v] = u;
	             }
	         }
	     }
	
	     // Print edges of MST using parent array
	     for (int i = 1; i < V; i++) {
	         System.out.println(parent[i] + " - " + i);
	     }
	 }
}

public class MinimumSpanningTree2 {

	public static void main(String[] args) {
		
		/* Test 1 */
		System.out.println("Test 1 - Graph 1");
		
        // create the graph given in above figure
        int V = 9;
 
        Graph2 graph = new Graph2(V);
 
        //  making above shown graph
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 7, 8);
        graph.addEdge(1, 2, 8);
        graph.addEdge(1, 7, 11);
        graph.addEdge(2, 3, 7);
        graph.addEdge(2, 8, 2);
        graph.addEdge(2, 5, 4);
        graph.addEdge(3, 4, 9);
        graph.addEdge(3, 5, 14);
        graph.addEdge(4, 5, 10);
        graph.addEdge(5, 6, 2);
        graph.addEdge(6, 7, 1);
        graph.addEdge(6, 8, 6);
        graph.addEdge(7, 8, 7);
 
        graph.primMST();
        /* output:
		0 - 1
		1 - 2
		2 - 3
		3 - 4
		2 - 5
		5 - 6
		6 - 7
		2 - 8
         */
        
        /***********************************************/
        
		/* Test 2 */
        /*
		System.out.println("Test 2 - Graph 2");
		
        // create the graph given in above figure
        V = 7;
 
        Graph2 graph2 = new Graph2(V);
 
        //  making above shown graph
        graph2.addEdge(1, 4, 2);
        graph2.addEdge(1, 3, 1);
        graph2.addEdge(1, 2, 2);
        graph2.addEdge(3, 4, 1);
        graph2.addEdge(2, 4, 3);
        graph2.addEdge(3, 5, 2);
        graph2.addEdge(4, 7, 2);
        graph2.addEdge(5, 6, 1);
        graph2.addEdge(5, 7, 2);
	    
        graph2.primMST();
        /* output:
		0 - 1
		1 - 2
		2 - 3
		3 - 4
		2 - 5
		5 - 6
		6 - 7
		2 - 8
         */	    
    }
}

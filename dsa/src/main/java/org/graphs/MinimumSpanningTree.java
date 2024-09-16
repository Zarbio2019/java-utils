/*
 * Minimum Spanning Tree
 * https://www.educative.io/minimum-spanning-tree
 * https://www.geeksforgeeks.org/prims-mst-for-adjacency-list-representation-greedy-algo-6/
 * 
 * Approach: Prim's Algorithm using Adjacency List
 */
package org.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinimumSpanningTree {
	
	public static void test_graph1() {
		System.out.println("Graph 1:");
		
		/* Initialization */
		graph g = new graph(new ArrayList<vertex>(), new ArrayList<edge>());
		 
	    int v = 5; // # vertices

	    // each edge contains: weight, src, dest
	    ArrayList<Integer> e1 = new ArrayList<Integer>(Arrays.asList(1, 1, 2));
	    ArrayList<Integer> e2 = new ArrayList<Integer>(Arrays.asList(1, 1, 3));
	    ArrayList<Integer> e3 = new ArrayList<Integer>(Arrays.asList(2, 2, 3));
	    ArrayList<Integer> e4 = new ArrayList<Integer>(Arrays.asList(3, 2, 4));
	    ArrayList<Integer> e5 = new ArrayList<Integer>(Arrays.asList(3, 3, 5));
	    ArrayList<Integer> e6 = new ArrayList<Integer>(Arrays.asList(2, 4, 5));

	    List<ArrayList<Integer>> e = new ArrayList<ArrayList<Integer>>();
	    e.add(e1);
	    e.add(e2);
	    e.add(e3);
	    e.add(e4);
	    e.add(e5);
	    e.add(e6);

	    g.generate_graph(v, e);
	    g.print_graph();
	    
	    /* Minimum Spanning Tree */
	    System.out.println("Minimum Spanning Tree - Graph 1:");
	    int w = g.find_min_spanning_tree();
	    g.print_mst(w);
	}

	public static void test_graph2() {
		System.out.println("Graph 2:");

		/* Initialization */
	    graph g = new graph(new ArrayList<vertex>(), new ArrayList<edge>());
	    
	    int v = 7;  // # vertices

	    // each edge contains: weight, src, dest
	    ArrayList<Integer> e1 = new ArrayList<Integer>(Arrays.asList(2, 1, 4));
	    ArrayList<Integer> e2 = new ArrayList<Integer>(Arrays.asList(1, 1, 3));
	    ArrayList<Integer> e3 = new ArrayList<Integer>(Arrays.asList(2, 1, 2));
	    ArrayList<Integer> e4 = new ArrayList<Integer>(Arrays.asList(1, 3, 4));
	    ArrayList<Integer> e5 = new ArrayList<Integer>(Arrays.asList(3, 2, 4));
	    ArrayList<Integer> e6 = new ArrayList<Integer>(Arrays.asList(2, 3, 5));
	    ArrayList<Integer> e7 = new ArrayList<Integer>(Arrays.asList(2, 4, 7));
	    ArrayList<Integer> e8 = new ArrayList<Integer>(Arrays.asList(1, 5, 6));
	    ArrayList<Integer> e9 = new ArrayList<Integer>(Arrays.asList(2, 5, 7));

	    List<ArrayList<Integer>> e = new ArrayList<ArrayList<Integer>>();
	    e.add(e1);
	    e.add(e2);
	    e.add(e3);
	    e.add(e4);
	    e.add(e5);
	    e.add(e6);
	    e.add(e7);
	    e.add(e8);
	    e.add(e9);

	    g.generate_graph(v, e);
	    g.print_graph();
	    
	    /* Minimum Spanning Tree */
	    System.out.println("Minimum Spanning Tree - Graph 2:");
	    int w = g.find_min_spanning_tree();
	    g.print_mst(w);
	}
	
	public static void main(String[] args) {
		
		test_graph1();
		
	    test_graph2();

	    System.out.println("Completed!");
	    /* output:
		Graph 1:
		1 false
		2 false
		3 false
		4 false
		5 false
		
		1->2[1, false]  
		1->3[1, false]  
		2->3[2, false]  
		2->4[3, false]  
		3->5[3, false]  
		4->5[2, false]  
		
		
		Minimum Spanning Tree - Graph 1:
		1->2
		1->3
		2->4
		4->5
		weight: 7
		
		Graph 2:
		1 false
		2 false
		3 false
		4 false
		5 false
		6 false
		7 false
		
		1->4[2, false]  
		1->3[1, false]  
		1->2[2, false]  
		3->4[1, false]  
		2->4[3, false]  
		3->5[2, false]  
		4->7[2, false]  
		5->6[1, false]  
		5->7[2, false]  
		
		
		Minimum Spanning Tree - Graph 2:
		1->3
		1->2
		3->4
		3->5
		4->7
		5->6
		weight: 9
		
		Completed!
		*/
	}
}

// vertex/node
class vertex {
	private int id;
	private boolean visited;

	public vertex(int id, boolean visited) {
		super();
	    this.id = id;
	    this.visited = visited;
	}

	int getId() {
		return id;
	}

	void setId(int id) {
		this.id = id;
	}

	boolean isVisited() {
		return visited;
	}

	void setVisited(boolean visited) {
		this.visited = visited;
	}
}

// edge/ weight
class edge {
	private int weight;
	private boolean visited;
	private vertex src; // vertice source/origin
	private vertex dest;  // vertice destiny

	public edge(int weight, boolean visited, vertex src, vertex dest) {
		this.weight = weight;
		this.visited = visited;
		this.src = src;
		this.dest = dest;
	}

	int getWeight() {
		return weight;
	}

	void setWeight(int weight) {
		this.weight = weight;
	}

	boolean isVisited() {
		return visited;
	}

	void setVisited(boolean visited) {
		this.visited = visited;
	}

	vertex getSrc() {
		return src;
	}

	void setSrc(vertex src) {
		this.src = src;
	}

	vertex getDest() {
		return dest;
	}

	void setDest(vertex dest) {
		this.dest = dest;
	}
}

// graph: contains vertices and edges
class graph {
	private List<vertex> g;   // vertices
	private List<edge> e;     // edges

	public graph(List<vertex> g, List<edge> e) {
		super();
		this.g = g;
		this.e = e;
	}

	// getters and setters
	public List<vertex> getG() {
		return g;
	}

	public void setG(List<vertex> g) {
		this.g = g;
	}

	public List<edge> getE() {
		return e;
	}

	public void setE(List<edge> e) {
		this.e = e;
	}

	// returns the vertex with a given id if it
	// already exists in the graph, returns NULL otherwise
	vertex vertex_exists(int id) {
		for (int i = 0; i < g.size(); i++) {
			if (g.get(i).getId() == id) {
				return g.get(i);
			}
		}
		return null;
	}

	// This method generates the graph with v vertices
	// and e edges
	void generate_graph(int vertices, List<ArrayList<Integer>> edges) {
		// create vertices, in this case vertices are 1, 2, 3, 4, 5
		for (int i = 0; i < vertices; i++) {
			vertex v = new vertex(i + 1, false);
			g.add(v);
		}

	    // create edges
	    for (int i = 0; i < edges.size(); i++) {
	    	vertex src = vertex_exists(edges.get(i).get(1)); // get vertice source
	    	vertex dest = vertex_exists(edges.get(i).get(2)); // get vertice destiny
	    	edge e = new edge(edges.get(i).get(0), false, src, dest);
	    	getE().add(e);
	    }
	}

	/**
	 * Find the MST of a graph using Prim's Algorithm
	 * returns the weight of the MST
	 * 
	 * Approach: using Prim's Algorithm using Adjacency List
	 * 
	 * Time Complexity: O(V^2), V = # vertices
	 * Auxiliary Space: O(V+E), to store vertices V and edges E
	 */
	int find_min_spanning_tree() {
		int vertex_count = 0;
		int weight = 0;

	    // Add first vertex to the MST (arbirary vertex)
	    vertex current = g.get(0);
	    current.setVisited(true);
	    vertex_count++;

	    // Construct the remaining MST using the
	    // smallest weight edge
	    while (vertex_count < g.size()) {
	    	edge smallest = null;
	    	
	    	for (int i = 0; i < e.size(); i++) {
	    		
	    		if (e.get(i).isVisited() == false) {
	    			
	    			if (e.get(i).getSrc().isVisited() == true
	    				&& e.get(i).getDest().isVisited() == false) {
	    				
	    				if (smallest == null) {
	    					smallest = e.get(i);
	    				} else if (e.get(i).getWeight() < smallest.getWeight()) {
	    					smallest = e.get(i);
	    				}
	    			}
	    		}
	    	}

	    	smallest.setVisited(true);
	    	smallest.getDest().setVisited(true);
	    	weight += smallest.getWeight();
	    	vertex_count++;
	    }
	    
	    return weight;
	}

	// print a graph 
	void print_graph() {
		
		// format: vertice id |  vertice is visited
		for (int i = 0; i < g.size(); i++) {
			System.out.println(g.get(i).getId() + " " + g.get(i).isVisited());
		}
    
		System.out.println();
		
		// format: vertice source | vertice destiny | weight | edge is visited
	    for (int i = 0; i < e.size(); i++) {
	    	System.out.println(e.get(i).getSrc().getId() + "->"
	          + e.get(i).getDest().getId() + "["
	          + e.get(i).getWeight() + ", "
	          + e.get(i).isVisited() + "]  ");
	    }
	    System.out.println("\n");
	}

	// print the Minimum Spanning Tree in format: vertice source | vertice destiny
	void print_mst(int w) {
		
		for (int i = 0; i < e.size(); i++) {
		
			if (e.get(i).isVisited() == true) {
				System.out.println(e.get(i).getSrc().getId() + "->"
				+ e.get(i).getDest().getId());
			}
		}
		
		System.out.println("weight: " + w);
		System.out.println();
	}
}

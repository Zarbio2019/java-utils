/*
 * Mirror Binary Tree Nodes
 * https://www.geeksforgeeks.org/write-an-efficient-c-function-to-convert-a-tree-into-its-mirror-tree/
 */

package org.trees.exercises;

import java.util.LinkedList;
import java.util.Queue;

public class MirrorBinaryTreeNodes2 {
	
	Node root;
	
	static class Node {
	    int data;
	    Node left, right;
	 
	    public Node(int d)
	    {
	        data = d;
	        left = right = null;
	    }
	}
	
	/**
	 * Convert a Binary Tree into its mirror (invert binary tree)
	 * 
	 * Approach: using post-order traversal (Left-Right-Root pattern)
	 * 
	 * Time Complexity: O(N), Traversing over the tree of size N
	 * Auxiliary Space: O(N), Using queue to store the nodes of the tree
	 */
    void mirror() { root = mirror(root); }
    
    /* Change a tree so that the roles of the left and
    right pointers are swapped at every node.
	So the tree...
  	     4
	    / \
	   2   5
	  /     \
	 1       3
	
	is changed to...
	     4
	    / \
	   5   2
	  /     \
	 3       1
	*/
    Node mirror(Node node)
    {
        if (node == null)
            return node;
 
        Queue<Node> q = new LinkedList<>();
        q.add(root);
 
        // Do BFS. While doing BFS, keep swapping
        // left and right children
        while (q.size() > 0) {
            // pop top node from queue
            Node curr = q.peek();
            q.remove();
 
            // swap left child with right child
            Node temp = curr.left;
            curr.left = curr.right;
            curr.right = temp;
 
            // push left and right children
            if (curr.left != null)
                q.add(curr.left);
            if (curr.right != null)
                q.add(curr.right);
        }
        
        return node;
    }
    
    /**
     * Print tree (binary search tree) in inorder traversal (Left-Root-Right pattern)
     */
    void printInOrder() { printInOrder(root); }

    // Helper function
    void printInOrder(Node node)
    {
        if (node == null)
            return;
 
        // Left-Root-Right pattern
        printInOrder(node.left); // left
        
        System.out.print(node.data + " "); // root
 
        printInOrder(node.right); // right
    }
    
    public static void main(String args[])
    {
        // creating a binary tree and entering the nodes
    	MirrorBinaryTreeNodes2 tree = new MirrorBinaryTreeNodes2();
        tree.root = new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(5);
        /*
					1
			   	   / \
				  2   3
				/   \
			   4     5
		*/
        
        // print inorder traversal of the input tree 
        System.out.println("Inorder traversal of the constructed tree is");
        tree.printInOrder();
        System.out.println("");
 
        /* convert tree to its mirror */
        tree.mirror();
 
        // print inorder traversal of the minor tree
        System.out.println("Inorder traversal of the mirror tree is");
        tree.printInOrder();
        /* output:
        Inorder traversal of the constructed tree is
		4 2 5 1 3 
		
		Inorder traversal of the mirror tree is 
		3 1 5 2 4
         */
    }
}

/*
 * Mirror Binary Tree Nodes
 * https://www.geeksforgeeks.org/write-an-efficient-c-function-to-convert-a-tree-into-its-mirror-tree/
 * 
 * Approach: Recursive, post-order traversal
 */

package org.trees.exercises;

public class MirrorBinaryTreeNodes {
	
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
	 * Time Complexity: O(N), Visiting all the nodes of the tree of size N
	 * Auxiliary Space: O(N), For function call stack space
	 */
    void mirror() { root = mirror(root); }
    
    Node mirror(Node node)
    {
        if (node == null)
            return node;
 
        /* do the subtrees.
        post-order traversal of the binary tree
        */
        Node left = mirror(node.left); // left
        Node right = mirror(node.right); // right
 
        // swap the left and right pointers
        node.left = right;
        node.right = left;
        
        return node; // root
    }
    
    /**
     * Print tree (binary search tree) in inorder traversal (Left-Root-Right pattern)
     */
    void printInOrder() { printInOrder(root); }

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
    	MirrorBinaryTreeNodes tree = new MirrorBinaryTreeNodes();
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
        tree.printInOrder(); // 4 2 5 1 3
        System.out.println("");
 
        /* convert tree to its mirror */
        tree.mirror();
 
        // print inorder traversal of the minor tree
        System.out.println("Inorder traversal of the mirror tree is");
        tree.printInOrder(); // 3 1 5 2 4
        /* output:
        Inorder traversal of the constructed tree is
		4 2 5 1 3 
		
		Inorder traversal of the mirror tree is 
		3 1 5 2 4
         */
    }
}

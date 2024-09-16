/*
 * Check if Two Binary Trees are Identical
 * https://www.educative.io/check-if-two-binary-trees-are-identical
 */

package org.trees.exercises;

public class CheckTwoBinaryTreesIdentical {
	
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
	
	public static boolean areIdentical(CheckTwoBinaryTreesIdentical tree1, CheckTwoBinaryTreesIdentical tree2) {
		return areIdentical(tree1.root, tree2.root);
	}

	public static boolean areIdentical(Node root1, Node root2) {

	    if (root1 == null && root2 == null) {
	      return true;
	    }

	    if (root1 != null && root2 != null) {
	      return ((root1.data == root2.data) &&
	              areIdentical(root1.left, root2.left) &&
	              areIdentical(root1.right, root2.right));
	    }

	    return false;
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
    	CheckTwoBinaryTreesIdentical tree1 = new CheckTwoBinaryTreesIdentical();
    	tree1.root = new Node(1);
    	tree1.root.left = new Node(2);
    	tree1.root.right = new Node(3);
    	tree1.root.left.left = new Node(4);
    	tree1.root.left.right = new Node(5);
        /*
					1
			       / \
				  2   3
				/   \
			   4     5
		*/
    	
    	CheckTwoBinaryTreesIdentical tree2 = new CheckTwoBinaryTreesIdentical();
    	tree2.root = new Node(1);
    	tree2.root.left = new Node(2);
    	tree2.root.right = new Node(3);
    	tree2.root.left.left = new Node(4);
    	tree2.root.left.right = new Node(5);
        /*
					1
			       / \
				  2   3
				/   \
			   4     5
		*/
    	
        // print tree
        System.out.println("Print tree1:");
        tree1.printInOrder();
        System.out.println("");
 
        // print tree
        System.out.println("Print tree2:");
        tree2.printInOrder();
        
        /* convert tree to its mirror */
        System.out.println("");
        
        if (areIdentical(tree1, tree2)) {
            System.out.println("The trees are identical");
        	} else {
            System.out.println("The trees are not identical");
        }
        /* output:
		Print tree1:
		4 2 5 1 3 
		
		Print tree2:
		4 2 5 1 3 
		The trees are identical
         */
    }
}

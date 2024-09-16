/*
 * Cloning/Copy Array
 * https://www.geeksforgeeks.org/arrays-in-java/
 */

package org.arrays.exercises;

import java.util.Arrays;

class CloningArray {
	
    public static void main(String args[])
    {
    	/* cloning of one-dimensional arrays */
    	
        /**
         * Deep Cloning a One-Dimensional Array of Primitive Types - Method 1
         * 
         * Time Complexity: O(N), n=size of the array, because clone() uses iteration,
         * time complexity is linear with respect to the size of the array.
  		 * 
  		 * Auxiliary Space: O(N), n=size of the array, because clone() creates a new array object
  		 * in memory and copies the elements from the original array into the new array.
  		 * The auxiliary space complexity is linear with respect to the size of the array.
         */  
        System.out.println("Deep Copy 1");

        int intArray[] = { 1, 2, 3 };
        
        int cloneArray[] = intArray.clone();
 
        // will print false as deep copy is created
        // for one-dimensional array
        System.out.println(intArray == cloneArray);
 
        for (int i = 0; i < cloneArray.length; i++) {
            System.out.print(cloneArray[i] + " ");
        }
        /* output:
        false
        1 2 3
        */
        
        /**
         * Deep Cloning a One-Dimensional Array of Primitive Types - Method 2
         */
        System.out.println("Deep Copy 2");
        
        int[] intArray3 = {1, 2, 3};
        int[] clonedArray3 = Arrays.copyOf(intArray3, intArray3.length);

        System.out.println(intArray3 == clonedArray3);
        
        /**
         * Deep Cloning a One-Dimensional Array of Objects - Method 3
         * 
         * Clone each object individually. The objects implement the Cloneable interface and override the clone method.
         */
        System.out.println("Deep Copy 3");
        
        class MyClass implements Cloneable {
            int value;

            public MyClass(int value) {
                this.value = value;
            }

            @Override
            protected Object clone() throws CloneNotSupportedException {
                return super.clone();
            }
        }

        MyClass[] objArray1 = { new MyClass(1), new MyClass(2) };
        MyClass[] clonedObjArray1 = new MyClass[objArray1.length];

        for (int i = 0; i < objArray1.length; i++) {
            try {
            	clonedObjArray1[i] = (MyClass) objArray1[i].clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
        
        System.out.println(objArray1 == clonedObjArray1);
        
        /***************************************/
        
        /* cloning of multi-dimensional arrays */
        
        /**
 		 * shallow copy
         */
        System.out.println("Shallow Copy 1");
        
        int intArray2[][] = { { 1, 2, 3 }, { 4, 5 } };
        
        int cloneArray2[][] = intArray2.clone();
 
        // will print false
        System.out.println(intArray2 == cloneArray2);
 
        // will print true as shallow copy is created
        // i.e. sub-arrays are shared
        System.out.println(intArray2[0] == cloneArray2[0]);
        System.out.println(intArray2[1] == cloneArray2[1]);
        /*
        output:
        false
        true
        true
        */
        
        /**
         * Deep Cloning a Multi-Dimensional Array of Primitive Types - Method 1
         * 
         * clone each sub-array recursively.
         */
        System.out.println("Deep Copy 2"); 
        
        int[][] intArray5 = {{1, 2}, {3, 4}};
        int[][] cloneArray5 = new int[intArray5.length][];

        for (int i = 0; i < intArray5.length; i++) {
        	cloneArray5[i] = Arrays.copyOf(intArray5[i], intArray5[i].length);
        }
        
        System.out.println(intArray5 == cloneArray5);
        
        /**
         * Deep Cloning a Multi-Dimensional Array of Objects - Method 2
         */
        class MyClass2 implements Cloneable {
            int value;

            public MyClass2(int value) {
                this.value = value;
            }

            @Override
            protected Object clone() throws CloneNotSupportedException {
                return super.clone();
            }
        }

        MyClass2[][] objArray2 = {
            { new MyClass2(1), new MyClass2(2) },
            { new MyClass2(3), new MyClass2(4) }
        };
        MyClass2[][] clonedObjArray2 = new MyClass2[objArray2.length][];

        for (int i = 0; i < objArray2.length; i++) {
        	clonedObjArray2[i] = new MyClass2[objArray2[i].length];
            for (int j = 0; j < objArray2[i].length; j++) {
                try {
                	clonedObjArray2[i][j] = (MyClass2) objArray2[i][j].clone();
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
            }
        }
        
        System.out.println(objArray2 == clonedObjArray2);
    }
}

/**
https://www.w3schools.com/java/java_packages.asp
 
* Packages/API

import package.name.Class; // Import a single class
import package.name.*; // Import the whole package

e.g.:
import java.util.Scanner; // import the Scanner class 

import java.util.Date;
import java.time.* // NEW API JAVA 8

import static java.lang.Math.*

java.util
	ArrayList

* Create a Package:

1. Save MyPackageClass.java

package mypack; // The package name should be written in lower case to avoid conflict with class names.
class MyPackageClass {
  public static void main(String[] args) {
    System.out.println("This is my package!");
  }
}

2. Compile: javac MyPackageClass.java

e.g.:
javac packagea/ClassA.java packageb/ClassB.java

3. Create Package: javac -d . MyPackageClass.java
	// d : destination for where to save the class file. May be: c:/user
	// . : keep the package within the same directory.

4. A new folder was created, called "mypack".

5. Run the package: java mypack.MyPackageClass

the result is: This is my package!

e.g.:
java packageb.ClassB
*/

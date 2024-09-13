/*
ref:
D:\ROMULO\JOBS\freelance-remote work\turing\tests\java.docx
 */

package org;

public class CodingInterview {
	
	// For testing
	public static void main(String[] args) {
		String input = "All the best";
		StringBuilder input1 = new StringBuilder();
		input1.append(input);
		input1.reverse();
		System.out.println(input1);	
	}
}

/**
* Write a Java Code to reverse a string without using the in-built function.
import java.lang.*;
import java.io.*;
import java.util.*;

String input = "All the best";
StringBuilder input1 = new StringBuilder();
input1.append(input);
input1.reverse();
System.out.println(input1);

* Which of the following declarations of an array contains an error?
A. int x[] = int[10]; (answer)
B. int[] y=new int[5];
C. float d[]={1,2,3};
D. int a[]={1,2,3}; int b[]; b=a;
E. None of the above

ref: turing

* Consider the following program:
public static void main(String[] args) {
	Integer myArray[] = {2, 3, 1};
	List<Integer> list = Arrays.asList(2, 3, 1);
	list.sort(new Sorting());
	System.out.println(list);
}

ref: turing

static class Sorting implements Comparator<Integer> {
		
	@Override
	public int compare(Integer o1, Integer o2) {
		return o2.compareTo(o1);
	}
}

What is the output?
A. Compilation Error
B. [1 2 3]
C. [3 2 1] (answer)
D. [2 3 1]

Obs: **
o2.compareTo(o1); // descendent order
o1.compareTo(o2); // ascendent order

ref: turing

* Consider the following program:

public static void main(String[] args) {
	String x = "abc";
	String y = "abc";
	x.concat(y);
	System.out.print(x);
}

What is the output?

A. abcabc
B. abc (answer)
C. null
D. Compile error

ref: turing

* Consider the following Java code:

class newthread extends Thread
{
	newthread()
	{
		super("My Thread");
		start();
	}
	
	public void run()
	{
		System.out.println(this);
	}
}

class multithreaded_programming
{
	public static void main(String args[])
	{
		new newthread();
	}
}

What is the output?
A. My Thread
B. Thread[My Thread,5,main] (answer) **
C. Compilation Error
D. Runtime Error

ref: turing

* If x=22.9, then which of the following will produce a value of 23?

A. ceil(x)
B. rint(x)
C. round(x) (answer)
D. abs(x)

ref: turing

* Consider following program:

public class Test {
	public static void main(String[] args) {
		Subclass s1 = new Subclass();
		s1.foo(); // line 7
		
		Super s = new Subclass();
		s.foo(); // line 10
	}
}
	
class Super {
	private void foo() {
		System.out.println("Super");
	}
}

class Subclass extends Super {
	public void foo() {
		System.out.println("Subclass");
	}
}

What is the output?
A. Compile time error at line 7
B. Compile time error at line 10 (answer: The method foo() from the type Super is not visible)
C. Compile time error at line 7 and 10 both
D. Works fine and prints "Subclass" two times

ref: turing

* Consider the following program:

public static void main(String[] args) {
	Set set = new TreeSet();
	set.add(1);
	set.add("2");
	set.add(3);
	
	System.out.println(set);
}

What is the output?
A. [1 2 3]
B. [3 2 1]
C. Compilation Error
D. Runtime Error (answer: Exception in thread "main" java.lang.ClassCastException: class java.lang.Integer cannot be cast to class java.lang.String (java.lang.Integer and java.lang.String are in module java.base of loader 'bootstrap'))

ref: turing

* Consider following program:

package com.turing.java;

public class Test {
	public static void main(String[] args) {
		Super s = new Subclass();
		s.foo();
	}
}
	
class Super {
	void foo() {
		System.out.println("Super");
	}
}

class Subclass extends Super {
	static void foo() {
		System.out.println("Subclass");
	}
}

What is the output?

A. Super
B. Subclass
C. Runtime Error
D. Compile Time Error (answer: static void foo(): This static method cannot hide the instance method from Super)

ref: turing

* Consider the following Java program:

class main_class
{
	public static void main(String args[])
	{
		int x = 9;
		if(x == 9)
		{
			int x = 8;
			System.out.println(x);
		}
	}
}

What is the output?
A. 9
B. 8
C. Compilation Error (answer: int x = 8;: Duplicate local variable x)
D. Runtime Error

ref: turing

* What is the result of the following code? **

public class Shape {
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public double getDiameter() {
		return diameter;
	}
	
	public void setDiameter(double diameter) {
		this.diameter = diameter;
	}
	
	private String name;
	private double diameter;
	
	@Override
	public String toString() {
		return String.format("%s [%.2f]", getName(), getDiameter());
	}
	
	public Shape(String name, double diameter) {
		this.setName(name);
		this.setDiameter(diameter);
	}

	public static void main(String[] args) {

		List<Shape> shapes = new ArrayList<>();
		shapes.add(new Shape("S1", 286));
		shapes.add(new Shape("S2", 512));
		shapes.add(new Shape("S3", 268));
		shapes.add(new Shape("S4", 258));
		shapes.add(new Shape("S5", 431));
		shapes.add(new Shape("S6", 289));
		
		Shape s = shapes.stream().reduce(shapes.get(0), (a,b) -> a.getDiameter()>b.getDiameter() ?
		a:b );
		System.out.println(s);
	}
}

A. 340 67
B. 512 00
C. S4 [258 00]
D. S2 [512 00] (answer)
E. 2044 00

ref: turing

* What is the output of the following code?

public static void main(String[] args) {

	Set<Integer> set = new TreeSet<Integer>();
	set.add(3);
	set.add((int)3.0);
	set.add(2);
	set.add(new Integer(2));
	set.add(Integer.parseInt("2"));
	
	System.out.println(set);
}

A. [3 2]
B. [3 2 2]
C. [2 3] (answer)
D. [3 3 2 2 2]

ref: turing

* Consider the following code:

List<Integer> list = new ArrayList<>(Arrays.asList(2, 3, 4, 5, 6, 8, 9));

// Method 1
Iterator<Integer> iterator = list.iterator();
while(iterator.hasNext()) {

	Integer element = iterator.next();
	if(element % 2 == 0) {
		iterator.remove();
	}
}

// Method 2
for (Integer element :list){
	list.remove(element);
} // output: Exception in thread "main" java.util.ConcurrentModificationException

// Method 3
List<Integer> tempList = new ArrayList<>();
for (Integer element :list){
	if (element%2 == 0){
		tempList.add(element);
	}
}
list.removeAll(tempList);

Which of the following methods will remove all the even numbers from a list of random integers?
(Select all that apply.)
A. Method 1 (answer)
B. Method 2
C. Method 3 (answer)
D. None of the above.

ref: turing

* Consider the following code:
int x, y, z;
x=9; y=10;
z=++x+y++;

After the execution of this code, what are the values of x, y and z?

A. z=21, x=11, y=11
B. z=20, x=10, y=11 (answer)
C. z=21, x=11, y=10
D. z=20, x=10, y=10

ref: turing

* What will stack and queue contain after this code is executed?

Stack<String> stack = new Stack<>();
Queue<String> queue = new LinkedList<String>();
stack.push("Apple");
stack.push("Banana");
stack.push("Cherry");
((LinkedList<String>) queue).add(stack.pop());
stack.push("Dingleberry");
stack.push("Eggplant");
((LinkedList<String>) queue).add("Fig");
((LinkedList<String>) queue).add(stack.pop());
((LinkedList<String>) queue).add(stack.pop());

System.out.println(stack);
System.out.println(queue);

A. Stack: [Apple, Banana], Queue: [Cherry, Fig, Dingleberry, Eggplant] (answer: must be [Cherry, Fig, Eggplant, Dingleberry])
B. Stack: [], Queue: []
C. Stack: [Apple, Banana, Dingleberry, Fig, Cherry, Eggplant], Queue: [Fig Cherry Eggplant]
D. Stack: [Apple, Banana, Dingleberry], Queue: [Fig Cherry Eggplant]

ref: turing

* Consider the following code:

public class ExceptionTest {
	public Integer divide(int a, int b) {
		try {
			return a/b;
		}
		finally {
			System.out.print("finally");
		}
	}
}

public static void main(String[] args) {
	ExceptionTest exceptionTest = new ExceptionTest();
	try {
		System.out.print(exceptionTest.divide(10,0));
	} catch (Exception ex){
		System.out.print("Division by 0!");
	}
}

After compiling and running this code, what is the result?
A. Division by 0!
B. Division by 0! finally
C. finally
D. finallyDivision by 0! (answer)
E. Compilation Error.

ref: turing

* Consider the following program: **

List<String> countries = Arrays.asList("Germany", "England", "China",
	"Denmark", "Brazil", "France", "Australia");
Optional<String> countryName = countries.stream().reduce(
	(c1, c2) -> c1.length() > c2.length() ? c1 :c2);
countryName.ifPresent(System.out::println);

What is the output?
A. China
B. Germany, England, Denmark
C. Australia (answer)
D. Brazil, France

ref: turing

* Which of the following is an example of a Method reference?

A. list.replaceAll(String::toUpperCase) (answer)
B. list.replaceAll(String::toUpperCase())
C. list.replaceAll(s -> s.toUpperCase())
D. None of the above.

ref: turing

* Which lines will cause errors when this code is compiled?

abstract class Animal {
	public abstract void makeNoise();
	public abstract void move();
}

abstract class Canine extends Animal {
	public void wagTail() {
		System.out.println("Wagging");
	}
	
	@Override
	public void move() {
		System.out.println("Run");
	}
}

class Dog extends Canine {
	public void fetch() {
		System.out.println("Fetch");
	}
	
	@Override
	public void makeNoise() {
		System.out.println("Bark");
	}
}

public class App {

	public static void main(String[] args) {
		Dog d = new Dog();
		d.makeNoise();
		d.move();
		d.wagTail();
		d.fetch();
		
		Canine c = new Dog();
		c.makeNoise();
		c.move();
		c.wagTail();
		c.fetch();
	
		Animal a = new Dog();
		a.makeNoise();
		a.move();
		a.wagTail();
		a.fetch();
	}
}
(Select all that apply.)

A. c.fetch(); (answer)
B. a.move();
C. Canine c = new Dog(); , Animal a = new Dog(); // You Cannot Assign an Object of Type Dog to a Variable Declared as typeAnimal.
D. a.wagTail(); (answer)
   a.fetch(); (answer)
E. None of the above. This code compile with no errors.

ref: turing

* What  is the result after executing the following code?

public class Forecast extends Parent {
	public int temperature;
	public int pressure;
}

public static void changeTheString(String weather){
	weather = "sunny";
}

public static void changeTheArray(String[] rainyDays){
	rainyDays[1] = "Sunday";
}

public static void changeTheObject(Forecast forecast){
	forecast.temperature = 35;
}

public static void main(String[] args){

	String weather="rainy";
	changeTheString(weather);
	System.out.println("The Weather is "+weather);
	
	String[] rainyDays = new String[]{"Monday", "Friday"};
	changeTheArray(rainyDays);
	System.out.println("The rainy days were on "+rainyDays[0]+" and "+rainyDays[1]);
	
	Forecast forecast = new Forecast();
	forecast.pressure= 700;
	forecast.temperature= 20;
	changeTheObject(forecast);
	System.out.println("The temperature is "+forecast.temperature+"C");
}

(Select all that apply.)

A. The weather is rainy (answer)
B. The weather is sunny
C. The rainy days were on Monday and Sunday (answer)
D. The Temperature is 20C
E. The Temperature is 35C (answer)

ref: turing

* Consider the following program: **

public class Test implements Runnable
{
	public void run()
	{
		System.out.printf("TURING ");
	}
	public static void main(String[] args) throws InterruptedException
	{
		Thread thread1 = new Thread(new Test());
		thread1.start();
		thread1.start();
		System.out.println(thread1.getState());
	}
}

What is the output?
A. TURING TURING TERMINATED
B. TURING TERMINATED
C. Compilation Error
D. Runtime Error (answer: Exception in thread "main" java.lang.IllegalThreadStateException
	at java.base/java.lang.Thread.start(Thread.java:792)
	at org.util.Test.main(Test.java:26)
TURING)

ref: turing

* Consider the following class definitions: **

abstract class Print
{
	abstract show();
}

class Display extends Print
{
	?
}
What is wrong with this definition?

A. Nothing is wrong.
B. The method show() should have a return type. (answer 2)
C. The method show() is not implemented in display. (answer 1)
D. Display does not contain any numbers

ref: turing

* What is the output of the below code? **

class Parent {
	{
		System.out.println("A ");
	}
	
	static {
		System.out.println("B ");
	}
}

public class Child extends Parent {
	{
		System.out.println("C ");
	}
	
	static {
		System.out.println("D ");
	}
	
	public static void main(String[] args){
		Child child = new Child();
	}
}

A. A B C D
B. B D A C (answer)
C. C D A B 
D. A C B D 

ref: turing

* Consider the following code:

String s1 = "a";
String s2 = "a";
String s3 = new String("a");

How many object are created?
A. 2 (answer)
B. 3
C. 1
D. 4

ref: turing

* What is the static import equivalent notation of list.sort(Comparator.comparing(p -> p.getName()));?
A. list.sort(comparing(Person::getName));
B. list.sort(comparing(Person::getName())); (answer: In Java, you can use static imports to directly use static methods from a class without referring to the class name. In this case, Comparator.comparing() is a static method from the Comparator class. Using a static import, the equivalent notation for list.sort(Comparator.comparing(p -> p.getName())); would be list.sort(comparing(Person::getName()));.)
C. list.sort(comparator(Person::getName));
D. list.sort(comparing(Person p -> p.getName()));

ref: turing

* Consider the following code:

String s1 = "yes";
String s2 = "yes";
String s3 = new String (s1);

Which of the following boolean expressions is true?
A. s1 == s2 (answer)
B. s1 = s2
C. s3 == s1
D. s3 = s1
E. s1!=s2

ref: turing

* Consider the following code:

public class MyClass {
	public static void main(String args[]) {
		System.out.println("In first main()");
	}
	public static void main(char args[]) {
		System.out.println('a');
	}
}

What is the result if we attempt to compile this code?
A. The code will compile correctly but will throw a runtime exception.
B. The code will compile correctly and will print "a" (without quotes) when it is run
C. The code will compile correctly and will print "In first main()" (without quotes) when it is run (answer)
D. The code will not compile and will give a "Duplicate main() Method Declaration" error

ref: turing

* Consider the following program: **

abstract class Demo {
	public int a;
	void demo() {
		a = 10;
	}
	abstract final public void get();
}

class Test extends Demo {
	final public void get() {
		System.out.println("a = " + a);
	}
	public static void main(String[] args) {
		Test obj = new Test();
		obj.get();
	}
}

What is the output?

A. 30
B. 10
C. 20
D. Compilation Error (answer: the abstract method get in type Demo can only set a visibility modifier, one of public or protected)

ref: turing

* What is the output of the following code? **
List<String> list1 = new ArrayList();
list1.add("foo");

List<String> list2 = list1;
List<String> list3 = new ArrayList(list2);

list1.clear();
list2.add("bar");
list3.add("baz");

System.out.print(list1);
System.out.print(list2);
System.out.print(list3);

A. [bar], [bar], [foo, baz]
B. [bar, baz], [bar, baz], [bar, baz]
C. [], [bar], [foo, baz] (answer)
D. [], [bar], [bar, baz]

ref: turing

* Consider the following Java program

class box
{
	int width;
	int height;
	int length;
}
class mainclass
{
	public static void main(String args[])
	{
		box obj1 = new box();
		box obj2 = new box();
		obj1.height = 1;
		obj1.length = 2;
		obj1.width = 1;
		obj2 = obj1;
		System.out.println(obj2.height);
	}
}

What is the output?
A. 1 (answer)
B. 2
C. Runtime Error
D. Garbage Value

ref: turing

* Consider the following code: **

Collection<String> collection;
//insert code here
collection.add("foo");
collection.add("bar");
collection.add("bar");
collection.forEach(System.out::prinln);

We want to print the strings in the same order that they were added to the collection. Which of the following code snippets should be inserted into the above code, at line 2?
(Select all that apply.)

A. collection = new LinkedList();
B. collection = new TreeSet(); (answer)
C. collection = new ArrayList();
D. collection = new LinkedHashSet();
E. collection = new ArrayDeque();

ref: turing
*/

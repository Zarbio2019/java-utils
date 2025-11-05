package org.functionalProgramming;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class FunctionalProgrammingUtil {

	public static void main(String[] args) throws IOException {
		System.out.println("FunctionalProgrammingUtil");
		
		/* Method Reference operator: Double colon(::) */
		// is a simplified version of lambda
		// https://www.geeksforgeeks.org/double-colon-operator-in-java/
		
		// String::length = x -> x.length();
		// stream.forEach(s -> System.out.println(s));
		// stream.forEach(System.out::println);
		// .reduce(0, StreamsUtil::sum);
	
		/***************************************/
		
		/* List */

		// print a List of numbers
		// List.of // Java 9
		// Arrays.asList // Java 8
		System.out.println("print a List");
		List<Integer> numberList = Arrays.asList(12, 9, 13, 4, 6, 2); // Java 8
//		List<Integer> numberList = List.of(12, 9, 13, 4, 6, 2); // Java 9

		System.out.println(numberList);
		
		// with structured approach
		System.out.println("with structured approach");
		for(int num:numberList) {
			System.out.println("element: " + num);
		}
		
		// with functional approach
		System.out.println("with functional approach");
		numberList.stream()
				  .forEach(FunctionalProgrammingUtil::print); // Method Reference
				  //.forEach(System.out::println);
				
		// print a List only even numbers
		// with structured approach
		System.out.println("with structured approach");
		for(int num:numberList) {
			if(num%2 == 0)
				System.out.println("element: " + num);
		}
		
		// with functional approach
		System.out.println("with functional approach");
		numberList.stream()
				  //.filter(FunctionalProgrammingUtil::isEven)
				  .filter(number -> number%2 == 0) // is more simpler
				  .forEach(System.out::println);
		
		// print a List only odd numbers
		System.out.println("print a List only odd numbers");
		numberList.stream()
				  .filter(number -> number%2 != 0)
				  .forEach(System.out::println);
		
		// print a list squares of even numbers
		System.out.println("print a list squares of even numbers");
		numberList.stream()
			   .filter(number -> number%2 == 0)
			   .map(number -> number * number)
			   .forEach(System.out::println);

		// print a List of String, print all courses individually
		List<String> courses = Arrays.asList("Spring", "Spring Boot", "API", "Microservices", "AWS", "PCF", "Azure", "Docker", "Kubernetes"); // Java 8
//		List<String> courses = List.of("Spring", "Spring Boot", "API", "Microservices", "AWS", "PCF", "Azure", "Docker", "Kubernetes"); // Java 9
		courses.stream()
		 	   .forEach(System.out::println);
		
		// print courses containing the word "Spring"
		courses.stream()
		 	   .filter(course -> course.contains("Spring"))
		 	   .forEach(System.out::println);
		
		// print courses whose name has at least 4 letters
		courses.stream()
			   .filter(course -> course.length() >= 4)
			   .forEach(System.out::println);
		
		// print a List length of each string
		System.out.println("print a List length of each string");
		courses.stream()
			   .map(course -> course + " " + course.length())
			   .forEach(System.out::println);
		
		/***************************************/
		
		/* immutable/mutable collection List */
		
		// immutable collection
		List<String> courses2 = Arrays.asList("Spring", "Spring Boot", "API"); // Java 8
//		List<String> courses2 = List.of("Spring", "Spring Boot", "API"); // Java 9
		
		// mutable collection
		List<String> coursesModifiable = new ArrayList(courses2);
		
		coursesModifiable.replaceAll(str -> str.toUpperCase());
		
		coursesModifiable.removeIf(course -> course.length() < 6);
		
		/***************************************/
		
		/* File */
		
		// to read a file
		Files.lines(Paths.get("file.txt"))
			 .map(str -> str.split(" ")) // each line is split into array of strings. result: stream of array of string
			 .flatMap(Arrays::stream)// convert into a single stream with strings
			 .distinct()
			 .sorted()
			 .forEach(System.out::println);
		
		// list files
		Files.list(Paths.get("."))
			 .filter(Files::isDirectory)
			 .forEach(System.out::println);
		
		/***************************************/
		
		/* Thread */

		// Traditional approach
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				for (int i=0; i<10000; i++) {
					System.out.println(Thread.currentThread().getId() + ":" + i);
				}
			}
		};
		
		// Functional approach
		Runnable runnable2 = () -> {
			for (int i=0; i<10000; i++) {
				System.out.println(Thread.currentThread().getId() + ":" + i);
			}
		};
		
		// Functional approach by eliminating the loop
		Runnable runnable3 = () ->
			IntStream.range(0, 10000).forEach(
					i->System.out.println(Thread.currentThread().getId() + ":" + i)
			);
		
		Thread thread = new Thread(runnable2);
		thread.start();
		
		Thread thread1 = new Thread(runnable2);
		thread1.start();

		Thread thread2 = new Thread(runnable2);
		thread2.start();
	}
	
	private static void print(int number) {
		System.out.println(number);
	}
	
	private static boolean isEven(int number) {
		return number%2 == 0;
	}
}

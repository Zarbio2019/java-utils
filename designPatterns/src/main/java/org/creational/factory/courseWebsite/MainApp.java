package org.creational.factory.courseWebsite;

/**
 * How to use Factory Method Design Pattern to design a course website like Udacity, Edx, Coursera.
 * https://www.youtube.com/watch?v=s3Wr5_tsODs
 * 
 * 1. Demo
 * MainApp.java
 * 
 * 2. Factory class
 * CourseFactory.java
 * 
 * 3. Abstract class
 * Course.java
 * 
 * 4. Concrete class
 * HLD.java
 * LLD.java
 */
public class MainApp {
	public static void main(String[] args) {
		Course hldCourse = CourseFactory.getCourse("HLD");
		Course lldCourse = CourseFactory.getCourse("LLD");
		
		assert hldCourse != null;
		System.out.print("HLD Module: ");
		System.out.println(hldCourse.modules);
		
		assert lldCourse != null;
		System.out.print("LLD Module: ");
		System.out.println(lldCourse.modules);
	}
}

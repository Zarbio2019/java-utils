package org.creational.mvc.student;

/**
Model-View-Controller (MVC) Pattern

https://www.tutorialspoint.com/design_pattern/mvc_pattern.htm

We are going to create a Student object acting as a model.
StudentView will be a view class which can print student details on console and StudentController is
the controller class responsible to store data in Student object and update view StudentView accordingly.

MVCPatternDemo, our demo class, will use StudentController to demonstrate use of MVC pattern.

design.png

1. Model
Student.java

2. View
StudentView.java

3. Controller
StudentController.java

4. Use the StudentController methods to demonstrate MVC design pattern usage.
MainApp.java
*/
public class MainApp {

	public static void main(String[] args) {

		// fetch student record based on his roll no from the database
		Student model  = retriveStudentFromDatabase();

		// Create a view: to write student details on console
		StudentView view = new StudentView();

		StudentController controller = new StudentController(model, view);

		controller.updateView();

		// update model data
		controller.setStudentName("John");

		controller.updateView();
		/* output:
		Student: 
		Name: Robert
		Roll No: 10
		Student: 
		Name: John
		Roll No: 10
		*/
	}

	private static Student retriveStudentFromDatabase(){
		Student student = new Student();
		student.setName("Robert");
		student.setRollNo("10");
		return student;
	}
}

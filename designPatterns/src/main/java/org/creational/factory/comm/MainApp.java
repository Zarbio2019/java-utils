package org.creational.factory.comm;

/**
 * Factory Design Pattern in Java | Real Time Project Example
 * https://www.youtube.com/watch?v=fBDkYNZje0k
 */
import java.util.ArrayList;
import java.util.List;

public class MainApp {
	public static void main(String[] args) {
		List<Employee> empList = createEmployeeList();
	
		CommunicationFactory factory = new CommunicationFactory();
		Communication processor;
		
		for(Employee emp : empList) {
			processor = factory.getProcess(emp.getModeOfContact());
			processor.process(emp);
		}
		System.out.println();
		
		System.out.println("====== Using java 8 ForEach ======");
		empList.forEach(emp -> {
			factory.getProcess(emp.getModeOfContact()).process(emp);
		});
	}
	
	private static List<Employee> createEmployeeList() {
		List<Employee> empList = new ArrayList<>();
		
		Employee e1 = new Employee("Angel Priya", "1", "Pune", 23, "email", "email1@gmail.com", "1000");
		Employee e2 = new Employee("Thakur", "1", "Pune", 23, "mobile", "email2@gmail.com", "2000");
		Employee e3 = new Employee("Papa ki pari", "1", "Pune", 23, "email", "email3@gmail.com", "3000");
		Employee e4 = new Employee("Gabbar Singh", "1", "Pune", 23, "mobile", "email4@gmail.com", "4000");
		Employee e5 = new Employee("Ramu kaka", "1", "Pune", 23, "email", "email5@gmail.com", "5000");
	
		empList.add(e1);
		empList.add(e2);
		empList.add(e3);
		empList.add(e4);
		empList.add(e5);
		
		return empList;
	}
}

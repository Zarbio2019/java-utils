package org.creational.factory.comm;

public class EmailCommunication implements Communication {
	
	@Override
	public void process(Employee emp) {
		System.out.println(emp.getName() + " communication via email : " + emp.getEmail());
	}
}

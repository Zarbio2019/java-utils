package org.creational.factory.comm;

public class MobileCommunication implements Communication {
	
	@Override
	public void process(Employee emp) {
		System.out.println(emp.getName() + " communication via mobile : " + emp.getPhoneNumber());
	}
}

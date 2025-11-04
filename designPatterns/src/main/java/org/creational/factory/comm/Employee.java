package org.creational.factory.comm;

public class Employee {
	private String name;
	private String id;
	private String city;
	private int age;
	private String modeOfContact;
	private String email;
	private String phoneNumber;
	
	public Employee(String name, String id, String city, int age, String modeOfContact, String email,
			String phoneNumber) {
		super();
		this.name = name;
		this.id = id;
		this.city = city;
		this.age = age;
		this.modeOfContact = modeOfContact;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getModeOfContact() {
		return modeOfContact;
	}
	public void setModeOfContact(String modeOfContact) {
		this.modeOfContact = modeOfContact;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}

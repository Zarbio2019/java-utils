package org.classes;

public class Constructor {
	
    public static void main(String[] args) {
    	
    	/* Copy Constructor */
        // Creating a Person object
        Person person1 = new Person("Alice", 30);

        // Using the copy constructor to create a new Person object
        Person person2 = new Person(person1);
        
        System.out.println("equals: " + person1.equals(person2)); // false
        System.out.println("==: " + (person1 == person2)); // false

        // Displaying details of both Person objects
        System.out.println("Person 1: " + person1.getName() + ", " + person1.getAge());
        System.out.println("Person 2 (copy of Person 1): " + person2.getName() + ", " + person2.getAge());
    }
}

class Person {
    private String name;
    private int age;

    // Constructor
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Copy constructor
    public Person(Person otherPerson) {
        this.name = otherPerson.name; // Copying name from otherPerson
        this.age = otherPerson.age;   // Copying age from otherPerson
    }

    // Getter methods
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}

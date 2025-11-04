package org.creational.factory.courseWebsite;

import java.util.ArrayList;
import java.util.List;

public abstract class Course {
	protected List<Module> modules = new ArrayList<>();
	
	public Course() {
		this.createCourse();
	}
	
	public List<Module> getModule() {
		return modules;
	}

	public abstract void createCourse();
}

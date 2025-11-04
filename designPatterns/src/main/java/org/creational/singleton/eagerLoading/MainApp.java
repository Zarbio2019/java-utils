package org.creational.singleton.eagerLoading;

public class MainApp {

	public static void main(String[] args) {
		
		// Both share the same instance
		LoggerSingleton singletonObj = LoggerSingleton.getInstance();
		System.out.println(singletonObj);
		
		LoggerSingleton singletonObj2 = LoggerSingleton.getInstance();
		System.out.println(singletonObj2);
		
		/* output:
		org.singleton.eagerLoading.LoggerSingleton@26f0a63f
		org.singleton.eagerLoading.LoggerSingleton@26f0a63f
		 */
	}
}

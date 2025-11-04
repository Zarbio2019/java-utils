package org.creational.singleton.eagerLoading;

/**
 * Eager Loading
 * 
 * Basic implementation
 * Is not thread-safe
 */
public class LoggerSingleton {
	
	// Eager Loading, because the instance is already initialized
	private static LoggerSingleton instance = new LoggerSingleton();
	
	private LoggerSingleton() {
	}
	
	public static LoggerSingleton getInstance() {
		return instance;
	}
}

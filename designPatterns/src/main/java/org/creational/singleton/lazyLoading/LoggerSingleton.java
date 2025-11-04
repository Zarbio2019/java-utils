package org.creational.singleton.lazyLoading;

/**
 * Lazy Loading
 * 
 * Is not thread-safe
 */
public class LoggerSingleton {
	
	// Lazy Loading, because the instance is only initialized when is not initialized
	private static LoggerSingleton instance = null;
	
	private LoggerSingleton() {
	}
	
	public static LoggerSingleton getInstance() {
		if(instance == null) {
			instance = new LoggerSingleton();
		}
		return instance;
	}
}

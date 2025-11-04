package org.creational.singleton.lazyLoading;

/**
 * Lazy Loading with Thread Safe
 */
public class LoggerSingletonThreadSafe {
	
	// Lazy Loading, because the instance is only initialized when is not initialized
	private static volatile  LoggerSingletonThreadSafe instance = null;
	
	private LoggerSingletonThreadSafe() {
		if(instance != null) {
			throw new RuntimeException("Please don't try to be smart");
		}
	}
	
	public static LoggerSingletonThreadSafe getInstance() {
		
		if(instance == null) {
			synchronized (LoggerSingletonThreadSafe.class) {
							
				if(instance == null) {
					instance = new LoggerSingletonThreadSafe();
				}
			}
		}
		return instance;
	}
}

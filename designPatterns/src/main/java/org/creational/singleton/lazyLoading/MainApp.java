package org.creational.singleton.lazyLoading;

public class MainApp {

	public static void main(String[] args) {
		
		// Both share the same instance
		
		/* No Thread-Safe */
		LoggerSingleton singletonObj = LoggerSingleton.getInstance();
		System.out.println(singletonObj);
		
		LoggerSingleton singletonObj2 = LoggerSingleton.getInstance();
		System.out.println(singletonObj2);
		
		/* output:
		org.singleton.eagerLoading.LoggerSingleton@26f0a63f
		org.singleton.eagerLoading.LoggerSingleton@26f0a63f
		 */
		
		/**************************************************/
		
		// Both share the same instance
		
		/* With Thread-Safe */
		LoggerSingletonThreadSafe singletonObj3 = LoggerSingletonThreadSafe.getInstance();
		System.out.println(singletonObj3);
		
		LoggerSingletonThreadSafe singletonObj4 = LoggerSingletonThreadSafe.getInstance();
		System.out.println(singletonObj4);
		
		/* output:
		org.singleton.eagerLoading.LoggerSingleton@26f0a63f
		org.singleton.eagerLoading.LoggerSingleton@26f0a63f
		 */
	}
}

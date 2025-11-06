// https://www.w3schools.com/java/java_hashmap.asp

package org;

import java.util.HashMap;
import java.util.Map;

public class MapUtil {
	
	public static void main(String[] args) {

		/* HashMap */
		// HashMap.class
		// package java.util
		// extends AbstractMap<K,V>
	    // implements Map<K,V>, Cloneable, Serializable
	    
		/* methods */
		/*
		put
		putAll
		get
		remove
		clear
		size
		keySet
		values
		isEmpty
		containsKey
		containsValue
		replace
		replaceAll
		forEach
		clone
		 */
		
		HashMap<String, String> capitalCities = new HashMap<String, String>();

		capitalCities.put("England", "London"); // {England=London}
		capitalCities.get("England"); // London
		capitalCities.remove("England");
		capitalCities.clear(); // remove all items
	
		capitalCities.size();
	
		HashMap<String, Integer> people = new HashMap<String, Integer>();
		people.put("John", 32);

		// put() vs putAll():
		// put: add/replace a key/value pair in the map
		Map<String, Integer> mapFruits1 = new HashMap<>();
		mapFruits1.put("Apple", 10);
		mapFruits1.put("Banana", 20);
		System.out.println("mapFruits1.put: " + mapFruits1); // Apple=10, Banana=20

		// putAll: adds another map (key-value pairs) to the map, replacing the existing keys with new values
		Map<String, Integer> mapFruits2 = new HashMap<>();
		mapFruits2.put("Banana", 60);
		mapFruits2.put("Orange", 40);
		mapFruits1.putAll(mapFruits2);
		System.out.println("mapFruits1.putAll: " + mapFruits1); // Apple=10, Orange=40, Banana=60

		/* loop */

		// for keys
		for (String i : capitalCities.keySet()) {
			System.out.println(i);
		}

		// for values
		for (String i : capitalCities.values()) {
			System.out.println(i);
		}

		// for key and values
		for (String i : capitalCities.keySet()) {
			System.out.println("key: " + i + " value: " + capitalCities.get(i));
		}

		Map<String, String> map = new HashMap<>();
		map.put("koala", "bamboo");
		map.put("lion", "meat");
		map.put("giraffe", "leaf");
		String food = map.get("koala"); // bamboo

		for (String key: map.keySet())
			System.out.print(key + ","); // koala,giraffe,lion,

		/**************************************************/

		/* TreeMap */

		/**************************************************/

		/* LinkedHashMap */
	}	
}

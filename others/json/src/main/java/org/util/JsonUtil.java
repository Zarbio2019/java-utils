/**
refs:
* org.json, org.json.JSONObject
https://www.baeldung.com/java-org-json
used to parse and manipulate json.

* com.fasterxml.jackson.core (better)
com.fasterxml.jackson.databind.ObjectMapper
https://www.baeldung.com/jackson-object-mapper-tutorial

* Google’s org.json.simple
https://code.google.com/archive/p/json-simple/

* 2021 BBVA_WorkflowComex\Source
*/
package org.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.json.JSONObject;

public class JsonUtil {
	
	public static void main(String[] args) throws IOException {
		System.out.println("JsonUtil");
		
		/**
		 * create a JSON String
		 */
		// import org.json.JSONObject;		
		String userJson = "{\n" +
			  "   \"firstName\":\"Sergey\",\n" +
			  "   \"lastName\":\"Kargopolov\",\n + " +
			  "   \"email\":\"test3@test.com\",\n + " +
			  "}";
		
		// is equal to:
		JSONObject userJsonObject = new JSONObject();
		userJsonObject.put("firstName", "Sergey");
		userJsonObject.put("lastName", "Kargopolov");
		userJsonObject.put("email", "test@test.com");
	  		
		String jsonStr = userJsonObject.toString();
		System.out.println("userJsonObject: " + userJsonObject); // {"firstName":"Sergey","lastName":"Kargopolov","email":"test@test.com"}

		String firstName = userJsonObject.getString("firstName"); // return: Sergey
		
		/**
		 * convert JSON String into JSONObject
		 */
		// import org.json.JSONObject;
		JSONObject userJsonObject1 = new JSONObject(
						  "{\"city\":\"chicago\",\"name\":\"jon doe\",\"age\":\"22\"}"
						);
		System.out.println("json: " + userJsonObject1); // {"city":"chicago","name":"jon doe","age":"22"}
		
		/**
		 * convert Object into JSONObject
		 */
		UserDTO user = new UserDTO();
		user.setFirstName("Sergey");
		user.setLastName("Kargopolov");
		user.setEmail("test@test.com");
		
		JSONObject userJsonObject2 = new JSONObject(user);
		System.out.println("userJsonObject2: " + userJsonObject2); // REVIEW
		
		/**
		 * convert Object into JSON String
		 */
		//import com.fasterxml.jackson.databind.ObjectMapper
		UserDTO user1 = new UserDTO();
		user1.setFirstName("Sergey");
		user1.setLastName("Kargopolov");
		user1.setEmail("test@test.com");
		
		jsonStr = new ObjectMapper().writeValueAsString(user1);
		System.out.println("jsonStr: " + jsonStr); // {"firstName":"Sergey","lastName":"Kargopolov","email":"test@test.com"}
		
		/**
		 * convert Object into JSON String and save in a .json file.
		 */
		//import com.fasterxml.jackson.databind.ObjectMapper
		new ObjectMapper().writeValue(new File("target/user.json"), user1); // {"firstName":"Sergey","lastName":"Kargopolov","email":"test@test.com"}
		
		/**
		 * convert JSON String into Object
		 */
		// import com.fasterxml.jackson.databind.ObjectMapper
		UserDTO user2 = new ObjectMapper().readValue(jsonStr, UserDTO.class);
		System.out.println("user2: " + user2); // [firstName=Sergey, lastName=Kargopolov, email=test@test.com]
		
		String json = "{ \"firstName\" : \"Sergey\", \"lastName\" : \"Kargopolov\", \"email\" : \"test@test.com\"}";
		UserDTO user3 = new ObjectMapper().readValue(json, UserDTO.class);
		System.out.println("user3: " + user3); // [firstName=Sergey, lastName=Kargopolov, email=test@test.com]
		
		/**
		 * convert JSON String from .json file into Object
		 */
		// import com.fasterxml.jackson.databind.ObjectMapper
		UserDTO user4 = new ObjectMapper().readValue(new File("target/user.json"), UserDTO.class);
		System.out.println("user4: " + user4); // [firstName=Sergey, lastName=Kargopolov, email=test@test.com]
	}
	
	/**************************************************/
	
	public static String getRequest(Object request) {
		JSONObject jsonObject = new JSONObject(request);
		Set<String> keys = jsonObject.keySet();
		Map<String, String> map = new HashMap<String, String>();
		for (String string : keys) {
			map.put(string.toLowerCase(), jsonObject.get(string).toString());
		}
		JSONObject response = new JSONObject(map);
		return response.toString();
	}

	/**
	 *
	 * @param json
	 * @param claseDTO
	 * @return
	 */
	public static Object convertirCadenaADTO(String json, Class<?> claseDTO) {
		ObjectMapper jackson = new ObjectMapper();
		Object objetoDTO = null;
		try {
			objetoDTO = jackson.readValue(json, claseDTO);

		} catch (Exception e) {
			throw new RuntimeException();
		}
		return objetoDTO;
	}

	public static String convertirObjectoACadena(Object objeto) {
		ObjectMapper jackson = new ObjectMapper();
		String json = null;
		try {
			json = jackson.writeValueAsString(objeto);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return json;
	}

	public static String convertirModelAJson(Object objeto) {
		Gson gson = new Gson();
		String json = null;
		try {
			 json = gson.toJson(objeto);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return json;
	}
	
	public static Object jsonToDTO(String json, Class<?> claseDTO) {
		Gson gson = new Gson();
		Object objetoDTO = null;
		try {
			objetoDTO = gson.fromJson(json, claseDTO);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return objetoDTO;
	}
}

class UserDTO {
	String firstName;
	String lastName;
	String email;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "UserDTO [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + "]";
	}
}

package org;

import org.enums.BusinessError;

import java.math.BigDecimal;
import java.util.*;

public class ValidationUtil {
	
	public static void main(String[] args) {
		System.out.print("ValidationUtil");

		// Optional.class
		System.out.println("\nOptional.class");
		/**
		 import java.util.Optional
		 package: java.util
		 Is a container object which may or may not contain a non-null value.

		 https://www.geeksforgeeks.org/java-8-optional-class/
		 https://www.tutorialspoint.com/java/java_optional_class.htm#:~:text=Optional%20is%20a%20container%20object,instead%20of%20checking%20null%20values

		 methods:
		 empty
		 equals
		 filter
		 map
		 flatMap
		 get
		 hashCode
		 ifPresent
		 of
		 ofNullable
		 orElse
		 orElseTHrow
		 toString
		 */
		 // sample without Optional:
		 String[] words = new String[10];
//		 String word = words[5].toLowerCase(); // Exception
//		 System.out.print(word);

		 // sample with Optional:
		 Optional<String> checkNull = Optional.ofNullable(words[5]);

		 if (checkNull.isPresent()) {
			 String word = words[5].toLowerCase();
		 	System.out.print(word);
		 }
		 else
		 	System.out.println("word is null");

		// isNumber
		System.out.println("\nisNumber");
		System.out.println(isNumber(""));

		// if String is not null and is not empty
		String str = "";
		if(Objects.nonNull(str) && !str.isEmpty()) {}

		// isNullOf
		System.out.println("\nisNullOf");

		List<Map<String, Object>> listMapClient = new ArrayList<>();
		Map<String, Object> mapClient = new HashMap<>();

		mapClient.put("name", "Allie");
		Map<String, Object> mapDocument = new HashMap<>();
		mapDocument.put("number", "1234");
		mapClient.put("document", mapDocument);

		listMapClient.add(mapClient);

		listMapClient.forEach(map -> {
			String name = isNullOf(map.get("name")); // {Allie}
			String doc = isNullOf(map.get("document")); // {number=1234}
		});

		// hasFormattedAddress
		System.out.println("\nhasFormattedAddress");

        PersonalInformation personalInformation = new PersonalInformation();
        if (hasFormattedAddress(personalInformation))
            System.out.println("true");
		System.out.println("false");
	}
	
	/*************************************************/

	/**
	 * Is number
	 */
	public static boolean isNumber(String value) {
		try {
			new BigDecimal(value);
//			Double.parseDouble(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/**
	 * checks if String number is decimal
	 */
	public static boolean checkDecimal(String value) {
		return value.contains(Constants.PUNTO);
	}

	/**
	 * Is Null
	 * checks if the object is null.
	 */
	public static boolean isNull(Object object) {
//		if(!Objects.isNull(object))
		if (object != null)
			return false;
		return true;
	}

	/**
	 * Is NotNull
	 * checks if the object is not null.
	 */
	public static boolean isNotNull(Object object) {
		if(!Objects.nonNull(object))
			return false;
		return true;
	}
//	// listNonCustomerBO is a entity
//	if (Objects.isNull(listNonCustomerBO) || Objects.isNull(listNonCustomerBO.getData())) {
//		return Collections.emptyList();
//	}

	/**
	 * Is Null (more readable)
	 *
	 * better way to do:
	 * if(!Objects.isNull(personalInformation.getAddress()) &&
	 *   	!Objects.isNull(personalInformation.getAddress().getLocation()) &&
	 * 		!Objects.isNull(personalInformation.getAddress().getLocation().getFormattedAddress()))
	 */
    private static boolean hasFormattedAddress(PersonalInformation personalInformation) {
        return Optional.ofNullable(personalInformation)
                .map(PersonalInformation::getAddress) // each map checks for null and maps to the next method if the value is present
                .map(Address::getLocation)
                .map(Location::getFormattedAddress)
                .isPresent();
    }

	/**
	 * isNullOf
	 */
	static String isNullOf(Object field) {
		return Objects.nonNull(field) ? String.valueOf(field) : null;
	}

	/**
	 * Is empty (Spring framework)
	 */
//	import org.springframework.util.StringUtils;
//	StringUtils.isEmpty("1234");

	/**
	 * isNotBlank (Spring framework)
	 */
//	import org.springframework.util.StringUtils;
//	StringUtils.isNotBlank("1234");
//	StringUtils.isBlank(eventId)

//	public static String stringNotBlank(String value) {
//		if(BooleanUtils.isFalse(StringUtils.isNotBlank(value))) {
//			throw new BusinessException(BusinessError.WRONG_PARTICIPANT_TYPE_ERROR.getCodeAdvice(), false);
//		}
//		return value;
//	}

	/**
	 * Is empty (org.apache.commons)
	 */
//	 import org.apache.commons.lang.BooleanUtils;
// 	 import org.apache.commons.lang3.StringUtils;
//	if (BooleanUtils.isFalse(response))

	/**
	 * Validate if string contains whitespaces and is empty (spring framework)
	 * import org.springframework.util.StringUtils;
	 */
//	private static boolean isValidStringField(String field) {
//		return !StringUtils.containsWhitespace(field) && !StringUtils.isEmpty(field);
//	}

	/**
	 * Is empty
	 */
	public static boolean isEmpty(String s) {
		if (s == null || s.contentEquals(Constants.EMPTY)) {
			return true;
		}
		s = s.replaceAll("\\s", Constants.EMPTY);
		if (s != null && s.trim().length() > 0) {
			return false;
		}
		return true;
	}

	/**
	 * checks if the object is empty.
	 */
	public static boolean isEmpty(Object object) {
		return isObjectEmpty(object);
	}

	/**
	 * checks if the object is not empty.
	 */
	public static boolean isNotEmpty(Object object) {
		return !isObjectEmpty(object);
	}

	/**
	 * checks if the object is empty.
	 */
	public static boolean isObjectEmpty(Object value) {
		if (value == null) {
			return true;
		} else if (value instanceof String) {
			return isEmpty((String) value);
		} else if (value instanceof CharSequence) {
			return isEmpty((CharSequence) value);
		} else if (value instanceof Collection || value instanceof Map) {
			return isCollectionEmpty(value);
		}
		return false;
	}

	/**
	 * checks if the collection is empty.
	 */
//	List<String> listStr = new ArrayList<>();
//	listStr.isEmpty();

	/**
	 * checks if the collection is empty.
	 */
	public static <E> boolean isEmpty(Collection<E> collection) {
		return (collection == null) || collection.isEmpty();
	}

	/**
	 * checks if the collection is empty.
	 */
	private static boolean isCollectionEmpty(Object value) {
		if (value instanceof Collection) {
			return isEmpty((Collection<? extends Object>) value);
		} else {
			return isEmpty((Map<? extends Object, ? extends Object>) value);
		}
	}

	/**
	 * checks if the map is empty.
	 */
	public static <K, E> boolean isEmpty(Map<K, E> map) {
		return (map == null) || (map.isEmpty());
	}

	/**
	 * checks if the character is empty.
	 */
	public static boolean isEmpty(CharSequence character) {
		return (character == null) || (character.length() == 0);
	}

	/**
	 * checks if the string is null.
	 */
	public static String emptyIfStringNull(String value) {
		return (value == null ? Constants.EMPTY : value);
	}

	/**
	 * If equals
	 */
//	Objects.equals(type, amountTypeBO.type);

	/**
	 * Validate currency
	 */
//	private void validCurrency(String value){
//		if(Currency.getAvailableCurrencies().stream().noneMatch(c->c.getCurrencyCode().equals(value))){
//			throw new BusinessException(BusinessError.CURRENCY_CODE_IS_NOT_VALID.getCodeAdvice(), true);
//		}
//	}

	/**
	 * Validator
	 */
//	Boolean isValid(T value); // interface
}

class PersonalInformation {
    private Address address;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}

class Address {
    private Location location;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}

class Location {
    private String formattedAddress;

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }
}

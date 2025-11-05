package org;

import java.math.BigDecimal;
import java.util.*;

public class OthersUtils {

	public static void main(String[] args) {

		System.out.println("OthersUtils");

		// obfuscateFormattedAddress
		System.out.println("\nobfuscateFormattedAddress");
		PersonalInformation personalInformation = new PersonalInformation();
		Address address = new Address();
		Location location = new Location();
		location.setFormattedAddress("COUNTY SAN FRANCISCO");
		address.setLocation(location);;
		personalInformation.setAddress(address);
		obfuscateFormattedAddress(personalInformation);
		System.out.println("getFormattedAddress: " + personalInformation.getAddress().getLocation().getFormattedAddress());

/**
 		* Conversion
 		Long long = Long.parseLong("1");

		* Java Spring Debug
		https://www.youtube.com/watch?v=Yse_KTn5wFs

		* shortcuts:
		sysout + Ctrl + BackSpace = System.out.println();

		* Largest int
		Integer.MAX_VALUE

		* Emit beep of the system
		Toolkit.getDefaultToolkit().beep();

		* Count Number of Words in a String
		String words = "One Two Three Four";
		int countWords = words.split("\\s").length; // output: 4

		* Reverse a String
		String originalStr = "Hello";
		String reversedStr = "";
		System.out.println("Original string: " + originalStr);

		for (int i = 0; i < originalStr.length(); i++) {
		reversedStr = originalStr.charAt(i) + reversedStr;
		}

		System.out.println("Reversed string: "+ reversedStr);

		// output:
		// Original string: Hello
		// Reversed string: olleH

		* Calculate the Sum of an Array
		int[] myArray = {1, 5, 10, 25};
		int sum = 0;
		int i;

		// Loop through array elements and get the sum
		for (i = 0; i < myArray.length; i++) {
		sum += myArray[i];
		}
		System.out.println("The sum is: " + sum);
		// output:
		// The sum is: 41

		* Check Whether a Number is Even or Odd
		int number = 5;

		if (number % 2 == 0) {
		System.out.println(number + " is even.");
		} else {
		System.out.println(number + " is odd.");
		}
		// output: 5 is odd.

		* Change values of a list
		entityIn.getParticipants().forEach(participantDTO -> desencryptFieldsParticipant(participantDTO));

		*
		if (Objects.nonNull(cardProposalDTOInput.getRates())) {
		cardProposalDTOInput.getRates().getItemizeRates().forEach(x -> x.setDescription(x.getRateType()));
		}
		// ref: PPGSR041Impl/ProposalCardMapper.java

		*
		if (Objects.nonNull(cardProposalDTOInput.getContacts())) {
		cardProposalDTOInput.getContacts().stream()
		.filter(x-> Objects.nonNull(x.getContact().getPhoneCompany()))
		.forEach(x-> x.getContact().getPhoneCompany().setName(x.getContact().getPhoneCompany().getId()));
		cardProposalDTOInput.getContacts().forEach(x-> x.setId(Objects.nonNull(x.getContact().getNumber())? I_MOBIL_CONTACT:I_EMAIL_CONTACT));
		}
		// ref: PPGSR041Impl/ProposalCardMapper.java
 */
	}

	/**************************************************/

	/**
	 * Obfuscate/asterisk (*) a String field
	 */
	private static void obfuscateFormattedAddress(PersonalInformation personalInformation) {
		Optional.ofNullable(personalInformation)
				.map(PersonalInformation::getAddress)
				.map(Address::getLocation)
				.map(Location::getFormattedAddress)
				.map(OthersUtils::obfuscateString)
				.ifPresent(obfuscatedAddress -> personalInformation.getAddress().getLocation().setFormattedAddress(obfuscatedAddress));
	}

	public static String obfuscateString(String input) {
		char[] obfuscatedArray = new char[input.length()];
		java.util.Arrays.fill(obfuscatedArray, '*');
		return new String(obfuscatedArray);
	}

//	public static void limpiarObjeto(Object object,String _valor) throws Exception {
//		PropertyDescriptor arrayPropertyDescriptor[]  = PropertyUtils.getPropertyDescriptors(object);
//		Object valor = null;
//		String atributo = null;
//		for (PropertyDescriptor propertyDescriptor : arrayPropertyDescriptor) {
//			atributo =  propertyDescriptor.getName();
//			if(propertyDescriptor.getPropertyType().equals(String.class)){
//				valor = PropertyUtils.getSimpleProperty(object,atributo);
//				if(valor !=null){
//					if(valor.toString().contains(_valor)){
//						PropertyUtils.setSimpleProperty(object, atributo, valor.toString().replaceAll(_valor,StringUtils.EMPTY));
//					}
//				}
//			}
//		}
//	} 

//	public static void trimObjecto(Object object) throws Exception{
//		PropertyDescriptor arrayPropertyDescriptor[]  = PropertyUtils.getPropertyDescriptors(object);
//		Object valor = null;
//		String atributo = null;
//		for (PropertyDescriptor propertyDescriptor : arrayPropertyDescriptor) {
//			atributo =  propertyDescriptor.getName();
//			valor = PropertyUtils.getSimpleProperty(object,atributo);
//			if(valor !=null && valor instanceof String){
//				PropertyUtils.setSimpleProperty(object, atributo, ((String)valor).trim());
//			}
//		}
//	}

//	public static String serializeObjectToString(Object object) throws IOException {
//		ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
//		GZIPOutputStream gzipOutputStream = new GZIPOutputStream(arrayOutputStream);
//		ObjectOutputStream objectOutputStream = new ObjectOutputStream(gzipOutputStream);
//		objectOutputStream.writeObject(object);
//		objectOutputStream.flush();
//		return new String(base64.getEncoder().encodeToString(arrayOutputStream.toByteArray()));
//	}

	public static void converToUpperCase(Object input) {
		try {
			for (java.lang.reflect.Field field : input.getClass().getDeclaredFields()) {
				if (field.getType().equals(String.class)) {
					if (!field.isAccessible())
						field.setAccessible(true);
					try {
						if (field.get(input) != null && !((String) field.get(input)).trim().equals(Constants.EMPTY)) {
							field.set(input, ((String) field.get(input)).toUpperCase());
						}
					} catch (IllegalAccessException e) {
					}
				}
			}
		} catch (IllegalArgumentException e) {
		}
	}

	public static void trim(Object input) {
		try {
			for (java.lang.reflect.Field field : input.getClass().getDeclaredFields()) {
				if (field.getType().equals(String.class)) {
					if (!field.isAccessible())
						field.setAccessible(true);
					try {
						if (field.get(input) != null) {
							field.set(input, ((String) field.get(input)).trim());
						} else {
							field.set(input, Constants.EMPTY);
						}
					} catch (IllegalAccessException e) {
					}
				}
			}
		} catch (IllegalArgumentException e) {
		}
	}

	public static void fillEmpty(Object input) {
		try {
			for (java.lang.reflect.Field field : input.getClass().getDeclaredFields()) {
				if (field.getType().equals(String.class)) {
					if (!field.isAccessible())
						field.setAccessible(true);
					try {
						if (field.get(input) == null) {
							field.set(input, Constants.EMPTY);
						}
					} catch (IllegalAccessException e) {
					}
				}
			}
		} catch (IllegalArgumentException e) {
		}
	}

	public static void replaceAll(Object input, String value) {
		try {
			for (java.lang.reflect.Field field : input.getClass().getDeclaredFields()) {
				if (field.getType().equals(String.class)) {
					if (!field.isAccessible())
						field.setAccessible(true);
					try {
						if (field.get(input) != null) {
							field.set(input, ((String) field.get(input)).trim().replaceAll(value, Constants.EMPTY));
						}
					} catch (IllegalAccessException e) {
					}
				}
			}
		} catch (IllegalArgumentException e) {
		}
	}

	public static String splitText(String text, int longitud) {
		if (ValidationUtil.isEmpty(text)) {
			return Constants.EMPTY;
		}
		if (longitud <= text.length()) {
			return text.substring(0, longitud);
		}
		return text.substring(0, text.length());
	}

	public static String fillCeros(String importe, Integer longitud) {
		int ceros = 0;
		if (!ValidationUtil.isEmpty(importe)) {
			ceros = longitud - importe.length();
			if (ceros < 0) {
				importe = importe.substring(0, longitud);
			}
		} else {
			ceros = longitud;
		}
		StringBuilder builder = new StringBuilder();
		String car = "0";

		boolean importeNoNumero=false;
		if(!ValidationUtil.isEmpty(importe) && !ValidationUtil.isNumber(importe.trim())){
			car =" ";
			importeNoNumero= true;
		}

		for (int i = 0; i < ceros; i++) {
			builder.append(car);
		}

		if(importeNoNumero)
			builder.insert(0, importe);
		else
			builder.append(importe);

		return builder.toString();
	}

//	public static void limpiarObjeto(Object object, String _valor) throws Exception {
//		PropertyDescriptor arrayPropertyDescriptor[] = PropertyUtils.getPropertyDescriptors(object);
//		Object valor = null;
//		String atributo = null;
//		for (PropertyDescriptor propertyDescriptor : arrayPropertyDescriptor) {
//			atributo = propertyDescriptor.getName();
//			valor = PropertyUtils.getSimpleProperty(object, atributo);
//			if (valor != null) {
//				if (valor.toString().contains(_valor)) {
//					try{
//					PropertyUtils.setSimpleProperty(object, atributo,
//							valor.toString().replaceAll(_valor, EMPTY));
//					}catch(Exception e){}
//				}
//			}
//		}
//	}

	public static String addNumerocuenta(String numeroCuenta) {
		if (ValidationUtil.isEmpty(numeroCuenta)) {
			return Constants.EMPTY;
		}
		StringBuilder builderCuenta = new StringBuilder();
		builderCuenta.append(numeroCuenta.subSequence(0, 4));
		builderCuenta.append(Constants.GUION);
		builderCuenta.append(numeroCuenta.subSequence(4, 8));
		builderCuenta.append(Constants.GUION);
		builderCuenta.append(numeroCuenta.subSequence(8, 10));
		builderCuenta.append(Constants.GUION);
		builderCuenta.append(numeroCuenta.subSequence(10, numeroCuenta.length()));
		return builderCuenta.toString();
	}

	public static String getDefaultInt(String value) {
		String retorno = Constants.EMPTY;
		try {
			if (!ValidationUtil.isEmpty(value)) {
				BigDecimal nuevoValor = new BigDecimal(value);
				if (nuevoValor.longValue() > 0) {
					retorno = nuevoValor.toString();
				}
			}
		} catch (Exception e) {
			return Constants.EMPTY;
		}
		return retorno;
	}

	public static String fillNumber(String value) {
		StringBuilder builder = new StringBuilder();
		if (!ValidationUtil.isNumber(value)) {
			return Constants.EMPTY;
		}
		if (ValidationUtil.checkDecimal(value)) {
			String values[] = value.split(Constants.SPLIT_PUNTO);
			builder.append(values[0]);
			if (values.length > 1 && !ValidationUtil.isEmpty(values[1])) {
				String decimal = values[1].trim().substring(0, 2);
				builder.append(Constants.PUNTO);
				builder.append(decimal);
			}
			return builder.toString();
		} else {
			return value.concat("00");
		}
	}

	public static String fillPorcentaje(String value) {
		StringBuilder builder = new StringBuilder();
		if (!ValidationUtil.isNumber(value)) {
			return Constants.EMPTY;
		}
		if (ValidationUtil.checkDecimal(value)) {
			String values[] = value.split(Constants.SPLIT_PUNTO);
			builder.append(values[0]);
			if (values.length > 1 && !ValidationUtil.isEmpty(values[1])) {
				String decimal = values[1].trim().substring(0, 2);
				builder.append(Constants.PUNTO);
				builder.append(decimal);
			}
			return builder.toString();
		} else {
			return value.concat("00");
		}
	}

	public static String splitTextMoneda(String text, int longitud) {
		if (ValidationUtil.isEmpty(text)) {
			return Constants.EMPTY;
		}
		String moneda[] = text.split(Constants.SPLIT_GUION);
		if (longitud <= moneda[1].trim().length()) {
			return moneda[1].substring(0, longitud);
		}
		return moneda[1].substring(0, moneda[1].length());
	}

	public static Object getInstance(String className) {
		Object object = null;
		try {
			Class<?> clazz = Class.forName(className);
			java.lang.reflect.Constructor<?> ctor = clazz.getConstructor();
			object = ctor.newInstance();
		} catch (Exception e) {
			throw new RuntimeException();
		}
		return object;
	}

	public static Properties obtenerProperties(String file) {
		Properties properties = null;
		try {
			properties = new Properties();
			properties.load(OthersUtils.class.getClassLoader().getResourceAsStream(file));
		} catch (Exception e) {
		}
		return properties;
	}
}
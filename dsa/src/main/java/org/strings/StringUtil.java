package org.strings;

public class StringUtil {

	public static void main(String[] args) {
	
		System.out.println("StringUtil");

		/* methods */
		// https://www.w3schools.com/java/java_ref_string.asp
		String txt = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		txt.length();
		txt.toUpperCase();
		txt.toLowerCase();

		// substring: Time Complexity: O(n) n = length of string. O(1) to return the substring.
		txt.substring(1, 10);

		String string = "animals";
		System.out.println(string.substring(3)); // mals
		System.out.println(string.substring(string.indexOf('m'))); // mals
		System.out.println(string.substring(3, 4)); // m
		System.out.println(string.substring(3, 7)); // mals

		txt.trim();
		"\t a b c\n".trim(); // a b c

		txt = "H_R15_TIPOCAMB_20210405.txt";
		txt.replace(".txt", ".TXT");
		"abcabc".replace('a', 'A'); // AbcAbc
		"abcabc".replace("a", "A"); // AbcAbc

		String a = "a";
		int b = 1;
		String msg = String.format("El producto %s viene con %s beneficios", a, b);

		// Compare:
		"abc".equals("ABC"); // false
		"abc".equalsIgnoreCase("ABC"); // true, compare ignore upper and lower

		int bb = "abc".compareTo("ABC"); // bb = 32, compare two string lexicographically

		// Conversion from a String:
		txt = "123";
		int a1 = Integer.parseInt(txt); // String to primitive int
		double a2 = Double.parseDouble(txt); // String to primitive double
		Integer a3 = Integer.valueOf(txt); // String to wrapper class Integer

		// Searching/Finding:
		txt = "Please locate where 'locate' occurs!";
		txt.indexOf("locate"); // 7

		string = "animals";
		System.out.println(string.indexOf('a')); // 0
		System.out.println(string.indexOf("al")); // 4
		System.out.println(string.indexOf('a', 4)); // 4
		System.out.println(string.indexOf("al", 5)); // -1

		txt.lastIndexOf('a');

		// get character at position
		txt.charAt(1); // l

		// last character from string
		txt.charAt(txt.length() - 1);

		"abc".startsWith("A"); // false
		"abc".startsWith("b", 1); // true
		"abc".endsWith("c"); // true

		"abc".contains("B"); // false

		// Concatenation:
		String x = "10";
		int y = 20;
		String z = x + y;  // "1020"

		String firstName = "John ";
		String lastName = "Doe";
		firstName.concat(lastName);

		// String is immutable. StringBuffer/StringBuilder is mutable 
		String s1 = "1";
		String s2 = s1.concat("2");
		s2.concat("3");
		System.out.println(s2); // 12

		/* Special Characters */
		/*
		\'	Single quote
			"It\'s alright." // It's alright.
			
		\"	Double quote
			We are the so-called \"Vikings\" from the north. // We are the so-called "Vikings" from the north.
			
		\\	Backslash
			The character \\ is called backslash. // The character \ is called backslash.
		*/
		
		/* Escape Sequences */
		/*
		\n	New Line	
		\r	Carriage Return	
		\t	Tab	
		\b	Backspace	
		\f	Form Feed
		*/
		
		/***************************************/
		
		/* StringBuilder */
		// StringBuffer/StringBuilder is mutable (changeable)
		
		StringBuilder sb = new StringBuilder("start");
		
		// substring(): does not change the value
		String str = sb.substring(3); // str = rt , sb = start
		
		// append(), delete() and insert(): change the value
		sb.append("+middle"); // sb = "start+middle"
		StringBuilder same = sb.append("+end"); // "start+middle+end"
		sb.append("-").append(true);
		
		StringBuilder sb5 = new StringBuilder("animals");
		sb5.insert(7, "-"); // sb3 = animals-
		sb5.insert(0, "-"); // sb3 = -animals-
		sb5.insert(4, "-"); // sb3 = -ani-mals
		System.out.println(sb5);

		// methods:
		/*
		append()
		insert()
		delete()
		charAt()
		indexOf()
		length()
		substring()
		reverse()
		 */
		
		StringBuilder sb2 = new StringBuilder("abcdef");
		sb2.delete(1, 3); // sb = adef
		//sb2.deleteCharAt(5); // throws StringIndexOutOfBoundsException

		String s = sb2.toString(); // StringBuilder to String
		
		/***************************************/

		/* Equality */
		// ==		: Operator. Reference comparison. Compare values and objects.
		// equals()	: Method. Content Comparison. Compare values.
				
		// String:
		String x1 = new String("lion");
		String y1 = new String("lion");
		System.out.println(x1.equals(y1)); // true
		System.out.println(x1 == y1); // false

		String x2 = "Hello World";
		String z2 = " Hello World".trim();
		System.out.println(x2 == z2); // false
		System.out.println(x2.equals(z2)); // true

		String x3 = new String("Hello World");
		String y3 = "Hello World";
		System.out.println(x3 == y3); // false
		System.out.println(x3.equals(y3)); // true 
		
		// StringBuilder:
		// pass-by-reference
		// the equals() method of the StringBuilder class is not overridden to compare the content of the StringBuilder objects. 
		StringBuilder sb3 = new StringBuilder("lion");
		StringBuilder sb4 = new StringBuilder("lion");
		System.out.println(sb3.equals(sb4)); // false
		System.out.println(sb3 == sb4); // false

		System.out.println(sb3.toString().equals(sb4.toString())); // true
		
		StringBuilder one = new StringBuilder();
		StringBuilder two = new StringBuilder();
		StringBuilder three = one.append("a");
		System.out.println(one == two); // false
		System.out.println(one == three); // true

		/***************************************/

		/* Functions */

		// removeLineBreaks
		System.out.println(removeLineBreaks("AAAAABBBBBCCC\r\nDDDD")); // AAAAABBBBBCCCDDDD

		// addWhiteSpaceRight
		System.out.println(addWhiteSpaceRight("12",6)); // [12    ]

		// addZerosLeft
		System.out.println(addZerosLeft(12, 6)); // 000012

		// rtrim
		System.out.println(rtrim(" TEST B ")); //  TEST B

		// ltrim
		System.out.println(ltrim(" TEST C ")); // TEST C
	}

	public static String removeLineBreaks(String input) {
		if(input == null)
			return null;
		return input.replaceAll("\\r?\\n", ""); // replace all \r\n, \n, and \r with an empty string
	}

	/**
	 * length: length max desired of input
	 */
	public static String addWhiteSpaceRight(String input, int length) {
		if(input == null)
			return null;

		if(input.length() >= length)
			return input;

		StringBuilder sb = new StringBuilder(input);
		while(sb.length() < length) {
			sb.append(' ');
		}
		return sb.toString();
	}

	/**
	 * pad zeros to left
	 */
	public static String addZerosLeft(int number, int length) {
		String numberStr = String.valueOf(number);
		if(numberStr.length() >= length) {
			return numberStr; // No need to add zeros if the number's length is already the desired length or longer
		}

		StringBuilder sb = new StringBuilder();
		while(sb.length() < length - numberStr.length()) {
			sb.append('0'); // Append zeros until the total length is achieved
		}
		sb.append(numberStr);
		return sb.toString();
	}

	public static String rtrim(String input) {
		if(input == null)
			return null;
		int length = input.length();
		while(length > 0 && Character.isWhitespace(input.charAt(length - 1))) {
			length--;
		}
		return input.substring(0, length);
	}

	public static String ltrim(String input) {
		if(input == null)
			return null;
		int length = input.length();
		int start = 0;
		while(start < length && Character.isWhitespace(input.charAt(start))) {
			start++;
		}
		return input.substring(start);
	}
}

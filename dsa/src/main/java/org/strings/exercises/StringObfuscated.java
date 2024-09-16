package org.strings.exercises;

public class StringObfuscated {
    
	/*
	 * Java 8: Create a new string of asterisks with the same length as the input string
	 */
    public static String obfuscate(String input) {
        char[] obfuscatedArray = new char[input.length()];
        java.util.Arrays.fill(obfuscatedArray, '*');
        return new String(obfuscatedArray);
    }
    
	/*
	 * Java 11: Create a new string of asterisks with the same length as the input string
	 */
//    public static String obfuscate2(String input) {
//        return "*".repeat(input.length());
//    }
    
    public static void main(String args[])
    {
        String original = "HelloWorld";
        String obfuscated = obfuscate(original);

        System.out.println("Original: " + original);
        System.out.println("Obfuscated: " + obfuscated);
    }
}
// https://www.w3schools.com/java/java_ref_math.asp

package org;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.UUID;

public class MathUtil {
	
	public static void main(String[] args) {

		String tmp;

		/* methods */
		Math.max(5, 10); // 10
		Math.min(5, 10); // 5
		Math.sqrt(64); // 8.0
		Math.abs(-4.7); // 4.7

		// random
		Math.random(); // return a random number between 0.0 (inclusive) and 1.0 (exclusive)

		// random number between 0 and 100
		int randomNum = (int)(Math.random() * 101);

		// number between 0 and 9
		// import java.util.Random;
		Random r = new Random();
		System.out.println(r.nextInt(10));

		// generate random id string
		UUID.randomUUID().toString();
		
		/* print */
		// 2 decimals
		System.out.printf("%1.2f", Double.parseDouble("1234.5678")); // %1.2f expects float or double
																		// 1234.57
		/**************************************************/

		// BigDecimal to String
		BigDecimal numBigDecimal = new BigDecimal("12345.6789");
		System.out.println(numBigDecimal.toString()); // 12345.6789
		System.out.println(String.valueOf(numBigDecimal)); // 12345.6789

		// BigDecimal to String for Custom Formatting
		DecimalFormat df = new DecimalFormat("#,###.00");
		tmp = df.format(numBigDecimal);
		System.out.println(tmp);  // 12,345.68
	}
}

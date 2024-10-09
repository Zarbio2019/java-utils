/*
 * https://www.w3schools.com/java/java_lambda.asp
 */

package org.functionalProgramming;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public class LambdaUtil {
	
	public static void main(String[] args) {

		/* Lambda Expression */
		/*
		parameter -> expression

		(parameter1, parameter2) -> expression // expression cannot contain variables, assignments or statements such as if or for

		(parameter1, parameter2) -> { code block } // for complex operations
		
		ex:
			a -> a.canHop()
			
			(Animal a) -> { return a.canHop(); }
		*/
		
		// sample: for ArrayList
		// import java.util.ArrayList;
		
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		numbers.add(5);
		numbers.add(9);
		numbers.add(8);
		numbers.add(1);
		numbers.forEach(n -> System.out.println(n));
		
		// to print even elements
		numbers.forEach(n -> {
			if(n%2 == 0)
				System.out.println(n);
		});

		/***************************************/

		/* Functional Interface */

		List<Integer> numbersList = Arrays.asList(12, 9, 13, 4, 6, 2, 4, 12, 15); // Java 8
//		List<Integer> numbersList = List.of(12, 9, 13, 4, 6, 2, 4, 12, 15); // Java 9
		
		numbersList.stream()
			   .filter(x -> x%2==0) // Predicate
			   .map(x -> x * x) // Function
			   .forEach(System.out::println); // Consumer
		
		Predicate<Integer> isEvenPredicate = x -> x%2==0;
			// is the condition, to test, one input, one output boolean, return true/false
			// boolean test(T t);
		
		Function<Integer, Integer> squareFunction = x -> x * x;
			// to process, input and output
			// R apply(T t);
		
		Consumer<Integer> sysoutConsumer = System.out::println;
			// to consume, don't return anything
			// void accept(T t);
		
			// sample:
		numbersList.stream()
				   .filter(isEvenPredicate)
				   .map(squareFunction)
				   .forEach(sysoutConsumer);

		/* Consumer */
		
		// sample: for Consumer interface to store a lambda expression in a variable
		// import java.util.ArrayList;
		// import java.util.function.Consumer;
		ArrayList<Integer> numbers2 = new ArrayList<Integer>();
		numbers2.add(5);
		numbers2.add(9);
		numbers2.add(8);
		numbers2.add(1);
		
		Consumer<Integer> method = (n) -> { System.out.println(n); };
		numbers2.forEach(method);
		
		/* Predicate */
		// Lambdas work with interfaces that have only one method.
		/*
		public interface Predicate<T> {
			boolean test(T t);
		}
		*/
		
		/* Supplier */
		// No input, return something
		// used in factory pattern
		
			// sample:
		// Supplier<Integer> randomIntegerSupllier = () -> 2;
		
			// sample: return random number
		Supplier<Integer> randomIntegerSupllier = () -> {
			Random random = new Random();
			return random.nextInt(1000);
		};

		System.out.println(randomIntegerSupllier.get());
		
		/* BinaryOperator */
		BinaryOperator<Integer> sumBinaryOperator = (x, y) -> x + y;

		/* UnaryOperator */
		// one input, one output
		UnaryOperator<Integer> unaryOperator = (x) -> 3 * x;
		System.out.println(unaryOperator.apply(10));
		
		/* BiPredicate */
		// two inputs, one output boolean
		
			// sample:
		BiPredicate<Integer, String> biPredicate = (number,str) -> {
			return number<10 && str.length()>5;
		};
		System.out.println(biPredicate.test(15, "in28minutes")); // false
			
		/* BiFunction */
		// two inputs, one output

			// sample:
		BiFunction<Integer, String, String> biFunction = (number,str) -> {
			return number + " " + str; // return String
		};
		System.out.println(biFunction.apply(15, "in28minutes")); // "15 in28minutes"

		/* BiConsumer */
		// two inputs, don't return anything
		
			// sample:
		BiConsumer<Integer, String> biConsumer = (s1,s2) -> {
			System.out.println(s1);
			System.out.println(s2);
		};
		biConsumer.accept(25, "in28Minutes");
		
		/* Primitives Functional Interfaces */
		// is better to use primitive in lambda expressions
		/*
		IntBinaryOperator
		IntConsumer
		IntFunction
		IntPredicate
		IntSupplier
		IntToDoubleFunction
		IntToLongFunction
		IntUnaryOperator
		 */
			// sample:
		IntBinaryOperator intBinaryOperator = (x,y) -> x + y;
		
		/* Comparator */
		// List, Set, Map for sorting using compare()
		// https://www.geeksforgeeks.org/java-lambda-expression-with-collections/?ref=lbp
			
		// For ArrayList
		ArrayList<Integer> al = new ArrayList<Integer>();
        al.add(205);
        al.add(102);
        al.add(98);
        al.add(275);
        al.add(203);
        System.out.println("Elements of the ArrayList " + 
                              "before sorting : " + al);
 
        // using lambda expression in place of comparator object
        Collections.sort(al, (o1, o2) -> (o1 > o2) ? -1 :
                                         (o1 < o2) ? 1 : 0);
 
        System.out.println("Elements of the ArrayList after" + 
                           " sorting : " + al);
        
        /* Behaviour Parameterization */
        /*
        Predicate<? super Integer> evenPredicate = x -> x%2==0;
        numbers.stream()
        .filter(evenPredicate)
        .forEach(System.out::println);
         */
		
        filterAndPrint(numbers, x -> x%2==0);
        
        filterAndPrint(numbers, x -> x%2!=0);
        
        	// sample: Do Behaviour Parameterization for the mapping logic.
        /*
        List<Integer> squaredNumbers = numbers.stream()
        									  .map(x -> x*x)
        									  .collect(Collectors.toList()
         */
        // Function<Integer, Integer> mappingFunction = x -> x*x;
        System.out.println("sample: Do Behaviour Parameterization for the mapping logic.");
        List<Integer> squaredNumbers = mapAndCreateNewList(numbers, x -> x*x);
 
        List<Integer> cubedNumbers = mapAndCreateNewList(numbers, x -> x*x*x);

        List<Integer> doubledNumbers = mapAndCreateNewList(numbers, x -> x + x);

        System.out.println(doubledNumbers);  
	}

	private static List<Integer> mapAndCreateNewList(ArrayList<Integer> numbers,
			Function<Integer, Integer> mappingFunction) {
		return numbers.stream()
        			  .map(mappingFunction)
        			  .collect(Collectors.toList());
	}

	private static void filterAndPrint(ArrayList<Integer> numbers, Predicate<? super Integer> predicate) {
		numbers.stream()
        .filter(predicate)
        .forEach(System.out::println);
	}
}

/* others samples */
// sample: Create a method which takes a lambda expression as a parameter:
/*
interface StringFunction {
	String run(String str);
}

public class Main {
	public static void main(String[] args) {
		StringFunction exclaim = (s) -> s + "!";
		StringFunction ask = (s) -> s + "?";
		printFormatted("Hello", exclaim);
		printFormatted("Hello", ask);
	}
	
	public static void printFormatted(String str, StringFunction format) {
		String result = format.run(str);
		System.out.println(result);
	}
}
*/
/* output:
Hello!
Hello?
 */

// sample:
/*
interface ITest {
	void f1();
}

public class MainTest{
	
	public static void main(String[] args) {
		ITest a = () -> System.out.println("hello");
		a.f1();
	}
}
*/
/* output:
hello
 */

// sample:
/*
interface ITest {
	boolean fun(int n);
}

public class MainTest{
	
	public static void main(String[] args) {
		ITest a = (n) -> (n % 2) == 0;

        if (a.fun(21))
            System.out.println("21 is even");
        else
            System.out.println("21 is odd");
	}
}
*/
/* output:
hello
 */

/* Tips:
Refactor, Extract Local Variable

Refactor, Extract Method

Refactor, Inline...
*/
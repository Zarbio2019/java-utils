/* refs:
https://medium.com/@mehar.chand.cloud/java-stream-coding-interview-questions-part-1-dc39e3575727
https://testbook.com/interview/java-stream-api-interview-questions
https://www.naukri.com/code360/library/stream-api-interview-questions
*/
package org.functionalProgramming;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import org.MainTest;

public class StreamsUtil {

	public static void main(String[] args) {
		
		System.out.println("StreamUtil");
		
		/* Stream.class */
		// Streams API Java 8
		// package java.util.stream;
		// public interface Stream<T> extends BaseStream<T, Stream<T>>
		
		// static methods:
		/*
		builder
		empty
		of
		ofNullable
		iterate
		generate
		concat
		 */

		// create a stream
		Stream<String> stream1 = Stream.empty();
		Stream<Integer> stream2 = Stream.of(1);
		Stream<Integer> stream3 = Stream.of(1,2,3);
		
		// generate a stream of random numbers
		Stream<Double> randoms = Stream.generate(Math::random); // returns an infinite sequential unordered stream
		//randoms.forEach(System.out::println);
		
		// print a stream
		stream3.forEach(x -> System.out.println(x));
		/* output:
		1
		2
		3
		 */
		// or:
		//stream3.forEach(System.out::println);

		// convert array to list, then list to stream
		List<String> list = Arrays.asList("a", "b", "c");
		Stream<String> stream4 = list.stream(); // list to stream
		Stream<String> stream5 = list.parallelStream(); // process elements in parallel (better)
		
		// convert stream to list
        Stream<String> stream = Stream.of("Hello", "world", "!");
        List<String> list1 = stream.collect(Collectors.toList());
        System.out.println("list1: " + list1);
        
        // convert List into Set
        List<Product> productsList = new ArrayList<>();  
        productsList.add(new Product(1,"HP Laptop",25000f));  
        productsList.add(new Product(2,"Dell Laptop",30000f));  
        productsList.add(new Product(3,"Lenevo Laptop",28000f));  
        productsList.add(new Product(4,"Sony Laptop",28000f));  
        productsList.add(new Product(5,"Apple Laptop",90000f));  

        Set<Float> productPriceSet = productsList.stream()  
            .filter(product->product.price < 30000) // filter product on the base of price  
            .map(product->product.price)  
            .collect(Collectors.toSet()); // collect it as Set(remove duplicate elements)
        System.out.println(productPriceSet); // output: [25000.0, 28000.0]
        
        // convert List into Map
        Map<Integer, String> productPriceMap = productsList.stream()
        		.collect(Collectors.toMap(p->p.id, p->p.name));  
        System.out.println(productPriceMap);
        // output: {1=HP Laptop, 2=Dell Laptop, 3=Lenevo Laptop, 4=Sony Laptop, 5=Apple Laptop}
            
        // using method reference (::)
        List<Float> productPriceList = productsList.stream()  
                            .filter(p -> p.price > 30000) // filtering data  
                            //.map(x -> x.getPrice()) // with lambda
                            .map(Product::getPrice) // fetching price by referring getPrice method  
                            .collect(Collectors.toList()); // collecting as list  
        System.out.println(productPriceList); // output: [90000.0]
        
		// iterate (Java 9)
		// create a stream of odd numbers 
		Stream<Integer> stream6 = Stream.iterate(1, n->n+2);
		//stream6.forEach(System.out::println); // 1 3 5 ...
		
		// concatenate streams
        Stream<Integer> stream8 = Stream.of(1, 2, 3);
        Stream<Integer> stream9 = Stream.of(4, 5, 6);

        Stream<Integer> concatenatedStream = Stream.concat(stream8, stream9);
        concatenatedStream.forEach(System.out::println);
        /* output:
        1
		2
		3
		4
		5
		6
         */
        
        // ofNullable (Java 9)
        
        // Stream of Primitives
        /*
        IntStream
        LongStream
        DoubleStream
        */
        IntStream intStream = IntStream.range(1, 3); // [1, 3>
        LongStream longStream = LongStream.rangeClosed(1, 3); // [1, 3]
        intStream.forEach(System.out::println); // 1 2
        longStream.forEach(System.out::println); // 1 2 3
        
        Random random = new Random();
        DoubleStream doubleStream = random.doubles(3); // 3 random values
        doubleStream.forEach(System.out::println); 

		/***************************************/
		
		/* Intermediate Operations */
		// operations that return stream (Stream<T>).
		/*
		filter
		distinct
		limit
		skip
		takeWhile
		dropWhile
		map
		mapToInt, mapToLong, mapToDouble
		flatMap
		sorted
		peek
		 */
		
		// filter()
		// returns a Stream with elements that match a given expression
		// Stream<T> filter(Predicate<? super T> predicate)
		Stream<String> m = Stream.of("monkey", "gorilla", "bonobo");
		m.filter(x -> x.startsWith("m"))
		 .forEach(System.out::println); // monkey
		
		// distinct()
		// returns a stream with duplicated values removed
		// Stream<T> distinct()
		Stream<String> m2 = Stream.of("duck", "duck", "duck", "goose");
		m2.distinct()
		  .forEach(System.out::print); // duckgoose
		
		// limit() and skip()
		// make a Stream smaller.
		// Stream<T> limit(int maxSize)
		// Stream<T> skip(int n)
		Stream<Integer> m3 = Stream.iterate(1, n -> n+1); // from 1 to infinite
		m3.skip(5) // skips the first 5 elements
		  .limit(2) // takes the first 2 of those
		  .forEach(System.out::print); // 67

		// Java 8
		List<Course> courses = Arrays.asList(new Course("Spring", "Framework", 98, 20000),
				new Course("Spring Boot", "Framework", 95, 18000),
				new Course("API", "Microservices", 90, 10000),
				new Course("FullStack", "FullStack", 96, 14000));

		/**
		// takeWhile() (Java 9)
		// take the elements until break the criteria
		List<Course> courses = List.of(new Course("Spring", "Framework", 98, 20000),
				new Course("Spring Boot", "Framework", 95, 18000),
				new Course("API", "Microservices", 90, 10000),
				new Course("FullStack", "FullStack", 96, 14000));

		System.out.println(
				courses.stream()
					.takeWhile(course -> course.getReviewScore()>=95)
					.collect(Collectors.toList())); // [Spring:20000:98, Spring Boot:18000:95]
		
		// dropWhile() (Java 9)
		// take the elements while skip the criteria
		System.out.println(
				courses.stream()
					   .dropWhile(course -> course.getReviewScore()>=95)
					   .collect(Collectors.toList())); // [API:10000:90, FullStack:14000:96]
 */

		// map()
		// transform data
		// <R> Stream<R> map(Function<? super T, ? extends R> mapper)
		Stream<String> m4 = Stream.of("monkey", "gorilla", "bonobo");
		m4.map(String::length)
		  .forEach(System.out::print); // 676
		
		// flatMap()
		// takes each element in the stream and makes them in a single stream.
		// used: when you want to remove empty elements from a stream or you want to combine a stream of lists.
		// <R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper)
		
			// convert Stream<List<String>> into Stream<String>
		List<String> zero = Arrays.asList(); // empty list
		List<String> one = Arrays.asList("Bonobo");
		List<String> two = Arrays.asList("Mama Gorilla", "Baby Gorilla");
		Stream<List<String>> animals = Stream.of(zero, one, two);
		animals.flatMap(l -> l.stream())
		       .forEach(System.out::println);
		/* output:
		Bonobo
		Mama Gorilla
		Baby Gorilla
		*/
		
			// convert Stream<List<String>> into Stream<String>
	    List<List<String>> namesNested = Arrays.asList( 
	    	      Arrays.asList("Jeff", "Bezos"), 
	    	      Arrays.asList("Bill", "Gates"), 
	    	      Arrays.asList("Mark", "Zuckerberg"));

	    List<String> namesFlatStream = namesNested.stream()
	    	      .flatMap(Collection::stream)
	    	      .collect(Collectors.toList());
		
		// sorted()
		// Stream<T> sorted()
		// Stream<T> sorted(Comparator<? super T> comparator)
		Stream<String> m5 = Stream.of("brown-", "bear-");
		m5.sorted().forEach(System.out::print); // bear-brown-
		//m5.sorted(Comparator.naturalOrder()).forEach(System.out::print);
			
			// sort numbers increasing order
		System.out.println("sort numbers increasing order");
		List<Integer> m51 = Arrays.asList(2,7,3,4); // Java 8
		// List<Integer> m51 = List.of(2,7,3,4); // Java 9
		m51.stream().distinct().sorted().forEach(System.out::println);
		/* output:
		2
		3
		4
		7
		 */
		
			// sort numbers decreasing order
		System.out.println("sort numbers decreasing order");
		m51.stream().distinct().sorted(Comparator.reverseOrder())
			.forEach(System.out::println);
		/* output:
		7
		4
		3
		2
		 */
        
			//  reverse order for string
		Stream<String> m6 = Stream.of("brown bear-", "grizzly-");
		m6.sorted(Comparator.reverseOrder())
		  .forEach(System.out::print); // grizzly-brown bear-
		
			// compare by length string
		Stream<String> m61 = Stream.of("brown bear-", "grizzly-");
		m61.sorted(Comparator.comparing(str -> str.length()))
		  .forEach(System.out::print); // grizzly-brown bear-
		
			// increase sorting by No of students
		Comparator<Course> comparingByNoOfStudentsIncreasing = Comparator.comparing(Course::getNoOfStudents);
		System.out.println(courses.stream().sorted(comparingByNoOfStudentsIncreasing).collect(Collectors.toList()));
			// output: [API:10000:90, FullStack:14000:96, Spring Boot:18000:95, Spring:20000:98]

			// filter reviewScore > 95 and increase sorting by No of students
		System.out.println(courses.stream()
				.filter(rs -> rs.getReviewScore() > 95)
				.sorted(Comparator.comparing(Course::getNoOfStudents))
				.collect(Collectors.toList()));
			// output: [FullStack:14000:96, Spring:20000:98]

			// decrease sorting by No of students
		Comparator<Course> comparingByNoOfStudentsDecreasing = Comparator.comparing(Course::getNoOfStudents).reversed();
		System.out.println(courses.stream().sorted(comparingByNoOfStudentsDecreasing).collect(Collectors.toList()));
			// output: [Spring:20000:98, Spring Boot:18000:95, FullStack:14000:96, API:10000:90]

			// decrease sorting by No of students and No of reviews
			// comparingInt: use primitive type is better
		Comparator<Course> comparingByNoOfStudentsAndNoOfReviews = Comparator.comparingInt(Course::getNoOfStudents)
						.thenComparingInt(Course::getReviewScore)
						.reversed();
		System.out.println(
				courses.stream()
				.sorted(comparingByNoOfStudentsAndNoOfReviews)
				.collect(Collectors.toList()));
			// output: [Spring:20000:98, Spring Boot:18000:95, FullStack:14000:96, API:10000:90]
		
		// peek()
		// useful for debugging because it allows us to perform a stream operation without actually changing the stream
		// Stream<T> peek(Consumer<? super T> action)
		Stream<String> m7 = Stream.of("black bear", "brown bear", "grizzly");
		long count = m7.filter(s7 -> s7.startsWith("g"))
						   .peek(System.out::println)
						   .count(); // here print: grizzly
		System.out.println(count); // 1
		
		/***************************************/

		/* Terminal Operations */
		// produce a result.
		/*
		anyMatch
		allMatch
		noneMatch
		min
		max
		findAny
		findFirst
		count
		sum
		average
		forEach
		forEachOrdered
		reduce
		collect
		 */
		
		// anyMatch, allMatch, noneMatch
		// used: search, query operations.
		// boolean anyMatch(Predicate <? super T> predicate)
		// boolean allMatch(Predicate <? super T> predicate)
		// boolean noneMatch(Predicate <? super T> predicate)
		
			// sample:
		List<String> list2 = Arrays.asList("monkey", "2", "chimp");
		Stream<String> infinite2 = Stream.generate(() -> "chimp");
		Predicate<String> pred = x -> Character.isLetter(x.charAt(0)); // filter by first character is letter
		System.out.println(list2.stream().anyMatch(pred)); // true, any character start with letter
		System.out.println(list2.stream().allMatch(pred)); // false, all start with letter
		System.out.println(list2.stream().noneMatch(pred)); // false, none start with letter
		System.out.println(infinite2.anyMatch(pred)); // true, compare stream and list
		
			// sample:		
		Predicate<Course> reviewScoreGreaterThan95Predicate = course -> course.getReviewScore() > 95;
			
		Predicate<Course> reviewScoreGreaterThan90Predicate	= course -> course.getReviewScore() > 90;
			
		Predicate<Course> reviewScoreLessThan90Predicate = course -> course.getReviewScore() < 90;
			
		System.out.println(courses.stream().allMatch(reviewScoreGreaterThan95Predicate)); // false
		System.out.println(courses.stream().noneMatch(reviewScoreGreaterThan90Predicate)); // false
		System.out.println(courses.stream().anyMatch(reviewScoreLessThan90Predicate)); // false
		System.out.println(courses.stream().anyMatch(reviewScoreGreaterThan95Predicate)); // true
			
		// min() and max()
		// Optional<T> min(<? super T> comparator)
		// Optional<T> max(<? super T> comparator)
			
			// look for min length
		Stream<String> st = Stream.of("monkey", "ape", "bonobo");
		Optional<String> min = st.min((s1, s2) -> s1.length()-s2.length());
		min.ifPresent(System.out::println); // ape
		
			// look for max value of decreasing sorting by No of students and No of reviews
		System.out.println(
				courses.stream()
				.max(comparingByNoOfStudentsAndNoOfReviews)); // output: Optional[API:10000:90]
		
			// look for max value of course.getReviewScore() > 95
		System.out.println(
				courses.stream()
				.filter(reviewScoreGreaterThan95Predicate)
				.mapToInt(Course::getNoOfStudents)
				.max()); // output: OptionalInt[20000]
	
			// look for min value of decreasing sorting by No of students and No of reviews
			// and return if doesn't exist.
		System.out.println(
				courses.stream()
				.filter(reviewScoreLessThan90Predicate)
				.min(comparingByNoOfStudentsAndNoOfReviews) // return: Optional.empty
				.orElse(new Course("Kubernetes", "Cloud", 91, 20000))); // if not found (empty), then return this, Kubernetes:20000:91
	
		// findAny() and findFirst(): return Optional
		// Optional<T> findAny()
		// Optional<T> findFirst()
		
			// sample:
		Stream<String> s2 = Stream.of("monkey", "gorilla", "bonobo");
		Stream<String> infinite = Stream.generate(() -> "chimp");
		s2.findAny().ifPresent(System.out::println); // monkey
		infinite.findAny().ifPresent(System.out::println); // chimp
		
			// sample:
		System.out.println(
				courses.stream()
				.filter(reviewScoreGreaterThan95Predicate)
				.findFirst()
				); // Optional[Spring:20000:98]

			// sample:
		System.out.println(
				courses.stream()
				.filter(reviewScoreGreaterThan95Predicate)
				.findAny()
				); // Optional[Spring:20000:98]
	
		// count()
		Stream<String> s = Stream.of("monkey", "gorilla", "bonobo");
		System.out.println(s.count()); // 3
		
			// sample:
		System.out.println(
				courses.stream()
				.filter(reviewScoreGreaterThan95Predicate)
				.mapToInt(Course::getNoOfStudents)
				.count()); // output: 2
		
		// sum()
		
			// sample:
		System.out.println(
				courses.stream()
				.filter(reviewScoreGreaterThan95Predicate)
				.mapToInt(Course::getNoOfStudents)
				.sum()); // output: 34000
	
		// average()
		
			// get average of noOfStudents filtering by course.getReviewScore() > 95
		System.out.println(
				courses.stream()
				.filter(reviewScoreGreaterThan95Predicate)
				.mapToInt(Course::getNoOfStudents)
				.average()); // OptionalDouble[17000.0]
	
		// forEach()
		// used: print output, store to database
		Stream<String> s3 = Stream.of("Monkey", "Gorilla", "Bonobo");
		s3.forEach(System.out::print); // MonkeyGorillaBonobo
		
		//Stream s4 = Stream.of(1);
		//for (Integer i: s4 ) {} // DOES NOT COMPILE
		
		// reduce()
		// combine a stream into a single object
		// used: concatenation, sum, average, accumulate elements
		
			// string concatenation without reduce()
		String[] array = new String[] { "w", "o", "l", "f" };
		String result = "";
		for (String x: array) result = result + x;
			System.out.println(result); // wolf
		
			// string concatenation with reduce()
		Stream<String> stream7 = Stream.of("w", "o", "l", "f");
		String word = stream7.reduce("", (s1, c1) -> s1+c1);
		// or:
		// word = stream3.reduce("", String::concat);
		System.out.println(word); // wolf

			// sum of elements
		System.out.println("addListStructured");
		List<Integer> numbers = Arrays.asList(12, 9, 13, 4, 6, 2, 4, 12, 15); // Java 8
		// List<Integer> numbers = List.of(12, 9, 13, 4, 6, 2, 4, 12, 15); // Java 9
		int sum = addListStructured(numbers); // return sum, Structured approach
		System.out.println(sum); // output: 77
		
		System.out.println("addListFunctional");
		sum = addListFunctional(numbers); // return sum, Functional approach
		System.out.println(sum); // output: 77
		
		sum = numbers.stream().reduce(0, (x,y)->x+y); // sum: x=aggregate, y=next number
		// output: 77

			// samples:
		sum = numbers.stream().reduce(0, (x,y)->x); // output: 0 (don't sum)

		sum = numbers.stream().reduce(0, (x,y)->y); // output: 15 (last value)

		sum = numbers.stream().reduce(0, (x,y)->x>y?x:y); // output: 15, max value (for positive numbers)
		
		sum = numbers.stream().reduce(Integer.MIN_VALUE, (x,y) -> x>y?x:y); // output: 15, max value (for negative/positive numbers)

		sum = numbers.stream().reduce(Integer.MAX_VALUE, (x,y) -> x>y?y:x); // output: 2, min value (for negative/positive numbers)

			// square every number in a list and find the sum of squares
		List<Integer> numbers2 = Arrays.asList(2,4, 5); // Java 8
		// List<Integer> numbers2 = List.of(2,4, 5); // Java 9
		int sum2 = numbers2.stream()
			   .map(x -> x * x)
			   .reduce(0, Integer::sum);
		System.out.println("sum of squares: " + sum2); // output: sum of squares: 45
		
			// find sum of even numbers in a list
		sum2 = numbers.stream()
					  .filter(x -> x%2==0)
					  .reduce(0, Integer::sum); // output: sum2 = 40
		
		// collect()
		// create from a stream into another object (list, map, set).
		// used: to group elements to a collection
	
			// create list of square values
		System.out.println("sample: create list of square values");
		List<Integer> createIntegerList = numbers.stream()
			   .map(number -> number * number)
			   .collect(Collectors.toList());
		System.out.println("createIntegerList: " + createIntegerList); // output: createIntegerList: [144, 81, 169, 16, 36, 4, 16, 144, 225]
		
			// create a list with even numbers filtered from the numbers list
		System.out.println("sample: create a list with even numbers filtered from the numbers list");
		numbers.stream()
			   .filter(x -> x%2==0)
			   .collect(Collectors.toList())
			   .forEach(System.out::println); // output: 12 4 6 2 4 12
		
			// create a list with lengths of all course titles
		System.out.println("sample: create a list with lengths of all course titles");
		List<String> courses2 = Arrays.asList("Spring", "Spring Boot", "API", "Microservices", "AWS", "PCF", "Azure", "Docker", "Kubernetes"); // Java 8
		// List<String> courses2 = List.of("Spring", "Spring Boot", "API", "Microservices", "AWS", "PCF", "Azure", "Docker", "Kubernetes"); // Java 9
		List<Integer> createStrList = courses2.stream()
			   .map(x -> x.length())
			   .collect(Collectors.toList());
		System.out.println("createStrList: " + createStrList); // output: createStrList: [6, 11, 3, 13, 3, 3, 5, 6, 10]
		
		/***************************************/
		
		/* Short-Circuiting Operations */
		// operations that produce finite stream for an infinite stream.
		
		// of Intermediate Operations:
		/*
		skip
		limit
		*/
		
		// of Terminal Operations:
		/*
		anyMatch
		allMatch
		noneMatch
		findFirst
		findAny
		*/
		
		/***************************************/

		/* Group */
		
			// group by Category
		System.out.println(
				courses.stream()
				.collect(Collectors.groupingBy(Course::getCategory)));
		/* output:
		{FullStack=[FullStack:14000:96],
		Microservices=[API:10000:90],
		Framework=[Spring:20000:98, Spring Boot:18000:95]}
		 */
		
			// group by category and count them
		System.out.println(
				courses.stream()
				.collect(Collectors.groupingBy(Course::getCategory, Collectors.counting())));
		/* output:
		{FullStack=1, Microservices=2, Framework=2}
		 */
		
			// group by category and get the max reviewScore
		System.out.println(
				courses.stream()
				.collect(Collectors.groupingBy(Course::getCategory,
						 Collectors.maxBy(Comparator.comparing(Course::getReviewScore)))));
		/* output:
		{FullStack=Optional[FullStack:14000:96], Microservices=Optional[API:10000:90], Framework=Optional[Spring:20000:98]}
		 */	
	
			// group by category and get list by names
		System.out.println(
				courses.stream()
				.collect(Collectors.groupingBy(Course::getCategory,
						 Collectors.mapping(Course::getName, Collectors.toList()))));
		/* output:
		{FullStack=[FullStack], Microservices=[API], Framework=[Spring, Spring Boot]}
		 */
		
		/***************************************/
		
		/* Parallelization */
		// is better for Stream
		
		/* methods */
		/*
		parallel
		parallelStream
		 */
		
		// parallel()
		// used: if the source of a stream is something other than a Collection or an array.
		IntStream intStreamParallel = IntStream.range(1, 150).parallel(); // [1, 150>
		boolean isParallel = intStreamParallel.isParallel(); // true
		
			// sum with LongStream.parallel()
		long time = System.currentTimeMillis();
		
		//System.out.println(LongStream.range(0, 10000000).sum()); // 4999999999
		System.out.println(LongStream.range(0, 10000000).parallel().sum()); // 49999995000000
		System.out.println(System.currentTimeMillis() - time); // get time in ms
		
			// sum with Streams.parallel()
		sum = numbers.stream()
			.parallel()
			//.reduce(0, StreamsUtil::sum);
			//.reduce(0, (x,y) -> x + y);
			.reduce(0, Integer::sum);
		System.out.println("sum: " + sum); // output: sum: 77
	
		// parallelStream()
		// used: if the source of a stream is a Collection or an array.
		// to convert Collection or an array into Stream
		
			// create a Stream
		List<String> list3 = Arrays.asList("a", "b", "c");
		Stream<String> stream10 = list3.parallelStream(); // process elements in parallel (better)
		
			// convert to parallelStream() from a Stream
		List<Product> productList = Arrays.asList(new Product(23, "potatoes"),
				  new Product(14, "orange"), new Product(13, "lemon"),
				  new Product(23, "bread"), new Product(13, "sugar"));
		
		Stream<Product> streamOfCollection = productList.parallelStream();
		boolean isParallel2 = streamOfCollection.isParallel(); // true
		boolean bigPrice = streamOfCollection
		  .map(product -> product.getPrice() * 12)
		  .anyMatch(price -> price > 200); // false
		
		// sequential()
		// from parallel mode convert back to sequential mode
		IntStream intStreamSequential = intStreamParallel.sequential();
		boolean isParallel3 = intStreamSequential.isParallel(); // false
		
		/***************************************/
		
		/* File Operation */
		
		/* File Read Operation */
        String fileName = "StreamsReadFile.txt"; 
	    //String fileName = "C:\\file.txt"; 
        
        // Create a Stream of lines from the file 
        try (Stream<String> lines = Files.lines(Paths.get("temp", fileName))) { 
        	// Stream<String> lines = Files.lines(Paths.get(fileName), Charset.forName("UTF-8"))
            List<String> filteredStrings = filterAndConvertToUpper(lines, 5);
            
            System.out.println( 
                "Filtered strings with length 5 (converted to uppercase): "
                + filteredStrings); 
        } 
        catch (IOException e) { 
            e.printStackTrace(); 
        }
        /* input from file:
		Geeks
		gfg
		geeks
		geeksforgeeks
		Coder
		Guys
		*/
        /* output:
		Filtered strings with length 5 (converted to uppercase): [GEEKS, GEEKS, CODER]
         */

        /* File Write Operation */
        String[] words = { "Geeks", "for", "Geeks", "Hello", "World" }; 

	    fileName = "temp\\StreamsWriteFile.txt";
	    
	    // Create a PrintWriter to write to the file 
	    try (PrintWriter pw = new PrintWriter(Files.newBufferedWriter(Paths.get(fileName)))) { 
	
	        // Use Stream to write each word to the file 
	        Stream.of(words).forEach(pw::println); 
	
	        // Print success message to the console 
	        System.out.println("Words written to the file successfully."); 
	    } 
	    catch (IOException e) { 
	        // Handle any IO exception that occurs during the file writing process 
	        e.printStackTrace(); 
	    }
	    /* output:
	    Words written to the file successfully.
	     */
    
		/***************************************/
		
		/* Exception Handling */
		
		// 1. throws a checked exception from a lambda directly
		// convert this:
		/*
		myList.stream()
		  .map(item -> {
		    try {
		      return doSomething(item);
		    } catch (MyException e) {
		      throw new RuntimeException(e);
		    }
		  })
		  .forEach(System.out::println);
		*/
	    
		// to this:
		// handle the exception within a method to make the code more readable.
		/*
		myList.stream()
		  .map(this::trySomething)
		  .forEach(System.out::println);


		private Item trySomething(Item item) {
		  try {
		    return doSomething(item);
		  } catch (MyException e) {
		    throw new RuntimeException(e);
		  }
		}
		*/
		
		// 2. handle exception in a functional way
		// using Scala library (Try)
		
		/*
		refs:
		Aggregate Runtime Exceptions in Java Streams
		https://www.baeldung.com/java-streams-aggregate-exceptions
		 */
	    
		/***************************************/
		
	    /* Exceptions */
	    
	    // 1. Exception in thread "main" java.lang.IllegalStateException: stream has already been operated upon or closed.
		/*
		cause:
		occurs when you attempt to use a stream after it has been consumed or closed.
	    streams are designed to be used once.
		Once a terminal operation is performed on a stream (such as forEach, collect, reduce, etc.),
	    the stream is considered consumed and cannot be reused.
		 */
		
		// sample:
		//stream3.forEach(x -> System.out.println(x));
		//stream3.forEach(System.out::println);
		
		// sample:
		/*
		Stream<String> stringStream = Stream.of("A", "B", "C", "D");
		Optional<String> result1 = stringStream.findAny();
		System.out.println(result1.get());
		Optional<String> result2 = stringStream.findFirst();
		*/
		// solution:
		Supplier<Stream<String>> streamSupplier = () -> Stream.of("A", "B", "C", "D");
		
		Optional<String> result1 = streamSupplier.get().findAny();
		System.out.println(result1.get()); // A
		
		Optional<String> result2 = streamSupplier.get().findFirst();
		System.out.println(result2.get()); // A
		
		/***************************************/
		
		/* Exercises */
		
		// Find the longest string in a list of string using Java streams
		List<String> strings = Arrays.asList("apple", "banana", "cherry", "date", "grapefruit");
		Optional<String> longestString = strings.stream()
												.max(Comparator.comparingInt(String::length));
		System.out.println("longestString: " + longestString.get());
		
		//---------------------------------------
		
		// Calculate the average age of a list of Person objects using Java streams
		List<Person> persons = Arrays.asList(
								new Person("Alice", 25),
								new Person("Bob", 30),
								new Person("Charlie", 35)
								);
		
		double averageAge = persons.stream()
							  	   .mapToInt(Person::getAge)
							  	   .average()
							  	   .orElse(0);
		
		//---------------------------------------
		
		// Check if a list of integers contains a prime number using Java streams
		List<Integer> numbers3 = Arrays.asList(2, 4, 6, 8, 10, 11, 12, 13, 14, 15);
		boolean containsPrime = numbers3.stream()
									   .anyMatch(StreamsUtil::isPrime);
		System.out.println("List contains a prime number: " + containsPrime);
				
		//---------------------------------------
		
		// Merging two sorted lists into a single sorted list using Java streams
		List<Integer> list1m = Arrays.asList(1, 3, 5, 7, 9);
		List<Integer> list2m = Arrays.asList(2, 4, 6, 8, 10);
		List<Integer> list3m = Stream.concat(list1m.stream(), list2m.stream())
								     .sorted()
									 .collect(Collectors.toList());
		
		//---------------------------------------

		// Find the intersection of two lists using Java streams
		List<Integer> list4m = Arrays.asList(1, 2, 3, 4, 5);
		List<Integer> list5m = Arrays.asList(3, 4, 5, 6, 7);
		List<Integer> intersection = list4m.stream()
										   .filter(list5m::contains)
										   .collect(Collectors.toList());
		
		System.out.println("intersection: " + intersection); // [3, 4, 5]
		
		//---------------------------------------

		// Remove duplicates from a list while preserving the order using Java streams:
		List<Integer> numbersWithDuplicates = Arrays.asList(1, 2, 3, 2, 4, 1, 5, 6, 5);
		List<Integer> uniqueNumbers = numbersWithDuplicates.stream()
															.distinct()
															.collect(Collectors.toList());

		//---------------------------------------

		// Given a list of transactions, find the sum of transaction amounts for each day using Java streams:
		List<Transaction> transactions = Arrays.asList(
			    new Transaction("2022-01-01", 100),
			    new Transaction("2022-01-01", 200),
			    new Transaction("2022-01-02", 300),
			    new Transaction("2022-01-02", 400),
			    new Transaction("2022-01-03", 500)
			);
		
		Map<String, Integer> sumByDay = transactions.stream()
												    .collect(Collectors.groupingBy(Transaction::getDate,
												    		 Collectors.summingInt(Transaction::getAmount)));
		
		//---------------------------------------

		// Find the kth smallest element in an array using Java streams
		int[] array1m = {4, 2, 7, 1, 5, 3, 6};
		int k = 3; // Find the 3rd smallest element
		int kthSmallest = Arrays.stream(array1m)
						.sorted()
						.skip(k-1)
						.findFirst()
						.orElse(-1);
				
		//---------------------------------------

		// Given a list of strings, find the frequency of each word using Java streams
		List<String> words2 = Arrays.asList("apple", "banana", "apple", "cherry", "banana", "apple");
		Map<String, Long> wordFrequency = words2.stream()
												   .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		System.out.println("wordFrequency: " + wordFrequency);										 
		
		//---------------------------------------

		// Implement a method to partition a list into two groups based on a predicate using Java streams
		// these two groups are: even and odd numbers.
		List<Integer> numbers4 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
		Map<Boolean, List<Integer>> partitioned = numbers4
		                        .stream()
		                        .collect(Collectors.partitioningBy(n -> n % 2 == 0));
		List<Integer> evenNumbers = partitioned.get(true);
		List<Integer> oddNumbers = partitioned.get(false);
		System.out.println("Even numbers: " + evenNumbers);
		System.out.println("Odd numbers: " + oddNumbers);
	}
	
	/***************************************/
	
	/* Methods */
	
	// return sum of two values
	private static int sum (int a, int b) {
		return a + b;
	}
	
	// return the sum of elements of a List<Integer>
	// Functional approach (Functional Programming)
	private static int addListFunctional(List<Integer> numbers) {
		return numbers.stream()
					  // with lambda expression
					  //.reduce(0, (a, b) -> a + b); // aggregation = sum: a = aggregate, b = next number
				
			   		  // with method reference
					  //.reduce(0, StreamsUtil::sum);
				
					  // with reduce
					  .reduce(0, Integer::sum); // better
	}

	// return the sum of elements of a List<Integer>
	// Structured approach
	private static int addListStructured(List<Integer> numbers) {
		int sum=0;
		for(int num:numbers)
			sum += num;
		
		return sum;
	}
	
	// Method to filter strings of a given length and 
    // convert them to uppercase
    private static List<String> filterAndConvertToUpper(Stream<String> stream, int length) 
    { 
        return stream.filter(s -> s.length() == length) 
            .map(String::toUpperCase) 
            .collect(Collectors.toList()); 
    }

    // verify if a numer is prime or not
	// prime numbers: can only be divided by 1 and themselves without leaving a remainder.
	// e.g.: 2 (smallest and the only even prime number), 3, 5, 7, 11, 13, ...
	public static boolean isPrime(int number) {
		if (number <= 1) {
			return false;
		}
		
		for (int i = 2; i <= Math.sqrt(number); i++) {
			if (number % i == 0) {
				return false;
			}
		}
		
		return true;
	}
}

class Course {
	private String name;
	private String category;
	private int reviewScore;
	private int noOfStudents;
	
	public Course(String name, String category, int reviewScore, int noOfStudents) {
		super();
		this.name = name;
		this.category = category;
		this.reviewScore = reviewScore;
		this.noOfStudents = noOfStudents;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getReviewScore() {
		return reviewScore;
	}
	public void setReviewScore(int reviewScore) {
		this.reviewScore = reviewScore;
	}
	public int getNoOfStudents() {
		return noOfStudents;
	}
	public void setNoOfStudents(int noOfStudents) {
		this.noOfStudents = noOfStudents;
	}
	
	public String toString() {
		return name + ":" + noOfStudents + ":" + reviewScore;
	}
}

class Product{
    int id;  
    String name;  
    float price;
    
    public Product(int id, String name) {  
        this.id = id;  
        this.name = name;  
    }  
    
    public Product(int id, String name, float price) {  
        this.id = id;  
        this.name = name;  
        this.price = price;  
    }  
    
    public int getId() {  
        return id;  
    }
    
    public String getName() {  
        return name;  
    }
    
    public float getPrice() {  
        return price;  
    }
}

class Person {
	String name;
	int age;
	
	Person(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	public int getAge() {
		return this.age;
	}
}

class Transaction {
	String date;
	int amount;
	
	Transaction(String date, int amount) {
		this.date = date;
		this.amount = amount;
	}
	
	public String getDate() {
		return this.date;
	}
	
	public int getAmount() {
		return this.amount;
	}
}

/**
* find user

private static List<User> users = new ArrayList<>();

static {
	users.add(new User(1,"Adam",LocalDate.now().minusYears(30)));
	users.add(new User(2,"Eve",LocalDate.now().minusYears(25)));
	users.add(new User(3,"Jim",LocalDate.now().minusYears(20)));
}

public User findOne(int id) {
	Predicate<? super User> predicate = user -> user.getId().equals(id);
	return users.stream().filter(predicate).findFirst().get();
}
*/

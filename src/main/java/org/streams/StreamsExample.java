package org.streams;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Java Streams represents a sequence of elements and supports different kind of operations
 * to perform computations upon those elements.
 * Streams can greatly reduce code, and speed up code execution, because they scan and work with collection in parallel.
 * Streams works with lambda expressions:
 *  - Lambda expression is a short block of code which takes in parameters and returns a value.
 *  - Lambda expressions are similar to methods, but they do not need a name and they can be implemented right in the body of a method.
 *  - Left side of lambda expression is parameter that is needed for an expression
 *  - Right side of lambda expression is body which specifies action of lambda expression
 *  - Example: s -> System.out.println(s)
 *
 *
 * Working with streams:
 * 1, Create a stream
 * 2a, Do intermediate stream operation that will transfer one stream to another ({@link IntermediateOperationsExamples})
 *     Examples:
 *        - filter()    - filters stream elements based on a given predicate
 *        - map()       - modifies stream elements based on a application of given function
 *        - limit()     - limits size of stream
 *        - sorted()    - sorts elements of stream
 *        - distinct()  - returns stream that consists of distinct elements (repeating elements will stay in stream only once)
 *        - skip()      - skips the given n elements of stream. Useful when performing operations on last n elements of stream
 *        - flatMap()   - works best for a list of collections
 *        - peek()      - exists mainly to support debugging, where you want to see the elements as they flow in a pipeline
 * 2b, Do X other intermediate stream operations
 * 3, Do terminal stream operation (@see {@link TerminalOperationsExamples})
 *     Examples:
 *        - collect()   - collects stream elements and convert them back to Collection
 *        - count()     - returns total number of elements in the stream
 *        - forEach()   - performs an operation for all elements of stream
 *        - min()       - returns the minimum element in stream according to provided comparator
 *        - max()       - returns the maximum element in stream according to provided comparator
 *        - allMatch()  - returns true if all elements match a provided predicate, otherwise returns false
 *        - anyMatch()  - returns true if any element in stream matches a provided predicate, otherwise returns false
 *        - noneMatch() - returns true if no elements in stream matches a provided predicate, otherwise returns false
 *        - reduce()    - performs a reduction on the elements of the stream
 */
public class StreamsExample {

    CollectionDefinitions cd = new CollectionDefinitions();
    ArrayList<String> namesArray = cd.names;
    HashSet<String> monthsHash = cd.months;

    @Test
    public void createStream() {
        System.out.println("createStream()");

        //Create stream directly
        Stream days = Stream.of("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday");
        Stream primeNumbers = Stream.of(1, 2, 3, 5, 8, 13, 21);

        //Create stream by converting a Collection to a Stream
        Stream names = namesArray.stream();
        Stream months = monthsHash.stream();
    }

    @Test
    public void basicStreamUsage() {
        System.out.println("basicStreamUsage()");
        List<String> namesFilteredAndSorted = namesArray
                  .stream()        //1, create a stream
                  .distinct()      //2, do intermediate operation that will filter reoccurring stream elements
                  .filter(name -> name.startsWith("A"))   //2, do another intermediate operation that will filter only names starting with "A"
                  .sorted()        //2, do another intermediate operation that will sort results
                  .collect(Collectors.toList()); //3; do terminal stream operation

        System.out.println("List of names starting with letter A: " + namesFilteredAndSorted.toString());
    }

    @Test
    public void concatStreams() {
        System.out.println("concatStreams()");
        Stream names = namesArray.stream();
        Stream months = monthsHash.stream();
        Stream<String> concatenatedStream = Stream.concat(names, months);

        concatenatedStream.forEach(element->System.out.print(element + ", "));
    }

    @Test
    public void differentStyles() {
        System.out.println("differentStyles()");

        monthsHash.stream()                                 //1, create a stream
                  .map(day -> day.toUpperCase())            //2, do intermediate operation
                  .sorted()                                 //2, do another intermediate operation
                  .forEach(day -> System.out.println(day)); //3, do terminal operation

        System.out.println("-----");
        monthsHash.stream()                        //1, create a stream
                  .map(String::toUpperCase)        //2, do intermediate operation
                  .sorted()                        //2, do another intermediate operation
                  .forEach(System.out::println);   //3, do terminal operation
    }
}

package org.streams;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Examples of intermediate stream operations
 * Note: forEach is ternary operator that will print all elements of Stream.
 */
public class IntermediateOperationsExamples {

    CollectionDefinitions cd = new CollectionDefinitions();
    ArrayList<String> namesArray = cd.names;
    ArrayList<String> daysArray = cd.days;
    HashSet<Integer> integersSet = cd.integers;

    /**
     * Filters stream elements based on a given predicate
     */
    @Test
    public void filterOperation() {
        System.out.println("filterOperation()");

        //Filter names that does not contain letter "a"
        namesArray.stream().filter(a -> ! a.contains("a")).forEach(a -> System.out.println(a));

        //Filter days that starts with letter "S"
        daysArray.stream().filter(m -> m.startsWith("S")).forEach(m -> System.out.println(m));
    }

    /**
     * Modifies stream elements based on a application of given function
     */
    @Test
    public void mapOperation() {
        System.out.println("mapOperation()");

        //For each day in stream, transform it to uppercase form
        daysArray.stream().map(day -> day.toUpperCase()).forEach(day -> System.out.println(day));
        System.out.println("-----");
        daysArray.stream().map(String::toUpperCase).forEach(System.out::println); //different way of how to use it
    }

    /**
     * Limits size of stream
     */
    @Test
    public void limitOperation() {
        System.out.println("limitOperation()");

        //Limit elements in stream to 3
        daysArray.stream().limit(3).forEach(day -> System.out.println(day));
    }

    /**
     * Sorts elements of stream
     */
    @Test
    public void sortedOperation() {
        System.out.println("sortedOperation()");

        namesArray.stream().sorted().forEach(name -> System.out.println(name));
    }

    /**
     * Returns stream that consists of distinct elements (repeating elements will stay in stream only once)
     */
    @Test
    public void distinctOperation() {
        System.out.println("distinctOperation()");

        //Filters numbers that are multiple times in stream and prints them to one line concatenated with ", "
        integersSet.stream().distinct().forEach(integer -> System.out.print(integer + ", "));
    }

    /**
     * Skips the given n elements of stream. Useful when performing operations on last n elements of stream.
     */
    @Test
    public void skipOperation() {
        System.out.println("skipOperation()");

        //Skip first 3 elements in stream, and print all others
        daysArray.stream().skip(3).forEach(day -> System.out.println(day));
    }

    /**
     * Works best for a list of collections.
     * Returns a stream consisting of the results of replacing each element of this stream with the contents of
     * a mapped stream produced by applying the provided mapping function to each element.
     */
    @Test
    public void flatmapOperation() {
        System.out.println("flatmapOperation()");

        List<List<String>> listOfList = Arrays.asList(namesArray, daysArray);
        System.out.println("List of list: " + listOfList);

        List<String> listOfStrings = listOfList.stream().flatMap(list -> list.stream()).collect(Collectors.toList());
        System.out.println("The structure after flattening is: " + listOfStrings);
    }

    /**
     * Exists mainly to support debugging, where you want to see the elements as they flow in a pipeline
     */
    @Test
    public void peekOperation() {
        System.out.println("peekOperation()");

        daysArray.stream()
                 .filter(day -> ! day.startsWith("S"))   //filter weekend days (starting with S)
                 .peek(day -> System.out.println("Filtered value: " + day))
                 .map(day -> day.toUpperCase())
                 .peek(day -> System.out.println("Mapped value: " + day))
                 .forEach(day -> System.out.println(day));
    }
}

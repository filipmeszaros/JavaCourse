package org.streams;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Examples of terminal stream operations
 */
public class TerminalOperationsExamples {

    CollectionDefinitions cd = new CollectionDefinitions();
    ArrayList<String> daysArray = cd.days;
    HashSet<String> emailsSet = cd.emails;
    HashSet<Integer> integersSet = cd.integers;

    /**
     * Collects stream elements and convert them back to Collection
     */
    @Test
    public void collect() {
        System.out.println("collectOperation()");

        Set<String> namesSet = daysArray.stream().collect(Collectors.toSet());
        System.out.println("Stream of days converted to Set: " + namesSet);
    }

    /**
     * Returns total number of elements in the stream
     */
    @Test
    public void countOperation() {
        System.out.println("countOperation()");

        System.out.println("Total number of elements in stream of days: " + daysArray.stream().count());
        daysArray.stream().forEach(s->System.out.println(s));
    }

    /**
     * Performs an operation for all elements of stream
     */
    @Test
    public void forEachOperation() {
        System.out.println("forEachOperation()");

        daysArray.stream().forEach(element->System.out.println(element));
        daysArray.stream().forEach(System.out::println);  //same as previous command

        daysArray.stream().forEach(e->System.out.println(e.length()));
    }

    /**
     * Returns the minimum element in stream according to provided comparator
     */
    @Test
    public void minOperation() {
        System.out.println("minOperation()");

        int minValue = integersSet.stream().min(Integer::compare).get();
        System.out.println("Min value in stream is: " + minValue);
    }

    /**
     * Returns the maximum element in stream according to provided comparator
     */
    @Test
    public void maxOperation() {
        System.out.println("maxOperation()");

        int maxValue = integersSet.stream().max(Integer::compare).get();
        System.out.println("Max value in stream is: " + maxValue);
    }

    /**
     * Returns true if all elements match a provided predicate, otherwise returns false
     */
    @Test
    public void allMatchOperation() {
        System.out.println("allMatchOperation()");

        boolean areAllValuesPositive = integersSet.stream().allMatch(i->i>0);
        boolean areAllValuesLowerThanMillion = integersSet.stream().allMatch(i->i<1000000);

        System.out.println("Are all values positive?: " + areAllValuesPositive);
        System.out.println("Are all values lower than million?: " + areAllValuesLowerThanMillion);
    }

    /**
     * Returns true if any element in stream matches a provided predicate, otherwise returns false
     */
    @Test
    public void anyMatchOperation() {
        System.out.println("anyMatchOperation()");

        boolean valuesContainsZero = integersSet.stream().anyMatch(i -> i == 0);
        boolean containValueHigherThanMillion = integersSet.stream().anyMatch(i -> i > 1000000);
        boolean containsGmail = emailsSet.stream().anyMatch(x -> x.endsWith("@gmail.com"));

        System.out.println("Integers contains value 0?: " + valuesContainsZero);
        System.out.println("Integers contains value higher than a million?: " + containValueHigherThanMillion);
        System.out.println("Emails contain gmail domain: " + containsGmail);
    }

    /**
     * Returns true if no elements in stream matches a provided predicate, otherwise returns false
     */
    @Test
    public void noneMatchOperation() {
        System.out.println("noneMatchOperation()");

        boolean doesNotContainZero = integersSet.stream().noneMatch(i -> i < 0);
        boolean doesNotContain42 = integersSet.stream().noneMatch(i -> i == 42);
        boolean doesNotContainYahooDomain = emailsSet.stream().noneMatch(e -> e.endsWith("yahoo.com"));

        System.out.println("Integers does not contain value 0?: " + doesNotContainZero);
        System.out.println("Integers does not contain value 42?: " + doesNotContain42);
        System.out.println("Emails does not contain yahoo domain: " + doesNotContainYahooDomain);
    }

    /**
     * Performs a reduction on the elements of the stream
     */
    @Test
    public void reduceOperation() {
        System.out.println("reduceOperation()");

        //Example 1
        //Reduce 2 consecutive elements (days in our case) to one string with plus sign between 2 elements (days)
        Optional<String> reducedValue = daysArray.stream().reduce((day1, day2) -> (day1 + "+" + day2));
        if (reducedValue.isPresent()) {
            System.out.println("All elements reduced to one string concatenated with plus: " + reducedValue.get());
        }

        //Example 2
        //Reduce 2 consecutive elements (numbers in our case) to one element with addition
        int sumOfIntegers = integersSet.stream().reduce(0, (x, y) -> (x + y));
        System.out.println("Sum of all integers in stream: " + sumOfIntegers);
    }
}

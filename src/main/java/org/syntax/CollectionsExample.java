package org.syntax;

import java.util.*;
import java.util.Arrays;

/**
 * Java Collections is framework that provides an architecture to store and manipulate the group of objects.
 * Java Collections can achieve all the operations that you perform on a data such as searching, sorting, insertion, manipulation, and deletion.
 * Collection types:
 * a) List - ordered collection (also known as a sequence) that can contain duplicate elements
 *         - types: ArrayList, LinkedList, Vector
 * b) Set  - unordered collection that does not contain duplicate elements
 *         - types: HashSet, LinkedHashSet, TreeSet
 * c) Map  - object that maps keys to values. Map cannot contain duplicate values.
 *         - types: HashMap (not synchronized == not thread safe), LinkedHashMap, TreeMap, HashTable (thread safe)
 *
 * Collections are working with Java Wrapper classes.
 * Wrapper classes provide a way to use primitive data types (int, boolean, double, char, ...) as objects (Integer, Boolean, Double, Character, ...)
 * When working with Java Collections, wrapper classes as used, and not primitive types.
 * Example:
 * ArrayList<int> myNumbers = new ArrayList<int>();         // Invalid
 * ArrayList<Integer> myNumbers = new ArrayList<Integer>(); // Valid
 *
 * An Iterator is an object that can be used to loop through collections, like ArrayList and HashSet.
 * It is called an "iterator" because "iterating" is the technical term for looping.
 * Iterator contain 2 important methods:
 *   - hasNext() - returns true if the iteration has more elements
 *   - next()    - returns the next element in the iteration.
 */
public class CollectionsExample {

    public static void main(String[] args) {
        System.out.println("-------- List examples ---------");
        listExamples();
        System.out.println("-------- Set examples ----------");
        setExamples();
        System.out.println("-------- Map examples -----------");
        mapExamples();
        System.out.println("-------- Collection examples -------");
        collectionExamples();
    }

    /**
     * List = ordered collection (also known as a sequence) that can contain duplicate elements
     * ArrayList  - has a regular array inside it. If the array is not big enough, a new, larger array is created
     *              to replace the old one and the old one is removed.
     *              When it is best to use: - if you want to access random items frequently
     *                                      - when you only need to add or remove elements at the end of the list
     * LinkedList - stores its items in "containers". Each container has a link to the next container in the list.
     *              When it is best to use: - when you only use the list by looping through it, instead of accessing random items
     *                                      - when you frequently need to add/remove items from beginning/middle/end of the list
     */
    public static void listExamples() {
        ArrayList<String> list = new ArrayList<>();
        list.add("str1");      //add element to last position of list
        list.add("mistake");
        list.add("str2");
        list.add(0, "str0"); //add element to first position of list
        list.add("aaa");
        list.remove("mistake");        //remove element from list
        System.out.println("ArrayList: " + list);
        System.out.println("Size of ArrayList is " + list.size());
        System.out.println("Element on index 2 of ArrayList is: " + list.get(2));
        System.out.println("Index of str1 is: " + list.indexOf("str1"));
        System.out.println("ArrayList contains 'mistake': " + list.contains("mistake"));

        Collections.sort(list);  //Will sort ArrayList
        System.out.println("Sorted ArrayList: " + list);

        Collections.reverse(list);   //Will reverse ArrayList
        System.out.println("Reversed ArrayList: " + list);

        list.clear();
        System.out.println("ArrayList size after clear: " + list.size());

        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("1st");
        linkedList.add("2nd");
        linkedList.addFirst("0th"); //add item to first index
        linkedList.addLast("last"); //add item to last index
        //print all items of LinkedList
        for (String element : linkedList) {
            System.out.println("LinkedList item: " + element);
        }
        System.out.println("First item in linkedList that will be removed: "  + linkedList.getFirst());
        System.out.println("Last item in linkedList that will be removed: "  + linkedList.getLast());
        linkedList.removeFirst(); //remove first item
        linkedList.removeLast();  //remove last item
        //print all items of LinkedList
        for (String element : linkedList) {
            System.out.println("LinkedList item after delete: " + element);
        }
    }

    /**
     * Set = unordered collection that does not contain duplicate elements
     */
    public static void setExamples() {
        HashSet<String> set = new HashSet<>();
        set.add("USA");
        set.add("SK");
        set.add("CZ");
        set.add("SK");  //SK element will be in HashSet only once - Set contains elements only once
        set.add("UK");
        set.add("SK");  //SK element will be in HashSet only once - Set contains elements only once
        System.out.println("HashSet which is in random order: " + set.toString());
        System.out.println("HashSet contains element SK: " + set.contains("SK"));
        set.remove("SK"); //remove will remove SK element from Set, which is there only once!
        System.out.println("HashSet contains element SK: " + set.contains("SK"));

        System.out.println("HashSet size " + set.size());
        Iterator i = set.iterator();  //Iterator helps to iterate trough HashSet
        while (i.hasNext()) {
            System.out.println(i.next());
        }
        System.out.println("End of HashSet");
        System.out.println("HashSet is empty: " + set.isEmpty());

        set.clear();
        System.out.println("HashSet size after clear: " + set.size());
    }

    /**
     * Map = object that maps keys to values. Map cannot contain duplicate values.
     */
    public static void mapExamples() {
        HashMap<String, String> map = new HashMap<>();   //Map of Key String to Value String
        map.put("SK", "Bratislava");
        map.put("SK", "Nitra");        //Overwrite Key SK - Map does not contain duplicate values
        map.put("SK", "Trnava");       //Overwrite Key SK - Map does not contain duplicate values
        map.put("USA", "New York");
        map.put("USA", "Washington");  //Overwrite Key USA - Map does not contain duplicate values
        map.put("CZ", "Brno");
        System.out.println("CZ: " + map.get("CZ"));   //Get value of key CZ
        System.out.println("SK: " + map.get("SK"));   //Get value of key SK - will print last assigned SK value

        HashMap<String, Integer> numberOfResidents = new HashMap<>();
        numberOfResidents.put("Bratislava", 424400);
        numberOfResidents.put("Tvrdosovce", 5200);
        numberOfResidents.put("Nitra", 77600);
        numberOfResidents.replace("Tvrdosovce", 5150);   //Update value for Tvrdosovce
        numberOfResidents.put("Mistake", 1111);
        numberOfResidents.remove("Mistake");         //Remove key-value pair with key Mistake
        System.out.println("Number of residents in Bratislava is: " + numberOfResidents.get("Bratislava"));
        System.out.println("Map size: " + numberOfResidents.size());
        System.out.println("Set of KEYS in Map is: " + numberOfResidents.keySet());
        System.out.println("Set of VALUES in Map is: " + numberOfResidents.values());
        System.out.println("HashMap contains key 'Brno': " + numberOfResidents.containsKey("Brno"));
        System.out.println("HashMap contains value 1000000: " + numberOfResidents.containsValue(1000000));

        //Print keys and values
        for (String key : numberOfResidents.keySet()) {
            System.out.println("Key: " + key + " + value: " + numberOfResidents.get(key));
        }

        numberOfResidents.clear();   //clear map
        System.out.println("Map size after clear: " + numberOfResidents.size());
    }

    /**
     * Examples with operations with Collections
     */
    public static void collectionExamples() {
        ArrayList<Integer> integers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, -5, 50));

        ArrayList<String> list1 = new ArrayList<>();
        ArrayList<String> list2 = new ArrayList<>();

        list1.add("element1");
        list2.add("element2");

        Collections.addAll(list1, "element3", "element5", "element6");  //add elements to Collection
        System.out.println("New list1: " + list1);
        Collections.sort(list1);   //sort collection
        System.out.println("Sorted list1: " + list1);
        Collections.reverse(list1);
        System.out.println("Reversed list1: " + list1);
        boolean noCommonElements =  Collections.disjoint(list1, list2); //returns true if collection does not contain common elements
        System.out.println("Collections are disjoint: " + noCommonElements);

        System.out.println("Min value from collection: " + Collections.min(integers));
        System.out.println("Max value from collection: " + Collections.max(integers));
        Collections.shuffle(list1);
        System.out.println("Shuffled list1: " + list1);
        Collections.shuffle(list1);
        System.out.println("Shuffled list1: " + list1);
        Collections.shuffle(list1);
        System.out.println("Shuffled list1: " + list1);
        Collections.replaceAll(list1, "element1", "NEW ELEMENT");
        System.out.println("List1 with replaced element: " + list1);
    }
}

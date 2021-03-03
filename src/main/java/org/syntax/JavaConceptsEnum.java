package org.syntax;

/**
 * Enum is a special "class" that represents a group of constants (unchangeable variables, like final variables).
 * This is an example of advanced enum, that contains list of all Concepts of Java programming language.
 * For simple example of enum, @see class {@link Enums}
 */
public enum JavaConceptsEnum {

    ABSTRACTION("Abstraction means using simple things to represent complexity. In Java, abstraction means simple things like objects, classes, and variables represent more complex underlying code and data. This is important because it lets avoid repeating the same work multiple times. Abstraction provides definition of what object does (interface), but not how it does (implementation)."),
    ABSTRACT_METHOD("A method that is declared but not defined. Contains only method signature with no body. If our class contains at least abstract method, whole class must be abstract."),
    ABSTRACT_CLASS("An abstract class outlines the methods but not necessarily implements all the methods. Abstract class is a common way to achieve partial abstraction in Java."),
    ACCESS_SPECIFIERS("They decide the scope of a data member, method or class. Four types of access specifiers: public, private, protected, default (we do not need to explicitly mention default - if we do not specify access specifier, it is considered as default)."),
    CLASS("Template (blueprint) for building an Object. This template contains class variables, and class methods (functions)."),
    CONSTRUCTOR("Constructor is a method which name is same as class name and it does not return any value. This method is called as soon as object of class is created. Constructor can contain parameters (such constructors are known as parametrized constructor). Each class can contain multiple constructors with different parameters."),
    DESIGN_PATTER("A design pattern is a well-described solution to a common software problem."),
    DTO("Data Transfer Object Pattern. A Data Transfer Object is, essentially, like a data structure. It should not contain any business logic but should contain serialization and deserialization mechanisms."),
    DYNAMIC_POLYMORPHISM("Dynamic polymorphism is a process in which a call to an overridden method is resolved at runtime. Also called runtime polymorphism."),
    ENCAPSULATION("This is the practice of keeping fields within a class private, then providing access to them via public methods. It’s a protective barrier that keeps the data and code safe within the class itself."),
    GENERALIZATION("Generalization is the process of extracting shared characteristics from two or more classes, and combining them into a generalized superclass. Is opposite of specialization"),
    JAVADOC("Is a documentation generator for the Java language for generating API documentation in HTML format from Java source code."),
    JUNIT("JUnit is a unit testing framework for Java programming language."),
    IMMUTABLE_OBJECT("An immutable object is an object whose internal state remains constant after it has been entirely created. For example String is immutable. When calling String method replace(), our String is not updated, but new String is created. Opposite of immutable object is mutable object."),
    INHERITANCE("Inheritance lets programmers create new classes that share some of the attributes of existing classes. It works by letting a new class adopt the properties of another. This lets us build on previous work without reinventing the wheel."),
    INTERFACE("An interface is a blueprint of a class, which can be declared by using interface keyword. Interfaces can contain only constants and abstract methods (methods with only signatures no body). Interfaces cannot be instantiated (like abstract classes), they can only be implemented by classes or extended by other interfaces. Interface is a common way to achieve full abstraction in Java. One class can implement multiple interfaces."),
    LAMBDA_EXPRESSION("A lambda expression is a short block of code which takes in parameters and returns a value. Lambda expressions are similar to methods, but they do not need a name and they can be implemented right in the body of a method."),
    METHOD("A method is a block of code (function) which only runs when it is called. You can pass data, known as parameters, into a method. Each method returns data (known as return type)."),
    METHOD_OVERLOADING("A single method may perform different functions depending on the context in which it’s called. That is, a single method name might work in different ways depending on what arguments are passed to it."),
    METHOD_OVERRIDING("The child class can use the OOP polymorphism concept to override a method of its parent class. That allows a programmer to use one method in different ways depending on whether it’s invoked by an object of the parent class or an object of the child class."),
    MULTILEVEL_INHERITANCE("If we inherit method or variable not from parent class, but from parent of parent class."),
    MUTABLE_OBJECT("A mutable object can be changed after it's created. In Java, everything except for string) is mutable by default. Mutable objects are nice because you can make changes in-place, without allocating a new object. Opposite of mutable object is immutable object."),
    OBJECT("Is a bundle of data and its behaviour (often known as methods), created from a template called Class."),
    OOP("Object Oriented Programming"),
    OPERATORS("Operators are used to perform operations on variables and values."),
    PACKAGE("Package in Java is used to group related classes (think of it as a folder in a file directory)"),
    PARAMETRIZED_TEST("Parameterized tests allow a developer to run the same test over and over again using different values."),
    POLYMORPHISM("This Java OOP concept lets programmers use the same word to mean different things in different contexts. Polymorphism in Java works by using a reference to a parent class to affect an object in the child class. Another form of polymorphism in Java are: method overloading and method overriding."),
    REGULAR_EXPRESSIONS("A regular expression is a sequence of characters that forms a search pattern."),
    SPECIALIZATION("Specialization means creating new subclasses from an existing class. Is opposite of generalization"),
    STATIC_CLASS("Static class (nested class) is a class within another class"),
    STATIC_METHOD("Method that can be executed without creating an object of the class first"),
    STATIC_VARIABLE("Variable that can be accessed without creating an object of the class first"),
    STATIC_POLYMORPHISM("Polymorphism that is resolved during compiler time is known as static polymorphism. Method overloading can be considered as static polymorphism example."),
    THREAD("Threads allows a program to operate more efficiently by doing multiple things at the same time."),
    THREAD_SAFE("This means that different threads can access the same resources without exposing erroneous behavior or producing unpredictable results. Thread safety is not achieved automatically in Java, but must be achieved by programmer."),
    TYPE_CASTING("Type casting is when you assign a value of one primitive data type to another type."),
    VARIABLE("Variables are containers for storing data values."),
    WRAPPER_CLASSES("Wrapper classes provide a way to use primitive data types (int, boolean, char, double, etc..) as objects (Integer, Boolean, Character, Double, etc..");

    public final String description;

    JavaConceptsEnum(String description) {
        this.description = description;
    }

    public static void main(String[] args) {
        for (JavaConceptsEnum oneConcept : JavaConceptsEnum.values()) {   //process all values of enums
            System.out.println(oneConcept.name() + " - " + oneConcept.description);
        }
    }
}

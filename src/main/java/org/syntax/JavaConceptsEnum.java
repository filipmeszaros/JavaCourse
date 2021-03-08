package org.syntax;

/**
 * Enum is a special "class" that represents a group of constants (unchangeable variables, like final variables).
 * This is an example of advanced enum, that contains list of all Concepts of Java programming language.
 * For simple example of enum, @see class {@link Enums}.
 * Each enum item contains name, description, and in most cases link to class that is demonstrating mentioned concept.
 */
public enum JavaConceptsEnum {

    ABSTRACTION("Abstraction means using simple things to represent complexity. In Java, abstraction means simple things like objects, classes, and variables represent more complex underlying code and data. This is important because it lets avoid repeating the same work multiple times. Abstraction provides definition of what object does (interface), but not how it does (implementation)."), /** {@link org.abstraction.AbstractionExample} */
    ABSTRACT_METHOD("A method that is declared but not defined. Contains only method signature with no body. If our class contains at least abstract method, whole class must be abstract."), /** {@link org.abstraction.AbstractClassExample} */
    ABSTRACT_CLASS("An abstract class outlines the methods but not necessarily implements all the methods. Abstract class is a common way to achieve partial abstraction in Java."),          /** {@link org.abstraction.AbstractClassExample} */
    ACCESS_MODIFIERS("They decide the scope of a data member, method or class. Four types of access specifiers: public, private, protected, default (we do not need to explicitly mention default - if we do not specify access specifier, it is considered as default)."), /** {@link org.packages.packageC.AccessModifiersClass} */
    CLASS("Template (blueprint) for building an Object. This template contains class variables, and class methods (functions)."), /** {@link org.abstraction.OOPExample} */
    CONSTRUCTOR("Constructor is a method which name is same as class name and it does not return any value. This method is called as soon as object of class is created. Constructor can contain parameters (such constructors are known as parametrized constructor). Each class can contain multiple constructors with different parameters."), /** {@link org.abstraction.OOP} */
    CUCUMBER("Cucumber is the BDD (Behaviour Driven Development) framework for running automated tests."),
    DESERIALIZATION("In Rest Assured context is a process of converting a response body in form of JSON with JackSon/Gson/Johnzon framework (or with JAXB framework for XML) into a Java object. Opposite concept is serialization."),
    DESIGN_PATTER("A design pattern is a well-described solution to a common software problem."),
    DTO("Data Transfer Object Pattern. A Data Transfer Object is, essentially, like a data structure. It should not contain any business logic but should contain serialization and deserialization mechanisms."),
    DYNAMIC_POLYMORPHISM("Dynamic polymorphism is a process in which a call to an overridden method is resolved at runtime. Also called runtime polymorphism."),  /** {@link org.inheritance.InheritanceExample} */
    ENCAPSULATION("This is the practice of keeping fields within a class private, then providing access to them via public methods. It’s a protective barrier that keeps the data and code safe within the class itself."), /** {@link org.abstraction.Encapsulation} */
    FRAMEWORK("Frameworks are bundles of prewritten code used by developers to create apps using selected programming language."),
    GENERALIZATION("Generalization is the process of extracting shared characteristics from two or more classes, and combining them into a generalized superclass. Is opposite concept of specialization"), /** {@link org.inheritance.InheritanceExample} and class {@link org.inheritance.Vehicle} */
    GETTERS("Getters are public methods of class that are retrieving private class variables to achieve Encapsulation"), /** {@link org.abstraction.Encapsulation} */
    GHERKIN("Gherkin is a business readable, domain specific language used by Cucumber framework to define test cases in a common format suitable for technical and non-technical users."),
    GIT("Git is a free and open source distributed version control system designed to handle everything from small to very large projects with speed and efficiency."),
    GITHUB("The world's leading software development platform, where you can store your GIT repositories and work with them."),
    JAVADOC("Is a documentation generator for the Java language for generating API documentation in HTML format from Java source code."), /** {@link JavaDocumentation} */
    JUNIT("JUnit is a unit testing framework for Java programming language."),
    IMMUTABLE_OBJECT("An immutable object is an object whose internal state remains constant after it has been entirely created. For example String is immutable. When calling String method replace(), our String is not updated, but new String is created. Opposite of immutable object is mutable object."),
    INHERITANCE("Inheritance lets programmers create new classes that share some of the attributes of existing classes. It works by letting a new class adopt the properties of another. This lets us build on previous work without reinventing the wheel."),  /** {@link org.inheritance.InheritanceExample} */
    INTERFACE("An interface is a blueprint of a class, which can be declared by using interface keyword. Interfaces can contain only constants and abstract methods (methods with only signatures no body). Interfaces cannot be instantiated (like abstract classes), they can only be implemented by classes or extended by other interfaces. Interface is a common way to achieve full abstraction in Java. One class can implement multiple interfaces."), /** {@link org.abstraction.InterfaceExample} */
    LAMBDA_EXPRESSION("A lambda expression is a short block of code which takes in parameters and returns a value. Lambda expressions are similar to methods, but they do not need a name and they can be implemented right in the body of a method."), /** {@link org.streams.StreamsExample} */
    METHOD("A method is a block of code (function) which only runs when it is called. You can pass data, known as parameters, into a method. Each method returns data (known as return type)."),  /** {@link org.abstraction.MethodExamples} */
    METHOD_OVERLOADING("A single method may perform different functions depending on the context in which it’s called. That is, a single method name might work in different ways depending on what arguments are passed to it."), /** {@link org.abstraction.MethodExamples} */
    METHOD_OVERRIDING("The child class can use the OOP polymorphism concept to override a method of its parent class. That allows a programmer to use one method in different ways depending on whether it’s invoked by an object of the parent class or an object of the child class."), /** {@link org.inheritance.InheritanceExample} and class {@link org.inheritance.Car} */
    MULTILEVEL_INHERITANCE("If we inherit method or variable not from parent class, but from parent of parent class."),
    MUTABLE_OBJECT("A mutable object can be changed after it's created. In Java, everything except for string) is mutable by default. Mutable objects are nice because you can make changes in-place, without allocating a new object. Opposite of mutable object is immutable object."),
    OBJECT("Is a bundle of data and its behaviour (often known as methods), created from a template called Class."), /** {@link org.abstraction.OOP} and {@link org.abstraction.OOPExample}*/
    OOP("Object Oriented Programming"), /** {@link org.abstraction.OOP} and {@link org.abstraction.OOPExample} */
    OPERATORS("Operators are used to perform operations on variables and values."), /** {@link Operators} */
    PACKAGE("Package in Java is used to group related classes (think of it as a folder in a file directory)"), /** {@link org.packages.PackagesExample1} or {@link org.packages.packageC.PackagesExample2} */
    PARAMETRIZED_TEST("Parameterized tests allow a developer to run the same test over and over again using different values."),
    POJO("Plain Old Java Object - a straightforward type with no references to any particular frameworks. POJO usually contains getters and setters to achieve Encapsulation."), /** {@link org.abstraction.Encapsulation} */
    POLYMORPHISM("This Java OOP concept lets programmers use the same word to mean different things in different contexts. Polymorphism in Java works by using a reference to a parent class to affect an object in the child class. Another form of polymorphism in Java are: method overloading and method overriding."), /** {@link org.inheritance.InheritanceExample} */
    REGULAR_EXPRESSIONS("A regular expression is a sequence of characters that forms a search pattern."), /** {@link RegularExpressions} */
    REQUEST_SPEC_BUILDER("Is an utility of RestAssured framework, that is used to construct an API request specifications. Opposite concept is ResponseSpecBuilder."),
    RESPONSE_SPEC_BUILDER("Is an utility of RestAssured framework, that is used to verify an API responses. Opposite concept is RequestSpecBuilder."),
    RESTASSURED("Java framework for testing REST APIs"),
    SELENIUM("Framework for automating browsers (Chrome, Edge, Firefox, ...) for UI (or even non-UI with headless option) testing."),
    SELENIUM_GRID("Selenium Grid is used to scale Selenium tests by distributing and running tests on several machines and manage multiple environments from a central point, making it easy to run the tests against a vast combination of browsers/OS."),
    SELENIUM_HEADLESS("Headless option of Selenium is an option where UI tests will be run without opening browser UI."),
    SERIALIZATION("In Rest Assured context is a process of converting a Java object into Request body (payload) in form of JSON with Jackson/Gson/Johnzon framework (or with JAXB framework for XML). Opposite concept is deserialization"),
    SETTERS("Setters are public methods of class that are assigning values to private class variables to achieve Encapsulation"), /** {@link org.abstraction.Encapsulation} */
    SPECIALIZATION("Specialization means creating new subclasses from an existing class. Is opposite concept of generalization"), /** {@link org.inheritance.InheritanceExample} and class {@link org.inheritance.Car} */
    STATIC_CLASS("Static class (nested class) is a class within another class"), /** {@link org.abstraction.StaticClassExample} */
    STATIC_METHOD("Method that can be executed without creating an object of the class first"), /** {@link org.abstraction.StaticClassExample} */
    STATIC_VARIABLE("Variable that can be accessed without creating an object of the class first"), /** {@link org.abstraction.StaticClassExample} */
    STATIC_POLYMORPHISM("Polymorphism that is resolved during compiler time is known as static polymorphism. Method overloading can be considered as static polymorphism example."), /** {@link org.abstraction.MethodExamples} */
    TESTNG("Java framework for test automation. This framework provides: annotations, flexible test configuration, parametrized tests, multithread testing, ..."),
    THREAD("Threads allows a program to operate more efficiently by doing multiple things at the same time."), /** {@link Threads1} and {@link Threads2} */
    THREAD_SAFE("This means that different threads can access the same resources without exposing erroneous behavior or producing unpredictable results. Thread safety is not achieved automatically in Java, but must be achieved by programmer."),
    TYPE_CASTING("Type casting is when you assign a value of one primitive data type to another type."),
    VARIABLE("Variables are containers for storing data values."), /** {@link Variables} */
    WRAPPER_CLASSES("Wrapper classes provide a way to use primitive data types (int, boolean, char, double, etc..) as objects (Integer, Boolean, Character, Double, etc.."); /** {@link CollectionsExample} */

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

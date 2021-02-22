package abstraction;


/**
 * Abstraction hides implementation details from user.
 * Abstract class => partial abstraction
 * Abstract class contains list of methods with their parameters and return values, without an implementation of these methods.
 * But it can also contain methods that are implemented!
 * Each abstract class can be implemented in different java class, that uses "extends AbstractClassName" in definition.
 */
public abstract class AbstractClassExample {
    //method that is not implemented
    public abstract void abstractClassMethodUnimplemented();

    //method that is implemented
    public void abstractClassMethodImplemented() {
        System.out.println("Implementation of method in Abstract class");
    }
}

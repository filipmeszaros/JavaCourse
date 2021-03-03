package org.packages.packageC;

/**
 * Class with examples of different access modifiers.
 * Access modifiers (private/default/public/protected) specifies the accessibility or scope of a
 * field, method, constructor, or class.
 *
 * PRIVATE   = accessible within our class only (not accessible from other classes, or other packages)
 * DEFAULT   = accessible within our class + within package
 * PROTECTED = accessible within our class + within package + outside package by subclass only
 * PUBLIC    = accessible from everywhere
 */
public class AccessModifiersClass {

    //From most strict to least strict: private -> default -> protected -> public
    private   String privateAttribute   = "Private";
              String defaultAttribute   = "Default";
    protected String protectedAttribute = "Protected";
    public    String publicAttribute    = "Public";

    //From most strict to least strict: private -> default -> protected -> public
    private void privateMethod() {
        System.out.println("Private method of Class1 from packageC");
    }

    void defaultMethod() {
        System.out.println("Default method of Class1 from packageC");
    }

    protected void protectedMethod() {
        System.out.println("Protected method of Class1 from packageC");
    }

    public void publicMethod() {
        System.out.println("Public method of Class1 from packageC");
    }

    public static void main(String args[]) {
        AccessModifiersClass am = new AccessModifiersClass();

        System.out.println("Private attribute: " + am.privateAttribute); //private attribute will work from the same class
        am.privateMethod();                                              //private method will work from the same class
    }
}

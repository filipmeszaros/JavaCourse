package org.packages.packageC;

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

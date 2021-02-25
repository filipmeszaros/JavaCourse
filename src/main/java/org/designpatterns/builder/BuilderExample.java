package org.designpatterns.builder;

/**
 * Builder pattern lets you to set properties of Class with multiple setters, which returns the same Class.
 * Note: I'm not sure if this pattern is called like this, but it is widely used.
 * Pattern is used for example in {@link org.openqa.selenium.interactions.Actions}, which is used like
 * Action a = new Action(driver);
 * a.dragAndDrop(source, target)
 *  .build()
 *  .perform();
 */
public class BuilderExample {
    private String productArea;
    private String feature;
    private String severity;
    private String description;

    public static void main(String[] argv) {
        BuilderExample builder = new BuilderExample();

        System.out.println("Printing default builder object created in constructor");
        printObject(builder);

        //set of builder object that will be saved to identical object
        builder.setFeature("Overwritten feature")
               .setSeverity("Overwritten severity")
               .setProductArea("Overwritten product area");

        System.out.println("---------------------------------------------------");

        //this will print overwritten strings that were set by builder setters
        System.out.println("Print of overwritten builder object set up in BuilderExample class");
        printObject(builder);
    }

    /**
     * Assign default values in constructor
     */
    public BuilderExample() {
        this.productArea = "Default product area";
        this.feature = "Default feature";
        this.severity = "Default severity";
        this.description = "Default description";
    }

    /**
     * Print each attribute of object
     */
    static void printObject(BuilderExample builder) {
        System.out.println("Severity: " + builder.severity);
        System.out.println("Product area: " + builder.productArea);
        System.out.println("Feature: " + builder.feature);
        System.out.println("Description: " + builder.description);
    }

    /**
     * Will set product area and return identical object
     */
    BuilderExample setProductArea(String productArea) {
        this.productArea = productArea;
        return this;
    }

    BuilderExample setFeature(String feature) {
        this.feature = feature;
        return this;
    }

    BuilderExample setSeverity(String severity) {
        this.severity = severity;
        return this;
    }

    BuilderExample setDescription(String description) {
        this.description = description;
        return this;
    }
}

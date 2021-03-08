package org.cucumber.stepdefinition;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.AfterStep;

/**
 * Cucumber hooks are methods that will be executed After/Before each Step or Annotation of *.feature file of Cucumber framewok.
 * They are similar to TestNG annotation @BeforeMethod, @AfterMethod, and others. See {@link org.testng.TestNgAnnotationExamples}
 */
public class Hooks {

    @BeforeStep
    public void methodBeforeStep() {
        System.out.println("This method will be run before each Step of *.feature file");
    }

    @AfterStep
    public void methodAfterStep() {
        System.out.println("This method will be run after each Step of *.feature file");
    }

    @Before("@RegressionTest")
    public void methodBeforeRegressionTest() {
        System.out.println("This method will be run before each scenario annotated with @RegressionTest");
        System.out.println("@RegressionTest started");
    }

    @After("@SmokeTest")
    public void methodAfterSmokeTest() {
        System.out.println("This method will be run after each scenario annotated with @SmokeTest");
        System.out.println("@SmokeTest ended");
    }
}

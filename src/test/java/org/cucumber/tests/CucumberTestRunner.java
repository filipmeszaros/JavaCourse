package org.cucumber.tests;

import io.cucumber.junit.Cucumber;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * If you want to run cucumber tests from TestNG, here is what you need to do:
 * 1, Add cucumber-testng to pom.xml
 * 2, Create Cucumber test runner (this class) that extends AbstractTestNGCucumberTests class
 * 3, Use RunWith and CucumberOption annotations with features and glues
 * 4, Add Cucumber test runner from point 2 to testng.xml (@see cucumberTestNG.xml)
 *
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/java/org/cucumber/features"},
        glue = {"org.cucumber.stepdefinition"}
)
public class CucumberTestRunner extends AbstractTestNGCucumberTests {
}

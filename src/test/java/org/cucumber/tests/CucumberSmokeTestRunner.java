package org.cucumber.tests;

import io.cucumber.junit.Cucumber;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * This runner will run scenarios in cucumber *.feature files annotated with @SmokeTest annotation.
 * It can be run directly from IDE, or it can be run by creating testng.xml file (see cucumberTestNG.xml)
 *
 * If you want to run cucumber tests from TestNG, here is what you need to do:
 * 1, Create Cucumber test runner (this class) that extends AbstractTestNGCucumberTests class
 * 2, Use RunWith and CucumberOption annotations with features and glues
 * 3, Add Cucumber test runner from point 1 to testng.xml (see file cucumberSmokeTestNG.xml)
 *
 * Note: you can try implementation of popular cucumber reporting tool for generating reports: maven-cucumber-reporting
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/java/org/cucumber/features"},
        glue = {"org.cucumber.stepdefinition"},
        tags = "@SmokeTest"          //run only tests with @SmokeTest annotation in *.feature files
)
public class CucumberSmokeTestRunner extends AbstractTestNGCucumberTests {
}

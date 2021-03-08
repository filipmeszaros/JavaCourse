package org.frameworkdevelopment;

/**
 * This empty class contains description about high-level overview of how to create your own testing framework.
 *
 * Steps to creating your own testing framework:
 * 1, Create a GIT repository for versioning and team collaboration
 * 2, Create maven project
 * 3, Add all dependencies that you will need to your pom.xml file (TestNG, Cucumber, Selenium, RestAssured, Log4j2, ...)
 * 4, Create settings.properties with all your global settings (usernames, location of webdrivers, ...)
 * 6, Create some utils class, in which you will implement all reusable methods for your framework
 * 5, Create some base configuration class in which you will configure Selenium, log4j2, waitings, and other frameworks.
 *    This class can extend utils class. This class will implement mechanism to driver global properties from settings.properties file
 * 6, Create an Enum class with constants to centralize all your constants
 * 7, Create some TestNG test class with some tests, that will extend your base configuration class
 * 8, Cucumber: Create cucumber *.feature file with definition of your features
 * 9, Cucumber: Implement Cucumber test runner to run all your tests
 * 10, Cucumber: Implement step definitions needed for testing your feature with Cucumber framework
 * 11, Cucumber: Implement pre and post conditions for test with Cucumber Hooks
 * 12, Cucumber: Implement maven-cucumber-reporting
 * 13, Add more tests and implement tagging mechanism to run selected groups of tests from TestRunner or testng.xml file
 * 14, Optional: create a TestNG listener to collect reports of your tests, save screenshots upon test failures, or automatically create JIRA issues
 * 15, Execute complete framework with maven
 *    mvn compile - will compile project
 *    mvn test    - will run all tests
 *    mvn test -Dcucumber.options="--tags @SmokeTest"  - will run only cucumber tests annotated with @SmokeTest annotation
 * 16, Create testng.xml file or Cucumber runners to contain multiple sets of tests (Smoke/Sanity/Regression) or add parallel execution of tests
 * 17, Integrate framework into CI/CD tool like Jenkins
 * 18, Implement parametrized Jenkins job to choose the global values at run time, like suite type (SmokeTests, RegressionTests, ...)
 * 19, ???
 * 20, Profit :-D 
 */
public class HowToDevelopTestingFramework {
    public static void main(String[] args) {
        System.out.println("Have fun :-)");
    }
}

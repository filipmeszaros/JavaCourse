package org.testng;

import org.selenium.SeleniumBasicTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

/**
 * TestNg framework allows user to use annotations for methods, that will be executed before/after each test method or test class or test suite.
 * For running test with testNG, you don't need main method, but you can annotate method with @Test annotation and run it.
 * If you run a method from Idea, annotated methods that are related will be executed as well in this order:
 * BeforeSuite -> BeforeTest -> BeforeClass -> BeforeMethod -> Test method -> AfterMethod -> AfterClass -> AfterTest -> AfterSuite
 *
 * You are able to create multiple test classes and test methods, and create XML file (@see testng.xml) with list of all tests,
 * if they should be executed in parallel, etc. All tests are divided to Suite/Test/Class/Method.
 * You can divide test in this testng.xml file to Smoke tests, Full regression tests, Sanity tests, etc.
 * If you run this testng.xml file (Right click -> Run as TestNG suite), you will see how all tests are executed from 2 different classes,
 * and BeforeSuite is executed as first. You will see test results from all methods.
 *
 */
@Test
public class TestNgAnnotationExamples extends SeleniumBasicTest {

    //Useful to prepare test suite - executed before running all test in <suite> tag of testng.xml
    @BeforeSuite
    public void beforeSuiteAnnotation() {
        System.out.println("@BeforeSuite annotation - TestNgAnnotationExamples class");
    }

    //Useful to tear-down test suite - executed after running all test in <suite> tag of testng.xml
    @AfterSuite
    public void afterSuiteAnnotation() {
        System.out.println("@AfterSuite annotation - TestNgAnnotationExamples class");
    }

    @BeforeTest //Executed before running all test in <test> tag of testng.xml
    public void beforeTestAnnotation() {
        System.out.println("@BeforeTest annotation - TestNgAnnotationExamples class");
    }

    @AfterTest //Executed after running all test in <test> tag of testng.xml
    public void afterTestAnnotation() {
        System.out.println("@AfterTest annotation - TestNgAnnotationExamples class");
    }

    //Executed before running all test in current class
    //Useful to prepare test class, like: login, access testing page, delete caches, delete cookies, etc.
    @BeforeClass
    public void beforeClassAnnotation() {
        System.out.println("@BeforeClass annotation - TestNgAnnotationExamples class");
    }

    //Executed after running all test in current class
    //Useful to tear-down test class, like: logout, restore database, delete cookies, etc.
    @AfterClass
    public void afterClassAnnotation() {
        System.out.println("@AfterClass annotation - TestNgAnnotationExamples class");
    }

    //Executed before running each test method in current class
    //Useful to prepare test method, like: access testing page, etc.
    @BeforeMethod
    public void beforeMethodAnnotation() {
        System.out.println("@BeforeMethod annotation - TestNgAnnotationExamples class");
    }

    //Executed after running each test method in current class
    //Useful to tear-down test method, like: restore database, etc.
    @AfterMethod
    public void afterMethodAnnotation() {
        System.out.println("@AfterMethod annotation - TestNgAnnotationExamples class");
    }

    @Test
    public void exampleTest1() throws InterruptedException {
        System.out.println("This is a exampleTest1 - TestNgAnnotationExamples class");
        Thread.sleep(3000);
    }

    @Test(groups={"Smoke", "Login"})   //will assign test to group Smoke and Login
    public void exampleTest2() throws InterruptedException {
        System.out.println("This is a exampleTest2 - TestNgAnnotationExamples class");
        Thread.sleep(2500);
    }

    @Test(timeOut = 30000)   //sets timeout of this test to 30 seconds
    public void exampleTest3() throws InterruptedException {
        System.out.println("This is a exampleTest3 - TestNgAnnotationExamples class");
        Thread.sleep(2000);
    }

    @Test
    public void exampleTest4() throws InterruptedException {
        System.out.println("This is a exampleTest4 - TestNgAnnotationExamples class");
        Thread.sleep(1500);
    }

    @Test
    public void exampleTest5() throws InterruptedException {
        System.out.println("This is a exampleTest5 - TestNgAnnotationExamples class");
        Thread.sleep(1000);
    }

    @Test
    public void exampleTest6Fail() {
        System.out.println("This is a exampleTest6Fail - TestNgAnnotationExamples class");
        driver.get("https://www.google.com");
        assertTrue(false);
    }

    @Test(dependsOnMethods = {"exampleTest6Fail"})
    public void exampleTest7Skip() {
        System.out.println("This is a exampleTest7Skipped - TestNgAnnotationExamples class");
        assertTrue(false);
    }
}

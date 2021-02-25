package org.testng;

import org.selenium.SeleniumBasicTest;
import org.testng.annotations.*;

import static org.testng.Assert.assertTrue;

/**
 * For description, @see org.testng.TestNgAnnotationExamples, and testng.xml
 */
public class TestNgAnnotationExamples2 extends SeleniumBasicTest {

    @BeforeTest
    public void beforeTestAnnotation() {
        System.out.println("@BeforeTest annotation - TestNgAnnotationExamples2 class");
    }

    @AfterTest
    public void afterTestAnnotation() {
        System.out.println("@AfterTest annotation - TestNgAnnotationExamples2 class");
    }

    @BeforeClass
    public void beforeClassAnnotation() {
        System.out.println("@BeforeClass annotation - TestNgAnnotationExamples2 class");
    }

    @AfterClass
    public void afterClassAnnotation() {
        System.out.println("@AfterClass annotation - TestNgAnnotationExamples2 class");
    }

    @BeforeMethod
    public void beforeMethodAnnotation() {
        System.out.println("@BeforeMethod annotation - TestNgAnnotationExamples2 class");
    }

    @AfterMethod
    public void afterMethodAnnotation() {
        System.out.println("@AfterMethod annotation - TestNgAnnotationExamples2 class");
    }

    @Test
    public void example2Test1() throws InterruptedException {
        System.out.println("This is a example2Test1 - TestNgAnnotationExamples2 class");
        Thread.sleep(1000);
    }

    @Test
    public void example2Test2() throws InterruptedException {
        System.out.println("This is a example2Test2 - TestNgAnnotationExamples2 class");
        Thread.sleep(1500);
    }

    @Test
    public void example2Test3() throws InterruptedException {
        System.out.println("This is a example2Test3 - TestNgAnnotationExamples2 class");
        Thread.sleep(2000);
    }

    @Test
    public void example2Test4Fail() {
        System.out.println("This is a example2Test4 - TestNgAnnotationExamples2 class");
        driver.get("https://www.facebook.com");
        assertTrue(false);
    }

    @Test(dependsOnMethods = {"example2Test4Fail"})
    public void example2Test5Skip() {
        System.out.println("This is a example2Test5Skip - TestNgAnnotationExamples2 class");
        driver.get("https://www.facebook.com");
    }
}

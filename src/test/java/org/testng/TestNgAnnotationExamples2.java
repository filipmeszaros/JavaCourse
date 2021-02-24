package org.testng;

import org.testng.annotations.*;

/**
 * For description, @see org.testng.TestNgAnnotationExamples, and testng.xml
 */
public class TestNgAnnotationExamples2 {

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
}

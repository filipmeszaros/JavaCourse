package org.testng;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Here are some other TestNG annotation examples that can be used.
 * @DataProvider - Provides a way of how to run one test multiple time, with different input parameters fetched from Java code
 * @Test(enabled=false) - Provides a way of how to ignore a test
 * @Test(dependsOnMethods = {"method"}) - Provides a way of how to set a prerequisity for a test
 * @Parameter - Provides a way of how to run one test multiple times, with different input parameters fetched from XML file
 */
public class TestNgConstructsExamples {

    @Test
    public void enabledTest() {
        System.out.println("This test is enabled");
    }

    @Test(enabled=false)
    public void disabledTest() {
        System.out.println("This test is disabled");
    }

    /**
     * This test will run multiple times with different inputs, that are provided by searchQueries data provider object.
     * When you want to add dataprovider from other class, you can use parameter dataProviderClass = ClassName.class
     * @param  searchQuery          search query input from data provider
     * @throws InterruptedException for Thread.sleep
     */
    @Test(dataProvider="searchQueries")
    public void testWithMultipleInputs(String searchQuery) throws InterruptedException {
        System.out.println("Search query provided is '" + searchQuery + "'");
        Thread.sleep(500);
        assertTrue(true);
    }

    @DataProvider
    public Object[] searchQueries() {
        String[] searchQueries = new String[3];
        searchQueries[0] = "FirstSearchQuery";
        searchQueries[1] = "SecondSearchQuery";
        searchQueries[2] = "ThirdSearchQuery";
        return searchQueries;
    }

    /**
     * This test will run multiple times with different combination of email and password, that are provided by data provider
     */
    @Test(dataProvider="loginData")
    public void tryToLogIn(String email, String password, boolean shouldLogin) throws InterruptedException {
        System.out.println("Trying to login with email '" + email + "' and password '" + password + "'");
        Thread.sleep(500);
        //Assert that login was successful
        if (shouldLogin == true) {
            assertTrue(true);
        //Assert that login failed
        } else {
            assertFalse(false);
        }
    }

    /**
     * Format of data provider is: email, password, boolean value if login details are OK
     */
    @DataProvider(name = "loginData")
    public Object[][] loginDataProvider() {
        return new Object[][]{
                {"email@gmail.com", "incorrectPassword", false},
                {"mail@gmail.com", "correctPassword", true},
                {"email@domain.com", "incorrect", false},
                {"email@domain.com", "correct", true},
                {"email@domain.com", " ", false},
        };
    }

    @Test(dependsOnMethods = {"tryToLogIn"})
    public void secondTest() {
        System.out.println("This test will be always executed after tryToLoginTest, even if you execute it alone");
    }
}

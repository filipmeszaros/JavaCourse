package org.selenium.guru99login;

import org.selenium.SeleniumConfiguration;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.assertTrue;

/**
 * Simple test that will login to page http://demo.guru99.com/V4/ with credentials and assert that login was successful.
 * First test is using Page Object model.
 * Second test is using Page Factory model.
 * Tests are basically identical, but you can see distinction in PageFactory/PageObject classes.
 */
public class Guru99LoginTest extends SeleniumConfiguration {

    //Important: this serves as a learning purposes for distinction between Page Object and Page Factory models
    //You can use only Page Object, or only Page Factory classes in your tests (no need to create them twice)

    //Page Object - All methods are maintained in these classes
    Guru99LoginPagePageObject loginPage;
    Guru99HomePagePageObject homePage;

    //Page Factory - All methods are maintained in these classes
    Guru99LoginPagePageFactory loginPage2;
    Guru99HomePagePageFactory homePage2;

    @BeforeTest
    public void openPage() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://demo.guru99.com/V4/");
    }

    /**
     * This test case will login in http://demo.guru99.com/V4/
     * Verify login page title as guru99 bank
     * Login to application
     * Verify the home page using Dashboard message
     */
    @Test(priority=0)
    public void testLoginPageWithPageObjectPattern(){
        //Create Login Page object
        loginPage = new Guru99LoginPagePageObject(driver);

        //Verify login page title
        String loginPageTitle = loginPage.getLoginTitle();
        assertTrue(loginPageTitle.contains("Guru99 Bank"));

        //Login to Guru99 web-application
        loginPage.loginToGuru99("mgr123", "mgr!23");

        //Create Home Page object - because now we are on home page
        homePage = new Guru99HomePagePageObject(driver);

        //Verify home page text
        assertTrue(homePage.getHomePageDashboardUserName().contains("Manger Id : mgr123"));

        homePage.logout();
    }

    /**
     * This test case will login in http://demo.guru99.com/V4/
     * Verify login page title as guru99 bank
     * Login to application
     * Verify the home page using Dashboard message
     */
    @Test(priority=1)
    public void testLoginPageWithPageFactoryPattern(){
        //Create Login Page object
        loginPage2 = new Guru99LoginPagePageFactory(driver);

        //Verify login page title
        String loginPageTitle = loginPage2.getLoginTitle();
        assertTrue(loginPageTitle.contains("Guru99 Bank"));

        //Login to Guru99 web-application
        loginPage2.loginToGuru99("mgr123", "mgr!23");

        //Create Home Page object - because now we are on home page
        homePage2 = new Guru99HomePagePageFactory(driver);

        //Verify home page text
        assertTrue(homePage2.getHomePageDashboardUserName().contains("Manger Id : mgr123"));

        homePage2.logout();
    }
}

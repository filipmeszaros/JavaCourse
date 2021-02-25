package org.selenium;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.util.concurrent.TimeUnit;

/**
 * Basic setup configuration for Selenium WebDriver used in our tests.
 * You need to download ChromeDriver for your version of Chrome browser that you are using.
 */
public class SeleniumConfiguration {

    public static WebDriver driver;

    @BeforeSuite
    public void setProperties() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Filip\\JavaLibraries\\chromedriver.exe");
        driver = new ChromeDriver();

        //Set implicit waiting (global waiting for all operations of webdriver)
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.manage().window().maximize(); //maximize window
    }

    @AfterSuite
    public void closeDriver() {
        driver.quit();
    }
}

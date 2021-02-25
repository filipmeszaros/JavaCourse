package org.selenium;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

/**
 * Basic setup configuration for Selenium.
 * You need to download ChromeDriver for your version of Chrome browser that you are using
 */
public class SeleniumConfiguration {

    public static WebDriver driver;

    @BeforeSuite
    public void setProperties() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Filip\\JavaLibraries\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @AfterSuite
    public void closeDriver() {
        driver.quit();
    }
}

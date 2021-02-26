package org.selenium;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Basic setup configuration for Selenium WebDriver used in our tests.
 * You need to download ChromeDriver for your version of Chrome browser that you are using.
 */
public class SeleniumConfiguration {

    public static WebDriver driver;

    @BeforeSuite
    public void configureProperties() throws IOException {
        //How to load properties from configuration file
        final String PROPERTIES_FILE_PATH = System.getProperty("user.dir") + "\\" + "settings.properties";
        Properties properties = new Properties();
        FileInputStream propertiesFile = new FileInputStream(PROPERTIES_FILE_PATH);
        properties.load(propertiesFile);

        //Load property from file
        System.out.println("Tester email: " + properties.getProperty("tester.email"));

        //Check what kind of browser we want to use from properties file
        //Then read driver location from properties file and set it to system
        if (properties.getProperty("browser").equalsIgnoreCase("chrome")) {
            System.out.println("Selected browser for testing is: Chrome");
            System.out.println("Going to set chrome.driver to: " + properties.getProperty("webdriver.chrome.driver"));

            System.setProperty("webdriver.chrome.driver", properties.getProperty("webdriver.chrome.driver"));
            driver = new ChromeDriver();
        } else if (properties.getProperty("browser").equals("firefox")) {
            System.out.println("Selected browser for testing is: Firefox");
            System.out.println("Going to set gecko.driver to: " + properties.getProperty("webdriver.gecko.driver"));System.setProperty("webdriver.gecko.driver", properties.getProperty("webdriver.gecko.driver"));

            System.setProperty("webdriver.gecko.driver", properties.getProperty("webdriver.gecko.driver"));
            driver = new FirefoxDriver();
        } else if (properties.getProperty("browser").equals("edge")) {
            System.out.println("Selected browser for testing is: Microsoft Edge");
            System.out.println("Going to set edge.driver to: " + properties.getProperty("webdriver.edge.driver"));

            System.setProperty("webdriver.edge.driver", properties.getProperty("webdriver.edge.driver"));
            driver = new EdgeDriver();
        }

        //Generate current time in readable format
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        Date currentDate = new Date(System.currentTimeMillis());

        //Save current time to properties file - currently skipped
        //properties.setProperty("last.modified.time", formatter.format(currentDate));
        //FileOutputStream propertiesOutputFile = new FileOutputStream(PROPERTIES_FILE_PATH);
        //properties.store(propertiesOutputFile, "Properties file opened and re-saved from SeleniumCofiguration.java");

        //Set implicit waiting (global waiting for all operations of webdriver)
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.manage().window().maximize(); //maximize window
    }

    @AfterSuite
    public void closeDriver() {
        driver.quit();
    }
}

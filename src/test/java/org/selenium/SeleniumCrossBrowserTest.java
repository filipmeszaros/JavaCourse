package org.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.assertTrue;

/**
 * This is a simple test that will be executed with 2 threads, in 3 different browsers: Chrome, Firefox, Edge.
 * Test is parametrized from crossBrowserTest.xml.
 * From this XML, different browser versions are passed to a BeforeTest method, which sets up test.
 * After it, test is executed, each time with different browser.
 * To run this test, right click on crossBrowserTest.xml from IDE, and select Run.
 */
public class SeleniumCrossBrowserTest {

    WebDriver driver;

    /**
     * This function will set up WebDriver either to Chrome, Firefox or Edge, based on settings.properties.
     * Method is parametrized, which means that browser name is fetched from crossBrowserTest.xml.
     * @param browser fetched from crossBrowserTest.xml
     * @throws Exception when wrong browser is set up
     */
    @BeforeTest
    @Parameters("browser")
    public void setup(String browser) throws Exception {

        //load settings.properties file with path to webdrivers
        final String PROPERTIES_FILE_PATH = System.getProperty("user.dir") + "\\" + "settings.properties";
        Properties properties = new Properties();
        FileInputStream propertiesFile = new FileInputStream(PROPERTIES_FILE_PATH);
        properties.load(propertiesFile);

        //Check if parameter passed from TestNG file is 'firefox'
        if(browser.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", properties.getProperty("webdriver.gecko.driver"));
            driver = new FirefoxDriver();
        //Check if parameter passed from TestNG file is 'chrome'
        } else if(browser.equalsIgnoreCase("chrome")){
            System.setProperty("webdriver.chrome.driver", properties.getProperty("webdriver.chrome.driver"));
            driver = new ChromeDriver();
        //Check if parameter passed from TestNG file is 'Edge'
        } else if(browser.equalsIgnoreCase("Edge")){
            System.setProperty("webdriver.edge.driver", properties.getProperty("webdriver.edge.driver"));
            driver = new EdgeDriver();
        } else{
            //If no browser is passed throw exception
            throw new Exception("Incorrect Browser");
        }

        //Set up implicit waiting
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    /**
     * Close browser after test, soo that browsers won't stay opened
     */
    @AfterTest
    public void closeBrowser() {
        driver.quit();
    }

    /**
     * Basic test for alerts. What is special about this test is that it will be executed for 3 different browsers.
     */
    @Test
    public void testParameterWithXML() {
        final String TEXT = "Text that will be displayed in alert";
        driver.get("http://qaclickacademy.com/practice.php");

        driver.findElement(By.id("name")).sendKeys(TEXT);
        driver.findElement(By.id("alertbtn")).click();

        String alertMsg = driver.switchTo().alert().getText();  //we need to switch context to alert window first
        //assert correct alert message is displayed and accept alert
        assertTrue("Alert contains text that was set up", alertMsg.contains(TEXT));
        driver.switchTo().alert().accept();
    }
}

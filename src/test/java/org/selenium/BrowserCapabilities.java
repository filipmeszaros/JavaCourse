package org.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Browser Capabilities is a way of how to set up WebDriver settings in Selenium tests.
 * Desired Capabilities is a class in Selenium used to set properties of browsers to perform cross browser testing of web applications.
 * It stores the capabilities as key-value pairs and these capabilities are used to set browser properties like
 * browser name, browser version, path of browser driver in the system,
 * to determine the behaviour of browser at run time.
 * Desired Capabilities are needed because every Testing scenario should be executed on some specific testing environment.
 */
public class BrowserCapabilities {

    public static void main(String[] args) {

        //Create DesiredCapabilities object for chrome
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();

        //Do some settings
        //capabilities.acceptInsecureCerts(); //same as next 2 commands
        capabilities.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true); //accept insecure SSL certificates
        capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        //capabilities.setBrowserName("chrome"); //same as next command
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "chrome");

        //Create ChromeOptions object and then merge it with desired capabilities
        ChromeOptions options = new ChromeOptions();
        options.merge(capabilities);
        options.addArguments("headless");   //headless means that no UI will be used. Even UI tests will be run without opening Chrome.
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");

        WebDriver driver = new ChromeDriver(options); //webdriver will now accept insecure SSL certificates, and use Chrome without UI
    }
}

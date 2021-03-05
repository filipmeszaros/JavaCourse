package org.selenium;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.assertTrue;

/**
 * Selenium Grid is used to scale Selenium tests by distributing and running tests on several machines and manage
 * multiple environments from a central point, making it easy to run the tests against a vast combination of browsers/OS.
 *
 * Selenium Grid Architecture:
 * Hub - is the central point where you load your tests into, that is launched on single machine
 * Nodes - are the Selenium instances that will execute the tests that you loaded on the hub. Selenium grid can contain multiple nodes.
 *
 * How to set up Selenium Grid:
 * 1, Download Selenium Server Jar from https://www.selenium.dev/downloads/ to a Hub and all Nodes
 * 2, On all computers, download and set up Java
 * 3, On all Nodes, download WebDriver
 * 4, Setup hub: $java -jar selenium-server-standalone-3.141.jar -role hub
 * 5, Setup node: $java -Dwebdriver.chrome.driver="C:\chromedriver.exe" -jar selenium-server-standalone-3.141.jar -role webdriver -hub http://HUB_IP:hub_port/grid/register -port 5566
 * 6, Accessing console from Hub: http://<HUB_IP>:<HUB_PORT>/grid/console
 * 7, Execute Java test on different RemoteWebDriver remotely.
 *
 * Note: this test will fail and therefore is ignored. You need to setup Selenium Grid Hub and Node for it to properly function.
 * If you want to execute test in parallel, with different browsers, see class {@link SeleniumCrossBrowserTest}
 */
public class SeleniumGridTest {

    public WebDriver driver;

    /**
     * Set up remote driver for test, with hardcoded URL and set up for Chrome
     * @throws MalformedURLException
     */
    @BeforeTest
    public void setUpRemoteDriver() throws MalformedURLException {
        System.out.println("Executing test on Chrome");
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setBrowserName("chrome");
        capabilities.setPlatform(Platform.WIN10);
        String Node = "http://192.168.1.3:5567/wd/hub";
        driver = new RemoteWebDriver(new URL(Node), capabilities);
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
     * Execute test remotely with RemoteWebDriver()
     * Note: this test will fail and therefore is ignored. You need to setup Selenium Grid Hub and Node for it to properly function.
     */
    @Test(enabled = false)
    public void simpleRemoteTest () {
        driver.get("https://www.facebook.com");
        assertTrue("Page url contains facebook", driver.getCurrentUrl().contains("facebook"));
    }
}

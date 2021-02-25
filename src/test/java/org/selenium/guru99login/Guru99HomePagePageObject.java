package org.selenium.guru99login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.selenium.SeleniumConfiguration;

/**
 * Guru99 Home page implemented with Page Object pattern
 * Note: see differences with {@link Guru99HomePagePageFactory}
 */
public class Guru99HomePagePageObject extends SeleniumConfiguration {

    By homePageUserName = By.xpath("//table//tr[@class='heading3']");

    public Guru99HomePagePageObject(WebDriver driver){
        this.driver = driver;
    }

    //Get the User name from Home Page
    public String getHomePageDashboardUserName(){
        return driver.findElement(homePageUserName).getText();
    }
}

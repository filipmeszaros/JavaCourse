package org.selenium.guru99login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.selenium.SeleniumConfiguration;

/**
 * Guru99 Home page implemented with Page Factory pattern
 * Note: see differences with {@link Guru99HomePagePageObject}
 */
public class Guru99HomePagePageFactory extends SeleniumConfiguration {

    @FindBy(xpath="//table//tr[@class='heading3']")
    WebElement homePageUserName;

    @FindBy(xpath="//a[text()='Log out']")
    WebElement logoutButton;

    public Guru99HomePagePageFactory(WebDriver driver){
        this.driver = driver;

        //This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
    }

    //Get the User name from Home Page
    public String getHomePageDashboardUserName(){
        return homePageUserName.getText();
    }

    //logout
    public void logout() {
        logoutButton.click();
    }
}

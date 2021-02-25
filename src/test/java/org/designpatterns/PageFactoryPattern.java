package org.designpatterns;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.selenium.SeleniumBasicTest;

/**
 * Page Factory in Selenium is an inbuilt Page Object Model framework concept for Selenium WebDriver but it is very optimized.
 * It is used for initialization of Page objects or to instantiate the Page object itself.
 * It is also used to initialize Page class elements without using "findElements()"
 * Additionally, with the help of class PageFactory in Selenium, we use annotations @FindBy to find WebElement.
 * Basically: Page Object and Page Factory are 2 very similar ways of creating the same thing.
 * See {@link org.selenium.guru99login.Guru99LoginTest that is using both methods}
 */
public class PageFactoryPattern extends SeleniumBasicTest {
    @FindBy(name="username") WebElement userName;
    @FindBy(name="password") WebElement password;
    @FindBy(name="button_login") WebElement loginButton;

    public PageFactoryPattern(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebElement userName() {
        return userName;
    }

    public WebElement password() {
        return password;
    }

    public WebElement loginButton() {
        return loginButton;
    }
}

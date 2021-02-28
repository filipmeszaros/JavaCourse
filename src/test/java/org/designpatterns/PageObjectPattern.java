package org.designpatterns;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.selenium.SeleniumBasicTest;

/**
 * Page Object is a design pattern, popularly used in test automation that creates Object Repository for web UI elements.
 * The advantage of the model is that it reduces code duplication and improves test maintenance.
 * Under this model, for each web page in the application, there should be a corresponding Page Class.
 * This Page class will identify the WebElements of that web page
 * and also contains Page methods which perform operations on those WebElements.
 *
 * Advantages:
 *   - better readability
 *   - reduces code duplication
 *   - improves test maintenance
 *
 * Example:
 * When testing login page of Facebook, you don't want to implement method setEmail in SuccessfulLoginTest and UnsuccessfulLoginTest.
 * You want to implement method setEmail() in FacebookLoginPage class, and you will be using this class in all tests.
 * This class will contain setEmail(), setPassword(), savePassword(), login(), acceptCookies() and other methods.
 * You will be maintaining all methods in this one class, and you will be using this class in all testing classes.
 */
public class PageObjectPattern extends SeleniumBasicTest {

    //Elements on page that we are implementing
    By userName = By.name("username");
    By password = By.name("password");
    By loginButton = By.name("button_login");

    public PageObjectPattern(WebDriver driver) {
        this.driver = driver;
    }

    //Method that will set username
    public void setUserName(String username) {
        driver.findElement(userName).sendKeys(username);
    }

    //Method that will set password
    public void setPassword(String passwd) {
        driver.findElement(password).sendKeys(passwd);
    }

    //Method that will click on login
    public void clickLogin(){
        driver.findElement(loginButton).click();
    }

    //In other testing class, you will create object of this class, and you will call methods setUserName(), setPassword() and clickLogin()
    //instead of implementing these methods in test class.
    //Why? Because you will have it in one class, instead of on 10 places (in case of 10 test methods), and it is easier to read.
}

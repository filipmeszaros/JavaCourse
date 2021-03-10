package org.designpatterns.pageobject;

import org.openqa.selenium.By;

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
public class HomePageObject {

    //Set private locators of our webpage elements
    By userName = By.name("username");
    By password = By.name("password");
    By loginButton = By.name("login_button");
    By rememberMeCheckbox = By.name("remember_me");

    /**
     * Sets username to our webpage
     * @param username username that will be set
     */
    public void setUserName(String username) {
        //... implementation omitted ...
    }

    /**
     * Sets password to our webpage
     * @param password password that will be set
     */
    public void setPassword(String password) {
        //... implementation omitted ...
    }

    /**
     * Method that will toggle (check/uncheck) remember me checkbox
     */
    public void toggleRememberMeCheckbox(){
        //... implementation omitted ...
    }

    /**
     * Method that will return true if remember me checkbox is checked
     */
    public boolean isRememberMeChecked(){
        //... implementation omitted ...
        return true;
    }

    /**
     * Method that will click on login
     */
    public void login(){
        //... implementation omitted ...
    }

    /**
     * Method that will fill our user details and login to webpage
     * @param username username
     * @param password password
     * @param checkRememberMe if remember me checkbox should stay checked or unchecked before login
     */
    public void loginWithUserDetails(String username, String password, boolean checkRememberMe){
        //... implementation omitted ...
    }
}

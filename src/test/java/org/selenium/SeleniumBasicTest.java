package org.selenium;

import org.testng.annotations.Test;

public class SeleniumBasicTest extends SeleniumConfiguration {

    @Test
    public void openFacebookTest() {
        driver.get("https://www.facebook.com");
    }
}

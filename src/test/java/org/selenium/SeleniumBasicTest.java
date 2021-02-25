package org.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.testng.AssertJUnit.*;

/**
 * Basic constructions in Seleniu, each in separate test.
 */
public class SeleniumBasicTest extends SeleniumConfiguration {


    @Test
    public void openFacebookTest() {
        driver.get("https://www.facebook.com");
        assertTrue("Facebook page was successfully opened", driver.getTitle().contains("Facebook"));
    }

    /**
     * When you open URL http://the-internet.herokuapp.com/basic_auth, alert with authorization will pop-up.
     * This test will bypass alert with authorization by providing authorization details directly in URL.
     */
    @Test
    public void authorizationAlertHandlingTest() {
        final String SUCCESS_MESSAGE = "Congratulations! You must have the proper credentials";

        driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
        assertTrue("Successfully logged in", driver.getPageSource().contains(SUCCESS_MESSAGE));
    }

    /**
     * Check a checkbox, assert if checkbox is checked and unchecked, and assert number of checkboxes on whole page
     */
    @Test
    public void workingWithCheckboxes() {
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");

        //Click on first checkbox and assert that it is checked
        WebElement checkbox1 = driver.findElement(By.xpath("//*[@id='checkBoxOption1']"));
        checkbox1.click();
        assertTrue("Checkbox1 is checked", checkbox1.isSelected());
        assertFalse("Checkbox2 is unchecked", driver.findElement(By.xpath("//*[@id='checkBoxOption2']")).isSelected());

        //Get checkbox count
        int checkboxCount = driver.findElements(By.xpath("//*[@type='checkbox']")).size();
        assertEquals("Number of checkboxes on page is 3", checkboxCount, 3);
    }

    /**
     * Find select in page, print list of options, select by index, select by value
     */
    @Test
    public void workingWithSelectBoxes() {
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");

        Select select = new Select(driver.findElement(By.tagName("select")));
        List<WebElement> options = select.getOptions();
        System.out.println("Options of select are: " + options.stream().map(o->o.getText()).collect(Collectors.toList()).toString());
        
        assertEquals("Default selected option is 'Select'", select.getFirstSelectedOption().getText(), "Select");
        select.selectByIndex(2);
        assertEquals("Newly selected option is 'Option2'", select.getFirstSelectedOption().getText(), "Option2");
        select.selectByVisibleText("Option3");
        assertEquals("Newly selected option is 'Option3'", select.getFirstSelectedOption().getText(), "Option3");
    }

    /**
     * Explicit waiting of 5s until ajax loading disappears
     */
    @Test
    public void explicitWaiting() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        driver.get("http://www.itgeared.com/demo/1506-ajax-loading.html");

        driver.findElement(By.xpath("//a[contains(text(), 'Click to load')]")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='loader']")));
        assertTrue("Loader disappeared successfully", driver.findElement(By.xpath("//div[@id='results']")).getText().contains("Process completed!"));
    }

    /**
     * Open a page that will open a new tab. Try switching between those 2 tabs
     */
    @Test
    public void workingWithMultipleWindows() {
        driver.get("https://the-internet.herokuapp.com");
        driver.findElement(By.linkText("Multiple Windows")).click();
        driver.findElement(By.linkText("Click Here")).click(); //will open second tab

        //Get window handles
        Set<String> ids = driver.getWindowHandles();
        Iterator<String> it = ids.iterator();
        String parentWindowId = it.next();   //parent window is always the first one
        String childWindowId = it.next();    //child window is always the second one

        //Switch to child window and assert it
        driver.switchTo().window(childWindowId);
        assertTrue("Second tab is opened", driver.findElement(By.tagName("h3")).getText().contains("New Window"));

        //Switch to parent window (first tab) and assert it
        driver.switchTo().window(parentWindowId);
        assertTrue("First tab is opened", driver.findElement(By.tagName("h3")).getText().contains("Opening a new window"));
    }

    /**
     * Before Selenium can work with elements on a specific frame, you must switch to it.
     * Open a page with nested frames, and switch between those frames.
     * Page outline:
     * top frame - contains 3 frames (left, middle, right)
     * bottom frame - contains bottom frame
     */
    @Test
    public void workingWithFrames() {
        String frameText;
        driver.get("https://the-internet.herokuapp.com");
        driver.findElement(By.linkText("Nested Frames")).click();
        driver.switchTo().frame("frame-top");
        driver.switchTo().frame("frame-middle");
        frameText = driver.findElement(By.id("content")).getText();
        assertEquals("Text of middle frame is correct", frameText, "MIDDLE");

        driver.switchTo().defaultContent(); //switch back to default page - back from middle frame
        driver.switchTo().frame("frame-bottom");
        frameText = driver.findElement(By.tagName("body")).getText();
        assertEquals("Text of bottom frame is correct", frameText, "BOTTOM");

        driver.switchTo().defaultContent(); //switch back to default page - back from bottom frame
        driver.switchTo().frame("frame-top");
        driver.switchTo().frame("frame-right");
        frameText = driver.findElement(By.tagName("body")).getText();
        assertEquals("Text of right frame is correct", frameText, "RIGHT");
    }
}

package org.selenium;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static org.testng.AssertJUnit.assertTrue;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotSame;

/**
 * Basic constructions of Selenium, each in separate test.
 * Some small documentation of WebDriver methods:
 *  - getCurrentUrl()       - gets current URL
 *  - getPageSource()       - gets source code of page
 *  - navigate().back()     - back button
 *  - navigate().forward()  - forward button
 *  - close()               - closes browser window
 *  - quit()                - closes all browses windows opened by Selenium
 *  - findElement(locator)  - finds first WebElement of given locator on page
 *  - findElements(locator) - finds all WebElements of given locator on page
 *  - findElement(locator).isDisplayed() - returns true if element is displayed
 *  - findElement(locator).isSelected()  - returns true if element is selected
 *  - manage().window().maximize()       - maximize window
 *  - manage().window().minimize()       - minimize window
 *  - manage().deleteAllCookies()        - deletes cookies
 *  - manage().deleteCookieNamed("cookieName") - delete cookie with given name
 *  - manage().window().getSize().getHeight()  - get window height
 *  - manage().window().getSize().getWidth()   - get window width
 *  - getWindowHandles()                       - get window handles for working with multiple windows
 *  - switchTo().window("windowName")          - switch to window with given name
 *  - navigate().refresh()                     - refreshes window
 *  - switchTo().newWindow(WindowType.TAB)     - opens new window in Selenium 4
 *  - switchTo().frame("frameName")            - switch Selenium context to iframe/frame
 *  - switchTo().defaultContent()              - switch Selenium context to default window
 */
public class SeleniumBasicTest extends SeleniumConfiguration {

    public static Logger log = LogManager.getLogger(SeleniumBasicTest.class.getName());

    @Test
    public void workingWithWebDriver() {
        driver.get("https://www.google.com/search?=selenium");
        driver.manage().window().maximize();

        driver.manage().addCookie(new Cookie("CUSTOM_COOKIE", "myCookieValue", "/"));
        Set<Cookie> cookies = driver.manage().getCookies();
        for (Cookie cookie : cookies) {
            System.out.println("Google cookies: " + cookie.toString());
        }
        driver.manage().deleteAllCookies();

        System.out.println("window width is: " + driver.manage().window().getSize().getWidth());
        System.out.println("window height is: " + driver.manage().window().getSize().getHeight());
    }

    /**
     * Open URL, then open another URL, then go back, then go forward, and assert URLs and titles.
     */
    @Test
    public void openURLsBackAndForward() {
        final String FACEBOOK_URL = "https://www.facebook.com/";
        final String GOOGLE_URL   = "https://www.google.com/";
        driver.get(FACEBOOK_URL);
        assertTrue("Facebook page was successfully opened", driver.getTitle().contains("Facebook"));
        assertEquals("Facebook URL is OK", FACEBOOK_URL, driver.getCurrentUrl());
        driver.get(GOOGLE_URL);
        assertTrue("Google page was successfully opened", driver.getTitle().contains("Google"));
        assertEquals("Google URL is OK", GOOGLE_URL, driver.getCurrentUrl());
        driver.navigate().back();    //back button
        assertEquals("URL after going back is OK", FACEBOOK_URL, driver.getCurrentUrl());
        driver.navigate().forward(); //forward button
        assertEquals("URL after going forward is OK", GOOGLE_URL, driver.getCurrentUrl());
    }

    /**
     * Check if alert button is displayed on page
     */
    @Test
    public void visibilityOfElements() {
        driver.get("https://www.rahulshettyacademy.com/AutomationPractice/");

        WebElement alertButton = driver.findElement(By.id("alertbtn"));
        assertTrue("WebElement of alert button is displayed", alertButton.isDisplayed());
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
        log.info("Options of select are: " + options.stream().map(o->o.getText()).collect(Collectors.toList()).toString());
        
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
        driver.get("https://the-internet.herokuapp.com/windows");
        driver.findElement(By.linkText("Click Here")).click(); //click will open second tab

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
     * This test find a footer element, and then finds all links in footer (working with limited scope).
     * Then we open all footer links on new tab with keypress CTRL + ENTER
     * Then we close all tabs except one
     */
    @Test
    public void workingWithMultipleWindows2() {
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");

        //limit scope to footer and find all links (tag <a>) in a footer
        WebElement footer = driver.findElement(By.xpath("//*[contains(@class,'footer_top')]"));
        List<WebElement> linksInFooter = footer.findElements(By.tagName("a"));
        //Open all links in NEW tab
        for (WebElement link : linksInFooter) {
            System.out.println("Opening link in footer in new tab: " + link.getAttribute("href"));
            link.sendKeys(Keys.CONTROL, Keys.ENTER);
        }

        //Get all tabs except current one, loop through them and close them all
        String originalHandle = driver.getWindowHandle();

        for(String handle : driver.getWindowHandles()) {
            if (!handle.equals(originalHandle)) {
                driver.switchTo().window(handle);
                driver.close();
            }
        }

        driver.switchTo().window(originalHandle);
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
        driver.get("https://the-internet.herokuapp.com/nested_frames");
        driver.switchTo().frame("frame-top");
        int framesCount = driver.findElements(By.tagName("frame")).size();  //get number of frames within top frame
        assertEquals("Top frame should contain 3 frames", framesCount, 3);

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

        driver.switchTo().defaultContent(); //switch back to default page - back from right frame
        driver.switchTo().frame("frame-top");
        driver.switchTo().frame(0);      //switch to first frame (left one)
        frameText = driver.findElement(By.tagName("body")).getText();
        assertEquals("Text of left frame is correct", frameText, "LEFT");

        driver.switchTo().defaultContent();
    }

    /**
     * Open a page, set an input text to input alert field, click on alert.
     * Alert pop-up will appear. Before we start working with alert window, we need to switch context to it.
     * We will switch to context of alert window and assert that text is correct, and then accept alert.
     */
    @Test
    public void workingWithAlerts() {
        final String TEXT = "Text that will be displayed in alert";
        driver.get("http://qaclickacademy.com/practice.php");

        driver.findElement(By.id("name")).sendKeys(TEXT);
        driver.findElement(By.id("alertbtn")).click();

        String alertMsg = driver.switchTo().alert().getText();  //we need to switch context to alert window first
        //assert correct alert message is displayed and accept alert
        assertTrue("Alert contains text that was set up", alertMsg.contains(TEXT));
        driver.switchTo().alert().accept();
        //driver.switchTo().alert().dismiss();  //another option is to dismiss alert
    }

    /**
     * Get number of rows and columns in table, and then print third row of table
     */
    @Test
    public void workingWithTables() {
        driver.get("http://qaclickacademy.com/practice.php");

        int tableRows = driver.findElement(By.id("product")).findElements(By.tagName("tr")).size();
        int tableColumns = driver.findElement(By.id("product")).findElements(By.tagName("th")).size();
        log.info("Table Rows x Columns: " + tableRows + " x " + tableColumns);

        //Print second row of data
        WebElement secondTableRow = driver.findElement(By.id("product")).findElements(By.tagName("tr")).get(2); //index 2 is 3rd row
        log.info("Row 3 - column 1: " + secondTableRow.findElements(By.tagName("td")).get(0).getText());
        log.info("Row 3 - column 2: " + secondTableRow.findElements(By.tagName("td")).get(1).getText());
        log.info("Row 3 - column 3: " + secondTableRow.findElements(By.tagName("td")).get(2).getText());
    }

    /**
     * On webpage, you can use keyboard keys like DOWN, UP, ENTER, ESCAPE, etc.
     * Test will open bing page and save page title. Then we add search query, and press DOWN key twice.
     * Each time we assert that current search query has changed. On 3rd search query we press enter, and assert
     * that new page is opened by asserting that page title has changed (from "Bing" to "New York - Bing", or similar)
     */
    @Test
    public void workingWithKeys() throws InterruptedException {
        final String SEARCH_QUERY = "New";
        driver.get("https://www.bing.com");
        String originalTitle = driver.getTitle();

        WebElement input = driver.findElement(By.tagName("input"));
        input.sendKeys("New");
        Thread.sleep(2000);     //wait for a while until autocomplete suggestions appears
        input.sendKeys(Keys.DOWN);   //press DOWN key for first suggestion
        String autocomplete1 = input.getText();
        assertNotSame("Autocomplete value 1 is not the same as our search query", autocomplete1, SEARCH_QUERY);
        input.sendKeys(Keys.DOWN);   //press DOWN key for second suggestion
        String autocomplete2 = input.getText();
        assertNotSame("Autocomplete value 2 is not the same as our search query", autocomplete2, SEARCH_QUERY);
        assertNotSame("Autocomplete value 2 is not the same as autocomplete value 2 search query", autocomplete2, SEARCH_QUERY);

        input.sendKeys(Keys.ENTER);  //press ENTER key for opening page with second suggestion
        assertNotSame("Title of articles are not the same after opening specific article", driver.getTitle(), originalTitle);
    }

    /**
     * We will write part on input to textarea, wait until autocomplete will suggest a new keyword, and then press
     * key DOWN and ENTER, and then assert if autocomplete value is correct.
     */
    @Test
    public void workingWithAutocomplete() throws InterruptedException {
        final String AUTOCOMPLETE_VALUE = "United Kingdom (UK)";
        driver.get("http://qaclickacademy.com/practice.php");

        WebElement autocomplete = driver.findElement(By.id("autocomplete"));
        autocomplete.sendKeys("King");
        Thread.sleep(1000);
        autocomplete.sendKeys(Keys.DOWN);
        autocomplete.sendKeys(Keys.ENTER);
        assertEquals("Autocompleted value is correct", autocomplete.getAttribute("value"), AUTOCOMPLETE_VALUE);
    }

    /**
     * When you open Developers console in Chrome, you are able to access console in which you can execute Javascript code.
     * Selenium allows user to run Javascript code in your browsers.
     * This test will print some log to javascript console, then change page title, and then opens an Alert.
     */
    @Test
    public void workingWithJavascriptExecutor() {
        final String ALERT_MESSAGE = "FACEBOOK WAS HACKED";

        driver.get("https://www.facebook.com");
        String oldTitle = driver.getTitle();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("console.log('this is a log that will appear in console');");
        js.executeScript(String.format("document.title = '%s';", ALERT_MESSAGE));
        assertNotSame("Page title was successfully changed by javascript", oldTitle, driver.getTitle());
        assertEquals("Page title contains correct value", driver.getTitle(), ALERT_MESSAGE);

        js.executeScript("alert('XSS attack!');");
        driver.switchTo().alert().dismiss();
    }

    /**
     * Perform a drag & drop operation with Actions class.
     * Action class is used in Selenium to work with mouse, drag&drop, context click, hover, etc.
     * First you need to create instance of this class, then set up an operation, build it, and perform it.
     */
    @Test
    public void workingWithDragAndDrop() {
        driver.get("http://www.jqueryui.com/droppable/");
        driver.switchTo().frame(driver.findElement(By.cssSelector("iframe[class='demo-frame']")));
        Actions a = new Actions(driver);
        WebElement source = driver.findElement(By.id("draggable"));
        WebElement target = driver.findElement(By.id("droppable"));
        a.dragAndDrop(source, target).build().perform();
        driver.switchTo().defaultContent();

        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        WebElement alertInput = driver.findElement(By.xpath("//input[@id='name']"));
        //Move to element, click, press & hold shift key and enter an input
        a.moveToElement(alertInput).click().keyDown(Keys.SHIFT).sendKeys("uppercased string because of shift").build().perform();
        System.out.println("Entered text is: " + alertInput.getAttribute("value"));

        a.moveToElement(alertInput).doubleClick().build().perform();  //perform double click
        a.moveToElement(alertInput).contextClick().build().perform(); //perform context click (right click)
    }

    /**
     * Test will create a screenshot to downloads directory
     */
    @Test
    public void workingWithScreenshots() throws IOException {
        driver.get("https://facebook.com/");

        File screenshotObject = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshotObject, new File("logs\\screenshot.png"));
    }

    /**
     * Selenium supports multiple waiting types.
     * Implicit, Explicit, Fluent, sleep.
     * Implicit waiting - globally defined waiting type for each operation of webdriver for all elements
     *                  - slows execution of tests
     *                  - webdriver will wait maximally for specified time and execution will stop when this time is reached
     * Explicit waiting - locally defined waiting type for specific element, until conditions are applied
     *                  - as soon as condition is true, waiting is stopped and test execution continues
     *                  - you can specify multiple conditions, e.g. element is visible, not visible, url contains text, ...
     * Fluent waiting   - defined by operation, maximum wait time, and how often is this operation controlled
     *                  - this is widely not used
     * Sleep            - not part of Selenium, but is part of Java. Will stop thread processing for specified amount of time
     *
     * Note: this test is only a demonstration of waiting functions. Test does not work and will fail, that's why it is disabled.
     */
    @Test(enabled=false)
    public void workingWithWaitings() throws InterruptedException {
        //Set up implicit waiting for 10 seconds
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //Set up explicit waiting of 5 seconds for each condition to be true
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2"))); //element is visible
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='loader']"))); //element is not visible, e.g. loader
        wait.until(ExpectedConditions.alertIsPresent()); //alert is visible
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='clicker']"))); //element is now clickable
        wait.until(ExpectedConditions.elementToBeSelected(By.xpath("//*[@id='selector']"))); //element is selected
        wait.until(ExpectedConditions.numberOfWindowsToBe(2)); //number of windows changes
        wait.until(ExpectedConditions.titleIs("New title")); //title of page is
        wait.until(ExpectedConditions.attributeToBe(By.xpath("//select"), "value", "42")); //attribute of a specific element has expected value
        wait.until(ExpectedConditions.urlContains("order.php")); //url contains text

        //Set up Sleep for half a second
        Thread.sleep(500);
    }
}

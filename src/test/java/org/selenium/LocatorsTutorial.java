package org.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.AssertJUnit.assertNotSame;
import static org.testng.AssertJUnit.assertEquals;

/**
 * Locators are used by Selenium to locate elements on web page.
 * In Selenium, we can locate elements according to
 *  - id name        (<div id="btn1">)
 *  - class name     (<div class="className">) - we cannot locate element with multiple classes by this approach, like <div class="class1 class2 class3">
 *  - link text      (<a href="http://google.sk">Link text</a>)
 *  - tag name       (<h2>)
 *  - xPath selector (//div[@id='btn1']/input)
 *  - CSS selector   (div[id='btn1'].input)
 *
 *  xPath and CSS:
 *  - one element can be located with multiple xPaths or CSS selectors (e.g. element <div class="className" id="id" attribute="atr1">
 *  - one xPath or CSS selector can locate multiple elements (e.g. xPath //div will locate all div tags on webpage)
 *  - if multiple elements are matched, first element found from top-left is returned
 *  - xPath selector can be verified with Browser developer tools, in console by: $x("<xpath>"), e.g. $x("//div[@id='alert']")
 *  - CSS selector can be verified with Browser developer tools, in console by: $("<CSS>"), e.g. $("div[id='alert'])
 *  - xPath/CSS selector are indexed from 1, so second element of xPath is [2], like "//div[2]"
 *  - xPath/CSS selector can be absolute (/html/body/div[1]/section) or relative (//*[@class='login']). Preferred is to use relative ones
 *  - with CSS selector, you can reference only child elements. You can locate parent and then access child element, but not vice versa.
 *
 * Syntax of xPath selector = //tagName[@attribute='value']
 * Syntax of  CSS  selector = tagName[attribute='value']
 *
 * Examples of xPath selectors:
 * //div[@id='alert']                    - tag div with id="alert"
 * //div[@class='btn']                   - tag div with class="btn" (will only work for single class)
 * //div[contains(@class,'btn link')]    - tag div containing class with substring "btn link" (will work for multiple classes)
 * //*[@type='table']                    - any tag with type="table"
 * //input[@value='Log In']              - tag input with attribute value='Log In'
 * //button[contains(@id,'login')]       - tag button containing id with substring "login" (usable for IDs with dynamic names)
 * //div[@class='month']/div[2]          - second (not third!) child div of tag div with class="month"
 * //li[@id='id']/following-sibling::li  - following sibling with tag li, of tag li with id='id'
 * //li[@id='tablist']/parent::ul        - parent with tag ul of element li with id="tablist"
 * //li[@id='tablist']/parent::*         - parent with any tag of element li with id="tablist"
 * //button[text()='Click here']         - tag button with exact text 'Click here'
 * //div[contains(text(), 'Open here')]  - tag div that contains substring 'Open here'
 * (//input[@type='submit'])[3]          - third tag input of type="submit" found from top-left
 * //tr/td[2]                            - second column of table
 * //div[contains(@class, 'class1') and contains(@class, 'class2')]    - div containing mentioned 2 classes
 * //*[contains(@id,'result')] | //*[contains(@class,'result')]        - any tag containing either id with substring "result" or class with substring "result"
 *
 *
 * Examples of CSS selectors:
 * div#alert                             - tag div with id="alert"
 * div[class='btn']                      - tag div with class="btn"
 * div[class*='btn link']                - tag div containing classes "btn link"
 * [type='table']                        - any tag with type="table"
 * input[value='Log In']                 - tag input with attribute value='Log In'
 * button[id*='login']                   - tag button containing id with substring "login" (usable for IDs with dynamic names)
 * div.month > div:nth-of-type(2)        - second (not third!) child div of tag div with class="month"
 * ???
 * ???
 * ???
 * button:contains(^Click here$)         - tag button with exact text 'Click here'
 * div:contains(Open here)               - tag div that contains substring 'Open here'
 * ???
 * tr td:nth-child(2)                    - second column of table
 * ???
 * ???
 */
public class LocatorsTutorial extends SeleniumConfiguration {

    final String PRACTICE_URL = "https://www.rahulshettyacademy.com/AutomationPractice/";

    //Before running class, open our URL
    @BeforeClass
    public void openPracticeURL() {
        driver.get(PRACTICE_URL);
    }

    @Test
    public void locateElementByID() {
        WebElement alertButton = driver.findElement(By.id("alertbtn"));   //find web elemeny by its id
        alertButton.click();                   //click on it -> alert will appear
        driver.switchTo().alert().dismiss();   //dismiss alert
    }

    @Test
    public void locateElementByLinkText() {
        String oldUrl = driver.getCurrentUrl();
        WebElement linkElement = driver.findElement(By.linkText("Home"));  //find link with text Home
        linkElement.click();  //open link -> we will be redirected to another page
        String newUrl = driver.getCurrentUrl();
        assertNotSame("We are on new url", newUrl, oldUrl);
        driver.navigate().back();  //go back
        assertEquals("We are on old url again", oldUrl, driver.getCurrentUrl());
    }

    @Test
    public void locateElementByClassName() {
        WebElement inputElement = driver.findElement(By.className("inputs"));
        String text = inputElement.getAttribute("placeholder");
        assertEquals("Placeholder of located element is correct", text, "Type to Select Countries");
    }

    @Test
    public void locateAllElementsByTagName() {
        List<WebElement> allElements = driver.findElements(By.tagName("legend"));
        for (WebElement element : allElements) {
            System.out.println("Text of legend: " + element.getText());
        }
    }

    @Test
    public void locateElementByXPath() {
        WebElement alertButton = driver.findElement(By.xpath("//input[@id='alertbtn']"));
        alertButton.click();
        driver.switchTo().alert().dismiss();
    }

    @Test
    public void locateElementByCSS() {
        WebElement alertButton = driver.findElement(By.cssSelector("input[id='alertbtn']"));
        alertButton.click();
        driver.switchTo().alert().dismiss();
    }

    @Test
    public void printAllLinks() {
        List<WebElement> allLinkElements = driver.findElements(By.xpath("//a"));
        System.out.println("Number of all links in webpage: " + allLinkElements.size());
        for (WebElement linkElement : allLinkElements) {
            System.out.println(linkElement.getAttribute("href"));
        }
    }
}

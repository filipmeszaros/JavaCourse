package org.cucumber.stepdefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * Cucumber is Behaviour Driven Development (BDD) framework for testing.
 * Business requirements are in form of semi-formal language Gherkin, common and understandable both for technical and non-technical team members.
 * Usage: run specific test/tests from Login.feature file or all tests from cucumberTestNG.xml file or from CucumberTestRunner file.
 * Advantages:
 *  - one common template for manual and automatic tests usable for
 *  - each test scenario describes one business logic (e.g, sending money, submitting a case, ...)
 *  - able to run tests from TestNG (@see {@link org.cucumber.tests.CucumberTestRunner})
 *
 * Terminology:
 *  - Feature file        - *.feature file defines all test cases that we are covering (manual or automated)
 *                        - all tests can be run from this file
 *  - StepDefinition file - java class with code that is covering automated tests
 *                        - can be created with Chrome plugin "Tidy Gherkin" or if we run a test
 *  - TestRunner file     - JUnit testrunner for executing tests, in which we annotate Features file and StepDefinition files
 *
 * User stories or requirements are written in form of:
 *   As a (role)
 *   In order to (achieve something) -> Business outcome
 *   I want to (Do this)
 *
 * Example:
 *   As a wallet user
 *   In order to send money
 *   I want wallet balances to update
 *
 *
 * Test Cases are written in form of:
 *   Given (what you need to have to perform action)
 *   When (performs actions)
 *   Then (the desired outcome for the user - contains asserts)
 *
 * Example:
 *   Given that I have $40 in my balance
 *   And my friend has $10 is their balance
 *   When I transfer $20 to my friend
 *   Then I should have $20 in my balance
 *   And my friend should have $30 in their balance.
 */
public class StepDefinition {

    private static Logger log = LogManager.getLogger(StepDefinition.class.getName());

    @Given("^User is on LockTrip landing page$")
    public void goToLandingPage() {
        log.info("Going to landing page of LockTrip");
        //assert landing page is opened
    }

    @When("^User logs into application with username \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void login(String username, String password) {
        log.info("Logging in with given credentials " + username + ":" + password);
        log.info("Username: " + username);
        log.info("Password: " + password);
    }

    @Then("^HomePage of LockTrip is populated$")
    public void assertHomePage() {
        log.info("Asserting homepage after login");
        //assert home page is opened after successful login
    }

    @Then("^Admin cards are displayed are \"([^\"]*)\"$")
    public void cards_are_displayed(String cardsAreDisplayed) {
        log.info("Asserting cards are displayed after login. Are: " + cardsAreDisplayed);
        //assert if admin cards are displayed or not
    }

    @When("^User logs into application with parametrized username (.+) and password (.+)$")
    public void userLogsIntoApplicationWithUsernameAndPassword(String username, String password) {
        log.info("Logging in with given credentials " + username + ":" + password);
    }
}

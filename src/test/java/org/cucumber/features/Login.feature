#Feature represents business requirement of your application.
#Feature file is used to define and run testing scenarios (both manually and automatically).
#It defines each scenario with given/when/then syntax, which is automated in Step definition implementation.
#Each feature is therefore mapped with step definition, that provides implementation.
Feature: Application Login

  #Scenario represents a testcase of your feature
  Scenario: HomePage admin login
    Given User is on LockTrip landing page
    When User logs into application with username "admin" and password "passwd"
    Then HomePage of LockTrip is populated
    And Admin cards are displayed are "true"

  Scenario: HomePage support login
    Given User is on LockTrip landing page
    When User logs into application with username "support" and password "suppword"
    Then HomePage of LockTrip is populated
    And Admin cards are displayed are "false"

  Scenario Outline: Parametrized login test
    Given User is on LockTrip landing page
    When User logs into application with parametrized username <username> and password <password>
    Then HomePage of LockTrip is populated
    And Admin cards are displayed are "<adminCardsDisplayed>"

    Examples:
      |username|password   |adminCardsDisplayed|
      |admin   |admin      |true               |
      |support |supportpass|false              |
      |CEO     |CEOpasswd  |true               |
      |john1   |user       |false              |
      |badlogin|invalidpass|false              |
Feature: Application Login

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
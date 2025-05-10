Feature: Login to SauceDemo

  Scenario: Valid login
    Given the user is on the bookstore homepage
    When the user enters a valid username
    And the user enters a valid password
    And clicks the login button
    Then the user should be redirected to the products page

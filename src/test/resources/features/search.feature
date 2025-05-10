Feature: Search Functionality

  Scenario: Search with an invalid book
    Given the user is on the bookstore homepage
    When the user enters a valid username
    And the user enters a valid password
    And clicks the login button
    Then the user should be redirected to the products page
    When the user enters "ThisBookDoesNotExist123" in the search box
    And clicks the search button
    Then the book results should be displayed

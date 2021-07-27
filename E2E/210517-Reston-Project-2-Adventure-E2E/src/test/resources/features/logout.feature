Feature: Logout

  Scenario: customer logout
    Given the customer is logged into the UI
    When the customer clicks the logout button
    Then the browser returns to the login portal

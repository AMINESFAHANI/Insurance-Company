Feature: Login

  Scenario: customer login happy
    Given the customer is at the login portal
    When the customer enters their correct username
    When the customer enters their correct password
    When the customer clicks the login button
    Then the browser navigates to the customer UI

  Scenario: customer login sad
    Given the customer is at the login portal
    When the customer enters their correct username
    When the customer enters their incorrect password
    When the customer clicks the login button
    Then an error message is displayed under the login box

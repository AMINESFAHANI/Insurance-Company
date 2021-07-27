Feature: Manager Logs In

  Scenario: Manager Logs In Happy
    Given The manager is on the login page
    When the manager enters their correct username
    When the manager enters their correct password
    When the manager clicks the manager login button
    Then the browser navigates to the Manager UI

  Scenario: Manager Logs In Sad Password
    Given The manager is on the login page
    When the manager enters their correct username
    When the manager enters their incorrect password
    When the manager clicks the manager login button
    Then an error message is displayed under the login box

  Scenario: Manager Logs In Sad Username
    Given The manager is on the login page
    When the manager enters their incorrect username
    When the manager enters their correct password
    When the manager clicks the manager login button
    Then an error message is displayed under the login box

  Scenario: Manager Logs In Sad Username And Password
    Given The manager is on the login page
    When the manager enters their incorrect username
    When the manager enters their incorrect password
    When the manager clicks the manager login button
    Then an error message is displayed under the login box
Feature: Customer Browse Healthcare Plans

  Scenario: Customer Browse Plans
    Given the customer is on the claims page
    When the customer clicks the shop plans button
    Then the browser navigates to the plans page

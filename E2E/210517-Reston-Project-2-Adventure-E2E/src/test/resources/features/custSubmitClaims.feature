Feature: Customer Submits Claims

  Scenario: Customer submits a claim happy
    Given the customer is on the claims page
    When the customer selects a claim in the dropdown
    When the customer inputs a claim amount
    When the customer clicks the submit button
    Then a confirmation window or popup appears
    When the customer clicks the confirm button
    Then the claim appears in the claims section

  Scenario: Customer submits a claim sad
    Given the customer is on the claims page
    When the customer selects a claim in the dropdown
    When the customer inputs a claim amount
    When the customer clicks the submit button
    Then a confirmation window or popup appears
    When the customer clicks the back button
    Then the confirmation window or popup disappears

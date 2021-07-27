Feature: View customers claims
  Scenario: Customer views claims
    Given The customer is logged in
    When The customer navigates to the claim page
    Then The customer should see their claims

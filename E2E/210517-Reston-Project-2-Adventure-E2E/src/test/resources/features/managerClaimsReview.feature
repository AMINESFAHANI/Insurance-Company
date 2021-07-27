Feature: Manager can approve and deny claims.

  Scenario: Manager approves a claim.
    Given a manager is on the claims page
    When an accept button is clicked
    Then the claim disappears

  Scenario: Manager denies a claim.
    Given a manager is on the claims page
    When a deny button is clicked
    Then the claim disappears
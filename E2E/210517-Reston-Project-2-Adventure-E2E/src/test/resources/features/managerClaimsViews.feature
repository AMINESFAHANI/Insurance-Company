Feature: Manager can view the claims page with different filters.

  Scenario: Manager toggles through the different views.
    Given a manager is on the claims page
    Then the pending claims view is shown
    When the completed button is clicked
    Then the completed claims view is shown
    When the all button is clicked
    Then the all claims view is shown
    When the pending button is clicked
    Then the pending claims view is shown

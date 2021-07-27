Feature: Customer Update Healthcare Plan

  Scenario Outline: Choose different plans sad
    Given the customer is on the plans page
    When the customer clicks the "<plan>" button
    Then a confirmation window or popup appears
    When the customer clicks the back button
    Then the confirmation window or popup disappears

    Examples:
      | plan   |
      | bronze |
      | silver |
      | gold   |

  Scenario Outline: Choose different plans happy
    Given the customer is on the plans page
    When the customer clicks the "<plan>" button
    Then a confirmation window or popup appears
    When the customer clicks the confirm button
    Then the confirmation window or popup disappears
    Then "<plan>" is reflected on the navigation bar

    Examples:
      | plan   |
      | bronze |
      | silver |
      | gold   |

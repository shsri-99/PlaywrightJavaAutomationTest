Feature: Exploring Categories

  Scenario: Search for categories of product
    Given User is on the homepage
    When User clicks on Categories
    And User selects Power Tools
    Then User can see 8 items in result
    And User can see below products

    """
      {
        "Sheet Sander": 58.48,
        "Belt Sander": 73.59,
        "Circular Saw": 80.19,
        "Random Orbit Sander": 100.79,
        "Cordless Drill 20V": 125.23,
        "Cordless Drill 24V": 66.54,
         "Cordless Drill 18V": 119.24,
          "Cordless Drill 12V": 46.5
      }
      """
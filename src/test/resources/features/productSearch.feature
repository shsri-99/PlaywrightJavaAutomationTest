Feature: Product Search

  Scenario: Search a product through Text input.
    Given User is on the homepage
    When User search "pliers" in search input
    And User selects Search button
    Then User can see below products results

      | Combination Pliers   |
      | Pliers               |
      | Long Nose Pliers     |
      | Slip Joint Pliers    |

  Scenario: Search a product through checkbox.
    Given User is on the homepage
    When User selects checkbox by "Wrench" and waits for product API response 200
    Then User can see below products for checkbox search

      | Adjustable Wrench       |
      | Angled Spanner          |
      | Open-end Spanners (Set) |

  Scenario: Sort products through filter "Price (High - Low)"
    Given User is on the homepage
    When User adds filter "Price (High - Low)" on the products and wait for API response 200
    Then User  can view 45 products as a result
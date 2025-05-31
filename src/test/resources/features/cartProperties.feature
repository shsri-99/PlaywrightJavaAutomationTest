Feature: Add to cart

  Scenario: Add product through cart from homepage
    Given User is on the homepage
    When click on a "Combination Pliers" item
    And User selects Add to cart button
    Then message "Product added to shopping cart." is displayed on the screen

  Scenario: Validate the count of basket increments
    Given User is on the homepage
    When click on a "Bolt Cutters" item
    And User selects Add to cart button
    Then message "Product added to shopping cart." is displayed on the screen
    And basket count is incremented by 1

  Scenario: Validate the items in basket along with its price and quantity.
    Given User is on the homepage
    When click on a "Combination Pliers" item
    And User selects Add to cart button
    And basket count is incremented by 1
    When User navigates to the checkout page
    Then User can see below product details

      | Combination Pliers       |
      | 1                        |
      | $14.15	                 |
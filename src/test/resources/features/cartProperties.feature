Feature: Add to cart

  Scenario: Add product through cart from homepage
    Given User is on the homepage
    When click on a "Combination Pliers" item
    And User selects Add to cart button
    Then message "Product added to shopping cart." is displayed on the screen
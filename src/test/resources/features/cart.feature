Feature: Shopping cart

  Scenario: Add a product to the cart
    Given the user is on the home page
    When the user adds the first product to the cart
    Then the product should appear in the cart
Feature: Product search on the e-commerce site

  Scenario Outline: Search for a product and see results
    Given the user is on the home page
    When the user searches for "<productName>"
    Then search results should be displayed

    Examples:
      | productName |
      | Dress       |
      | Jeans       |
      | Top         |
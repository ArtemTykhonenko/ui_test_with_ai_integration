@C2
@Smoke
@AddProduct
Feature: Add product to Cart

  Scenario: Adding product to cart
    Given Open 'Swag Labs' login page
    Given Login to 'Swag Labs' as standard user
    Then The 'Swag Labs' home page is opened
    When Remember information about 1 product
    And Click 'Add to cart' button for 1 product
    Then The 'Remove' button is displayed for 1 product
    When Click on 'Cart' link
    Then The item 1 values are equal
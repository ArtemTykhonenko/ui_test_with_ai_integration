@C2
@Smoke
@AddProduct
Feature: Add product to Cart
  # This feature covers the functionality of adding a product to the cart in Swag Labs.
  # It is tagged as a smoke test to ensure that critical functionalities, such as adding products, work as expected.

  Scenario: Adding product to cart
    # Scenario for adding a product to the cart and verifying its details.

    Given Open 'Swag Labs' login page
    # Opens the Swag Labs login page.

    Given Login to 'Swag Labs' as standard user
    # Logs in using the standard user's credentials.

    Then The 'Swag Labs' home page is opened
    # Verifies that the home page is displayed after logging in.

    When Remember information about 1 product
    # Stores information about the first product (name, description, price) for later verification.

    And Click 'Add to cart' button for 1 product
    # Adds the first product to the cart.

    Then The 'Remove' button is displayed for 1 product
    # Verifies that the 'Remove' button is displayed, indicating the product has been added to the cart.

    When Click on 'Cart' link
    # Navigates to the cart page.

    Then The item 1 values are equal
    # Verifies that the product's name, description, and price in the cart match the remembered values.
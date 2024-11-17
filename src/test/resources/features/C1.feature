@C1
@Smoke
@Login
Feature: User Login and logout
  # This feature covers the login and logout functionality of Swag Labs.
  # It is tagged as a smoke test to be included in the smoke testing suite.

  Scenario: Successful login and logout with valid credentials
    # Scenario for a successful login and logout using valid credentials.
    Given Open 'Swag Labs' login page
    # Opens the login page of Swag Labs.

    When Login to 'Swag Labs' as standard user
    # Logs in using the standard user credentials.

    Then The 'Swag Labs' home page is opened
    # Verifies that the home page is displayed after login.

    When Click on side menu
    # Opens the side menu on the home page.

    And Click on 'Logout' link
    # Logs the user out using the 'Logout' link in the side menu.

    Then The 'Swag Labs' login page is displayed
    # Verifies that the user is redirected to the login page after logging out.

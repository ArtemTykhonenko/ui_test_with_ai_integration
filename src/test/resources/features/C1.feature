@C1
@Smoke
@Login
Feature: User Login and logout
  # This feature covers the login and logout functionality of Swag Labs.
  # It is tagged as a smoke test to be included in the smoke testing suite.

  Scenario: Successful login and logout with valid credentials
    Given Open 'Swag Labs' login page
    When Login to 'Swag Labs' as standard user
    Then The 'Swag Labs' home page is opened
    When Click on side menu
    And Click on 'Logout' link
    Then The 'Swag Labs' login page is displayed
@C1
@Smoke
@Login
Feature: User Login and logout

  Scenario: Successful login and logout with valid credentials
    Given Open 'Swag Labs' login page
    When Login to 'Swag Labs' as standard user
    Then The 'Swag Labs' home page is opened
    When Click on side menu
    And Click on 'Logout' link
    Then The 'Swag Labs' login page is displayed
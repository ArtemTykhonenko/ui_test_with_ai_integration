package automation.actions;


import automation.pages.Pages;

public class LoginActions {

    /**
     * This method is used to login to Swag Labs using provided username and password.
     *
     * @param userName The username for the Swag Labs login. This should be a valid username registered on Swag Labs.
     * @param password The password for the Swag Labs login. This should be the correct password associated with the provided username.
     */
    public void loginToSwagLabs(String userName, String password) {
        Pages.loginPage().typeUsername(userName);
        Pages.loginPage().typePassword(password);
        Pages.loginPage().clickSubmitButton();
    }

}
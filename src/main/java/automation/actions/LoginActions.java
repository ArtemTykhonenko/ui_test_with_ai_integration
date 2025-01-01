package automation.actions;


import automation.pages.Pages;

public class LoginActions {

    public void loginToSwagLabs(String userName, String password) {
        Pages.loginPage().typeUsername(userName);
        Pages.loginPage().typePassword(password);
        Pages.loginPage().clickSubmitButton();
    }

}
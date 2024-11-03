package automation.actions;


import automation.pages.Pages;

public class LoginActions {

    public void loginToWebAdmin(String userName, String password) {
        Pages.loginPage().typeUsername(userName);
        Pages.loginPage().typePassword(password);
        Pages.loginPage().clickSubmitButton();
    }

    // Інші методи для дій з логіном
}
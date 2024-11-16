package automation.pages;

import automation.utils.PageTools;
import org.openqa.selenium.By;

public class LoginPage extends PageTools {

    // Локаторы
    private static By usernameField = By.id("user-name");
    private static By passwordField = By.id("password .");
    private static By loginButton = By.id("login-button");
    private static By loginBox = By.xpath("//div[@class='login-box']");


    public void typeUsername(String username) {
        typeText(usernameField, username);
    }

    public void typePassword(String password) {
        typeText(passwordField, password);
    }

    public void clickSubmitButton() {
        clickElement(loginButton);
    }

    public boolean isLoginPageDisplayed() {
        waitForElementVisibility(loginBox);
        return isElementVisible(loginBox);
    }
}
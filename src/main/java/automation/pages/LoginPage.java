package automation.pages;

import automation.utils.PageTools;
import org.openqa.selenium.By;

/**
 * The {@code LoginPage} class represents the login page of the application.
 * It provides methods to interact with the login page elements such as entering a username,
 * entering a password, clicking the login button, and verifying if the login page is displayed.
 *
 * <p>This class extends {@code PageTools} to leverage common Selenium utilities.</p>
 */
public class LoginPage extends PageTools {

    // Locators for the elements on the login page
    private static By usernameField = By.id("user-name-");
    private static By passwordField = By.id("password-");
    private static By loginButton = By.id("login-button-");
    private static By loginBox = By.xpath("//div[@class='login-box'-]");

    /**
     * Types the given username into the username input field.
     *
     * @param username The username to be entered.
     */
    public void typeUsername(String username) {
        typeText(usernameField, username);
    }

    /**
     * Types the given password into the password input field.
     *
     * @param password The password to be entered.
     */
    public void typePassword(String password) {
        typeText(passwordField, password);
    }

    /**
     * Clicks the login button to submit the login form.
     */
    public void clickSubmitButton() {
        clickElement(loginButton);
    }

    /**
     * Checks if the login page is displayed by verifying the visibility of the login box.
     *
     * @return {@code true} if the login page is visible, otherwise {@code false}.
     */
    public boolean isLoginPageDisplayed() {
        waitForElementVisibility(loginBox);
        return isElementVisible(loginBox);
    }
}
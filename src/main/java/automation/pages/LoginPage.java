package automation.pages;

import automation.base.BaseTest;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private BaseTest baseTest;

    // Элементы страницы
    private SelenideElement usernameField = $("#username");
    private SelenideElement passwordField = $("#password");
    private SelenideElement loginButton = $("#loginButton");

    public LoginPage(BaseTest baseTest) {
        this.baseTest = baseTest;
    }

    public void enterUsername(String username) {
        usernameField.setValue(username);
    }

    public void enterPassword(String password) {
        passwordField.setValue(password);
    }

    public void clickLogin() {
        loginButton.click();
    }
}

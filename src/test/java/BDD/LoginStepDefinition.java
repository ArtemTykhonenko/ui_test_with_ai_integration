package BDD;

import automation.base.BaseTest;
import automation.pages.Pages;
import com.codeborne.selenide.Selenide;
import io.cucumber.java.en.Given;


public class LoginStepDefinition {
    private Pages pages;

    public LoginStepDefinition() {
        pages = new Pages(new BaseTest());
    }

    @Given("Open 'Swag Labs' login page")
    public void iGoToLoginPage() {
        Selenide.open("https://www.saucedemo.com/");
    }
}

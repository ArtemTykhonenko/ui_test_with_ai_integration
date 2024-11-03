package BDD;

import automation.actions.Actions;
import automation.base.BaseTest;
import automation.utils.Constants;
import com.codeborne.selenide.Selenide;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class LoginStepDefinition extends BaseTest {

    private Actions actions;

    public LoginStepDefinition() {
        actions = new Actions();
    }

    @Given("Open 'Web Admin' login page")
    public void iGoToLoginPage() {
        Selenide.open(Constants.SWAG_LABS_LOGIN_PAGE);
    }

    @When("Login to 'Web Admin' as admin user")
    public void loginToWebAdminAsAdminUser() {
        actions.loginActions().loginToWebAdmin(Constants.STANDARD_USER, Constants.PASSWORD);
    }
}

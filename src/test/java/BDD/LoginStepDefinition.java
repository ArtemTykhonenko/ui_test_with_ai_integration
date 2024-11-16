package BDD;

import automation.actions.Actions;
import automation.pages.Pages;
import automation.utils.Constants;
import automation.utils.PageTools;
import com.codeborne.selenide.Selenide;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class LoginStepDefinition extends PageTools {

    private Actions actions;

    public LoginStepDefinition() {
        actions = new Actions();
    }

    @Given("Open 'Swag Labs' login page")
    public void goToLoginPage() {
        Selenide.open(Constants.SWAG_LABS_LOGIN_PAGE);
    }

    @Then("The 'Swag Labs' login page is displayed")
    public void swagLabsPageIsDisplayed() {
        Assert.assertTrue(Pages.loginPage().isLoginPageDisplayed(), "The Login page is NOT displayed");
    }

    @When("Login to 'Swag Labs' as standard user")
    public void loginAsStandardUser() {
        Actions.loginActions().loginToSwagLabs(Constants.STANDARD_USER, Constants.PASSWORD);
    }

    @When("Login to 'Swag Labs' as locked user")
    public void loginAsLockedUser() {
        Actions.loginActions().loginToSwagLabs(Constants.LOCKED_USER, Constants.PASSWORD);
    }

    @When("Login to 'Swag Labs' as problem user")
    public void loginAsProblrmUser() {
        Actions.loginActions().loginToSwagLabs(Constants.PROBLEM_USER, Constants.PASSWORD);
    }

    @When("Login to 'Swag Labs' as performance glitch user")
    public void loginAsPerformanceGlitchUser() {
        Actions.loginActions().loginToSwagLabs(Constants.PERFORMANCE_USER, Constants.PASSWORD);
    }

    @When("Login to 'Swag Labs' as error user")
    public void loginAsErrorUser() {
        Actions.loginActions().loginToSwagLabs(Constants.ERROR_USER, Constants.PASSWORD);
    }

    @When("Login to 'Swag Labs as visual user")
    public void loginAsVisualUser() {
        Actions.loginActions().loginToSwagLabs(Constants.VISUAL_USER, Constants.PASSWORD);
    }
}

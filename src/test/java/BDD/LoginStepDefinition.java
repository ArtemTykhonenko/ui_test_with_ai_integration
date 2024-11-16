package BDD;

import automation.actions.Actions;
import automation.pages.Pages;
import automation.utils.Constants;
import automation.utils.PageTools;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import automation.base.DriverManager;

public class LoginStepDefinition extends PageTools {

    private Actions actions;
    private WebDriver driver;

    public LoginStepDefinition() {
        actions = new Actions();
        // Используем существующий экземпляр драйвера, инициализированный в Hooks
        driver = DriverManager.getDriver(null);
    }

    @Given("Open 'Swag Labs' login page")
    public void goToLoginPage() {
        openUrl(Constants.SWAG_LABS_LOGIN_PAGE);
    }

    @Then("The 'Swag Labs' login page is displayed")
    public void swagLabsPageIsDisplayed() {
        Assert.assertTrue(Pages.loginPage().isLoginPageDisplayed(), "The Login page is NOT displayed");
    }

    @When("Login to 'Swag Labs' as standard user")
    public void loginAsStandardUser() {
        actions.loginActions().loginToSwagLabs(Constants.STANDARD_USER, Constants.PASSWORD);
    }

    @When("Login to 'Swag Labs' as locked user")
    public void loginAsLockedUser() {
        actions.loginActions().loginToSwagLabs(Constants.LOCKED_USER, Constants.PASSWORD);
    }

    @When("Login to 'Swag Labs' as problem user")
    public void loginAsProblemUser() {
        actions.loginActions().loginToSwagLabs(Constants.PROBLEM_USER, Constants.PASSWORD);
    }

    @When("Login to 'Swag Labs' as performance glitch user")
    public void loginAsPerformanceGlitchUser() {
        actions.loginActions().loginToSwagLabs(Constants.PERFORMANCE_USER, Constants.PASSWORD);
    }

    @When("Login to 'Swag Labs' as error user")
    public void loginAsErrorUser() {
        actions.loginActions().loginToSwagLabs(Constants.ERROR_USER, Constants.PASSWORD);
    }

    @When("Login to 'Swag Labs' as visual user")
    public void loginAsVisualUser() {
        actions.loginActions().loginToSwagLabs(Constants.VISUAL_USER, Constants.PASSWORD);
    }
}
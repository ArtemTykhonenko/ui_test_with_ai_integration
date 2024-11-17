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

/**
 * This class contains step definitions for the login functionality of the Swag Labs application.
 * It utilizes the Page Object Model (POM) pattern and interacts with the WebDriver instance.
 *
 * @author Artem Tykhonenko
 * @version 1.0
 */
public class LoginStepDefinition extends PageTools {

    private Actions actions;
    private WebDriver driver;

    /**
     * Constructor for the LoginStepDefinition class.
     * Initializes the Actions and WebDriver instances.
     */
    public LoginStepDefinition() {
        actions = new Actions();
        driver = DriverManager.getDriver(null);
    }

    /**
     * Opens the Swag Labs login page.
     *
     * @see Constants#SWAG_LABS_LOGIN_PAGE
     */
    @Given("Open 'Swag Labs' login page")
    public void goToLoginPage() {
        openUrl(Constants.SWAG_LABS_LOGIN_PAGE);
    }

    /**
     * Verifies if the Swag Labs login page is displayed.
     *
     * @see Pages#loginPage()
     * @see automation.pages.LoginPage#isLoginPageDisplayed()
     */
    @Then("The 'Swag Labs' login page is displayed")
    public void swagLabsPageIsDisplayed() {
        Assert.assertTrue(Pages.loginPage().isLoginPageDisplayed(), "The Login page is NOT displayed");
    }

    /**
     * Logs in to Swag Labs as a standard user.
     *
     * @see Constants#STANDARD_USER
     * @see Constants#PASSWORD
     * @see automation.actions.Actions#loginActions()
     * @see automation.actions.LoginActions#loginToSwagLabs(String, String)
     */
    @When("Login to 'Swag Labs' as standard user")
    public void loginAsStandardUser() {
        Actions.loginActions().loginToSwagLabs(Constants.STANDARD_USER, Constants.PASSWORD);
    }
}
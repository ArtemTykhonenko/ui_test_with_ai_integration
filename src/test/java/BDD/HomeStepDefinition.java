package BDD;

import automation.actions.Actions;
import automation.data.entities.Product;
import automation.pages.Pages;
import automation.utils.DefaultLogger;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class HomeStepDefinition extends DefaultLogger {
    private final GlobalContext globalContext;

    public HomeStepDefinition (GlobalContext crmGlobalContext) {
        this.globalContext = crmGlobalContext;
    }

    @Then("The 'Swag Labs' home page is opened")
    public void theSwagLabsHomePageIsOpened() {
        Assert.assertTrue(Pages.homePage().isMainPageDisplayed(), "The 'Home' Page is NOT displayed");
    }

    @When("Click on side menu")
    public void clickOnSideMenu() {
        Pages.homePage().clickSideMenu();
    }

    @When("Click on 'All Items' link")
    public void clickOnAllItemsLink() {
        Pages.homePage().clickAllItems();
    }

    @When("Click on 'About' link")
    public void clickOnAboutLink() {
        Pages.homePage().clickAboutLink();
    }

    @When("Click on 'Logout' link")
    public void clickOnLogoutLink() {
        Pages.homePage().clickLogoutLink();
    }

    @When("Click on 'Reset App State' link")
    public void clickResetAppStateLink() {
        Pages.homePage().clickResetAppState();
    }

    @When("Click on 'Cart' link")
    public void crclickCartLink() {
        Pages.homePage().clickCartLink();
    }

    @When("Click 'Add to cart' button for {int} product")
    public void clickAddToCartButtonForProductByNumber(Integer number) {
        Pages.homePage().clickAddToCartProductByNumber(number);
    }

    @When("Remember information about {int} product")
    public void rememberInformationAboutProductByNumber(int number) {
        globalContext.product = new Product();
        globalContext.product.setProductName(Pages.homePage().getProductNameByNumber(number));
        globalContext.product.setProductDescription(Pages.homePage().getProductDescriptionByNumber(number));
        globalContext.product.setProductPrice(Pages.homePage().getProductPriceByNumber(number));
        logInfo("");
    }

    @Then("The 'Remove' button is displayed for {int} product")
    public void theRemoveButtonIsDisplayedForProductByNumber(Integer number) {
        Assert.assertTrue(Pages.homePage().isRemoveButtonDisplayedForProductByNumber(number), "The 'Remove' button is NOT displayed");
    }
}

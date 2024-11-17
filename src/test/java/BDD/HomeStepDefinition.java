package BDD;

import automation.data.entities.Product;
import automation.pages.Pages;
import automation.utils.DefaultLogger;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

/**
 * The {@code HomeStepDefinition} class defines step definitions for BDD tests related to the Home Page of "Swag Labs".
 * It utilizes the Cucumber framework to interact with the home page and perform various actions,
 * such as navigating through the side menu, adding products to the cart, and remembering product information.
 *
 * <p>This class extends {@code DefaultLogger} for logging capabilities and uses the {@code GlobalContext}
 * to store shared data between test steps.</p>
 */
public class HomeStepDefinition extends DefaultLogger {
    private final GlobalContext globalContext;

    /**
     * Constructs a new {@code HomeStepDefinition} with the provided {@code GlobalContext}.
     *
     * @param crmGlobalContext The global context used for storing product information.
     */
    public HomeStepDefinition(GlobalContext crmGlobalContext) {
        this.globalContext = crmGlobalContext;
    }

    /**
     * Step definition to verify that the "Swag Labs" home page is opened.
     * This corresponds to the Cucumber step:
     * {@code Then The 'Swag Labs' home page is opened}
     */
    @Then("The 'Swag Labs' home page is opened")
    public void theSwagLabsHomePageIsOpened() {
        Assert.assertTrue(Pages.homePage().isMainPageDisplayed(), "The 'Home' Page is NOT displayed");
    }

    /**
     * Step definition to click on the side menu.
     * Corresponds to the Cucumber step: {@code When Click on side menu}
     */
    @When("Click on side menu")
    public void clickOnSideMenu() {
        Pages.homePage().clickSideMenu();
    }

    /**
     * Step definition to click on the "All Items" link in the side menu.
     * Corresponds to the Cucumber step: {@code When Click on 'All Items' link}
     */
    @When("Click on 'All Items' link")
    public void clickOnAllItemsLink() {
        Pages.homePage().clickAllItems();
    }

    /**
     * Step definition to click on the "About" link in the side menu.
     * Corresponds to the Cucumber step: {@code When Click on 'About' link}
     */
    @When("Click on 'About' link")
    public void clickOnAboutLink() {
        Pages.homePage().clickAboutLink();
    }

    /**
     * Step definition to click on the "Logout" link in the side menu.
     * Corresponds to the Cucumber step: {@code When Click on 'Logout' link}
     */
    @When("Click on 'Logout' link")
    public void clickOnLogoutLink() {
        Pages.homePage().clickLogoutLink();
    }

    /**
     * Step definition to click on the "Reset App State" link in the side menu.
     * Corresponds to the Cucumber step: {@code When Click on 'Reset App State' link}
     */
    @When("Click on 'Reset App State' link")
    public void clickResetAppStateLink() {
        Pages.homePage().clickResetAppState();
    }

    /**
     * Step definition to click on the "Cart" link.
     * Corresponds to the Cucumber step: {@code When Click on 'Cart' link}
     */
    @When("Click on 'Cart' link")
    public void crclickCartLink() {
        Pages.homePage().clickCartLink();
    }

    /**
     * Step definition to click the "Add to cart" button for a specific product by its number.
     * Corresponds to the Cucumber step: {@code When Click 'Add to cart' button for {int} product}
     *
     * @param number The index of the product (1-based index).
     */
    @When("Click 'Add to cart' button for {int} product")
    public void clickAddToCartButtonForProductByNumber(Integer number) {
        Pages.homePage().clickAddToCartProductByNumber(number);
    }

    /**
     * Step definition to remember information about a specific product by its number.
     * This includes saving the product's name, description, and price to the {@code GlobalContext}.
     * Corresponds to the Cucumber step: {@code When Remember information about {int} product}
     *
     * @param number The index of the product (1-based index).
     */
    @When("Remember information about {int} product")
    public void rememberInformationAboutProductByNumber(int number) {
        globalContext.product = new Product();
        globalContext.product.setProductName(Pages.homePage().getProductNameByNumber(number));
        globalContext.product.setProductDescription(Pages.homePage().getProductDescriptionByNumber(number));
        globalContext.product.setProductPrice(Pages.homePage().getProductPriceByNumber(number));
        logInfo("Product information remembered: " + globalContext.product);
    }

    /**
     * Step definition to verify that the "Remove" button is displayed for a specific product by its number.
     * Corresponds to the Cucumber step: {@code Then The 'Remove' button is displayed for {int} product}
     *
     * @param number The index of the product (1-based index).
     */
    @Then("The 'Remove' button is displayed for {int} product")
    public void theRemoveButtonIsDisplayedForProductByNumber(Integer number) {
        Assert.assertTrue(Pages.homePage().isRemoveButtonDisplayedForProductByNumber(number), "The 'Remove' button is NOT displayed");
    }
}
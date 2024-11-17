package BDD;

import automation.pages.Pages;
import io.cucumber.java.en.Then;
import org.testng.Assert;

/**
 * The {@code CartStepDefinition} class defines step definitions for BDD tests related to the shopping cart.
 * It utilizes the Cucumber framework to verify that the cart page is displayed correctly and
 * that the item details match the expected values stored in the {@code GlobalContext}.
 */
public class CartStepDefinition {

    private final GlobalContext globalContext;

    /**
     * Constructs a new {@code CartStepDefinition} with the provided {@code GlobalContext}.
     *
     * @param crmGlobalContext The global context containing product information.
     */
    public CartStepDefinition(GlobalContext crmGlobalContext) {
        this.globalContext = crmGlobalContext;
    }

    /**
     * Step definition for verifying that the "Your Cart" page is opened.
     * This step corresponds to the Cucumber step: <br>
     * {@code Then The 'Your Cart' page is opened}
     *
     * <p>It asserts that the cart page title is visible on the page.</p>
     */
    @Then("The 'Your Cart' page is opened")
    public void isYourCartPageOpened() {
        Assert.assertTrue(Pages.cartPage().isCartPageTitleDisplayed(),
                "The 'Your Cart' page is not displayed.");
    }

    /**
     * Step definition for verifying that the item values (name, description, and price) in the cart
     * match the values stored in the {@code GlobalContext}.
     * This step corresponds to the Cucumber step: <br>
     * {@code Then The item {int} values are equal}
     *
     * @param number The index of the item to check (1-based index).
     *
     * <p>It checks that the product's name, description, and price in the cart are equal
     * to those stored in the {@code GlobalContext} object.</p>
     */
    @Then("The item {int} values are equal")
    public void checkItemValues(int number) {
        Assert.assertEquals(globalContext.product.getProductName(),
                Pages.cartPage().getItemNameInCartByNumber(number),
                "The item name is not equal");

        Assert.assertEquals(globalContext.product.getProductDescription(),
                Pages.cartPage().getItemDescriptionInCartByNumber(number),
                "The item description is not equal");

        Assert.assertEquals(globalContext.product.getProductPrice(),
                Pages.cartPage().getItemPrice(number),
                "The item price is not equal");
    }
}
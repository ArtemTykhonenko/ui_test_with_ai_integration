package BDD;

import automation.pages.Pages;
import io.cucumber.java.en.Then;
import org.testng.Assert;

public class CartStepDefinition {

    private final GlobalContext globalContext;
    public CartStepDefinition (GlobalContext crmGlobalContext) {
        this.globalContext = crmGlobalContext;
    }

    @Then("The 'Your Cart' page is opened")
    public void isYourCartPageOpened() {
        Assert.assertTrue(Pages.cartPage().isCartPageTitleDisplayed());
    }

    @Then("The item {int} values are equal")
    public void checkItemValues(int number) {
        Assert.assertEquals(globalContext.product.getProductName(), Pages.cartPage().getItemNameInCartByNumber(number), "The item name is not equal");
        Assert.assertEquals(globalContext.product.getProductDescription(), Pages.cartPage().getItemDescriptionInCartByNumber(number),"The item description is not equal");
        Assert.assertEquals(globalContext.product.getProductPrice(), Pages.cartPage().getItemPrice(number), "The item price is not equal");
    }
}

package automation.pages;

import automation.utils.PageTools;
import org.openqa.selenium.By;


public class CartPage extends PageTools {

    // Locators for the main cart page
    private static By cartPageTitle = By.xpath("//span[@class='title' and contains(text(), 'Your Cart')]");
    private static By itemNameByNumber = By.xpath("(//div[@class='cart_item'])[%d]//div[@class='inventory_item_name']");
    private static By itemDescriptionByNumber = By.xpath("(//div[@class='cart_item'])[%d]//div[@class='inventory_item_desc']");
    private static By itemPriceByNumber = By.xpath("(//div[@class='cart_item'])[%d]//div[@class='inventory_item_price']");
    private static By removeButtonByNumber = By.xpath("(//button[contains(text(), 'Remove')])[%d]");
    private static By continueShoppingButton = By.xpath("//button[contains(text(), 'Continue Shopping')]");
    private static By checkoutShoppingButton = By.xpath("//button[contains(text(), 'Checkout')]");

    // Locators for the checkout information page
    private static By checkoutPageTitle = By.xpath("//span[contains(text(), 'Checkout: Your Information')]");
    private static By firstNameField = By.id("first-name");
    private static By secondNameField = By.id("last-name");
    private static By postalCodeField = By.id("postal-code");
    private static By continueButton = By.xpath("//input[@type='submit']");
    private static By cancelButton = By.id("cancel");
    private static By finishButton = By.id("finish");
    private static By paymentInformationLabel = By.xpath("//div[@class='summary_info_label' and contains(text(), 'Payment Information')]");
    private static By shippingLabel = By.xpath("//div[@class='summary_info_label' and contains(text(), 'Shipping Information')]");
    private static By priceTotalLabel = By.xpath("//div[@class='summary_info_label' and contains(text(), 'Price Total')]");
    private static By priceWithTaxesLabel = By.xpath("//div[@class='summary_total_label']");

    public boolean isCartPageTitleDisplayed() {
        waitForElementVisibility(cartPageTitle);
        return isElementVisible(cartPageTitle);
    }

    public String getItemNameInCartByNumber(int number) {
        waitForElementVisibility(itemNameByNumber, number);
        return getElementText(itemNameByNumber, number);
    }

    public String getItemDescriptionInCartByNumber(int number) {
        waitForElementVisibility(itemDescriptionByNumber, number);
        return getElementText(itemDescriptionByNumber, number);
    }

    public String getItemPrice(int number) {
        waitForElementVisibility(itemPriceByNumber, number);
        return getElementText(itemPriceByNumber, number);
    }
}
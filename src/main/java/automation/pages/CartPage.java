package automation.pages;

import automation.utils.PageTools;
import org.openqa.selenium.By;

public class CartPage extends PageTools {

    //Main Cart page
    private static final By cartPageTitle = By.xpath("//span[@class='title' and contains(text(), 'Your Cart')]");
    private static final By itemNameByNumber = By.xpath("(//div[@class='cart_item'])[%d]//div[@class='inventory_item_name']");
    private static final By itemDescriptionByNumber = By.xpath("(//div[@class='cart_item'])[%d]//div[@class='inventory_item_desc']");
    private static final By itemPriceByNumber = By.xpath("(//div[@class='cart_item'])[%d]//div[@class='inventory_item_price']");
    private static final By removeButtonByNumber = By.xpath("(//button[contains(text(), 'Remove')])[%d]");
    private static final By continueShoppingButton = By.xpath("//button[contains(text(), 'Continue Shopping')]");
    private static final By checkoutShoppingButton = By.xpath("//button[contains(text(), 'Checkout')]");

    //Checkout information page
    private static final By checkoutPageTitle = By.xpath("//span[contains(text(), 'Checkout: Your Information')]");
    private static final By firstNameField = By.id("first-name");
    private static final By secondNameField = By.id("last-name");
    private static final By postalCodeField = By.id("postal-code");
    private static final By continueButton = By.xpath("//input[@type='submit']");
    private static final By cancelButton = By.id("cancel");
    private static final By finishButton = By.id("finish");
    private static final By paymentInformationLabel = By.xpath("//div[@class='summary_info_label' and contains(text(), 'Payment Information')]");
    private static final By shippingLabel = By.xpath("//div[@class='summary_info_label' and contains(text(), 'Shipping Information')]");
    private static final By priceTotalLabel = By.xpath("//div[@class='summary_info_label' and contains(text(), 'Price Total')]");
    private static final By priceWithTaxesLabel = By.xpath("//div[@class='summary_total_label']");

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

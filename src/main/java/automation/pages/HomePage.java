package automation.pages;

import automation.utils.PageTools;
import org.openqa.selenium.By;

/**
 * The {@code HomePage} class represents the main page of the application.
 * It provides methods to interact with various elements on the homepage,
 * such as the side menu, product listings, and cart functionality.
 *
 * <p>This class extends {@code PageTools} to leverage common Selenium utilities.</p>
 */
public class HomePage extends PageTools {

    // Locators for the elements on the homepage
    private static By pageTitle = By.xpath("//span[@class='title' and contains(text(), 'Products')] +");
    private static By sideMenu = By.id("react-burger-menu-btn");
    private static By logoutLink = By.id("logout_sidebar_link");
    private static By allItemsLink = By.id("inventory_sidebar_link");
    private static By aboutLink = By.id("about_sidebar_link");
    private static By resetAppState = By.id("reset_sidebar_link");
    private static By cartLink = By.xpath("//a[@class='shopping_cart_link']");
    private static By addToCartLink = By.xpath("(//button[contains(text(), 'Add to cart')])[%d]");
    private static By productNameByNumber = By.xpath("(//div[@class='inventory_item'])[%d]/div[@class='inventory_item_description']//div[@class='inventory_item_name ']");
    private static By productDescriptionByNumber = By.xpath("(//div[@class='inventory_item'])[%d]/div[@class='inventory_item_description']//div[@class='inventory_item_desc']");
    private static By productPriceByNumber = By.xpath("(//div[@class='inventory_item'])[%d]/div[@class='inventory_item_description']//div[@class='inventory_item_price']");
    private static By removeButton = By.xpath("(//button[contains(text(), 'Remove')])[%d]");

    /**
     * Checks if the main page is displayed by verifying the visibility of the page title.
     *
     * @return {@code true} if the main page is visible, otherwise {@code false}.
     */
    public boolean isMainPageDisplayed() {
        waitForElementVisibility(pageTitle);
        return isElementVisible(pageTitle);
    }

    /**
     * Clicks the side menu button to open the navigation menu.
     */
    public void clickSideMenu() {
        waitForElementVisibility(sideMenu);
        clickElement(sideMenu);
    }

    /**
     * Clicks the logout link to log out of the application.
     */
    public void clickLogoutLink() {
        waitForElementVisibility(logoutLink);
        clickElement(logoutLink);
    }

    /**
     * Clicks the "All Items" link to navigate to the items listing page.
     */
    public void clickAllItems() {
        waitForElementVisibility(allItemsLink);
        clickElement(allItemsLink);
    }

    /**
     * Clicks the "About" link to navigate to the About page.
     */
    public void clickAboutLink() {
        waitForElementVisibility(aboutLink);
        clickElement(aboutLink);
    }

    /**
     * Clicks the "Reset App State" link to reset the application's state.
     */
    public void clickResetAppState() {
        waitForElementVisibility(resetAppState);
        clickElement(resetAppState);
    }

    /**
     * Clicks the cart link to navigate to the shopping cart page.
     */
    public void clickCartLink() {
        waitForElementVisibility(cartLink);
        clickElement(cartLink);
    }

    /**
     * Clicks the "Add to Cart" button for a specific product by its position.
     *
     * @param number The position of the product in the list (1-based index).
     */
    public void clickAddToCartProductByNumber(int number) {
        waitForElementVisibility(addToCartLink, number);
        clickElement(addToCartLink, number);
    }

    /**
     * Retrieves the name of a product by its position in the list.
     *
     * @param number The position of the product in the list (1-based index).
     * @return The name of the product.
     */
    public String getProductNameByNumber(int number) {
        waitForElementVisibility(productNameByNumber, number);
        return getElementText(productNameByNumber, number);
    }

    /**
     * Retrieves the description of a product by its position in the list.
     *
     * @param number The position of the product in the list (1-based index).
     * @return The description of the product.
     */
    public String getProductDescriptionByNumber(int number) {
        waitForElementVisibility(productDescriptionByNumber, number);
        return getElementText(productDescriptionByNumber, number);
    }

    /**
     * Retrieves the price of a product by its position in the list.
     *
     * @param number The position of the product in the list (1-based index).
     * @return The price of the product.
     */
    public String getProductPriceByNumber(int number) {
        waitForElementVisibility(productPriceByNumber, number);
        return getElementText(productPriceByNumber, number);
    }

    /**
     * Checks if the "Remove" button is displayed for a specific product by its position.
     *
     * @param number The position of the product in the list (1-based index).
     * @return {@code true} if the "Remove" button is visible, otherwise {@code false}.
     */
    public boolean isRemoveButtonDisplayedForProductByNumber(int number) {
        waitForElementVisibility(removeButton, number);
        return isElementVisible(removeButton, number);
    }
}
package automation.pages;

import automation.utils.PageTools;
import org.openqa.selenium.By;

public class HomePage extends PageTools {

    // Locators for the elements on the homepage
    private static By pageTitle = By.xpath("//span[@class='title' and contains(text(), 'Products')]");
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

    public boolean isMainPageDisplayed() {
        waitForElementVisibility(pageTitle);
        return isElementVisible(pageTitle);
    }

    public void clickSideMenu() {
        waitForElementVisibility(sideMenu);
        clickElement(sideMenu);
    }

    public void clickLogoutLink() {
        waitForElementVisibility(logoutLink);
        clickElement(logoutLink);
    }

    public void clickAllItems() {
        waitForElementVisibility(allItemsLink);
        clickElement(allItemsLink);
    }

    public void clickAboutLink() {
        waitForElementVisibility(aboutLink);
        clickElement(aboutLink);
    }

    public void clickResetAppState() {
        waitForElementVisibility(resetAppState);
        clickElement(resetAppState);
    }

    public void clickCartLink() {
        waitForElementVisibility(cartLink);
        clickElement(cartLink);
    }

    public void clickAddToCartProductByNumber(int number) {
        waitForElementVisibility(addToCartLink, number);
        clickElement(addToCartLink, number);
    }

    public String getProductNameByNumber(int number) {
        waitForElementVisibility(productNameByNumber, number);
        return getElementText(productNameByNumber, number);
    }

    public String getProductDescriptionByNumber(int number) {
        waitForElementVisibility(productDescriptionByNumber, number);
        return getElementText(productDescriptionByNumber, number);
    }

    public String getProductPriceByNumber(int number) {
        waitForElementVisibility(productPriceByNumber, number);
        return getElementText(productPriceByNumber, number);
    }

    public boolean isRemoveButtonDisplayedForProductByNumber(int number) {
        waitForElementVisibility(removeButton, number);
        return isElementVisible(removeButton, number);
    }
}
package automation.utils;

import automation.api.LocatorAutoFixer;
import automation.base.DriverManager;
import io.cucumber.core.logging.Logger;
import io.cucumber.core.logging.LoggerFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * The {@code PageTools} class provides utility methods for interacting with web elements
 * using Selenium WebDriver. It includes methods for clicking elements, typing text, retrieving text,
 * and waiting for elements to be visible on the page.
 *
 * <p>This class extends {@code DefaultLogger} for logging capabilities and uses
 * {@code DriverManager} to obtain the WebDriver instance.</p>
 *
 * <p>The class is also annotated with {@code LocatorAutoFixer} to enable automatic
 * locator correction in case of issues.</p>
 */
@LocatorAutoFixer
public class PageTools extends DefaultLogger {

    private Logger logger = LoggerFactory.getLogger(PageTools.class);
    private WebDriver driver;
    private WebDriverWait wait;

    /**
     * Initializes the {@code PageTools} with a WebDriver instance and sets a default wait time.
     */
    public PageTools() {
        this.driver = DriverManager.getDriver(null);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Opens the specified URL in the browser.
     *
     * @param url  The URL to open.
     * @param args Optional arguments for formatting the URL.
     */
    public void openUrl(String url, Object... args) {
        driver.get(url);
        logInfo("Opened URL: " + url);
    }

    /**
     * Types the given text into the element located by the specified locator.
     * Clears any existing text before typing.
     *
     * @param locator The locator of the element to type into.
     * @param text    The text to be entered.
     * @param args    Optional arguments for formatting the locator.
     */
    public void typeText(By locator, String text, Object... args) {
        WebElement element = waitForElementVisibility(locator, args);
        element.clear();
        element.sendKeys(text);
        logInfo("Typed text '" + text + "' into element: " + locator);
    }

    /**
     * Clicks on the element located by the specified locator.
     *
     * @param locator The locator of the element to click.
     * @param args    Optional arguments for formatting the locator.
     */
    public void clickElement(By locator, Object... args) {
        WebElement element = waitForElementVisibility(locator, args);
        element.click();
        logInfo("Clicked on element: " + locator);
    }

    /**
     * Retrieves the text content of the element located by the specified locator.
     *
     * @param locator The locator of the element to retrieve text from.
     * @param args    Optional arguments for formatting the locator.
     * @return The text content of the element.
     */
    public String getElementText(By locator, Object... args) {
        WebElement element = waitForElementVisibility(locator, args);
        return element.getText();
    }

    /**
     * Checks if the element located by the specified locator is visible on the page.
     *
     * @param locator The locator of the element to check visibility.
     * @param args    Optional arguments for formatting the locator.
     * @return {@code true} if the element is visible, otherwise {@code false}.
     */
    public boolean isElementVisible(By locator, Object... args) {
        WebElement element = waitForElementVisibility(locator, args);
        return element.isDisplayed();
    }

    /**
     * Waits for the visibility of the element located by the specified locator.
     *
     * @param locator The locator of the element to wait for.
     * @param args    Optional arguments for formatting the locator.
     * @return The {@code WebElement} once it becomes visible.
     */
    public WebElement waitForElementVisibility(By locator, Object... args) {
        By formattedLocator = formatLocator(locator, args);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(formattedLocator));
    }

    /**
     * Formats the specified locator using the provided arguments.
     * This method supports locators of type XPath, CSS Selector, and ID.
     *
     * @param by   The original locator.
     * @param args Optional arguments for formatting the locator.
     * @return The formatted {@code By} locator.
     */
    private By formatLocator(By by, Object... args) {
        if (args != null && args.length > 0) {
            String locator = String.format(by.toString(), args);
            if (by.toString().startsWith("By.xpath:")) {
                return By.xpath(locator.replace("By.xpath: ", ""));
            } else if (by.toString().startsWith("By.cssSelector:")) {
                return By.cssSelector(locator.replace("By.cssSelector: ", ""));
            } else if (by.toString().startsWith("By.id:")) {
                return By.id(locator.replace("By.id: ", ""));
            }
        }
        return by;
    }
}
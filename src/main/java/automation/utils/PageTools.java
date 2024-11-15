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

@LocatorAutoFixer
public class PageTools extends DefaultLogger {

    private static Logger logger = LoggerFactory.getLogger(PageTools.class);
    private WebDriver driver;
    private WebDriverWait wait;

    public PageTools() {
        this.driver = DriverManager.getInstance().getDriver("chrome");
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void openUrl(String url, Object... args) {
        driver.get(url);
        logInfo("Opened URL: " + url);
    }

    public void typeText(By locator, String text, Object... args) {
        WebElement element = waitForElementVisibility(locator, args);
        element.clear();
        element.sendKeys(text);
        logInfo("Typed text '" + text + "' into element: " + locator);
    }

    public void clickElement(By locator, Object... args) {
        WebElement element = waitForElementVisibility(locator, args);
        element.click();
        logInfo("Clicked on element: " + locator);
    }

    public String getElementText(By locator, Object... args) {
        WebElement element = waitForElementVisibility(locator, args);
        return element.getText();
    }

    public boolean isElementVisible(By locator, Object... args) {
        WebElement element = waitForElementVisibility(locator, args);
        return element.isDisplayed();
    }

    public WebElement waitForElementVisibility(By locator, Object... args) {
        By formattedLocator = formatLocator(locator, args);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(formattedLocator));
    }

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
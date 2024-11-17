package automation.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.safari.SafariDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class manages the creation and handling of WebDriver instances
 * for different browsers, ensuring thread safety using {@code ThreadLocal}.
 * It supports Chrome, Firefox, Edge, and Safari browsers.
 *
 * <p>Example usage:</p>
 * <pre>
 * WebDriver driver = DriverManager.getDriver("chrome");
 * driver.get("https://example.com");
 * DriverManager.quitDriver();
 * </pre>
 */
public class DriverManager {
    private static final Logger logger = LoggerFactory.getLogger(DriverManager.class);

    /**
     * ThreadLocal variable to store WebDriver instances for each thread separately.
     */
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    /**
     * Returns the WebDriver instance for the current thread.
     * If no instance exists, it creates one using the specified browser.
     *
     * @param browser The browser to use ("chrome", "firefox", "edge", or "safari").
     * @return The WebDriver instance for the current thread.
     * @throws IllegalArgumentException if the specified browser is not supported.
     */
    public static WebDriver getDriver(String browser) {
        if (driver.get() == null) {
            setupDriver(browser);
        }
        return driver.get();
    }

    /**
     * Sets up the WebDriver instance based on the specified browser.
     * This method also maximizes the browser window after creation.
     *
     * @param browser The browser to set up ("chrome", "firefox", "edge", or "safari").
     * @throws IllegalArgumentException if the specified browser is not supported.
     */
    private static void setupDriver(String browser) {
        WebDriver webDriver;
        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                webDriver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                webDriver = new FirefoxDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                webDriver = new EdgeDriver();
                break;
            case "safari":
                webDriver = new SafariDriver();
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
        webDriver.manage().window().maximize();
        driver.set(webDriver);
        logger.info("Browser {} has been set up successfully", browser);
    }

    /**
     * Quits the WebDriver instance for the current thread and removes it from ThreadLocal storage.
     * This method should be called after completing the browser session to avoid memory leaks.
     */
    public static void quitDriver() {
        WebDriver webDriver = driver.get();
        if (webDriver != null) {
            webDriver.quit();
            logger.info("Browser session has been terminated.");
            driver.remove();
        }
    }
}
package automation.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.safari.SafariDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DriverManager {

    private static Logger logger = LoggerFactory.getLogger(DriverManager.class);
    private static DriverManager instance;
    private WebDriver driver;

    private DriverManager() {
        // Private constructor to prevent instantiation
    }

    public static DriverManager getInstance() {
        if (instance == null) {
            synchronized (DriverManager.class) {
                if (instance == null) {
                    instance = new DriverManager();
                }
            }
        }
        return instance;
    }

    public WebDriver getDriver(String browser) {
        if (driver == null) {
            setupDriver(browser);
        }
        return driver;
    }

    private void setupDriver(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            case "safari":
                driver = new SafariDriver();
                break;
            default:
                throw new IllegalArgumentException("Do not support this browser: " + browser);
        }

        // Logging browser setup
        logger.info("Browser {} has been set up successfully", browser);
    }

    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            logger.info("Browser session has been terminated.");
            driver = null;
        }
    }
}
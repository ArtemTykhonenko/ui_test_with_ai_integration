package BDD;

import automation.base.DriverManager; // Убедитесь, что этот путь корректен
import automation.utils.DefaultLogger;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class Hooks extends DefaultLogger {

    private WebDriver driver;
    private WebDriverWait wait;
    private static final Logger logger = LoggerFactory.getLogger(Hooks.class);

    @Before
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");
        driver = DriverManager.getInstance().getDriver(browser); // Вызов нестатического метода через Singleton
        logger.info("Open {} browser", browser);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        logger.info("Initialized WebDriver и WebDriverWait");
        driver.manage().window().maximize();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            logger.info("Start clear cache..................................................................");
            driver.manage().deleteAllCookies();
            logger.info("Cache cleared.");
            DriverManager.getInstance().quitDriver(); // Вызов нестатического метода через Singleton
            logger.info("WebDriver closed");
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

    public WebDriverWait getWait() {
        return wait;
    }
}
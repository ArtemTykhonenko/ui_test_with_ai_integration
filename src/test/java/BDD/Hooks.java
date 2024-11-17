package BDD;

import automation.base.DriverManager;
import automation.utils.DefaultLogger;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

/**
 * The {@code Hooks} class provides setup and teardown methods for Cucumber tests.
 * It uses the {@code @Before} and {@code @After} annotations to initialize and clean up
 * the WebDriver before and after each scenario, ensuring a fresh browser session for each test.
 *
 * <p>This class extends {@code DefaultLogger} for enhanced logging capabilities and uses
 * {@code DriverManager} to manage the WebDriver instance.</p>
 */
public class Hooks extends DefaultLogger {

    private WebDriver driver;
    private WebDriverWait wait;
    private static final Logger logger = LoggerFactory.getLogger(Hooks.class);

    /**
     * The {@code setUp()} method is executed before each Cucumber scenario.
     * It initializes the WebDriver and WebDriverWait for the specified browser.
     *
     * <p>The browser type can be specified via the system property {@code browser}.
     * If no system property is provided, the default browser will be Chrome.</p>
     *
     * <p>Example usage:</p>
     * <pre>
     * mvn test -Dbrowser=firefox
     * </pre>
     */
    @Before
    public void setUp() {
        // Get the browser type from system properties, default to Chrome if not specified
        String browser = System.getProperty("browser", "chrome");

        // Initialize WebDriver using DriverManager
        driver = DriverManager.getDriver(browser);
        logger.info("Open {} browser", browser);

        // Initialize WebDriverWait with a 10-second timeout
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        logger.info("Initialized WebDriver and WebDriverWait");
    }

    /**
     * The {@code tearDown()} method is executed after each Cucumber scenario.
     * It closes the WebDriver instance to clean up resources.
     */
    @After
    public void tearDown() {
        // Quit the WebDriver and release resources
        DriverManager.quitDriver();
        logger.info("WebDriver closed");
    }
}
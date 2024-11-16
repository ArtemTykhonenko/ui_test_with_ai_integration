package BDD;

import automation.utils.DefaultLogger;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Hooks extends DefaultLogger {
    private static final Logger logger = LoggerFactory.getLogger(Hooks.class);

    @Before
    public void setUp() {
        // Устанавливаем браузер через системные свойства
        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.browserSize = "1920x1080";
        Configuration.headless = Boolean.parseBoolean(System.getProperty("headless", "false"));
        Configuration.timeout = 10000;
        Configuration.pageLoadStrategy = "eager";

        logger.info("Initialized Selenide WebDriver with browser: {}", Configuration.browser);
    }

    @After
    public void tearDown() {
        if (WebDriverRunner.hasWebDriverStarted()) {
            WebDriverRunner.closeWebDriver();
            logger.info("Closed Selenide WebDriver");
        } else {
            logger.warn("WebDriver was not started, nothing to close");
        }
    }
}

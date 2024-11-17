package BDD;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.chrome.ChromeOptions;
import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Hooks {
    private static final Logger logger = LoggerFactory.getLogger(Hooks.class);

    @Before(order = 0)
    public void setUpBrowser() {
        // Установка настроек для Chrome
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.setExperimentalOption("prefs", new java.util.HashMap<String, Object>() {{
            put("profile.password_manager_enabled", false);
            put("credentials_enable_service", false);
            put("profile.default_content_setting_values.notifications", 2);
        }});

        // Применение настроек к Selenide
        Configuration.browserCapabilities = options;
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";

        logger.info("Настроен Chrome WebDriver с отключением предупреждений о паролях");
    }

    @After
    public void tearDown() {
        if (!Boolean.parseBoolean(System.getProperty("holdbrowseropen", "false"))) {
            WebDriverRunner.closeWebDriver();
            logger.info("Закрыт браузер после теста");
        } else {
            logger.info("Браузер оставлен открытым по запросу");
        }
    }
}
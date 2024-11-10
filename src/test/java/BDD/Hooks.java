package BDD;

import automation.base.DriverManager;
import automation.utils.DefaultLogger;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
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

        // Инициализация WebDriver через DriverManager
        driver = DriverManager.getDriver(browser);
        logger.info("Запускаем тесты в {}", browser);

        // Инициализация WebDriverWait
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        logger.info("Инициализирован WebDriver и WebDriverWait");

        // Максимизация окна браузера
        maximizeWindow();
    }

    @After
    public void tearDown() {
        try {
            if (driver != null) {
                DriverManager.quitDriver(); // Используем метод из DriverManager для закрытия драйвера
                logger.info("Закрыт драйвер браузера");
            }
        } catch (Exception e) {
            logger.error("Ошибка при закрытии драйвера: {}", e.getMessage());
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

    public WebDriverWait getWait() {
        return wait;
    }

    public void clearCache() {
        logger.info("Очистка кеша...");
        driver.manage().deleteAllCookies();
        logger.info("Кэш очищен");
    }

    public void waitForElementToBeVisible(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        logger.info("Элемент стал видимым: {}", locator);
    }

    private void maximizeWindow() {
        try {
            driver.manage().window().maximize();
            logger.info("Максимизировано окно браузера");
        } catch (Exception e) {
            logger.warn("Не удалось максимизировать окно браузера: {}", e.getMessage());
        }
    }
}
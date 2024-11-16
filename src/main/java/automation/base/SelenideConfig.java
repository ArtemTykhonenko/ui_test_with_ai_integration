package automation.base;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class SelenideConfig {

    @BeforeSuite(alwaysRun = true)
    public void setUp() {
        // Устанавливаем браузер через системные свойства
        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.browserSize = "1920x1080"; // Разворачивать браузер на весь экран
        Configuration.headless = Boolean.parseBoolean(System.getProperty("headless", "false"));
        Configuration.timeout = 10000; // Таймаут ожидания элементов (10 секунд)
        Configuration.pageLoadStrategy = "eager"; // Стратегия загрузки страницы
        Configuration.reportsFolder = "target/selenide-reports"; // Папка для отчетов и скриншотов

        // Дополнительные настройки для Chrome (если нужен полноэкранный режим)
        if (Configuration.browser.equals("chrome")) {
            Configuration.browserCapabilities.setCapability("goog:chromeOptions", "--start-maximized");
        }
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        // Закрытие браузера после выполнения всех тестов
        WebDriverRunner.closeWebDriver();
    }
}

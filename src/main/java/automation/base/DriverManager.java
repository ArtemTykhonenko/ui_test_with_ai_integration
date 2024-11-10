package automation.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverManager {
    private static DriverManager instance;
    private WebDriver driver;
    private String currentBrowser;

    // Приватный конструктор
    private DriverManager() {}

    // Метод для получения единственного экземпляра
    public static DriverManager getInstance() {
        if (instance == null) {
            instance = new DriverManager(); // Ленивая инициализация
        }
        return instance;
    }

    public WebDriver getDriver(String browser) {
        // Проверяем, нужно ли создавать новый драйвер
        if (driver == null || !browser.equals(currentBrowser)) {
            currentBrowser = browser; // Сохраняем текущий браузер
            setupDriver(browser); // Инициализация драйвера в зависимости от выбранного браузера
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
            default:
                throw new IllegalArgumentException("Браузер не поддерживается: " + browser);
        }

        // Настройки драйвера
        driver.manage().window().maximize();
    }

    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null; // Обнуляем драйвер после выхода
            currentBrowser = null; // Сбрасываем выбранный браузер
        }
    }
}
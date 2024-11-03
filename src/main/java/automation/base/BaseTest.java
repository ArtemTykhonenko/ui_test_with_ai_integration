package automation.base;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        // Конфигурация браузера
        Configuration.browserSize = "1920x1080"; // Установка размера браузера
        Selenide.open(""); // Здесь можно оставить пустым или указать URL
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        Selenide.closeWebDriver(); // Закрытие драйвера
    }
}
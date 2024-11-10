package automation.utils;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.$;

public class SelenideTools {

    /**
     * Получает WebDriver.
     */
    public static WebDriver getDriver() {
        return WebDriverRunner.getWebDriver();
    }

    /**
     * Открывает указанный URL.
     */
    public static void openUrl(String url) {
        Selenide.open(url);
    }

    /**
     * Получает элемент по заданному локатору.
     */
    public static SelenideElement getElement(By by) {
        return $(by);
    }

    /**
     * Нажимает на элемент.
     */
    public static void clickElement(By by) {
        getElement(by).shouldBe(Condition.visible).click();
    }

    /**
     * Вводит текст в поле.
     */
    public static void typeText(By by, String text) {
        getElement(by).shouldBe(Condition.visible).setValue(text);
    }

    /**
     * Проверяет, является ли элемент видимым.
     */
    public static boolean isVisible(By by) {
        return getElement(by).is(Condition.visible);
    }

    /**
     * Проверяет, существует ли элемент.
     */
    public static boolean isElementPresent(By by) {
        return getElement(by).is(Condition.exist);
    }

    /**
     * Ожидает, пока элемент станет видимым.
     */
    public static void waitForVisibility(By by) {
        getElement(by).shouldBe(Condition.visible);
    }

    /**
     * Получает текст из элемента.
     */
    public static String getElementText(By by) {
        return getElement(by).shouldBe(Condition.visible).getText();
    }

    /**
     * Получает список элементов по заданному локатору.
     */
    public static ArrayList<SelenideElement> getElements(By by) {
        ElementsCollection elements = Selenide.$$(by);
        ArrayList<SelenideElement> elementList = new ArrayList<>();
        for (SelenideElement element : elements) {
            elementList.add(element);
        }
        return elementList;
    }

    /**
     * Закрывает текущее окно.
     */
    public static void closeCurrentTab() {
        Selenide.closeWindow();
    }

    /**
     * Ожидает, пока элемент станет кликабельным.
     */
    public static void waitForElementClickable(By by) {
        getElement(by).shouldBe(Condition.visible).shouldBe(Condition.enabled);
    }

    /**
     * Закрывает все вкладки, кроме текущей.
     */
    public static void closeAllTabsExceptCurrent() {
        String currentWindow = getDriver().getWindowHandle();
        ArrayList<String> tabs = new ArrayList<>(getDriver().getWindowHandles());
        for (String tab : tabs) {
            if (!tab.equals(currentWindow)) {
                Selenide.switchTo().window(tab);
                closeCurrentTab();
            }
        }
        Selenide.switchTo().window(currentWindow);
    }

    /**
     * Ожидает, пока элемент не исчезнет.
     */
    public static void waitForElementInvisibility(By by) {
        getElement(by).shouldBe(Condition.hidden);
    }
}
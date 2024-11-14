package automation.utils;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class PageTools extends DefaultLogger {

    private static String getPreviousMethodNameAsText() {
        String methodName = Thread.currentThread().getStackTrace()[3].getMethodName();
        String replacedMethodName = methodName.replaceAll(
                String.format("%s|%s|%s",
                        "(?<=[A-Z])(?=[A-Z][a-z])",
                        "(?<=[^A-Z])(?=[A-Z])",
                        "(?<=[A-Za-z])(?=[^A-Za-z])"
                ),
                " "
        );
        return replacedMethodName.substring(0, 1).toUpperCase() + replacedMethodName.substring(1).toLowerCase();
    }

    /**
     * Получает элемент по заданному локатору с помощью LocatorParser.
     */
    protected SelenideElement getElement(By by, Object... args) {
        logInfo("Getting element by locator: " + by, args);
        return $(LocatorParser.parseLocator(by, args));
    }

    /**
     * Нажимает на элемент.
     */
    protected void clickElement(By by, Object... args) {
        logInfo("Clicking on element: " + by);
        getElement(by, args).shouldBe(Condition.visible).click();
    }

    /**
     * Вводит текст в поле.
     */
    protected void typeText(By by, String text, Object... args) {
        logInfo("Typing text '" + text + "' into element: " + by);
        getElement(by, args).shouldBe(Condition.visible).setValue(text);
    }

    /**
     * Ожидает, пока элемент станет видимым.
     */
    protected void waitForElementVisibility(By by, Object... args) {
        logInfo("Waiting for visibility of element: " + by);
        getElement(by, args).shouldBe(Condition.visible);
    }

    /**
     * Проверяет, существует ли элемент.
     */
    protected boolean isElementPresent(By by, Object... args) {
        boolean exist = getElement(by, args).is(Condition.exist);
        logInfo("Element exists (" + by + "): " + exist);
        return exist;
    }

    /**
     * Проверяет видно ли элемент на странице.
     */
    protected boolean isElementVisible(By by, Object... args) {
        boolean exist = getElement(by, args).is(Condition.visible);
        logInfo("Element " + by + " visible");
        return exist;
    }

    protected String getElementText(By by, Object... args) {
        SelenideElement element = getElement(by, args);
        element.shouldBe(Condition.visible);
        String text = element.getText();
        logInfo("Text of element " + by + ": " + text);
        return text;
    }
}
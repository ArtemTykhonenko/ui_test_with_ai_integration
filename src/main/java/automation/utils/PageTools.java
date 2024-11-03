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
    protected SelenideElement getElement(By by) {
        logInfo("Getting element by locator: " + by);
        return $(LocatorParser.parseLocator(by));
    }

    /**
     * Нажимает на элемент.
     */
    protected void clickElement(By by) {
        logInfo("Clicking on element: " + by);
        getElement(by).shouldBe(Condition.visible).click();
    }

    /**
     * Вводит текст в поле.
     */
    protected void typeText(By by, String text) {
        logInfo("Typing text '" + text + "' into element: " + by);
        getElement(by).shouldBe(Condition.visible).setValue(text);
    }

    /**
     * Ожидает, пока элемент станет видимым.
     */
    protected void waitForVisibility(By by) {
        logInfo("Waiting for visibility of element: " + by);
        getElement(by).shouldBe(Condition.visible);
    }

    /**
     * Проверяет, существует ли элемент.
     */
    protected boolean isElementPresent(By by) {
        boolean exists = getElement(by).is(Condition.exist);
        logInfo("Element exists (" + by + "): " + exists);
        return exists;
    }
}
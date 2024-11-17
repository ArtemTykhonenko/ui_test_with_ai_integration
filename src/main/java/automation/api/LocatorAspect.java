package automation.api;

import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.ex.ElementNotFound;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class LocatorAspect {
    private static final Logger logger = LoggerFactory.getLogger(LocatorAspect.class);
    private final ChatGPTClient chatGPTClient = new ChatGPTClient();

    @Around("@within(automation.api.LocatorAutoFixer) || @annotation(automation.api.LocatorAutoFixer)")
    public Object handleLocatorAutoFix(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("Intercepted method call: {}", joinPoint.getSignature());

        try {
            // Первоначальная попытка выполнения метода
            return joinPoint.proceed();
        } catch (ElementNotFound e) {
            logger.warn("ElementNotFound intercepted: {}", joinPoint.getSignature(), e);
            return handleElementNotFound(joinPoint, e);
        } catch (Throwable e) {
            logger.warn("Throwable intercepted: {}", joinPoint.getSignature(), e);
            throw e;
        }
    }

    /**
     * Обработка исключения ElementNotFound.
     */
    private Object handleElementNotFound(ProceedingJoinPoint joinPoint, ElementNotFound exception) throws Throwable {
        logger.warn("Handling ElementNotFound...");
        String locatorInfo = extractLocatorFromMessage(exception.getMessage());
        return handleLocatorFix(joinPoint, locatorInfo, exception.getMessage());
    }

    private Object handleLocatorFix(ProceedingJoinPoint joinPoint, String originalLocator, String errorMessage) throws Throwable {
        Object[] args = joinPoint.getArgs();

        // Проверка, что первый аргумент является локатором
        if (args.length > 0 && args[0] instanceof By) {
            By locator = (By) args[0];
            String pageSource = getCurrentPageSource();

            logger.info("Requesting locator fix for locator: {}", locator);

            // Запрос на исправление локатора через ChatGPT
            String correctedLocatorStr = requestLocatorFix(originalLocator != null ? originalLocator : locator.toString(), pageSource);

            if (correctedLocatorStr != null) {
                logger.info("[FIX] Received corrected locator: {}", correctedLocatorStr);
                By correctedLocator = parseLocator(correctedLocatorStr);

                if (correctedLocator != null) {
                    logger.info("Parsed corrected locator: {}", correctedLocator);
                    args[0] = correctedLocator;

                    // Повторное выполнение с исправленным локатором
                    try {
                        logger.info("Retrying method execution with corrected locator...");
                        return joinPoint.proceed(args);
                    } catch (Exception retryException) {
                        logger.error("Retry with corrected locator failed: {}", retryException.getMessage());
                        throw retryException;
                    }
                }
            }
        }

        // Если исправить не удалось, выбрасываем оригинальное исключение
        throw new RuntimeException(errorMessage);
    }

    private String getCurrentPageSource() {
        WebDriver driver = WebDriverRunner.getWebDriver();
        return driver != null ? driver.getPageSource() : "";
    }

    private String requestLocatorFix(String locator, String pageSource) {
        try {
            logger.info("Sending request to ChatGPT for locator fix...");
            return chatGPTClient.requestLocatorFix(locator, pageSource);
        } catch (Exception e) {
            logger.error("Error while requesting locator fix from ChatGPT", e);
            return null;
        }
    }

    private By parseLocator(String locatorStr) {
        try {
            if (locatorStr.startsWith("By.id")) {
                return By.id(extractLocatorValue(locatorStr));
            } else if (locatorStr.startsWith("By.cssSelector")) {
                return By.cssSelector(extractLocatorValue(locatorStr));
            } else if (locatorStr.startsWith("By.xpath")) {
                return By.xpath(extractLocatorValue(locatorStr));
            }
        } catch (Exception e) {
            logger.error("Error while parsing locator string: {}", locatorStr, e);
        }
        return null;
    }

    private String extractLocatorValue(String locatorStr) {
        return locatorStr.substring(locatorStr.indexOf("\"") + 1, locatorStr.lastIndexOf("\""));
    }

    /**
     * Извлечение информации о локаторе из сообщения об ошибке.
     */
    private String extractLocatorFromMessage(String message) {
        if (message != null && message.contains("Unable to locate element")) {
            int startIndex = message.indexOf("{") + 1;
            int endIndex = message.indexOf("}");
            if (startIndex > 0 && endIndex > startIndex) {
                return message.substring(startIndex, endIndex);
            }
        }
        return null;
    }
}
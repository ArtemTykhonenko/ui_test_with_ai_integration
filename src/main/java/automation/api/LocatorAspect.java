package automation.api;

import com.codeborne.selenide.WebDriverRunner;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
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
        } catch (TimeoutException e) {
            logger.warn("TimeoutException intercepted: {}", joinPoint.getSignature(), e);
            return handleLocatorAutoFixLogic(joinPoint, e);
        } catch (Exception e) {
            logger.warn("Exception intercepted: {}", joinPoint.getSignature(), e);
            return handleLocatorAutoFixLogic(joinPoint, e);
        }
    }

    private Object handleLocatorAutoFixLogic(ProceedingJoinPoint joinPoint, Exception originalException) throws Throwable {
        Object[] args = joinPoint.getArgs();

        // Проверка, что первый аргумент является локатором
        if (args.length > 0 && args[0] instanceof By) {
            By originalLocator = (By) args[0];
            String pageSource = getCurrentPageSource();

            logger.info("Requesting locator fix for locator: {}", originalLocator);

            // Запрос на исправление локатора через ChatGPT
            String correctedLocatorStr = requestLocatorFix(originalLocator.toString(), pageSource);

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
                        logger.error("Retry with corrected locator failed. Original error: {}, Retry error: {}",
                                originalException.getMessage(), retryException.getMessage());
                        throw retryException;
                    }
                } else {
                    logger.error("Failed to parse corrected locator: {}", correctedLocatorStr);
                }
            } else {
                logger.error("No corrected locator received from ChatGPT for: {}", originalLocator);
            }
        }

        // Если исправить не удалось, выбрасываем оригинальное исключение
        throw originalException;
    }

    /**
     * Получение страницы из текущего драйвера с использованием Selenide.
     */
    private String getCurrentPageSource() {
        WebDriver driver = WebDriverRunner.getWebDriver();
        if (driver != null) {
            try {
                return driver.getPageSource();
            } catch (Exception e) {
                logger.error("Error while retrieving page source", e);
            }
        }
        return "";
    }

    /**
     * Запрос на исправление локатора через ChatGPT.
     */
    private String requestLocatorFix(String locator, String pageSource) {
        try {
            logger.info("Sending request to ChatGPT for locator fix...");
            return chatGPTClient.requestLocatorFix(locator, pageSource);
        } catch (Exception e) {
            logger.error("Error while requesting locator fix from ChatGPT", e);
            return null;
        }
    }

    /**
     * Парсинг исправленного локатора.
     */
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

    /**
     * Извлечение значения из строки локатора.
     */
    private String extractLocatorValue(String locatorStr) {
        return locatorStr.substring(locatorStr.indexOf("\"") + 1, locatorStr.lastIndexOf("\""));
    }
}
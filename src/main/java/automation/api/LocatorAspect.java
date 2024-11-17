package automation.api;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import automation.base.DriverManager;

/**
 * Aspect for handling Selenium locator issues using ChatGPT.
 * This aspect intercepts method calls annotated with {@code @LocatorAutoFixer}
 * and attempts to automatically fix broken locators using ChatGPT if a {@code TimeoutException} or other exceptions occur.
 */
@Aspect
public class LocatorAspect {
    private static final Logger logger = LoggerFactory.getLogger(LocatorAspect.class);
    private final ChatGPTClient chatGPTClient = new ChatGPTClient();

    /**
     * Intercepts method calls to {@code LocatorAutoFixer} tag and handles locator exceptions.
     * If a {@code TimeoutException} or other exception occurs, it attempts to auto-fix the locator using ChatGPT.
     *
     * @param joinPoint The join point representing the intercepted method call.
     * @return The result of the intercepted method if successful, or a retry with a corrected locator if an exception was handled.
     * @throws Throwable if the original exception cannot be resolved.
     */
    @Around("within(@automation.api.LocatorAutoFixer *)")
    public Object handleLocatorAutoFix(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("Intercepted method call: {}", joinPoint.getSignature());

        try {
            return joinPoint.proceed();
        } catch (TimeoutException e) {
            logger.warn("TimeoutException intercepted in method: {}", joinPoint.getSignature(), e);
            return handleLocatorAutoFixLogic(joinPoint, e);
        } catch (Exception e) {
            logger.warn("Exception intercepted in method: {}", joinPoint.getSignature(), e);
            return handleLocatorAutoFixLogic(joinPoint, e);
        }
    }

    /**
     * Handles the logic for automatically fixing the locator using ChatGPT.
     * This method retrieves the page source, sends a request to ChatGPT for fixing the locator,
     * and attempts to retry the method call with the corrected locator.
     *
     * @param joinPoint The join point representing the intercepted method call.
     * @param originalException The original exception that occurred.
     * @return The result of the method if the retry is successful.
     * @throws Throwable if the locator cannot be fixed and the original exception needs to be rethrown.
     */
    private Object handleLocatorAutoFixLogic(ProceedingJoinPoint joinPoint, Exception originalException) throws Throwable {
        Object[] args = joinPoint.getArgs();

        if (args.length > 0 && args[0] instanceof By) {
            By locator = (By) args[0];
            String pageSource = getCurrentPageSource();

            logger.info("Requesting locator fix for locator: {}", locator);
            String correctedLocator = requestLocatorFix(locator.toString(), pageSource);

            if (correctedLocator != null) {
                logger.info("[FIX] Received corrected locator: {}", correctedLocator);
                By newLocator = parseLocator(correctedLocator);

                if (newLocator != null) {
                    LocatorLogger.logLocatorFix(locator.toString(), correctedLocator);
                    logger.info("Parsed corrected locator: {}", newLocator);
                    args[0] = newLocator;

                    try {
                        logger.info("Retrying method execution with corrected locator...");
                        return joinPoint.proceed(args);
                    } catch (Exception retryException) {
                        logger.error("Retry with corrected locator failed. Original error: {}, Retry error: {}",
                                originalException.getMessage(), retryException.getMessage());
                    }
                } else {
                    logger.error("Failed to parse corrected locator: {}", correctedLocator);
                }
            } else {
                logger.error("No corrected locator received from ChatGPT. Original locator: {}", locator);
            }
        }

        // If unable to fix the locator, rethrow the original exception
        throw originalException;
    }

    /**
     * Retrieves the current page source from the active WebDriver instance.
     *
     * @return The page source as a string, or an empty string if the driver is not available.
     */
    private String getCurrentPageSource() {
        WebDriver driver = DriverManager.getDriver(null);
        return driver != null ? driver.getPageSource() : "";
    }

    /**
     * Sends a request to ChatGPT for fixing the given locator based on the page source.
     *
     * @param locator The original Selenium locator that needs fixing.
     * @param pageSource The HTML page source where the locator is used.
     * @return The corrected locator string from ChatGPT, or null if the request fails.
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
     * Parses the corrected locator string received from ChatGPT into a Selenium {@code By} object.
     *
     * @param locatorStr The corrected locator string in the format "By.id()", "By.cssSelector()", or "By.xpath()".
     * @return The parsed {@code By} object, or null if parsing fails.
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
     * Extracts the value from the locator string.
     *
     * @param locatorStr The locator string in the format "By.id("value")", "By.cssSelector("value")", or "By.xpath("value")".
     * @return The extracted locator value.
     */
    private String extractLocatorValue(String locatorStr) {
        return locatorStr.substring(locatorStr.indexOf("\"") + 1, locatorStr.lastIndexOf("\""));
    }
}
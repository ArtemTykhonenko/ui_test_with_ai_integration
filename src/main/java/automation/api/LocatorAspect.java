package automation.api;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import automation.base.DriverManager;

@Aspect
public class LocatorAspect {
    private static final Logger logger = LoggerFactory.getLogger(LocatorAspect.class);
    private final ChatGPTClient chatGPTClient = new ChatGPTClient();

    @Around("within(@automation.api.LocatorAutoFixer *)")
    public Object handleLocatorAutoFix(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("Intercepted method call: {}", joinPoint.getSignature());

        try {
            return joinPoint.proceed();
        } catch (Exception e) {
            logger.error("Exception intercepted during method execution: {}", joinPoint.getSignature(), e);

            Object[] args = joinPoint.getArgs();
            if (args.length > 0 && args[0] instanceof By) {
                By locator = (By) args[0];
                String pageSource = getCurrentPageSource();
                String correctedLocator = requestLocatorFix(locator.toString(), pageSource);

                if (correctedLocator != null) {
                    logger.info("[FIX] Corrected locator: {}", correctedLocator);
                    By newLocator = parseLocator(correctedLocator);
                    if (newLocator != null) {
                        args[0] = newLocator;
                        return joinPoint.proceed(args);
                    }
                }
            }
            throw e;
        }
    }

    private String getCurrentPageSource() {
        WebDriver driver = DriverManager.getInstance().getDriver("chrome");
        return driver != null ? driver.getPageSource() : "";
    }

    private String requestLocatorFix(String locator, String pageSource) {
        try {
            return chatGPTClient.requestLocatorFix(locator, pageSource);
        } catch (Exception e) {
            logger.error("Error while requesting locator fix from ChatGPT", e);
            return null;
        }
    }

    private By parseLocator(String locatorStr) {
        if (locatorStr.startsWith("By.id")) {
            return By.id(locatorStr.substring(locatorStr.indexOf("\"") + 1, locatorStr.lastIndexOf("\"")));
        } else if (locatorStr.startsWith("By.cssSelector")) {
            return By.cssSelector(locatorStr.substring(locatorStr.indexOf("\"") + 1, locatorStr.lastIndexOf("\"")));
        } else if (locatorStr.startsWith("By.xpath")) {
            return By.xpath(locatorStr.substring(locatorStr.indexOf("\"") + 1, locatorStr.lastIndexOf("\"")));
        }
        return null;
    }
}
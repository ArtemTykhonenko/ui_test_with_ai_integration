package automation.api;

import automation.base.DriverManager;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LocatorAspect {

    private static final Logger logger = LoggerFactory.getLogger(LocatorAspect.class);
    private final ChatGPTClient chatGPTClient = new ChatGPTClient(); // Инициализация клиента

    @Around("@within(automation.api.LocatorAutoFixer)") // Обработка классов с аннотацией
    public Object handleLocatorFix(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            return joinPoint.proceed(); // Выполнить оригинальный метод
        } catch (NoSuchElementException e) {
            By locator = getLocatorFromJoinPoint(joinPoint); // Получаем локатор
            logger.error("Локатор не найден: {}", locator);
            String pageSource = getCurrentPageSource();

            // Получаем исправленный локатор от ChatGPT
            String correctedLocator = chatGPTClient.requestLocatorFix(locator.toString(), pageSource);

            if (correctedLocator != null) {
                logger.info("Исправленный локатор: {}", correctedLocator);
                // Обрабатываем новый локатор
                return handleCorrectedLocator(correctedLocator);
            } else {
                logger.error("Не удалось исправить локатор: {}", locator);
                throw e; // Перекидываем оригинальное исключение
            }
        }
    }

    // Метод для извлечения локатора из joinPoint
    private By getLocatorFromJoinPoint(ProceedingJoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs(); // Извлечение аргументов метода
        if (args.length > 0 && args[0] instanceof By) {
            return (By) args[0]; // Если первым аргументом является локатор
        }
        return null; // Если не удалось получить локатор
    }

    // Метод для получения HTML-кода текущей страницы
    private String getCurrentPageSource() {
        WebDriver driver = DriverManager.getInstance().getDriver("chrome"); // Получаем WebDriver
        return driver.getPageSource(); // Получаем HTML-код текущей страницы
    }

    private Object handleCorrectedLocator(String correctedLocator) {
        // Логика для работы с исправленным локатором
        // Например, создание нового элемента по исправленному локатору
        return null; // Замените на логику возвращения необходимого объекта
    }
}
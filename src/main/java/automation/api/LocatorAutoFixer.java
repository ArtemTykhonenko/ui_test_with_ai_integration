package automation.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation used to indicate that automatic locator fixing should be applied.
 * This annotation can be applied to classes or methods to enable automatic correction
 * of broken Selenium locators using AI (e.g., ChatGPT).
 *
 * <p>By applying this annotation, methods or classes can be enhanced to automatically
 * handle locator issues and attempt to fix them in real-time if exceptions occur.
 * </p>
 *
 * <h3>Usage Example:</h3>
 * <pre>
 * &#64;LocatorAutoFixer
 * public class SomePage {
 *     // Methods in this class will automatically attempt to fix locators if they fail
 * }
 *
 * &#64;LocatorAutoFixer
 * public void clickElement(By locator) {
 *     // This method will attempt to fix the locator if a TimeoutException occurs
 * }
 * </pre>
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LocatorAutoFixer {
    // Additional parameters can be added here if needed in the future
}
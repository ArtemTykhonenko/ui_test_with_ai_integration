package automation.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// Указываем, что аннотация может применяться как к методам, так и к классам
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LocatorAutoFixer {
    // Дополнительные параметры могут быть добавлены здесь, если потребуется
}
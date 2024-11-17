package automation.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultLogger {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void logInfo(String message, Object... args) {
        logger.info(formatLogMessage(message, args));
    }

    public void logWarn(String message, Object... args) {
        logger.warn(formatLogMessage(message, args));
    }

    public void logError(String message, Throwable throwable) {
        logger.error(message, throwable);
    }

    private String formatLogMessage(String message, Object... args) {
        return String.format(message.contains("%") ? message.replaceAll("%", "%%") : message, args);
    }
}
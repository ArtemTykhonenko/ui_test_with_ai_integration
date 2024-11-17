package automation.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The {@code DefaultLogger} class provides utility methods for logging messages
 * with different log levels (INFO, WARN, ERROR).
 * It leverages SLF4J's {@code Logger} to handle logging throughout the application.
 *
 * <p>This class simplifies the logging process by allowing formatted messages
 * with placeholders for arguments.</p>
 */
public class DefaultLogger {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Logs an informational message with optional arguments.
     *
     * @param message The message to be logged.
     * @param args    Optional arguments to format the message.
     */
    public void logInfo(String message, Object... args) {
        logger.info(formatLogMessage(message, args));
    }

    /**
     * Logs a warning message with optional arguments.
     *
     * @param message The message to be logged.
     * @param args    Optional arguments to format the message.
     */
    public void logWarn(String message, Object... args) {
        logger.warn(formatLogMessage(message, args));
    }

    /**
     * Logs an error message with optional arguments.
     *
     * @param message The message to be logged.
     * @param args    Optional arguments to format the message.
     */
    public void logError(String message, Object... args) {
        logger.error(formatLogMessage(message, args));
    }

    /**
     * Formats a log message by replacing placeholders with the provided arguments.
     *
     * <p>Note: The method replaces '%' characters in the message to prevent issues with
     * {@code String.format} syntax.</p>
     *
     * @param message The message template with placeholders.
     * @param args    Arguments to be inserted into the placeholders.
     * @return A formatted string with the arguments inserted.
     */
    private String formatLogMessage(String message, Object... args) {
        return String.format(message.replace("%", "%%"), args);
    }
}
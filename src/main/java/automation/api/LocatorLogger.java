package automation.api;

import automation.utils.DefaultLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for logging locator fixes.
 * Logs old and new locators, and generates a report at the end.
 */
public class LocatorLogger extends DefaultLogger {
    private static final Logger logger = LoggerFactory.getLogger(LocatorLogger.class);
    private static final List<LocatorFixEntry> locatorFixEntries = new ArrayList<>();
    private static int maxLocatorLength = 0;

    // ANSI escape codes for green color
    private static final String GREEN_BORDER = "\033[0;32m";
    private static final String RESET_COLOR = "\033[0m";

    // Define the length of the header columns for alignment
    private static final int HEADER_LENGTH = "Old Locator -------------------- New Locator".length();
    private static final int NEW_LOCATOR_PADDING = 4; // Отступ для нового локатора

    /**
     * Logs the old and new locators after a fix.
     *
     * @param oldLocator The original locator that was not working.
     * @param newLocator The corrected locator returned by AI.
     */
    public static void logLocatorFix(String oldLocator, String newLocator) {
        LocatorFixEntry entry = new LocatorFixEntry(oldLocator, newLocator);
        locatorFixEntries.add(entry);

        // Update max length for formatting
        maxLocatorLength = Math.max(maxLocatorLength, Math.max(oldLocator.length(), newLocator.length()));
        logger.info("Locator fixed: Old Locator='{}' | New Locator='{}'", oldLocator, newLocator);
    }

    /**
     * Generates a report of all fixed locators with proper alignment, green border, and indentation.
     */
    public static void generateReport() {
        StringBuilder report = new StringBuilder();

        // Adding green border
        report.append(GREEN_BORDER);
        report.append("\n====================== Locator Fixed =========================\n");
        report.append("----------- Old Locator -------------------- New Locator -----------\n");

        for (LocatorFixEntry entry : locatorFixEntries) {
            String formattedOldLocator = String.format("%-" + (maxLocatorLength + 2) + "s", entry.oldLocator);
            String formattedNewLocator = String.format("%-" + (maxLocatorLength + 2) + "s", entry.newLocator);

            // Добавляем отступ для нового локатора
            String formattedLine = String.format(" %-"+ (HEADER_LENGTH / 2 - 4) +"s |%s%s",
                    formattedOldLocator,
                    " ".repeat(NEW_LOCATOR_PADDING),
                    formattedNewLocator);
            report.append(formattedLine).append("\n");
        }

        report.append("===========================================================\n");
        report.append(RESET_COLOR); // Reset color
        logger.info(report.toString());
    }

    /**
     * Inner class to store locator fix entries.
     */
    private static class LocatorFixEntry {
        String oldLocator;
        String newLocator;

        LocatorFixEntry(String oldLocator, String newLocator) {
            this.oldLocator = oldLocator;
            this.newLocator = newLocator;
        }
    }
}
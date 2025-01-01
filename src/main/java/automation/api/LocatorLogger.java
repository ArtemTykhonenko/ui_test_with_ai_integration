package automation.api;

import automation.utils.DefaultLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class LocatorLogger extends DefaultLogger {
    private static final Logger logger = LoggerFactory.getLogger(LocatorLogger.class);
    private static final List<LocatorFixEntry> locatorFixEntries = new ArrayList<>();
    private static int maxOldLocatorLength = 10; // Минимальная ширина колонки
    private static int maxNewLocatorLength = 10; // Минимальная ширина колонки

    // ANSI escape codes for colors
    private static final String GREEN_BORDER = "\033[0;32m";
    private static final String BLUE_TEXT = "\033[0;34m";
    private static final String YELLOW_TEXT = "\033[0;33m";
    private static final String WHITE_TEXT = "\033[1;37m";
    private static final String CYAN_TEXT = "\033[0;36m";
    private static final String RESET_COLOR = "\033[0m";

    public static void logLocatorFix(String oldLocator, String newLocator) {
        LocatorFixEntry entry = new LocatorFixEntry(oldLocator, newLocator);
        locatorFixEntries.add(entry);

        // Update max length for formatting with a minimum width of 10
        maxOldLocatorLength = Math.max(maxOldLocatorLength, oldLocator != null ? oldLocator.length() : 0);
        maxNewLocatorLength = Math.max(maxNewLocatorLength, newLocator != null ? newLocator.length() : 0);

        // Log in-process fix with blue color
        logger.info(CYAN_TEXT + "Locator fixed: Old Locator='{}' | New Locator='{}'" + RESET_COLOR, oldLocator, newLocator);
    }

    public static void generateReport() {
        // Check if there are any entries to report
        if (locatorFixEntries.isEmpty()) {
            logger.info(GREEN_BORDER + "No locators were fixed during the test run." + RESET_COLOR);
            return;
        }

        StringBuilder report = new StringBuilder();

        // Calculate total width for the columns and the space between them
        int totalWidth = maxOldLocatorLength + maxNewLocatorLength + 7;

        // Generate centered title
        String title = " Locator Fixed ";
        int padding = (totalWidth - title.length()) / 2;
        String centeredTitle = " ".repeat(Math.max(0, padding)) + title;

        // Header with green border and centered title
        report.append(GREEN_BORDER);
        report.append("\n").append("=".repeat(totalWidth)).append("\n");
        report.append(centeredTitle).append("\n");
        report.append("=".repeat(totalWidth)).append("\n");

        // Column headers
        String header = String.format(
                WHITE_TEXT + "%-" + maxOldLocatorLength + "s" + " | " + "%-" + maxNewLocatorLength + "s" + RESET_COLOR,
                "Old Locator", "New Locator");
        report.append(header).append("\n");

        // Green border under the headers
        report.append(GREEN_BORDER).append("=".repeat(totalWidth)).append("\n");

        // Aligning the columns and printing each entry with color formatting
        for (LocatorFixEntry entry : locatorFixEntries) {
            String formattedOldLocator = String.format(
                    BLUE_TEXT + "%-" + maxOldLocatorLength + "s" + RESET_COLOR,
                    entry.oldLocator != null ? entry.oldLocator : "N/A");
            String formattedNewLocator = String.format(
                    YELLOW_TEXT + "%-" + maxNewLocatorLength + "s" + RESET_COLOR,
                    entry.newLocator != null ? entry.newLocator : "N/A");

            report.append(WHITE_TEXT).append(formattedOldLocator)
                    .append(" | ")
                    .append(formattedNewLocator)
                    .append(RESET_COLOR)
                    .append("\n");
        }

        // Footer with green border
        report.append(GREEN_BORDER).append("=".repeat(totalWidth)).append("\n");
        report.append(RESET_COLOR);
        logger.info(report.toString());
    }

    private static class LocatorFixEntry {
        String oldLocator;
        String newLocator;

        LocatorFixEntry(String oldLocator, String newLocator) {
            this.oldLocator = oldLocator;
            this.newLocator = newLocator;
        }
    }
}
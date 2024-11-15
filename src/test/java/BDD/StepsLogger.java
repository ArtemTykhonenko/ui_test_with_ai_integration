package BDD;

import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import automation.base.DriverManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StepsLogger implements ConcurrentEventListener {

    private static final Logger logger = LoggerFactory.getLogger(StepsLogger.class);
    private final WebDriver driver = DriverManager.getInstance().getDriver("chrome");

    @Override
    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestStepStarted.class, this::stepStartedHandler);
        publisher.registerHandlerFor(TestStepFinished.class, this::stepFinishedHandler);
    }

    private void stepStartedHandler(TestStepStarted event) {
        if (event.getTestStep() instanceof PickleStepTestStep) {
            PickleStepTestStep step = (PickleStepTestStep) event.getTestStep();
            String keyWord = step.getStep().getKeyword().toUpperCase();
            String stepText = step.getStep().getText();
            logger.info(formatString(keyWord, stepText));
        }
    }

    private void stepFinishedHandler(TestStepFinished event) {
        if (event.getTestStep() instanceof PickleStepTestStep) {
            String testCaseId = event.getTestCase().getName().split(",")[0];
            if (Status.FAILED.equals(event.getResult().getStatus())) {
                try {
                    takeScreenshot(testCaseId);
                    logger.error("Test case {} failed, screenshot saved.", testCaseId);
                } catch (Exception e) {
                    logger.error("Error while taking screenshot for test case {}: {}", testCaseId, e.getMessage());
                }
            }
        }
    }

    private void takeScreenshot(String testCaseId) {
        if (driver instanceof TakesScreenshot) {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            String screenshotName = String.format("target/screenshots/%s_%s.png", testCaseId, timestamp);
            try {
                Files.createDirectories(Paths.get("target/screenshots"));
                Files.copy(screenshot.toPath(), Paths.get(screenshotName));
            } catch (IOException e) {
                logger.error("Error while saving screenshot: {}", e.getMessage());
            }
        }
    }

    private String formatString(String keyWord, String stepText) {
        return String.format("%s: %s", keyWord, stepText);
    }
}
package BDD;

import com.codeborne.selenide.Selenide;
import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StepsLogger implements ConcurrentEventListener {

    private static final Logger logger = LoggerFactory.getLogger("BDD.StepsLogger");

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
            logger.info("{} {}", keyWord, stepText);
        }
    }

    private void stepFinishedHandler(TestStepFinished event) {
        if (event.getTestStep() instanceof PickleStepTestStep) {
            String testCaseId = event.getTestCase().getName().split(",")[0];
            if (Status.FAILED.equals(event.getResult().getStatus())) {
                try {
                    Selenide.screenshot(testCaseId);
                    logger.error("❌ Test case '{}' failed, screenshot saved.", testCaseId);
                } catch (Exception e) {
                    logger.error("⚠️ Error while taking screenshot for test case '{}': {}", testCaseId, e.getMessage());
                }
            } else {
                logger.info("✅ Test case '{}' passed.", testCaseId);
            }
        }
    }
}
package BDD;

import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StepsLogger implements ConcurrentEventListener {

    private static final Logger logger = LoggerFactory.getLogger(StepsLogger.class);

    @Override
    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestStepStarted.class, this::logStepStart);
        publisher.registerHandlerFor(TestStepFinished.class, this::logStepFinish);
    }

    private void logStepStart(TestStepStarted event) {
        if (event.getTestStep() instanceof PickleStepTestStep) {
            PickleStepTestStep step = (PickleStepTestStep) event.getTestStep();
            logger.info("STEP STARTED: {}", step.getStep().getText());
        }
    }

    private void logStepFinish(TestStepFinished event) {
        if (event.getTestStep() instanceof PickleStepTestStep) {
            PickleStepTestStep step = (PickleStepTestStep) event.getTestStep();
            if (event.getResult().getStatus().is(Status.FAILED)) {
                logger.error("STEP FAILED: {}", step.getStep().getText());
            } else {
                logger.info("STEP PASSED: {}", step.getStep().getText());
            }
        }
    }
}
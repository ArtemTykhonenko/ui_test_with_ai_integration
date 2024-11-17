package BDD;

import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The {@code StepsLogger} class is a custom Cucumber plugin that logs the start and finish
 * of each test step during the execution of Cucumber scenarios.
 *
 * <p>This class implements {@code ConcurrentEventListener} to handle Cucumber events asynchronously,
 * ensuring that logging does not block the test execution flow.</p>
 */
public class StepsLogger implements ConcurrentEventListener {

    private static final Logger logger = LoggerFactory.getLogger(StepsLogger.class);

    /**
     * Registers event handlers for {@code TestStepStarted} and {@code TestStepFinished} events.
     * These handlers log the start and result of each step in the Cucumber scenario.
     *
     * @param publisher The {@code EventPublisher} used to register event handlers.
     */
    @Override
    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestStepStarted.class, this::logStepStart);
        publisher.registerHandlerFor(TestStepFinished.class, this::logStepFinish);
    }

    /**
     * Logs the start of a test step.
     *
     * @param event The {@code TestStepStarted} event containing information about the step.
     */
    private void logStepStart(TestStepStarted event) {
        if (event.getTestStep() instanceof PickleStepTestStep) {
            PickleStepTestStep step = (PickleStepTestStep) event.getTestStep();
            logger.info("STEP STARTED: {}", step.getStep().getText());
        }
    }

    /**
     * Logs the result of a test step after it finishes.
     * If the step failed, it logs an error; otherwise, it logs a success message.
     *
     * @param event The {@code TestStepFinished} event containing information about the step and its result.
     */
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
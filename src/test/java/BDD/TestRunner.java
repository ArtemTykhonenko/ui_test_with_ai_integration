package BDD;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

/**
 * The {@code TestRunner} class serves as the entry point for running Cucumber tests with TestNG.
 * It is configured to run tests in parallel using the {@code @DataProvider} annotation.
 *
 * <p>This class uses {@code CucumberOptions} to specify the location of feature files, plugins for reporting,
 * and tags for selecting specific tests to run.</p>
 *
 * <p>Some of the plugins used include:</p>
 * <ul>
 *   <li>{@code pretty} - Provides readable output in the console.</li>
 *   <li>{@code html} - Generates an HTML report of the test results.</li>
 *   <li>{@code json} - Generates a JSON report of the test results.</li>
 *   <li>{@code BDD.StepsLogger} - Custom logger for logging step executions.</li>
 *   <li>{@code AllureCucumber7Jvm} - Generates reports compatible with Allure.</li>
 * </ul>
 */
@CucumberOptions(
        features = "src/test/resources/features",
        plugin = {
                "pretty",
                "html:target/cucumber-reports/report.html",
                "json:target/cucumber-reports/report.json",
                "BDD.StepsLogger",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        },
        tags = "@test"
)
public class TestRunner extends AbstractTestNGCucumberTests {

        /**
         * Provides scenarios for running Cucumber tests in parallel.
         *
         * <p>This method overrides the default {@code scenarios()} method from {@code AbstractTestNGCucumberTests}
         * to enable parallel execution of scenarios using TestNG's {@code DataProvider}.</p>
         *
         * @return An array of scenarios to be executed in parallel.
         */
        @Override
        @DataProvider(parallel = true)
        public Object[][] scenarios() {
                return super.scenarios();
        }
}

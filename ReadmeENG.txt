# UI Test with AI integration

This project is a test application using Cucumber for BDD (Behavior-Driven Development) and Selenide for browser interaction. The project also includes integration with AI to handle errors and improve the testing process.

## Content

- [Requirements](#requirements)
- [Installation](#installation)
- [Start tests](#start-tests)
- [Configuration](#configuration)
- [Technologies](#technologies)

## Requirements

- Java 17
- Maven
- Installed WebDriver (e.g. ChromeDriver)

## Installation

1. Clone the repository:

 ```bash
 git clone <repository_URL>
 cd ui_test_with_ai_integration
 ```

2. Make sure you have Maven installed and JAVA_HOME configured.

3. Run the command to download the dependencies:

 ```bash
 mvn clean install
 ```

## Running tests

To run the tests, use the following command:

```bash
mvn clean test -Dtest=TestRunner "-Dcucumber.filter.tags=@C01"
Where @C01 is the tag used to filter tests.

## Configuration
## Maven plugins
The project uses the following Maven plugins:

- Maven Surefire Plugin: For running tests.

- Allure Maven Plugin: For generating test reports.

## Dependencies
The project uses the following dependencies:

- Selenide: To automate the testing of web applications.
- TestNG: For structuring tests.
- Logback: For logging.
- WebDriverManager: To manage drivers.
- Cucumber: For BDD testing.
- Allure: For test reports.
- JSON: To process AI responses.

## Technologies
- Java 17
- Maven
- Cucumber
- Selenide
- TestNG
- Allure
- Logback
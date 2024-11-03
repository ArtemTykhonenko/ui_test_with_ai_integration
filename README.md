# Automation of testing using artificial intelligence

---

In this project, we address the issue of manually updating locators (such as XPath, CSS, etc.) by integrating AI through an API interface. For this proof of concept, ChatGPT is utilized to simplify the process and test the hypothesis.

For future or production projects, more secure AI solutions or internal tools are recommended to ensure better data protection and reliability.

--- 

## Content

- [Installation](#Installation)
- [Usage](#Usage)
- [Configuration](#Configuration)
- [Contact information](#Contact information)

## Installation
1. Make sure Git is installed on your PC.

2. To download the project from GitHub, use the command:

```bash
git clone https://github.com/ArtemTykhonenko/ui_test_with_ai_integration.git
```

2. Make sure you have Maven installed and JAVA_HOME configured.

3. Run the command to download the dependencies:

 ```bash
 mvn clean install
 ```

## Usage

To run the tests, use the following command:

```bash
mvn clean test -Dtest=TestRunner "-Dcucumber.filter.tags=@C01"
```
Where "@C01" is the tag used to filter tests. You can add any tags to the "feature" file for filtering.

## Configuration

#### Maven plugins
The project uses the following Maven plugins:

- Maven Surefire Plugin: For running tests.

- Allure Maven Plugin: For generating test reports.

#### Dependencies
The project uses the following dependencies:

- Selenide: To automate the testing of web applications.
- TestNG: For structuring tests.
- Logback: For logging.
- WebDriverManager: To manage drivers.
- Cucumber: For BDD testing.
- Allure: For test reports.
- JSON: To process AI responses.

#### Technologies
- Java 17
- Maven
- Cucumber
- Selenide
- TestNG
- Allure
- Logback

## Contact-information
- **EMAIL**: [artem.tykhonenko.v@gmail.com](mailto:artem.tykhonenko.v@gmail.com)
- **Telegram**: [@Artem_Tykhonenko](https://t.me/Artem_Tykhonenko)

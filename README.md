# Automation of Testing Using Artificial Intelligence

---

In this project, we address the common issue of manually updating locators (such as XPath, CSS selectors, etc.) in automated UI tests by integrating artificial intelligence through an API interface. For this proof of concept, ChatGPT is used to dynamically repair broken locators, simplifying the process and validating the hypothesis.

Note: For future or production projects, consider using more secure AI solutions or internal tools to ensure better data protection and reliability.

---

## 📋 Content
- [🛠️ Installation](#-installation)
- [🚀 Usage](#-usage)
- [⚙️ Configuration](#-configuration)
- [🛠️ Technologies Used](#-technologies-used)
- [📁 Project Structure](#-project-structure)
- [📞 Contact Information](#-contact-information)


---

## 🛠️ Installation

### Prerequisites
1. Ensure Git is installed on your system.
2. Make sure Maven is installed and JAVA_HOME is configured.

### Steps to Set Up
1. Clone the repository:

```bash
git clone https://github.com/ArtemTykhonenko/ui_test_with_ai_integration.git
```

2. Install the project dependencies using Maven:
```bash
mvn clean install
```
3. Download any necessary drivers using WebDriverManager.
4. Insert your API key to the following variable "public static final String ChatGPTAPI". (could be found in "Constants" class)
---

## 🚀 Usage

### Running Tests
To run the automated tests, use the following Maven command:

```bash
mvn clean test -Dtest=TestRunner "-Dcucumber.filter.tags=@C2" -Ddataproviderthreadcount=1 -Dholdbrowseropen=true
```
### Explanation of the command:

- `mvn clean test`: This command cleans the project and runs the tests using Maven.
- `-Dtest=TestRunner`: Specifies which TestNG runner class to execute (`TestRunner` in this case).
- `"-Dcucumber.filter.tags=@C2"`: Filters and runs only the tests with the tag `@C2`. You can modify this tag in the feature files to run specific tests.
- `-Ddataproviderthreadcount=1`: Sets the number of threads for the data provider. A value of `1` ensures that tests run sequentially.
- `-Dholdbrowseropen=true`: Keeps the browser open after the test execution. This is useful for debugging purposes.


### Generating Allure Reports

To generate and open the Allure report after running tests, use the following command:

```bash
mvn allure:serve
```

This command will automatically generate the report and serve it on http://localhost:8080.

---

## ⚙️ Configuration

### Maven Plugins
The project uses the following Maven plugins:

- Maven Surefire Plugin: For executing tests.
- Allure Maven Plugin: For generating test reports.
- AspectJ Maven Plugin: For handling aspect-oriented programming (AOP).

### Dependencies
The following dependencies are included:

- Selenium WebDriver: For browser automation.
- WebDriverManager: To automatically manage browser drivers.
- TestNG: For organizing and structuring tests.
- Logback & SLF4J: For logging.
- Cucumber: For BDD-style testing.
- Allure: For generating test reports.
- OkHttp & JSON: For interacting with AI APIs and handling JSON responses.
- AspectJ: For intercepting method calls and repairing locators.

---

## 🛠️ Technologies Used
- Java 11
- Maven
- Selenium WebDriver
- TestNG
- Cucumber
- Allure
- AspectJ
- Logback
- OkHttp
- Gson & JSON Libraries

---

## 📁 Project Structure
```
src  
├── main  
│   ├── java  
│   │   ├── automation  
│   │   │   ├── api              # AI integration for repairing locators  
│   │   │   ├── base             # WebDriver management  
│   │   │   ├── data             # Test data entities (Product, UserInformation)  
│   │   │   ├── pages            # Page Object Model classes (LoginPage, HomePage, CartPage)  
│   │   │   └── utils            # Utility classes (Logger, Constants, PageTools)  
│   └── resources  
├── test  
│   ├── java  
│   │   ├── BDD                 # Step definitions and hooks for Cucumber tests  
│   └── resources  
│       └── features           # Cucumber feature files  
└── pom.xml                    # Maven project configuration
```
---

## 📄 Feature Examples

### User Login and Logout

@C1 @Smoke @Login  
Feature: User Login and Logout

Scenario: Successful login and logout with valid credentials  
Given Open 'Swag Labs' login page  
When Login to 'Swag Labs' as standard user  
Then The 'Swag Labs' home page is opened  
When Click on side menu  
And Click on 'Logout' link  
Then The 'Swag Labs' login page is displayed

---

## 📞 Contact Information

Feel free to reach out if you have any questions or need assistance:

- Email: artem.tykhonenko.v@gmail.com
- Telegram: https://t.me/Artem_Tykhonenko

---

## 📢 Future Improvements
- Implement additional AI models for enhanced locator stability.
- Explore the use of internal AI solutions for data security.
- Add support for parallel test execution in Docker containers.

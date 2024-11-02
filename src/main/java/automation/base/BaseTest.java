package automation.base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    protected WebDriver driver;

    @BeforeClass
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");
        driver = DriverManager.getDriver(browser);
    }

    @AfterClass
    public void tearDown() {
        DriverManager.quitDriver();

    }
}
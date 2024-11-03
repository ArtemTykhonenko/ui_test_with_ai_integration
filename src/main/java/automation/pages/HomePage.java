package automation.pages;

import automation.utils.PageTools;
import org.openqa.selenium.By;

public class HomePage extends PageTools {
    // Локаторы
    private static final By productTitle = By.xpath("//span[@class='title' and contains(text(), 'Products')]");



    public void isMainPageDisplayed() {

    }
}

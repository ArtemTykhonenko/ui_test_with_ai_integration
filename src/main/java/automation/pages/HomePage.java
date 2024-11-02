package automation.pages;

import automation.base.BaseTest;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class HomePage {
    private BaseTest baseTest;

    // Элементы страницы
    private SelenideElement welcomeMessage = $("#welcomeMessage");

    public HomePage(BaseTest baseTest) {
        this.baseTest = baseTest;
    }

    // Метод для проверки наличия приветственного сообщения
    public String getWelcomeMessage() {
        return welcomeMessage.getText();
    }
}

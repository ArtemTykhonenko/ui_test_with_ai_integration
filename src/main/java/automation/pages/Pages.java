package automation.pages;

import automation.base.BaseTest;

public class Pages {
    private BaseTest baseTest;
    private LoginPage loginPage;
    private HomePage homePage;

    public Pages(BaseTest baseTest) {
        this.baseTest = baseTest;
        this.loginPage = new LoginPage(baseTest);
        this.homePage = new HomePage(baseTest);
    }

    public LoginPage loginPage() {
        return loginPage;
    }

    public HomePage homePage() {
        return homePage;
    }
}


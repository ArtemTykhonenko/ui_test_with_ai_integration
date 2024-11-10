package automation.pages;

public class Pages {
    private static LoginPage loginPage;
    private static HomePage homePage;

    public static LoginPage loginPage() {
        if (loginPage == null) {
            loginPage = new LoginPage();
        }
        return loginPage;
    }
    public static HomePage homePage() {
        if (homePage == null) {
            homePage = new HomePage();
        }
        return homePage;
    }
}


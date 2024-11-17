package automation.pages;

/**
 * The {@code Pages} class provides singleton-like access to page objects.
 * This class ensures that only one instance of each page object is created and reused.
 * It includes access methods for the {@code LoginPage}, {@code HomePage}, and {@code CartPage}.
 *
 * <p>Usage Example:</p>
 * <pre>
 * Pages.loginPage().login("username", "password");
 * Pages.homePage().navigateToProfile();
 * Pages.cartPage().addItemToCart();
 * </pre>
 */
public class Pages {
    private static LoginPage loginPage;
    private static HomePage homePage;
    private static CartPage cartPage;

    /**
     * Returns the instance of {@code LoginPage}.
     * If it doesn't exist, a new instance is created.
     *
     * @return The singleton instance of {@code LoginPage}.
     */
    public static LoginPage loginPage() {
        if (loginPage == null) {
            loginPage = new LoginPage();
        }
        return loginPage;
    }

    /**
     * Returns the instance of {@code HomePage}.
     * If it doesn't exist, a new instance is created.
     *
     * @return The singleton instance of {@code HomePage}.
     */
    public static HomePage homePage() {
        if (homePage == null) {
            homePage = new HomePage();
        }
        return homePage;
    }

    /**
     * Returns the instance of {@code CartPage}.
     * If it doesn't exist, a new instance is created.
     *
     * @return The singleton instance of {@code CartPage}.
     */
    public static CartPage cartPage() {
        if (cartPage == null) {
            cartPage = new CartPage();
        }
        return cartPage;
    }
}
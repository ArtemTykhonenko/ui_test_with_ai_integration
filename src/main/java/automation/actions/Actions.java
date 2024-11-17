package automation.actions;

/**
 * The {@code Actions} class provides centralized access to various actions
 * that can be performed on different pages of the web application.
 *
 * <p>This class uses the Singleton pattern to manage instances of
 * {@link LoginActions} and {@link HomeActions} classes, ensuring that
 * these instances are created only when first accessed.</p>
 *
 * <p>This approach prevents duplication of objects and improves performance
 * by creating instances only once during the program execution.</p>
 *
 * <p>Example usage:
 * <pre>{@code
 * Actions.loginActions().performLogin("username", "password");
 * Actions.homeActions().navigateToDashboard();
 * }</pre>
 * </p>
 *
 * @author Your Name
 * @version 1.0
 */
public class Actions {

    private static LoginActions loginActions;
    private static HomeActions homeActions;

    /**
     * Returns a singleton instance of the {@link LoginActions} class.
     * If the instance does not exist, it creates a new one.
     *
     * <p>This method is used to perform actions on the login page,
     * such as entering user credentials and submitting the login form.</p>
     *
     * @return A singleton instance of {@code LoginActions} for performing login-related actions
     */
    public static LoginActions loginActions() {
        if (loginActions == null) {
            loginActions = new LoginActions();
        }
        return loginActions;
    }

    /**
     * Returns a singleton instance of the {@link HomeActions} class.
     * If the instance does not exist, it creates a new one.
     *
     * <p>This method is used to perform actions on the home page,
     * such as navigation, interacting with elements, and more.</p>
     *
     * @return A singleton instance of {@code HomeActions} for performing actions on the home page
     */
    public static HomeActions homeActions() {
        if (homeActions == null) {
            homeActions = new HomeActions();
        }
        return homeActions;
    }
}
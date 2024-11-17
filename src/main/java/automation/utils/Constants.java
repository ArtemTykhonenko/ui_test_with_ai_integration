package automation.utils;

/**
 * The {@code Constants} class stores commonly used constants such as URLs,
 * user credentials, and API keys for the application.
 *
 * <p>This class is designed to centralize constants used throughout the automation framework,
 * making it easier to maintain and update values in a single place.</p>
 */
public class Constants {

    /**
     * URL of the Swag Labs login page.
     */
    public static final String SWAG_LABS_LOGIN_PAGE = "https://www.saucedemo.com/";

    // ====================
    // User Credentials
    // ====================

    /**
     * Username for a standard user account.
     */
    public static final String STANDARD_USER = "standard_user";

    /**
     * Username for a locked-out user account.
     */
    public static final String LOCKED_USER = "locked_out_user";

    /**
     * Username for a problem user account.
     */
    public static final String PROBLEM_USER = "problem_user";

    /**
     * Username for a performance glitch user account.
     */
    public static final String PERFORMANCE_USER = "performance_glitch_user";

    /**
     * Username for a user that triggers errors.
     */
    public static final String ERROR_USER = "error_user";

    /**
     * Username for a visual bug testing user account.
     */
    public static final String VISUAL_USER = "visual_user";

    /**
     * Password for all user accounts.
     */
    public static final String PASSWORD = "secret_sauce";

    // ====================
    // API Keys
    // ====================

    /**
     * API key for accessing the ChatGPT API.
     * <p><strong>Note:</strong> Be cautious when storing sensitive data such as API keys in your code.</p>
     */
    public static final String ChatGPTAPI = "YOUR KEY";
}

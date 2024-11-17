package automation.actions;


public class Actions {

    private static LoginActions loginActions;
    private static HomeActions homeActions;

    /**
     * This method returns a singleton instance of the LoginActions class.
     * If the instance does not exist, it creates a new one.
     *
     * @return LoginActions singleton instance for performing login actions
     */
    public static LoginActions loginActions() {
        if (loginActions == null) {
            loginActions = new LoginActions();
        }
        return loginActions;
    }

    /**
     * This method returns a singleton instance of the HomeActions class.
     * If the instance does not exist, it creates a new one.
     *
     * @return HomeActions singleton instance for performing home page actions
     */
    public static HomeActions homeActions() {
        if (homeActions == null) {
            homeActions = new HomeActions();
        }
        return homeActions;
    }
}
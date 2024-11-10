package automation.actions;


public class Actions {

    private static LoginActions loginActions;
    private static HomeActions homeActions;
    /**
    * This method return "loginAction" action
    */
    public static LoginActions loginActions() {
        if (loginActions == null) {
            loginActions = new LoginActions();
        }
        return loginActions;
    }

    /**
     * This method return "HomeAction" action
     */
    public static HomeActions homeActions() {
        if (homeActions == null) {
            homeActions = new HomeActions();
        }
        return homeActions;
    }
}
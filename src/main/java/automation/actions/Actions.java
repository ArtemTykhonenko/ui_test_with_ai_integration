package automation.actions;

public class Actions {

    private static LoginActions loginActions;
    private static HomeActions homeActions;

    public static LoginActions loginActions() {
        if (loginActions == null) {
            loginActions = new LoginActions();
        }
        return loginActions;
    }

    public static HomeActions homeActions() {
        if (homeActions == null) {
            homeActions = new HomeActions();
        }
        return homeActions;
    }
}
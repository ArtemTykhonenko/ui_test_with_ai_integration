package BDD;

import automation.data.entities.Product;
import automation.data.entities.UserInformation;

/**
 * The {@code GlobalContext} class serves as a container for shared data across different BDD steps.
 * It holds information about the current product being tested and user information for test scenarios.
 *
 * <p>This class helps in maintaining context throughout the test execution, making it easier to share
 * data between different step definitions.</p>
 */
public class GlobalContext {

    /**
     * Holds the current product being tested.
     */
    public Product product;

    /**
     * Stores the user information for the current test scenario.
     * It is initialized with default values upon creation of the {@code GlobalContext} instance.
     */
    public UserInformation userInformation;

    /**
     * Constructs a new {@code GlobalContext} with default user information.
     * The {@code UserInformation} object is initialized with default values.
     */
    public GlobalContext() {
        this.userInformation = new UserInformation();
        this.userInformation.DefaultValue();
    }

    /**
     * Returns a string representation of the {@code GlobalContext} object.
     *
     * @return A string that includes information about the product and user information.
     */
    @Override
    public String toString() {
        return "GlobalContext{" +
                "product=" + product +
                ", userInformation=" + userInformation +
                '}';
    }
}
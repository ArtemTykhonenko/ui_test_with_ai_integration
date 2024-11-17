package automation.data.entities;

/**
 * Represents user information including first name, second name, and zip code.
 * This class provides methods to get and set user attributes, and includes
 * default values if no specific information is provided.
 */
public class UserInformation {
    private String firstName;
    private String secondName;
    private String zipCode;

    /**
     * Constructs a new {@code UserInformation} object with the specified first name, second name, and zip code.
     *
     * @param firstName  The user's first name.
     * @param secondName The user's second name.
     * @param zipCode    The user's zip code.
     */
    public UserInformation(String firstName, String secondName, String zipCode) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.zipCode = zipCode;
    }

    /**
     * Constructs a new {@code UserInformation} object with default values.
     * Default values are set using the {@link #DefaultValue()} method.
     */
    public UserInformation() {
        DefaultValue();
    }

    /**
     * Sets default values for the user's information.
     * Default values are:
     * <ul>
     *     <li>First Name: "John"</li>
     *     <li>Second Name: "Smith"</li>
     *     <li>Zip Code: "11111"</li>
     * </ul>
     */
    public void DefaultValue() {
        this.firstName = "John";
        this.secondName = "Smith";
        this.zipCode = "11111";
    }

    /**
     * Returns the user's first name.
     *
     * @return The user's first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the user's first name.
     *
     * @param firstName The new first name of the user.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Returns the user's second name.
     *
     * @return The user's second name.
     */
    public String getSecondName() {
        return secondName;
    }

    /**
     * Sets the user's second name.
     *
     * @param secondName The new second name of the user.
     */
    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    /**
     * Returns the user's zip code.
     *
     * @return The user's zip code.
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * Sets the user's zip code.
     *
     * @param zipCode The new zip code of the user.
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * Returns a string representation of the {@code UserInformation} object.
     *
     * @return A string containing the user's first name, second name, and zip code.
     */
    @Override
    public String toString() {
        return "UserInformation{" +
                "firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }
}
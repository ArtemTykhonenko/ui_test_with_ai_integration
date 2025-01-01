package automation.data.entities;

public class UserInformation {
    private String firstName;
    private String secondName;
    private String zipCode;

    public UserInformation(String firstName, String secondName, String zipCode) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.zipCode = zipCode;
    }

    public UserInformation() {
        DefaultValue();
    }

    public void DefaultValue() {
        this.firstName = "John";
        this.secondName = "Smith";
        this.zipCode = "11111";
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return "UserInformation{" +
                "firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }
}
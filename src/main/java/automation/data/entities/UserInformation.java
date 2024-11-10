package automation.data.entities;

public class UserInformation {
    private String firstName;
    private String secondName;
    private String zipCode;

    // Конструктор с параметрами для инициализации всех полей
    public UserInformation(String firstName, String secondName, String zipCode) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.zipCode = zipCode;
    }

    // Конструктор по умолчанию
    public UserInformation() {
        DefaultValue();
    }

    // Метод для установки значений по умолчанию
    public void DefaultValue() {
        this.firstName = "John";
        this.secondName = "Smith";
        this.zipCode = "11111";
    }

    // Геттеры и сеттеры
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
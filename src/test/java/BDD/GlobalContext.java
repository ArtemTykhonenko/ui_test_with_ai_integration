package BDD;

import automation.data.entities.Product;
import automation.data.entities.UserInformation;

public class GlobalContext {

    public Product product;
    public UserInformation userInformation;

    public GlobalContext() {
        this.userInformation = new UserInformation();
        this.userInformation.DefaultValue();
    }

    @Override
    public String toString() {
        return "CrmGlobalContext{" +
                ", product=" + product +
                ", userInformation=" + userInformation +
                '}';
    }
}

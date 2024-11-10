package BDD;

import automation.data.entities.Product;

public class GlobalContext {

    public Product product;

    @Override
    public String toString() {
        return "CrmGlobalContext{" +
                ", product=" + product +
                '}';
    }
}

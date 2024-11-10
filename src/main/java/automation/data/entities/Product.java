package automation.data.entities;

public class Product {

    private String productName;
    private String productDescription;
    private String productPrice;

    // Конструктор с параметрами для инициализации всех полей
    public Product(String productName, String productDescription, String productPrice) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
    }

    // Пустой конструктор
    public Product() {
    }

    // Геттеры и сеттеры
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    // Переопределение метода toString для удобного вывода информации о продукте
    @Override
    public String toString() {
        return "Product{" +
                "productName=" + productName +
                ", productDescription=" + productDescription +
                ", productPrice=" + productPrice +
                '}';
    }
}
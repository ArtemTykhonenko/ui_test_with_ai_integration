package automation.data.entities;

/**
 * Represents a product with a name, description, and price.
 * This class is used to store information about a product and provides
 * methods to get and set its attributes.
 */
public class Product {

    private String productName;
    private String productDescription;
    private String productPrice;

    /**
     * Constructs a new {@code Product} with the specified name, description, and price.
     *
     * @param productName        The name of the product.
     * @param productDescription The description of the product.
     * @param productPrice       The price of the product.
     */
    public Product(String productName, String productDescription, String productPrice) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
    }

    /**
     * Constructs an empty {@code Product} with no initial values.
     */
    public Product() {
    }

    /**
     * Returns the name of the product.
     *
     * @return The product's name.
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Sets the name of the product.
     *
     * @param productName The new name of the product.
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * Returns the description of the product.
     *
     * @return The product's description.
     */
    public String getProductDescription() {
        return productDescription;
    }

    /**
     * Sets the description of the product.
     *
     * @param productDescription The new description of the product.
     */
    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    /**
     * Returns the price of the product.
     *
     * @return The product's price.
     */
    public String getProductPrice() {
        return productPrice;
    }

    /**
     * Sets the price of the product.
     *
     * @param productPrice The new price of the product.
     */
    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    /**
     * Returns a string representation of the product.
     *
     * @return A string containing the product's name, description, and price.
     */
    @Override
    public String toString() {
        return "Product{" +
                "productName='" + productName + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", productPrice='" + productPrice + '\'' +
                '}';
    }
}
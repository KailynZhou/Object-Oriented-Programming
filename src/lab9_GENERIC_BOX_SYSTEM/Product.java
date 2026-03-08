/**
* Name: JiepingZhou, 24250243
 * Name: JOHN QUINN, 25259001
 * Product.java - Base class for items that can be stored in boxes
 * Represents a generic product with name and price
 */
package lab9_GENERIC_BOX_SYSTEM;

/**
 * Product class represents a basic product with name and price.
 * This serves as a parent class that can be extended by specific product types.
 */
public class Product {
    // Product name
    private String name;
    // Product price in dollars
    private double price;

    /**
     * Constructor to create a new Product
     * @param name The name of the product
     * @param price The price of the product in dollars
     */
    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    /**
     * Gets the product name
     * @return The name of the product
     */
    public String getName() { 
        return name; 
    }

    /**
     * Gets the product price
     * @return The price of the product
     */
    public double getPrice() { 
        return price; 
    }

    /**
     * Returns a string representation of the product
     * Uses getClass().getSimpleName() to get the actual class name
     * @return String representation of the product
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + "{'" + name + "', $" + String.format("%.2f", price) + "}";
    }
  
}

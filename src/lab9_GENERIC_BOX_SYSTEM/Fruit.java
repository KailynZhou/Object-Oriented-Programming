/**
* Name: JiepingZhou, 24250243
 * Name: JOHN QUINN, 25259001
 * Fruit.java - Represents a fruit item that can be stored in a Box
 * Extends Product class with fruit-specific properties
 */ 
package lab9_GENERIC_BOX_SYSTEM;

/**
 * Fruit class represents a fruit product with additional properties.
 * Extends the Product class and adds weight and harvest date information.
 * This demonstrates inheritance hierarchy that works with generics.
 */
public class Fruit extends Product {
    private int weight; // Weight in grams
    private String date; // Harvest date in YYYY-MM-DD format
    
    /**
     * Constructor to create a new Fruit
     * @param name The name of the fruit (e.g., "Apple", "Banana")
     * @param price The price of the fruit in dollars
     * @param weight The weight of the fruit in grams
     * @param date The harvest date in YYYY-MM-DD format
     */
    public Fruit(String name, double price, int weight, String date) {
        // Call parent class constructor
        super(name, price);
        this.weight = weight;
        this.date = date;
    }

    /**
     * Gets the weight of the fruit
     * @return The weight in grams
     */
    public int getWeight() { 
        return weight; 
    }
    
    /**
     * Gets the harvest date of the fruit
     * @return The harvest date
     */
    public String getDate() { 
        return date; 
    }

    /**
     * Returns a string representation of the fruit with all its properties
     * @return Detailed string representation including weight and date
     */
    @Override
    public String toString() {
        return "Fruit{'" + getName() + "' (" + weight + "g, " + date   + "), $" + String.format("%.2f", getPrice()) + "}";
    }
  
}

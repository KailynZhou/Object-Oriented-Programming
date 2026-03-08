/**
 * Generic Box System - Mini Project
 * Name: JiepingZhou, 24250243
 * Name: JOHN QUINN, 25259001
 * Box.java - A generic container class that can store any type of item
 * This class demonstrates the use of Java generics with type parameter <T>
 */
package lab9_GENERIC_BOX_SYSTEM;

/**
 * Generic Box class that can hold any type of item.
 * The type parameter T allows this class to be type-safe and reusable
 * for different types of objects.
 * 
 * @param <T> The type of item this box will contain
 */
public class Box<T> {
    // Generic field to store the item of type T
    private T item;

    /**
     * Sets the item in the box
     * @param item The item to store in the box
     */
    public void set(T item) { 
        this.item = item; 
    }

    /**
     * Gets the item from the box
     * @return The item stored in the box
     */
    public T get() { 
        return item; 
    }

    /**
     * Prints the runtime type of the item in the box.
     * This demonstrates that even though we use generics at compile time,
     * we can still get the actual type at runtime using reflection.
     */
    public void printItemType() {
        if (item == null) {
            System.out.println("Box is empty");
        } else {
            // Use getClass() to get the runtime type of the stored item
            System.out.println("Item type: " + item.getClass().getName());
        }
    }

    /**
     * Returns a string representation of the box and its contents
     * @return String representation of the box
     */
    @Override
    public String toString() { 
        return "Box{" + item + "}"; 
    }
  
}

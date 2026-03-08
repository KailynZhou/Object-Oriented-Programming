/**
 * Generic Box System - Mini Project
 * Name: JiepingZhou, 24250243
 * Name: JOHN QUINN, 25259001
 * Book.java - Represents a book item that can be stored in a Box
 * Extends Product class with book-specific properties
 */
package lab9_GENERIC_BOX_SYSTEM;

/**
 * Book class represents a book product with additional properties.
 * Extends the Product class and adds author and page count information.
 * This demonstrates how different types can be used with the generic Box class.
 */
public class Book extends Product {
    private String author; // Author of the book
    private int pages;     // Number of pages

    /**
     * Constructor to create a new Book
     * @param name The title of the book
     * @param price The price of the book in dollars
     * @param author The author's name
     * @param pages The number of pages in the book
     */
    public Book(String name, double price, String author, int pages) {
        // Call parent class constructor
        super(name, price);
        this.author = author;
        this.pages = pages;
    }

    /**
     * Gets the author of the book
     * @return The author's name
     */
    public String getAuthor() { 
        return author; 
    }
    
    /**
     * Gets the number of pages
     * @return The page count
     */
    public int getPages() { 
        return pages; 
    } 

    /**
     * Returns a string representation of the book with all its properties
     * @return Detailed string representation including author and pages
     */
    @Override
    public String toString() {
        return "Book{'" + getName() + "' by " + author + ", " + pages + " pages, $" + String.format("%.2f", getPrice()) + "}";
    }
  
}

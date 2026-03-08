/**
* Name: JiepingZhou, 24250243
 * Name: JOHN QUINN, 25259001
 * GenericsDemo01.java - Main demonstration program for the Generic Box System
 * This program demonstrates the use of generics through a Box container class
 * that can safely store different types of items while maintaining type safety.
 */
package   lab9_GENERIC_BOX_SYSTEM;

/**
 * Main demonstration class for the Generic Box System.
 * This class shows how generics provide type safety at compile time,
 * preventing invalid type assignments and eliminating the need for casting.
 */
public class GenericsDemo01 {

  public static void main(String[] args) {
    System.out.println("=== Generic Box System Demo ===");
    System.out.println();
    
    // Create boxes for different types using the generic Box<T> class
    // Each box is parameterized with a specific type (Product, Fruit, Book)
    // This ensures type safety - each box can only hold items of its declared type
    Box<Product> productBox = new Box<>();
    Box<Fruit> fruitBox = new Box<>();
    Box<Book> bookBox = new Box<>();

    // Store items in boxes
    // The compiler ensures we can only put the correct type in each box
    productBox.set(new Product("Generic Product", 9.99));
    fruitBox.set(new Fruit("Apple", 0.99, 150, "2024-06-01"));
    bookBox.set(new Book("Java Programming", 39.99, "John Doe", 500));
    

    // Print boxes and their item types
    // Demonstrate that we can retrieve items without casting
    System.out.println("Product Box:");
    System.out.println(productBox);
    productBox.printItemType();  // Shows runtime type information
    System.out.println();

    System.out.println("Fruit Box:");
    System.out.println(fruitBox);
    fruitBox.printItemType();
    System.out.println();

    System.out.println("Book Box:");
    System.out.println(bookBox);
    bookBox.printItemType();
    System.out.println();

    // Demonstrate empty box handling
    Box<String> emptyBox = new Box<>();
    System.out.println("Empty String Box:");
    emptyBox.printItemType();
    System.out.println();

    // 
    // Uncomment any line below to see the compile-time error:
     //productBox.set(new Fruit("Banana", 0.59, 120, "2024-06-02")); // Error: Box<Product> cannot hold Fruit
    // fruitBox.set(new Product("Another Product", 19.99)); // Error: Box<Fruit> cannot hold Product
    // bookBox.set(new Fruit("Orange", 0.79, 130, "2024-06-03")); // Error: Box<Book> cannot hold Fruit
    
    System.out.println("\n=== Demo completed successfully ===");
  }
  
}

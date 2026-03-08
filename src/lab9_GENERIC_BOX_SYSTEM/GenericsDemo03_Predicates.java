// Name: JiepingZhou, 24250243
// Name: JOHN QUINN, 25259001
package lab9_GENERIC_BOX_SYSTEM;
import java.util.*;
// This class demonstrates how to use Java's built-in Predicate interface with our generic ProductFilter.
public class GenericsDemo03_Predicates {
  public static void main(String[] args) {
    // Create sample data
    // Create lists of specific product types
    List<Book> books = List.of(
      new Book("Clean Code", 39.99, "Robert Martin", 464),
      new Book("Dune", 14.99, "Frank Herbert", 688),
      new Book("Clean Code", 29.99, "Robert Martin", 464),
      new Book("Java Concurrency", 49.99, "Brian Goetz", 384)
    );    
    // Create a ProductFilter for Books
    List<Fruit> fruits = List.of(
      new Fruit("Apple", 0.99, 150, "2024-06-01"),
      new Fruit("Banana", 0.59, 120, "2024-06-02"),
      new Fruit("Orange", 0.79, 130, "2024-06-03")
    );    
    // Create a ProductFilter for Fruits
    // We will use Java's built-in Predicate<T> functional interface to filter our products.
    // Create ProductFilter instances for Books and Fruits
    ProductFilter<Book> bookFilter = new ProductFilter<>();
    ProductFilter<Fruit> fruitFilter = new ProductFilter<>();

    System.out.println("=== PART 3: Generics + Predicates ===");

    // ---- Same thing as a lambda — much cleaner ----
    // Filter books with more than 400 pages using a lambda expression
    List<Book> longBooksLambda = bookFilter.filterBy(books, b -> b.getPages() > 400);
    System.out.println("Long books (lambda): " + longBooksLambda);

    // ---- Lambdas work for any type T — here we filter fruits by weight ----
    // Filter fruits heavier than 125 grams using a lambda expression
    List<Fruit> heavyFruits = fruitFilter.filterBy(fruits, f -> f.getWeight() > 125);
    System.out.println("Heavy fruits (lambda): " + heavyFruits);
  
}
}

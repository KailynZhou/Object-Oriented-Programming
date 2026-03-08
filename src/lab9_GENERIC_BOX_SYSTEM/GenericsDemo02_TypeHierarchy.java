package  lab9_GENERIC_BOX_SYSTEM;
// Name: JiepingZhou, 24250243
// Name: JOHN QUINN, 25259001
import java.util.List;
// This class demonstrates the type hierarchy in our generic box system.
public class GenericsDemo02_TypeHierarchy {
  public static void main(String[] args) {
    System.out.println("=== Generic Box System Type Hierarchy Demo ===\n");
    // Create lists of specific product types
    List<Book> books = List.of(
      new Book("Clean Code", 39.99, "Robert Martin", 464),
      new Book("Dune", 14.99, "Frank Herbert", 688),
      new Book("Clean Code", 29.99, "Robert Martin", 464),
      new Book("Java Concurrency", 49.99, "Brian Goetz", 384)
    );    
    // Create a list of fruits
    List<Fruit> fruits = List.of(
      new Fruit("Apple", 0.99, 150, "2024-06-01"),
      new Fruit("Banana", 0.59, 120, "2024-06-02"),
      new Fruit("Orange", 0.79, 130, "2024-06-03")
    );    
    // Print all products to see the toString output
        System.out.println("=== PART 1: Our Products ===");
        System.out.println("Books:");
        books.forEach(b -> System.out.println("  " + b));

        System.out.println("Fruits:");
        fruits.forEach(f -> System.out.println("  " + f));

        // Subclass-specific methods work when we have the specific type
        System.out.println("\nBook-specific: " + books.get(0).getAuthor());        // "Robert Martin"
        System.out.println("Fruit-specific: " + fruits.get(0).getDate()); // "2024-06-01"

        // But when we upcast to Product, we lose access to subclass methods
        Product p = books.get(0);           // upcast: Book → Product
        System.out.println("\nAs Product: " + p.getName());  // getName() works — it's on Product
  
        // To get it back, we must downcast:
        if (p instanceof Book) {
          Book b = (Book) p;
          System.out.println("Downcast back to Book: " + b.getAuthor());
        } else {
          System.out.println("Not a Book!");
        }     

}
}

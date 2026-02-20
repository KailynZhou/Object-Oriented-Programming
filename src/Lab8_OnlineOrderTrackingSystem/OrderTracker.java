package Lab8_OnlineOrderTrackingSystem;

import java.util.ArrayList;
import java.util.List;

/**
 * OrderTracker - Main class for the Online Order Tracking System
 * Manages order dashboard display and cancelable orders list
 * Demonstrates polymorphism by treating all order states uniformly
 * Name: Jieping Zhang 24250243，Name: Christopher Kearney 25259037
 */
public class OrderTracker {

  /**
   * Prints a dashboard showing all orders in the system
   * Uses polymorphism - calls each order's methods without checking specific type
   * @param orders List of OrderRequest objects in any state
   */
  public static void printDashboard(List<OrderRequest> orders) {
    System.out.println("\n==============================");
    System.out.println("ONLINE ORDER TRACKING DASHBOARD");
    
    
    if (orders.isEmpty()) {
      System.out.println("No orders to display.");
      return;
    }
    
    // Polymorphism: each order's printSummary() calls appropriate getStatus()
    for (OrderRequest order : orders) {
      order.printSummary();
      System.out.println();
    }
  }

   /* Main method - demonstrates the order tracking system
   * Creates sample orders in different states and displays them
   */
  public static void main(String[] args) {
    // Create a list to hold orders in various states
    List<OrderRequest> orders = new ArrayList<>();
    
    // Create orders in different states
    // Processing state - can be cancelled
    OrderRequest order1 = new Processing("ORD001", "Tech Store", "Laptop", "Alice", 1, "123 Main St");  
    OrderRequest order2 = new Processing("ORD002", "Book World", "Novel", "Bob", 2, "456 Oak Ave");   
    OrderRequest order3 = new Processing("ORD003", "Gadget Hub", "Smartphone", "Charlie", 1, "789 Pine Rd");  
    OrderRequest order4 = new Shipped("ORD004", "Fashion Outlet", "Jacket", "Diana", 1, "TRACK123", "FastShip", "2024-06-30");  
    OrderRequest order5 = new OutForDelivery("ORD005", "Home Goods", "Blender", "Ethan", 1, "John Doe", "2024-06-28 14:00"); 
    OrderRequest order6 = new Delivered("ORD006", "Sports Store", "Running Shoes", "Fiona", 1, "2024-06-25 10:00", "Jane Smith");

    
    // Shipped state - cannot be cancelled
 
    
    // Add all orders to the list
    orders.add(order1);
    orders.add(order2);
    orders.add(order3);
    orders.add(order4);
    orders.add(order5);
    orders.add(order6);
    
    // Display the order dashboard
    System.out.println("\n" + "=".repeat(55));
    System.out.println("          INITIAL ORDER DASHBOARD");
    System.out.println("=".repeat(55));
    printDashboard(orders);
    
    
    // Demonstrate cancelling orders
    System.out.println("\n\n" + "=".repeat(55));
    System.out.println("          DEMONSTRATING ORDER CANCELLATION");
    System.out.println("=".repeat(55));
    
    // Test 1: Cancel a Processing order (should succeed and show cancelled list)
    System.out.println("\n[TEST 1] Attempting to cancel ORD001 (Processing order - should succeed):");
    OrderService.cancelOrderById(orders, "ORD001");
    
    // Test 2: Try to cancel a Shipped order (should fail with warning)
    System.out.println("\n\n[TEST 2] Attempting to cancel ORD004 (Shipped order - should fail):");
    OrderService.cancelOrderById(orders, "ORD004");
    
    // Test 3: Cancel another Processing order (should succeed)
    System.out.println("\n\n[TEST 3] Attempting to cancel ORD002 (Processing order - should succeed):");
    OrderService.cancelOrderById(orders, "ORD002");
    
    // Test 4: Try to cancel OutForDelivery order (should fail)
    System.out.println("\n\n[TEST 4] Attempting to cancel ORD005 (Out for Delivery - should fail):");
    OrderService.cancelOrderById(orders, "ORD005");
    
    // Display updated dashboard
    System.out.println("\n\n" + "=".repeat(55));
    System.out.println("          UPDATED ORDER DASHBOARD");
    System.out.println("=".repeat(55));
    printDashboard(orders);
    
    
    // Final summary
    System.out.println("\n\n" + "=".repeat(55));
    System.out.println("          FINAL SUMMARY");
    System.out.println("=".repeat(55));
    System.out.println("Active Orders: " + orders.size());
    System.out.println("Cancelled Orders: " + OrderService.getCancelledOrdersCount());
    System.out.println("=".repeat(55));
  }
}

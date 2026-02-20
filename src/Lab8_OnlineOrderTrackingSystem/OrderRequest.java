package Lab8_OnlineOrderTrackingSystem;

/**
 * OrderRequest - Abstract base class representing an online order
 * This class defines the common properties and behaviors of all order states
 * Name: Jieping Zhang 24250243，Name: Christopher Kearney 25259037
 */
public abstract class OrderRequest {
  // Protected fields accessible by subclasses
  protected String shopName;        // Name of the shop where order was placed
  protected String itemName;        // Name of the item ordered
  protected String custName;        // Customer name
  protected String orderID;         // Unique order identifier
  protected String orderDate;       // Date when order was placed
  protected int itemQuantity; // Quantity of items ordered
  protected double totalPrice; // Total price of the order

  public OrderRequest(String orderID, String shopName, String itemName, String custName, int itemQuantity) {
    this.orderID = orderID;
    this.shopName = shopName;
    this.itemName = itemName;
    this.custName = custName;
    this.itemQuantity = itemQuantity; 
    this.totalPrice = itemQuantity * 10.0; // Default price calculation for simplicity
     
  }

  /**
   * Abstract method to get the current status description of the order
   * Each state class must implement this to provide specific status information
   * @return String describing the current order status
   */
  public abstract String getStatus();

  /**
   * Abstract method to check if the order can be cancelled
   * Different states have different cancellation rules
   * @return true if order can be cancelled, false otherwise
   */
  public abstract boolean isCancelable();

  /**
   * Prints a summary of the order information
   * This method displays all key details about the order
   */
  public OrderRequest cancel() {
        System.out.println("Order " + getOrderID() + " cannot be cancelled (Status: " + getStatus() + ").");
        return this;//
    }

  public void printSummary() {
    System.out.println("==============================================");
    System.out.println("Order ID: " + orderID);
    System.out.println("Shop: " + shopName);
    System.out.println("Product: " + itemName);
    System.out.println("Quantity: " + itemQuantity);
    System .out.println("Total Price: $" + totalPrice);
    System.out.println("Customer: " + custName);
    System.out.println("Status: " + getStatus());
    System.out.println("Cancelable: " + (isCancelable() ? "Yes" : "No"));
    System.out.println("=============================================");
  }

  // Getter methods for order properties
  public String getOrderID() {
    return orderID;
  }

  public String getShopName() {
    return shopName;
  }

  public String getItemName() {
    return itemName;
  }

  public String getCustName() {
    return custName;
  }

  public double getTotalPrice() {
    return totalPrice;
  }
  public int getItemQuantity() {
    return itemQuantity;
  }
} 

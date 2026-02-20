package Lab8_OnlineOrderTrackingSystem;
import java.util.ArrayList;
import java.util.List;

/**
 * OrderService - Service class for managing order operations
 * Provides functionality for cancelling orders and tracking cancelled orders
 * Name: Jieping Zhang 24250243，Name: Christopher Kearney 25259037
 */
public class OrderService {

    // Static list to store cancelled orders
    private static List<OrderRequest> cancelledOrders = new ArrayList<>();

    /**
     * Cancels an order by its Order ID
     * If the order is cancelable, it removes it from active orders and adds to cancelled list
     * If not cancelable, displays a warning message
     * 
     * @param orders List of active orders
     * @param orderId ID of the order to cancel
     */
    public static void cancelOrderById(List<OrderRequest> orders, String orderId) {
        for (int i = 0; i < orders.size(); i++) {
            OrderRequest order = orders.get(i);
            if (order.getOrderID().equals(orderId)) {
                // Check if order is cancelable
                if (order.isCancelable()) {
                    // Remove from active orders and add to cancelled list
                    orders.remove(i);
                    cancelledOrders.add(order);
                    
                    System.out.println("\n✓✓✓ ORDER CANCELLED SUCCESSFULLY ✓✓✓");
                    System.out.println("Order ID: " + order.getOrderID());
                    System.out.println("Customer: " + order.getCustName());
                    System.out.println("Item: " + order.getItemName());
                    System.out.println("Quantity: " + order.getItemQuantity());
                    System.out.println("Total Price: $" + order.getTotalPrice());
                    
                    // Display list of all cancelled orders
                    printCancelledOrders();
                    return;
                } else {
                    // Order cannot be cancelled
                    System.out.println("\n✗✗✗ THIS ORDER CANNOT BE CANCELLED ✗✗✗");
                    System.out.println("Order ID: " + order.getOrderID());
                    System.out.println("Current Status: " + order.getStatus());
                    System.out.println("Reason: Order is already in the delivery process");
                    System.out.println("Note: Only orders in 'Processing' state can be cancelled");
                    return;
                }
            }
        }
        System.out.println("\n✗ ERROR: Order ID " + orderId + " not found");
    }

    /**
     * Gets list of cancelable orders
     * 
     * @param orders List of active orders
     * @return List of orders that can be cancelled
     */
    public static List<OrderRequest> getCancelableOrders(List<OrderRequest> orders) {
        List<OrderRequest> cancelable = new ArrayList<>();
        for (OrderRequest order : orders) {
            if (order.isCancelable()) {
                cancelable.add(order);
            }
        }
        return cancelable;
    }

    /**
     * Prints list of all cancelled orders
     * Shows complete history of cancelled orders
     */
    public static void printCancelledOrders() {
        System.out.println("\n" + "=".repeat(55));// Separator line
        System.out.println("          CANCELLED ORDERS LIST");
        System.out.println("=".repeat(55));
        
        if (cancelledOrders.isEmpty()) {
            System.out.println("No cancelled orders yet");
            System.out.println("=".repeat(55));
            return;
        }
        
        System.out.println("Total Cancelled Orders: " + cancelledOrders.size());
        System.out.println();
        
        int count = 1;// Counter for display
        for (OrderRequest order : cancelledOrders) {
            System.out.println("[" + count + "] Order ID: " + order.getOrderID());
            System.out.println("    Customer: " + order.getCustName());
            System.out.println("    Item: " + order.getItemName());
            System.out.println("    Quantity: " + order.getItemQuantity());
            System.out.println("    Total Price: $" + order.getTotalPrice());
            System.out.println("    Shop: " + order.getShopName());
            System.out.println("    " + "-".repeat(50));
            count++;
        }
        System.out.println("=".repeat(55));
    }

    /**
     * Gets the cancelled orders list
     * 
     * @return List of cancelled orders
     */
    public static List<OrderRequest> getCancelledOrdersList() {
        return new ArrayList<>(cancelledOrders);
    }

    /**
     * Gets count of cancelled orders
     * 
     * @return Number of cancelled orders
     */
    public static int getCancelledOrdersCount() {
        return cancelledOrders.size();
    }

    /**
     * Clears the cancelled orders history
     */
    public static void clearCancelledOrders() {
        cancelledOrders.clear();
        System.out.println("\n✓ All cancelled order records have been cleared");
    }
}
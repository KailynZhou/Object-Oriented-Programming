package Lab8_OnlineOrderTrackingSystem;

/**
 * OutForDelivery - Represents an order that is out for delivery
 * Orders in this state are with the delivery person and cannot be cancelled
 * Name: Jieping Zhang 24250243，Name: Christopher Kearney 25259037
 */
public class OutForDelivery extends OrderRequest {
  private String deliveryPersonName;      // Name of the delivery person
  private String estimatedDeliveryTime;   // Estimated time of delivery

  public OutForDelivery(String orderID, String shopName, String itemName, String custName, int itemQuantity,
                       String deliveryPersonName, String estimatedDeliveryTime) {
    super(orderID, shopName, itemName, custName, itemQuantity);
    this.deliveryPersonName = deliveryPersonName;
    this.estimatedDeliveryTime = estimatedDeliveryTime;
  }

  /**
   * Returns the status description for OutForDelivery state
   * @return Status message including delivery person and time
   */
  @Override
  public String getStatus() {
    return "Out for Delivery: Package is with delivery person " + deliveryPersonName + 
           ", estimated delivery time is " + estimatedDeliveryTime;
  }

  /**
   * Determines if order can be cancelled
   * Orders out for delivery CANNOT be cancelled (delivery in progress)
   * @return false - orders out for delivery cannot be cancelled
   */
  @Override
  public boolean isCancelable() {
    return false;
  }
}

package Lab8_OnlineOrderTrackingSystem;

/**
 * Shipped - Represents an order that has been shipped
 * Orders in this state are in transit and cannot be cancelled
 * Name: Jieping Zhang 24250243，Name: Christopher Kearney 25259037
 */
public class Shipped extends OrderRequest {
  private String trackingNumber;           // Tracking number for the shipment
  private String shippingCompany;          // Name of shipping company
  private String estimatedDeliveryDate;    // Estimated delivery date            // Package number for tracking


  public Shipped(String orderID, String shopName, String itemName, String custName, int itemQuantity, 
                String trackingNumber, String shippingCompany, String estimatedDeliveryDate) {
    super(orderID, shopName, itemName, custName, itemQuantity);
    this.trackingNumber = trackingNumber;
    this.shippingCompany = shippingCompany;
    this.estimatedDeliveryDate = estimatedDeliveryDate;
  }

  /**
   * Returns the status description for Shipped state
   * @return Status message including tracking information
   */
  @Override
  public String getStatus() {
    return "Shipped: Package in transit. Tracking number " + trackingNumber + 
           " with " + shippingCompany + ", estimated delivery on " + estimatedDeliveryDate;
  }

  /**
   * Determines if order can be cancelled
   * Orders in Shipped state CANNOT be cancelled (already in transit)
   * @return false - shipped orders cannot be cancelled
   */
  @Override
  public boolean isCancelable() {
    return false;
  }   
}

package Lab8_OnlineOrderTrackingSystem;

/**
 * Delivered - Represents an order that has been successfully delivered
 * Orders in this state are complete and cannot be cancelled
 * 
 * Name: Jieping Zhang 24250243
 * Name: Christopher Kearney 25259037
 */
public class Delivered extends OrderRequest {
  private String actualDeliveryDate;      // Actual date of delivery
  private String signedBy;                 // Name of person who signed for delivery

  public Delivered(String orderID, String shopName, String itemName, String custName, int quantity,
                  String actualDeliveryDate, String signedBy) {
    super(orderID, shopName, itemName, custName, quantity);
    this.actualDeliveryDate = actualDeliveryDate;
    this.signedBy = signedBy;
  }

  /**
   * Returns the status description for Delivered state
   * @return Status message indicating successful delivery
   */
  @Override
  public String getStatus() {
    return "Delivered: Order successfully delivered on " + actualDeliveryDate + 
           ", signed by " + signedBy;
  }

  /**
   * Determines if order can be cancelled
   * Delivered orders CANNOT be cancelled (already completed)
   * @return false - delivered orders cannot be cancelled
   */
  @Override
  public boolean isCancelable() {
    return false;
  }
}

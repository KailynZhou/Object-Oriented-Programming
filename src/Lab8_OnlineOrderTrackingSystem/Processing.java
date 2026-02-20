package Lab8_OnlineOrderTrackingSystem;

/**
 * Processing - Represents an order in the Processing state
 * Orders in this state are being prepared and can still be cancelled
 * Name: Jieping Zhang 24250243，Name: Christopher Kearney 25259037
 */
public class Processing extends OrderRequest {
  private String custAddress;  // Customer's delivery address
  

  public Processing(String orderID, String shopName, String itemName, String custName, int itemQuantity, 
                   String custAddress) {
    super(orderID, shopName, itemName, custName, itemQuantity); // Default quantity to 1 for simplicity
    
    this.custAddress = custAddress;
  } 

  /**
   * Returns the status description for Processing state
   * @return Status message including delivery details
   */
  @Override
  public String getStatus() {
    return "Processing: Order is being prepared. Delivery scheduled for "  +  
            " to address " + custAddress;
  }

  /**
   * Determines if order can be cancelled
   * Orders in Processing state CAN be cancelled
   * @return true - orders being processed can be cancelled
   */
  @Override
  public boolean isCancelable() {
    return true;
  }
}
  


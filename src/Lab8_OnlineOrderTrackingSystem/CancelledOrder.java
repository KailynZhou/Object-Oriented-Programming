package Lab8_OnlineOrderTrackingSystem;
//Name: Jieping Zhang 24250243
//Name: Christopher Kearney 25259037
    public class CancelledOrder extends OrderRequest {
        private String cancelledReason; // Reason for cancellation
        private String cancelledDate;   // Date of cancellation
        


    public CancelledOrder(String orderID, String shopName, String itemName, String custName, int itemQuantity, 
                         String cancelledReason, String cancelledDate) {
        super(orderID, shopName, itemName, custName, itemQuantity);
        this.cancelledReason = cancelledReason;
        this.cancelledDate = cancelledDate;                         
    }

    @Override
    public String getStatus() {
        return "Cancelled: Order was cancelled on " + cancelledDate + ". Reason: " + cancelledReason ;
    }
    @Override
    public boolean isCancelable() {
        return false;
    }
}
# Online Order Tracking System

Name: Jieping Zhang 24250243，Name: Christopher Kearney 25259037

## Project Overview

The system tracks orders through different states and includes order cancellation functionality with automatic tracking of cancelled orders.

### Class Structure

OrderRequest (Abstract Base Class)
├── Processing
├── Shipped
├── OutForDelivery
└── Delivered

OrderService (Service Layer)
├── Order Cancellation Management
└── Cancelled Orders Tracking

### Order States and Rules

| State                | Description             | Can Cancel? |
| -------------------- | ----------------------- | ----------- |
| **Processing**       | Order is being prepared | ✅ YES      |
| **Shipped**          | Package is in transit   | ❌ NO       |
| **Out for Delivery** | With delivery person    | ❌ NO       |
| **Delivered**        | Successfully delivered  | ❌ NO       |

### Running the Application

Run the main class:

```bash
java Lab8.OrderTracker
```

---

## System Functionality

### 1. Order Dashboard

The system displays all active orders with:

- Order ID
- Shop name and item name
- Customer information
- Quantity and total price
- Current status
- Cancellation eligibility

### 2. Order Cancellation Feature

#### Cancelling a Cancelable Order (Processing State)

When successfully cancelled:

```
✓✓✓ ORDER CANCELLED SUCCESSFULLY ✓✓✓
Order ID: ORD001
Customer: Alice
Item: Laptop
Quantity: 1
Total Price: $10.0
```

Then automatically displays the **CANCELLED ORDERS LIST** showing all cancelled orders.

#### Attempting to Cancel a Non-Cancelable Order

When order cannot be cancelled:

```
✗✗✗ THIS ORDER CANNOT BE CANCELLED ✗✗✗
Order ID: ORD004
Current Status: Shipped: Package in transit...
Reason: Order is already in the delivery process
Note: Only orders in 'Processing' state can be cancelled
```

### 3. Cancelled Orders Tracking

The system maintains a complete history of cancelled orders, showing:

- Order ID
- Customer name
- Item details
- Quantity and price
- Shop information

---

## Sample Output

```
=======================================================
          INITIAL ORDER DASHBOARD
=======================================================

[Shows all 6 orders with full details]

=======================================================
          DEMONSTRATING ORDER CANCELLATION
=======================================================

[TEST 1] Attempting to cancel ORD001 (Processing order - should succeed):

✓✓✓ ORDER CANCELLED SUCCESSFULLY ✓✓✓
Order ID: ORD001
Customer: Alice
Item: Laptop
Quantity: 1
Total Price: $10.0

=======================================================
          CANCELLED ORDERS LIST
=======================================================
Total Cancelled Orders: 1

[1] Order ID: ORD001
    Customer: Alice
    Item: Laptop
    Quantity: 1
    Total Price: $10.0
    Shop: Tech Store
    --------------------------------------------------
=======================================================

[TEST 2] Attempting to cancel ORD004 (Shipped order - should fail):

✗✗✗ THIS ORDER CANNOT BE CANCELLED ✗✗✗
Order ID: ORD004
Current Status: Shipped: Package in transit...
Reason: Order is already in the delivery process
Note: Only orders in 'Processing' state can be cancelled

=======================================================
          FINAL SUMMARY
=======================================================
Active Orders: 4
Cancelled Orders: 2
=======================================================
```

---

## How the System Works

### Polymorphism in Action

The system demonstrates polymorphism by **never checking specific types**:

1. **Order Cancellation**:

   ```java
   if (order.isCancelable()) {
       // Cancel the order
       orders.remove(i);
       cancelledOrders.add(order);
   }
   ```

   Each state's `isCancelable()` method returns the appropriate value automatically.

2. **Dashboard Display**:
   ```java
   for (OrderRequest order : orders) {
       order.printSummary();  // Calls appropriate getStatus()
   }
   ```
   Java's dynamic binding calls the correct method based on actual object type.

### Service Layer Pattern

The `OrderService` class demonstrates the **Service Layer pattern**:

- Separates business logic from presentation
- Manages cancelled orders independently
- Provides reusable methods for order operations
- Maintains state (cancelled orders list) across operations

---

## Testing Scenarios

The system demonstrates four test cases:

| Test | Order ID | State            | Expected Result                             |
| ---- | -------- | ---------------- | ------------------------------------------- |
| 1    | ORD001   | Processing       | ✅ Cancel succeeds, added to cancelled list |
| 2    | ORD004   | Shipped          | ❌ Cancel fails, shows warning              |
| 3    | ORD002   | Processing       | ✅ Cancel succeeds, added to cancelled list |
| 4    | ORD005   | Out for Delivery | ❌ Cancel fails, shows warning              |

---

**End of README**

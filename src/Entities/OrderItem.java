package Entities;

import java.util.ArrayList;
import java.util.List;

// Κλάση που αναπαριστά κάθε στοιχείο μιας παραγγελίας
public class OrderItem {
    private String productName;
    private int quantity;
    private double cost;

    public OrderItem(String productName, int quantity, double cost) {
        this.productName = productName;
        this.quantity = quantity;
        this.cost = cost;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getCost() {
        return cost;
    }
}

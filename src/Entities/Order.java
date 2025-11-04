package Entities;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<OrderItem> items;
    private double totalCost;

    public Order() {
        this.items = new ArrayList<>();
        this.totalCost = 0.0;
    }

    public void addItem(OrderItem item) {
        items.add(item);
        totalCost += item.getCost();
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public double getTotalCost() {
        return totalCost;
    }
}

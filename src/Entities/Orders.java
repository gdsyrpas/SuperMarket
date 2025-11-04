package Entities;

import java.util.ArrayList;
import java.util.List;

public class Orders {
    private static List<Order> orderList = new ArrayList<>();

    public static void addOrder(Order order) {
        orderList.add(order);
    }

    public static List<Order> getOrders() {
        return orderList;
    }
}
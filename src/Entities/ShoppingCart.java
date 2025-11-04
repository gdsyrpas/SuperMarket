package Entities;

import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private static List<CartItem> items = new ArrayList<>();

    public ShoppingCart() {
        items = new ArrayList<>();
    }

    public static void addItem(Product product, int quantity) {
        // Έλεγχος αν το προϊόν υπάρχει ήδη στο καλάθι
        for (CartItem item : items) {
            if (item.getProduct().getTitle().equals(product.getTitle())) {
                // Ενημέρωση ποσότητας
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }

        // Προσθήκη νέου προϊόντος αν δεν υπάρχει ήδη
        items.add(new CartItem(product, quantity));
    }

    public void removeItem(Product product) {
        items.removeIf(item -> item.getProduct().getTitle().equals(product.getTitle()));
    }

    public double calculateTotalPrice() {
        double total = 0;
        for (CartItem item : items) {
            total += item.getProduct().getPrice() * item.getQuantity();
        }
        return total;
    }

    public static List<CartItem> getItems() {
        return items;
    }

    public static void clearCart() {
        items.clear();
    }

    @Override
    public String toString() {
        StringBuilder cartSummary = new StringBuilder();
        for (CartItem item : items) {
            cartSummary.append(item.toString()).append("\n");
        }
        cartSummary.append("Total Price: ").append(calculateTotalPrice()).append(" €");
        return cartSummary.toString();
    }
}

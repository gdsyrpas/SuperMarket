package Entities;

public class Product {
    private String title;
    private String description;
    private String category;
    private String subCategory;
    private double price;
    private int quantity; // Μόνο αριθμητική ποσότητα
    private String unit;  // "kg" ή "τεμάχια"

    public Product(String title, String description, String category, String subCategory, double price, int quantity, String unit) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.subCategory = subCategory;
        this.price = price;
        this.quantity = quantity;
        this.unit = unit;
    }

    // Getters και Setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getSubCategory() { return subCategory; }
    public void setSubCategory(String subCategory) { this.subCategory = subCategory; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }

    public String getFormattedQuantity() {
        return quantity + " " + unit;
    }

    public String toFileFormat() {
        return title + "\n" +
                description + "\n" +
                category + "\n" +
                subCategory + "\n" +
                price + "\n" +
                quantity + "\n" +
                unit;
    }
}

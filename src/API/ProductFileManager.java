package API;

import Entities.Product;

import java.io.*;
import java.util.List;

public class ProductFileManager {

    private final String filePath;

    public ProductFileManager(String filePath) {
        this.filePath = filePath;
    }

    // Μέθοδος για την ανάγνωση προϊόντων από το αρχείο
    public List<Product> loadProducts() throws IOException {
        return ProductLoader.loadProductsFromFile(filePath);
    }

    // Μέθοδος για την ενημέρωση ενός προϊόντος
    public void updateProduct(Product updatedProduct) throws IOException {
        List<Product> products = loadProducts();

        // Αντικατάσταση του προϊόντος
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getTitle().equals(updatedProduct.getTitle())) {
                products.set(i, updatedProduct);
                break;
            }
        }

        // Εγγραφή των προϊόντων πίσω στο αρχείο
        saveProductsToFile(products);
    }

    // Μέθοδος για την εγγραφή όλων των προϊόντων στο αρχείο
    private void saveProductsToFile(List<Product> products) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Product product : products) {
                writer.write("Τίτλος: " + product.getTitle());
                writer.newLine();
                writer.write("Περιγραφή: " + product.getDescription());
                writer.newLine();
                writer.write("Κατηγορία: " + product.getCategory());
                writer.newLine();
                writer.write("Τιμή: " + product.getPrice());
                writer.newLine();
                writer.write("Ποσότητα: " + product.getQuantity());
                writer.newLine();
                writer.newLine(); // Κενή γραμμή μεταξύ των προϊόντων
            }
        }
    }
}

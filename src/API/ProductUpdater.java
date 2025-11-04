package API;


import Entities.Product;
import java.io.*;
import java.util.List;

public class ProductUpdater {

    public static void updateProductInFile(Product updatedProduct) {
        String filePath = "src/DB/products.txt";
        List<Product> products = ProductLoader.loadProductsFromFile(filePath); // Χρήση του ProductLoader για ανάγνωση

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Product product : products) {
                if (product.getTitle().equals(updatedProduct.getTitle())) {
                    product = updatedProduct; // Αντικατάσταση του προϊόντος με το ενημερωμένο
                }

                // Γράφουμε τα στοιχεία του προϊόντος στο αρχείο
                writer.write("Τίτλος: " + product.getTitle() + "\n");
                writer.write("Περιγραφή: " + product.getDescription() + "\n");
                writer.write("Κατηγορία: " + product.getCategory() + "\n");
                writer.write("Υποκατηγορία: " + product.getSubCategory() + "\n");
                writer.write("Τιμή: " + String.format("%.2f", product.getPrice()) + "€\n");
                writer.write("Ποσότητα: " + product.getFormattedQuantity() + "\n\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

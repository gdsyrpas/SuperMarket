package API;

import Entities.Product;

import java.io.FileWriter;
import java.io.IOException;

public class ProductSaver {

    public static void saveProduct(Product product, String filePath) {
        try (FileWriter writer = new FileWriter(filePath, true)) {
            // Γράφουμε τα πεδία του προϊόντος με τη ζητούμενη μορφή
            writer.write("Τίτλος: " + product.getTitle() + "\n");
            writer.write("Περιγραφή: " + product.getDescription() + "\n");
            writer.write("Κατηγορία: " + product.getCategory() + "\n");
            writer.write("Υποκατηγορία: " + product.getSubCategory() + "\n");
            writer.write("Τιμή: " + product.getPrice() + "€\n");
            writer.write("Ποσότητα: " + product.getQuantity() +" " +product.getUnit() +"\n");
            writer.write("\n"); // Γράφουμε κενό για να διαχωρίσουμε τα προϊόντα
        } catch (IOException e) {
            System.out.println("Σφάλμα κατά την αποθήκευση του προϊόντος: " + e.getMessage());
        }
    }
}

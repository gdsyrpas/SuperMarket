package API;

import Entities.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProductLoader {

    public static List<Product> loadProductsFromFile(String filePath) {
        List<Product> products = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue; // Παραλείπουμε τις κενές γραμμές
                }

                try {
                    // Ανάγνωση ιδιοτήτων προϊόντος
                    String title = line.split(":")[1].trim();
                    String description = reader.readLine().split(":")[1].trim();
                    String category = reader.readLine().split(":")[1].trim();
                    String subCategory = reader.readLine().split(":")[1].trim();
                    double price = Double.parseDouble(
                            reader.readLine().split(":")[1].trim().replace("€", "").replace(",", ".")
                    );

                    // Ανάλυση ποσότητας και μονάδας
                    String quantityLine = reader.readLine().split(":")[1].trim();
                    int quantity = parseQuantity(quantityLine);
                    String unit = parseUnit(quantityLine);

                    products.add(new Product(title, description, category, subCategory, price, quantity, unit));

                    // Διαχείριση της κενής γραμμής μετά το προϊόν
                    reader.readLine(); // Διαβάζει και παραλείπει την κενή γραμμή
                } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
                    System.err.println("Σφάλμα κατά την ανάλυση προϊόντος: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Σφάλμα κατά την ανάγνωση του αρχείου: " + e.getMessage());
        }

        return products;
    }

    // Μέθοδος ανάλυσης ποσότητας
    private static int parseQuantity(String quantityLine) {
        return Integer.parseInt(quantityLine.replaceAll("[^0-9]", "").trim());
    }

    // Μέθοδος ανάλυσης μονάδας (kg ή τεμάχια)
    private static String parseUnit(String quantityLine) {
        if (quantityLine.toLowerCase().contains("kg")) {
            return "kg";
        } else if (quantityLine.toLowerCase().contains("τεμάχια")) {
            return "τεμάχια";
        } else {
            throw new IllegalArgumentException("Αγνωστή μονάδα ποσότητας: " + quantityLine);
        }
    }
}


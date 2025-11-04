package TESTS;

import API.ProductSaver;
import Entities.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

import static org.junit.jupiter.api.Assertions.*;

class ProductSaverTest {

    @TempDir
    File tempDir; // Χρησιμοποιούμε το @TempDir για να δημιουργήσουμε προσωρινά αρχεία για το test

    private File productFile;

    @BeforeEach
    void setUp() {
        // Δημιουργούμε το προσωρινό αρχείο για το test
        productFile = new File(tempDir, "products.txt");
    }

    @Test
    void testSaveProduct() throws IOException {
        String filePath = "src/DB/products.txt";
        // Δημιουργούμε ένα προϊόν για το test
        Product product = new Product("Πορτοκάλια", "Φρέσκα πορτοκάλια, ιδανικά για χυμό", "Φρέσκα τρόφιμα", "Φρούτα", 1.20, 10, "kg");

        // Αποθηκεύουμε το προϊόν στο προσωρινό αρχείο
        ProductSaver.saveProduct(product, productFile.getAbsolutePath());

        // Διαβάζουμε το αρχείο και ελέγχουμε αν το περιεχόμενο είναι σωστό
        try (BufferedReader reader = new BufferedReader(new FileReader(productFile))) {
            String line = reader.readLine();
            assertEquals("Τίτλος: Πορτοκάλια", line);  // Έλεγχος τίτλου
            line = reader.readLine();
            assertEquals("Περιγραφή: Φρέσκα πορτοκάλια, ιδανικά για χυμό", line);  // Έλεγχος περιγραφής
            line = reader.readLine();
            assertEquals("Κατηγορία: Φρέσκα τρόφιμα", line);  // Έλεγχος κατηγορίας
            line = reader.readLine();
            assertEquals("Υποκατηγορία: Φρούτα", line);  // Έλεγχος υποκατηγορίας
            line = reader.readLine();
            assertEquals("Τιμή: 1.2€", line);  // Έλεγχος τιμής
            line = reader.readLine();
            assertEquals("Ποσότητα: 10 kg", line);  // Έλεγχος ποσότητας και μονάδας
        }
    }

    @Test
    void testSaveProduct_EmptyFile() throws IOException {
        // Δημιουργούμε ένα προϊόν
        Product product = new Product("Καρότα", "Τραγανά καρότα, κατάλληλα για σαλάτες", "Φρέσκα τρόφιμα", "Λαχανικά", 1.00, 20, "kg");

        // Αποθηκεύουμε το προϊόν στο αρχείο
        ProductSaver.saveProduct(product, productFile.getAbsolutePath());

        // Διαβάζουμε το αρχείο και ελέγχουμε αν το περιεχόμενο είναι σωστό
        try (BufferedReader reader = new BufferedReader(new FileReader(productFile))) {
            String line = reader.readLine();
            assertEquals("Τίτλος: Καρότα", line);
            line = reader.readLine();
            assertEquals("Περιγραφή: Τραγανά καρότα, κατάλληλα για σαλάτες", line);
            line = reader.readLine();
            assertEquals("Κατηγορία: Φρέσκα τρόφιμα", line);
            line = reader.readLine();
            assertEquals("Υποκατηγορία: Λαχανικά", line);
            line = reader.readLine();
            assertEquals("Τιμή: 1.0€", line);
            line = reader.readLine();
            assertEquals("Ποσότητα: 20 kg", line);
        }
    }
}

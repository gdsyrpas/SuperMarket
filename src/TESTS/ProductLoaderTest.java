package TESTS;

import API.ProductLoader;
import Entities.Product;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;



class ProductLoaderTest {

    private static final String TEST_FILE_PATH = "src/DB/products.txt";


    @Test
    void testLoadProductsFromFile() {
        // Φόρτωμα προϊόντων από το αρχείο
        List<Product> products = ProductLoader.loadProductsFromFile(TEST_FILE_PATH);

        // Έλεγχος αν έχουμε τα αναμενόμενα προϊόντα
        assertEquals(40, products.size());

        // Έλεγχος για το πρώτο προϊόν
        Product firstProduct = products.get(0);
        assertEquals("Πορτοκάλια 1kg", firstProduct.getTitle());
        assertEquals("Φρέσκα πορτοκάλια, ιδανικά για χυμό ή κατανάλωση.", firstProduct.getDescription());
        assertEquals("Φρέσκα τρόφιμα", firstProduct.getCategory());
        assertEquals("Φρούτα", firstProduct.getSubCategory());
        assertEquals(1.20, firstProduct.getPrice());
        assertEquals(0, firstProduct.getQuantity());
        assertEquals("kg", firstProduct.getUnit());

        // Έλεγχος για το τελευταίο προϊόν
        Product lastProduct = products.get(4);
        assertEquals("Κατεψυγμένες Πίτσες Margherita 400g", lastProduct.getTitle());
        assertEquals("Πίτσα Margherita με φρέσκια σάλτσα ντομάτας και τυρί.", lastProduct.getDescription());
        assertEquals("Κατεψυγμένα τρόφιμα", lastProduct.getCategory());
        assertEquals("Κατεψυγμένες πίτσες", lastProduct.getSubCategory());
        assertEquals(4.00, lastProduct.getPrice());
        assertEquals(80, lastProduct.getQuantity());
        assertEquals("τεμάχια", lastProduct.getUnit());
    }


}

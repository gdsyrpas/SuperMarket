package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import Entities.User;
import Entities.Product;
import API.*;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.List;


public class AdminPanel extends JPanel {

    public AdminPanel(User currentUser) {
        // Ρυθμίσεις layout
        setLayout(new BorderLayout(10, 10));

        // Πληροφορίες Χρήστη (Όνομα και Ρόλος)
        JPanel userInfoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        userInfoPanel.add(new JLabel("Χρήστης: " + currentUser.getUsername()));
        userInfoPanel.add(new JLabel("Ρόλος: " + currentUser.getRole()));
        add(userInfoPanel, BorderLayout.NORTH);

        // Δημιουργία των καρτελών
        JTabbedPane tabbedPane = new JTabbedPane();

        // Προσθήκη της καρτέλας για την προσθήκη νέου προϊόντος
        tabbedPane.addTab("Add New Product", createAddProductPanel());

        // Προσθήκη της καρτέλας για την προβολή των προϊόντων
        tabbedPane.addTab("View Products", createViewProductsPanel());

        tabbedPane.addTab("Εξαντλημένα Προϊόντα", createStatisticsPanel());

        // Προσθήκη του TabbedPane στο κεντρικό panel
        add(tabbedPane, BorderLayout.CENTER);
    }

    // Δημιουργία της φόρμας για την προσθήκη νέου προϊόντος
    private JPanel createAddProductPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10)); // Εξωτερικό BorderLayout για τον τίτλο και την φόρμα

        // Προσθήκη τίτλου "Εισαγωγή Προϊόντος" κεντραρισμένου
        JLabel titleLabel = new JLabel("Εισαγωγή Προϊόντος", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Μεγαλύτερη γραμματοσειρά
        panel.add(titleLabel, BorderLayout.NORTH); // Προσθήκη τίτλου στο πάνω μέρος του panel

        // Δημιουργία της φόρμας με τα πεδία εισόδου
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);  // Αποστάσεις για τα στοιχεία

        // Δημιουργία πεδίων για τίτλο, περιγραφή, κατηγορία, τιμή και ποσότητα
        JLabel productTitleLabel = new JLabel("Τίτλος:");
        JTextField titleField = new JTextField(20);
        JLabel titleDescription = new JLabel("Εισάγετε τον τίτλο του προϊόντος");

        JLabel descriptionLabel = new JLabel("Περιγραφή:");
        JTextField descriptionField = new JTextField(20);
        JLabel descriptionDescription = new JLabel("Εισάγετε περιγραφή για το προϊόν");

        JLabel categoryLabel = new JLabel("Κατηγορία:");
        JTextField categoryField = new JTextField(20);
        JLabel categoryDescription = new JLabel("Εισάγετε την κατηγορία του προϊόντος");

        JLabel priceLabel = new JLabel("Τιμή:");
        JTextField priceField = new JTextField(20);
        JLabel priceDescription = new JLabel("Εισάγετε την τιμή του προϊόντος");

        JLabel quantityLabel = new JLabel("Διαθέσιμη ποσότητα:");
        JTextField quantityField = new JTextField(20);
        JLabel quantityDescription = new JLabel("Εισάγετε την διαθέσιμη ποσότητα του προϊόντος");

        // Δημιουργία νέων πεδίων για υποκατηγορία και μονάδα
        JLabel subcategoryLabel = new JLabel("Υποκατηγορία:");
        JTextField subcategoryField = new JTextField(20);
        JLabel subcategoryDescription = new JLabel("Εισάγετε την υποκατηγορία του προϊόντος");

        JLabel unitLabel = new JLabel("Τύπος Μονάδας:");
        JTextField unitField = new JTextField(20);
        JLabel unitDescription = new JLabel("Εισάγετε τον τύπο της μονάδας (π.χ., τεμάχιο, κιλό)");

        // Χρησιμοποιούμε το GridBagConstraints για τη θέση των στοιχείων
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(productTitleLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(titleField, gbc);
        gbc.gridx = 2;
        formPanel.add(titleDescription, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(descriptionLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(descriptionField, gbc);
        gbc.gridx = 2;
        formPanel.add(descriptionDescription, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(categoryLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(categoryField, gbc);
        gbc.gridx = 2;
        formPanel.add(categoryDescription, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(priceLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(priceField, gbc);
        gbc.gridx = 2;
        formPanel.add(priceDescription, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(quantityLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(quantityField, gbc);
        gbc.gridx = 2;
        formPanel.add(quantityDescription, gbc);

        // Προσθήκη νέων πεδίων για υποκατηγορία και τύπο μονάδας
        gbc.gridx = 0;
        gbc.gridy = 5;
        formPanel.add(subcategoryLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(subcategoryField, gbc);
        gbc.gridx = 2;
        formPanel.add(subcategoryDescription, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        formPanel.add(unitLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(unitField, gbc);
        gbc.gridx = 2;
        formPanel.add(unitDescription, gbc);

        // Κουμπί υποβολής
        JButton submitButton = new JButton("Προσθήκη Προϊόντος");
        submitButton.addActionListener(e -> {
            // Λήψη των δεδομένων από τα πεδία της φόρμας
            String title = titleField.getText();
            String description = descriptionField.getText();
            String category = categoryField.getText();
            String subcategory = subcategoryField.getText();  // Υποκατηγορία
            String unit = unitField.getText();  // Τύπος μονάδας
            String priceStr = priceField.getText();
            String quantityStr = quantityField.getText();

            try {
                double price = Double.parseDouble(priceStr);
                int quantity = Integer.parseInt(quantityStr);

                // Δημιουργία του νέου προϊόντος
                Product newProduct = new Product(title, description, category, subcategory, price, quantity, unit);

                // Καθορισμός του path του αρχείου
                String filePath = "src/DB/products.txt";

                // Αποθήκευση του προϊόντος στο αρχείο
                ProductSaver.saveProduct(newProduct, filePath);

                // Εμφάνιση μηνύματος επιτυχίας
                JOptionPane.showMessageDialog(this, "Προϊόν καταχωρήθηκε!\n" +
                        "Τίτλος: " + title + "\n" +
                        "Περιγραφή: " + description + "\n" +
                        "Κατηγορία: " + category + "\n" +
                        "Υποκατηγορία: " + subcategory + "\n" +
                        "Τύπος Μονάδας: " + unit + "\n" +
                        "Τιμή: " + price + "\n" +
                        "Ποσότητα: " + quantity);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Παρακαλώ εισάγετε έγκυρες τιμές για την τιμή και την ποσότητα.");
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 7;
        formPanel.add(submitButton, gbc);

        // Προσθήκη της φόρμας στο panel
        panel.add(formPanel, BorderLayout.CENTER);

        return panel;
    }


    // Δημιουργία του πίνακα προβολής των προϊόντων (προς το παρόν κενό)

    private JPanel createViewProductsPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));

        // Πάνω μέρος με πεδίο αναζήτησης και κουμπί
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextField searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        searchPanel.add(new JLabel("Αναζήτηση:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        panel.add(searchPanel, BorderLayout.NORTH);

        // Scrollable περιοχή με τα προϊόντα
        JPanel productsPanel = new JPanel(new GridLayout(0, 1, 10, 10)); // 1 στήλη, πολλές γραμμές
        productsPanel.setBackground(Color.WHITE); // Προαιρετικό για καλύτερη οπτική

        String filePath = "src/DB/products.txt"; // Διαδρομή αρχείου
        List<Product> products = ProductLoader.loadProductsFromFile(filePath);

        searchButton.addActionListener(e -> {
            String query = searchField.getText().trim(); // Παίρνουμε το query από το πεδίο αναζήτησης

            // Αναζήτηση προϊόντων
            List<Product> filteredProducts = ProductSearch.searchProductsByTitle(products, query);

            // Ενημέρωση του panel προϊόντων
            productsPanel.removeAll(); // Αδειάζουμε το panel
            for (Product product : filteredProducts) {
                JPanel productCard = createProductCard(product); // Δημιουργούμε την κάρτα για κάθε προϊόν
                productsPanel.add(productCard);
            }
            productsPanel.revalidate(); // Ενημέρωση GUI
            productsPanel.repaint();
        });


        // Προσθήκη προϊόντων στο panel
        for (Product product : products) {
            productsPanel.add(createProductCard(product));
        }

        // Δημιουργία JScrollPane
        JScrollPane scrollPane = new JScrollPane(productsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
                int scrollAmount = e.getUnitsToScroll() * 15; // Ρύθμισε την ταχύτητα (default: πολλαπλάσιο του 16)
                verticalScrollBar.setValue(verticalScrollBar.getValue() + scrollAmount);
            }
        });

        // Περιορισμός μεγέθους προβολής (4 κάρτες τη φορά)
        scrollPane.setPreferredSize(new Dimension(600, 500));
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }


    private JPanel createProductCard(Product product) {
        JPanel card = new JPanel(new BorderLayout(10, 10));
        card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));

        // Στοιχεία προϊόντος
        JPanel infoPanel = new JPanel(new GridLayout(0, 1));
        infoPanel.add(new JLabel("Τίτλος: " + product.getTitle()));
        infoPanel.add(new JLabel("Περιγραφή: " + product.getDescription()));
        infoPanel.add(new JLabel("Κατηγορία: " + product.getCategory()));
        infoPanel.add(new JLabel("Τιμή: " + product.getPrice() + " €"));
        infoPanel.add(new JLabel("Διαθέσιμη ποσότητα: " + product.getQuantity()));

        // Κουμπί για επεξεργασία
        JButton editButton = new JButton("Edit Product");
        editButton.addActionListener(e -> {
            openEditProductDialog(product);
        });

        card.add(infoPanel, BorderLayout.CENTER);
        card.add(editButton, BorderLayout.EAST);

        return card;
    }

    private void openEditProductDialog(Product product) {
        // Παράθυρο επεξεργασίας
        JDialog editDialog = new JDialog((Frame) null, "Edit Product", true);
        editDialog.setSize(400, 300);
        editDialog.setLayout(new BorderLayout(10, 10));

        JPanel formPanel = new JPanel(new GridLayout(0, 2, 5, 5));
        formPanel.add(new JLabel("Τίτλος:"));
        JTextField titleField = new JTextField(product.getTitle());
        formPanel.add(titleField);

        formPanel.add(new JLabel("Περιγραφή:"));
        JTextField descriptionField = new JTextField(product.getDescription());
        formPanel.add(descriptionField);

        formPanel.add(new JLabel("Κατηγορία:"));
        JTextField categoryField = new JTextField(product.getCategory());
        formPanel.add(categoryField);

        formPanel.add(new JLabel("Τιμή:"));
        JTextField priceField = new JTextField(String.valueOf(product.getPrice()));
        formPanel.add(priceField);

        formPanel.add(new JLabel("Ποσότητα:"));
        JTextField quantityField = new JTextField(String.valueOf(product.getQuantity()));
        formPanel.add(quantityField);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> {
            try {
                // Ενημέρωση του προϊόντος
                product.setTitle(titleField.getText());
                product.setDescription(descriptionField.getText());
                product.setCategory(categoryField.getText());
                product.setPrice(Double.parseDouble(priceField.getText()));

                // Προσθήκη της μονάδας στη νέα ποσότητα
                int newQuantity = Integer.parseInt(quantityField.getText());
                product.setQuantity(newQuantity);

                // Αποθήκευση στο αρχείο
                ProductUpdater.updateProductInFile(product);
                JOptionPane.showMessageDialog(editDialog, "Το προϊόν ενημερώθηκε επιτυχώς!", "Ενημέρωση", JOptionPane.INFORMATION_MESSAGE);
                editDialog.dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(editDialog, "Παρακαλώ εισάγετε έγκυρες τιμές.", "Σφάλμα", JOptionPane.ERROR_MESSAGE);
            }
        });


        editDialog.add(formPanel, BorderLayout.CENTER);
        editDialog.add(saveButton, BorderLayout.SOUTH);

        editDialog.setLocationRelativeTo(null);
        editDialog.setVisible(true);
    }

    private JPanel createStatisticsPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));

        // Διαδρομή αρχείου
        String filePath = "src/DB/products.txt";

        // Φόρτωση προϊόντων από το αρχείο
        List<Product> products = ProductLoader.loadProductsFromFile(filePath);

        // Δημιουργία δεδομένων για τον πίνακα
        String[] columnNames = {"Τίτλος", "Περιγραφή", "Κατηγορία", "Υποκατηγορία", "Τιμή", "Ποσότητα", "Μονάδα"};
        Object[][] data = convertProductsToTableData(ProductStatistics.getOutOfStockProducts(products));

        // Δημιουργία πίνακα
        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        // Δημιουργία κουμπιού "Ανανέωση"
        JButton refreshButton = new JButton("Ανανέωση");
        refreshButton.addActionListener(e -> {
            // Επαναφόρτωση προϊόντων από το αρχείο
            List<Product> updatedProducts = ProductLoader.loadProductsFromFile(filePath);

            // Ενημέρωση του πίνακα με τα εξαντλημένα προϊόντα
            List<Product> outOfStockProducts = ProductStatistics.getOutOfStockProducts(updatedProducts);
            Object[][] updatedData = convertProductsToTableData(outOfStockProducts);

            // Ενημέρωση μοντέλου πίνακα
            table.setModel(new DefaultTableModel(updatedData, columnNames));
        });

        // Προσθήκη του κουμπιού στο πάνω μέρος
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topPanel.add(refreshButton);
        panel.add(topPanel, BorderLayout.NORTH);

        // Προσθήκη του πίνακα στο κέντρο
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private Object[][] convertProductsToTableData(List<Product> products) {
        Object[][] data = new Object[products.size()][7];

        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            data[i][0] = product.getTitle();
            data[i][1] = product.getDescription();
            data[i][2] = product.getCategory();
            data[i][3] = product.getSubCategory();
            data[i][4] = product.getPrice();
            data[i][5] = product.getQuantity();
            data[i][6] = product.getUnit();
        }

        return data;
    }




}

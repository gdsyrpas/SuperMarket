package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

import Entities.*;
import API.*;

public class CustomerPanel extends JPanel {

    public CustomerPanel(User currentUser) {
        // Ρυθμίσεις layout
        setLayout(new BorderLayout(10, 10));

        // Πληροφορίες Χρήστη (Όνομα και Ρόλος)
        JPanel userInfoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        userInfoPanel.add(new JLabel("Χρήστης: " + currentUser.getUsername()));
        userInfoPanel.add(new JLabel("Ρόλος: " + currentUser.getRole()));
        add(userInfoPanel, BorderLayout.NORTH);

        // Δημιουργία των καρτελών
        JTabbedPane tabbedPane = new JTabbedPane();

        // Προσθήκη της καρτέλας για την αναζήτηση προϊόντων
        tabbedPane.addTab("Αναζήτηση Προϊόντων", createSearchProductPanel());
        tabbedPane.addTab("Το Καλάθι μου", createCartTab());
        tabbedPane.addTab("Οι παραγγελίες μου", createOrdersTab());

        // Προσθήκη του TabbedPane στο κεντρικό panel
        add(tabbedPane, BorderLayout.CENTER);
    }

    private JPanel createSearchProductPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));

        // Πάνω μέρος με πεδίο αναζήτησης και κουμπί
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextField searchField = new JTextField(20);
        JButton searchButton = new JButton("Αναζήτηση");
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

        // Κουμπί για προσθήκη στο καλάθι
        JButton addToCartButton = new JButton("Προσθήκη στο Καλάθι");
        addToCartButton.addActionListener(e -> {
            openAddToCartDialog(product);
        });

        card.add(infoPanel, BorderLayout.CENTER);
        card.add(addToCartButton, BorderLayout.EAST);

        return card;
    }

    private void openAddToCartDialog(Product product) {
        // Παράθυρο προσθήκης στο καλάθι
        JDialog addToCartDialog = new JDialog((Frame) null, "Προσθήκη στο Καλάθι", true);
        addToCartDialog.setSize(400, 250);
        addToCartDialog.setLayout(new BorderLayout(10, 10));

        // Πάνελ φόρμας
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        formPanel.add(new JLabel("Προϊόν:"));
        formPanel.add(new JLabel(product.getTitle()));

        formPanel.add(new JLabel("Διαθέσιμη Ποσότητα:"));
        formPanel.add(new JLabel(String.valueOf(product.getQuantity())));

        formPanel.add(new JLabel("Ποσότητα:"));
        JTextField quantityField = new JTextField();
        formPanel.add(quantityField);

        // Κουμπιά
        JButton addButton = new JButton("Προσθήκη");
        JButton cancelButton = new JButton("Ακύρωση");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(cancelButton);

        // Listener για το κουμπί "Προσθήκη"
        addButton.addActionListener(e -> {
            try {
                int quantityToAdd = Integer.parseInt(quantityField.getText());
                if (quantityToAdd > 0 && quantityToAdd <= product.getQuantity()) {
                    // Προσθήκη στο καλάθι
                    ShoppingCart.addItem(product, quantityToAdd);

                    JOptionPane.showMessageDialog(addToCartDialog,
                            "Το προϊόν προστέθηκε επιτυχώς στο καλάθι.");
                    addToCartDialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(addToCartDialog,
                            "Η ποσότητα πρέπει να είναι μεταξύ 1 και " + product.getQuantity() + ".",
                            "Σφάλμα",
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(addToCartDialog,
                        "Παρακαλώ εισάγετε έγκυρο αριθμό.",
                        "Σφάλμα",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        // Listener για το κουμπί "Ακύρωση"
        cancelButton.addActionListener(e -> addToCartDialog.dispose());

        // Τοποθέτηση πάνελ στο παράθυρο
        addToCartDialog.add(formPanel, BorderLayout.CENTER);
        addToCartDialog.add(buttonPanel, BorderLayout.SOUTH);

        addToCartDialog.setLocationRelativeTo(null); // Κεντρική εμφάνιση
        addToCartDialog.setVisible(true);
    }

    private JPanel createCartTab() {
        JPanel cartPanel = new JPanel(new BorderLayout(10, 10));

        // Μοντέλο πίνακα
        String[] columnNames = {"Προϊόν", "Ποσότητα", "Κόστος Προϊόντος"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable cartTable = new JTable(tableModel);

        // Συνολικό κόστος
        JLabel totalCostLabel = new JLabel("Συνολικό Κόστος: 0.00 €");
        totalCostLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        // Κουμπί ανανέωσης
        JButton refreshButton = new JButton("Ανανέωση");
        refreshButton.addActionListener(e -> refreshCartTable(tableModel, totalCostLabel));

        // Κουμπί αγοράς
        JButton purchaseButton = new JButton("Αγορά");
        purchaseButton.addActionListener(e -> {
            if (ShoppingCart.getItems().isEmpty()) {
                JOptionPane.showMessageDialog(cartPanel, "Το καλάθι είναι άδειο!", "Προσοχή", JOptionPane.WARNING_MESSAGE);
            } else {
                // Δημιουργία νέας παραγγελίας
                Order newOrder = new Order();
                for (CartItem item : ShoppingCart.getItems()) {
                    double itemCost = item.getQuantity() * item.getProduct().getPrice();
                    OrderItem orderItem = new OrderItem(
                            item.getProduct().getTitle(),
                            item.getQuantity(),
                            itemCost
                    );
                    newOrder.addItem(orderItem);
                }

                // Προσθήκη παραγγελίας στη λίστα παραγγελιών
                Orders.addOrder(newOrder);

                JOptionPane.showMessageDialog(cartPanel, "Η αγορά ολοκληρώθηκε επιτυχώς!", "Επιτυχία", JOptionPane.INFORMATION_MESSAGE);

                // Εκκαθάριση καλαθιού
                ShoppingCart.clearCart();
                refreshCartTable(tableModel, totalCostLabel);
                totalCostLabel.setText("Συνολικό Κόστος: 0.00 €");
            }
        });


        // Πάνελ κουμπιών
        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.add(refreshButton, BorderLayout.WEST);
        buttonPanel.add(totalCostLabel, BorderLayout.CENTER);
        buttonPanel.add(purchaseButton, BorderLayout.EAST);

        // Προσθήκη στοιχείων στο panel
        cartPanel.add(new JScrollPane(cartTable), BorderLayout.CENTER);
        cartPanel.add(buttonPanel, BorderLayout.SOUTH);

        return cartPanel;
    }

    private void refreshCartTable(DefaultTableModel tableModel, JLabel totalCostLabel) {
        // Εκκαθάριση πίνακα
        tableModel.setRowCount(0);

        // Προσθήκη προϊόντων από το καλάθι
        double totalCost = 0.0;
        for (CartItem item : ShoppingCart.getItems()) {
            double itemCost = item.getQuantity() * item.getProduct().getPrice();
            tableModel.addRow(new Object[]{
                    item.getProduct().getTitle(),
                    item.getQuantity(),
                    String.format("%.2f €", itemCost)
            });
            totalCost += itemCost;
        }

        // Ενημέρωση συνολικού κόστους
        totalCostLabel.setText("Συνολικό Κόστος: " + String.format("%.2f €", totalCost));
    }

    private JPanel createOrdersTab() {
        JPanel ordersPanel = new JPanel(new BorderLayout(10, 10));

        // Μοντέλο πίνακα
        String[] columnNames = {"Προϊόντα", "Ποσότητα", "Συνολικό Κόστος"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable ordersTable = new JTable(tableModel);

        // Μέθοδος Refresh
        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> refreshOrdersTable(tableModel));

        // Προσθήκη κουμπιού πάνω από τον πίνακα
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(refreshButton);

        ordersPanel.add(topPanel, BorderLayout.NORTH);
        ordersPanel.add(new JScrollPane(ordersTable), BorderLayout.CENTER);

        // Αρχική γέμιση του πίνακα
        refreshOrdersTable(tableModel);

        return ordersPanel;
    }

    private void refreshOrdersTable(DefaultTableModel tableModel) {
        tableModel.setRowCount(0); // Καθαρισμός του πίνακα

        // Γέμισμα πίνακα με τις παραγγελίες
        for (Order order : Orders.getOrders()) {
            StringBuilder productNames = new StringBuilder();
            int totalQuantity = 0;

            for (OrderItem item : order.getItems()) {
                productNames.append(item.getProductName()).append(", ");
                totalQuantity += item.getQuantity();
            }

            // Αφαίρεση του τελευταίου κόμματος
            if (productNames.length() > 0) {
                productNames.setLength(productNames.length() - 2);
            }

            tableModel.addRow(new Object[]{
                    productNames.toString(),
                    totalQuantity,
                    String.format("%.2f €", order.getTotalCost())
            });
        }
    }





}


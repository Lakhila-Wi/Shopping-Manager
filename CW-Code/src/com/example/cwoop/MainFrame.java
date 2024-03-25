package com.example.cwoop;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class MainFrame extends JFrame {
    /**
     * Declaration of GUI components and related objects.
     */
    private JPanel bottomPanel;
    private JTable productTable;
    private JPanel topPanel;
    private JButton cartButton;
    private JTextArea prductDesciption;
    private JComboBox<String> selectProduct;
    private ArrayList<Product> productList;
    private ShoppingCart shopping_Cart;
    private ShoppingCartFrame shoppingCartFrame;
    private JButton addCartButton;
    private JScrollPane scrollPane;
    private WestminsterShoppingManager west;

    /**
     * Constructor for the MainFrame class
     */
    public MainFrame(WestminsterShoppingManager west) {
        this.west = west;
        this.productList = this.west.getPrd_list();
        shopping_Cart = new ShoppingCart();
        shoppingCartFrame = new ShoppingCartFrame(this.west, shopping_Cart);
        westminsterPage();
    }
    /**
     * Method to initialize and set up the main Westminster Shopping Manager GUI.
     */
    private void westminsterPage() {
        setTitle("Westminster Shopping Centre");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 800);
        setLayout(new BorderLayout());
//     Top panel setup with product category selection and shopping cart button
        topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        selectProduct = new JComboBox<>(new String[]{"All", "Electronics", "Clothing"});
        cartButton = new JButton("Shopping Cart");
        cartButton.addActionListener(e -> shoppingCartFrame.setVisible(true));
        topPanel.add(new JLabel("Select Product Category: "));
        topPanel.add(selectProduct);
        topPanel.add(cartButton);
        add(topPanel, BorderLayout.NORTH);

//     Center panel setup with a scrollable product table
        productTable = new JTable();
        scrollPane = new JScrollPane(productTable);
        add(scrollPane, BorderLayout.CENTER);

//     Bottom panel for product details and Add to Cart button
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.PAGE_AXIS));

//     product description setup
        prductDesciption = new JTextArea(10, 20);
        prductDesciption.setEditable(false);
        bottomPanel.add(new JScrollPane(prductDesciption));

//     Initialize and add the Add to Cart button
        addCartButton = new JButton("Add to Shopping Cart");
        addCartButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Center align the button
        addCartButton.addActionListener(e -> addSelectedProductToCart());
        bottomPanel.add(addCartButton);

//     Add the bottom panel to the south of the layout
        add(bottomPanel, BorderLayout.SOUTH);

//     Update table based on ComboBox selection
        selectProduct.addActionListener(e -> updateProductTable((String) selectProduct.getSelectedItem()));

//     Listener for row selection in table
        productTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting() && productTable.getSelectedRow() != -1) {
                    displayProductDetails(productTable.getSelectedRow());
                }
            }
        });
//      Set the visibility of the frame
        setVisible(true);
    }
    /**
     * Updates the product table based on the selected product category.
     */
    private void updateProductTable(String category) {
        // Define column names for the product table
        String[] columnNames = {"Product ID", "Name", "Price", "Type", "Additional Info"};
        // Create a DefaultTableModel with the specified column names
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        // Iterate through the productList and add rows based on the selected category
        for (Product product : productList) {
            // Check if the product matches the selected category or if "All" is selected
            if (category.equals("All") ||
                    (category.equals("Electronics") && product instanceof Electronics) ||
                    (category.equals("Clothing") && product instanceof Clothing)) {
            // Determine the type and additional information based on the product type
                String type = product instanceof Electronics ? "Electronics" : "Clothing";
                String additionalInfo = product instanceof Electronics ?
                        "Brand: " + ((Electronics) product).getBrand() + ", Warranty: " + ((Electronics) product).getWarranty() :
                        "Size: " + ((Clothing) product).getSize() + ", Color: " + ((Clothing) product).getColor();
                // Add a new row to the table model with product details
                model.addRow(new Object[]{product.getPrdId(), product.getPrdName(), product.getPrice(), type, additionalInfo});
            }
        }
        // Set the updated model to the productTable
        productTable.setModel(model);
    }
    /**
     * Displays detailed information of the selected product in the JTextArea.
     */
    private void displayProductDetails(int rowIndex) {
        // Get the table model from the productTable
        DefaultTableModel model = (DefaultTableModel) productTable.getModel();
        // Prepare a string to store the details of the selected product
        String details = "Selected Product - Details:\n";
        // Iterate through columns and append column names and corresponding values to the details string
        for (int i = 0; i < model.getColumnCount(); i++) {
            details += model.getColumnName(i) + ": " + model.getValueAt(rowIndex, i) + "\n";
        }
        // Set the details string as the text of the prductDesciption JTextArea
        prductDesciption.setText(details);
    }
    /**
     * Retrieves the selected product from the product table based on the selected row.
     */
    private Product getSelectedProduct() {
        int selectedRow = productTable.getSelectedRow();
        // Check if a row is selected
        if (selectedRow >= 0) {
            // If the initial column in the table includes the Product ID, assuming this condition.
            String productId = (String) productTable.getModel().getValueAt(selectedRow, 0);
            // Search for the product in the productList with this ID
            for (Product product : productList) {
                if (product.getPrdId().equals(productId)) {
                    return product;
                }
            }
        }
        return null; // Return null if product is not found or no row is selected.
    }
    /**
     * Adds the selected product to the shopping cart, considering its type.
     */
    private void addSelectedProductToCart() {
        // Retrieve the selected product from the product table
        Product selectedProduct = getSelectedProduct();
        // Check if a valid product is selected
        if (selectedProduct != null) {
            Product productToAdd;
            // Check if the product is Electronics
            if (selectedProduct instanceof Electronics) {
                Electronics electronics = (Electronics) selectedProduct;
                productToAdd = new Electronics(electronics.getPrdId(), electronics.getPrdName(), electronics.getNoofItem(), electronics.getPrice(), electronics.getCategory(), electronics.getBrand(), electronics.getWarranty());
            }
            // Check if the product is Clothing
            else if (selectedProduct instanceof Clothing) {
                Clothing clothing = (Clothing) selectedProduct;
                productToAdd = new Clothing(clothing.getPrdId(), clothing.getPrdName(), clothing.getNoofItem(), clothing.getPrice(), clothing.getCategory(), clothing.getSize(), clothing.getColor());
            } else {
                // If the product is not a known subclass, do nothing
                return;
            }
            // Add the cloned product to the shopping cart
            shopping_Cart.addToCart(productToAdd);
            shoppingCartFrame.refreshCart();

        }
    }
}
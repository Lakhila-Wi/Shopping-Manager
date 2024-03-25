package com.example.cwoop;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public  class ShoppingCartFrame extends JFrame {
    /**
     * Declaration of GUI components and related objects for the Westminster Shopping Cart.
     */
    private JTable shoppingTable;
    private ShoppingCart shoppingCart;
    private JLabel totCostLabel1;
    private JLabel totCostLabel2;
    private JLabel totCostLabel3;
    private JLabel totCostLabel4;
    private WestminsterShoppingManager westminsterShoppingManager;
    /**
     * Constructor for the ShoppingCartFrame class, initializing components and setting up the shopping cart display.
     */
    public ShoppingCartFrame(WestminsterShoppingManager manager,ShoppingCart shoppingCart) {
        this.westminsterShoppingManager = manager;
        this.shoppingCart = shoppingCart;
        // Set the title and size of the shopping cart frame
        setTitle("Shopping Cart");
        setSize(800, 800);
        setLayout(new BorderLayout());

        // Setting up padding for the frame
        int padding = 10;
        getRootPane().setBorder(BorderFactory.createEmptyBorder(padding, padding, padding, padding));

        // Setting up the table to display the shopping cart contents
        String[] columnNames = {"Product", "Quantity", "Price"};
        shoppingTable = new JTable(new DefaultTableModel(columnNames, 0));
        JScrollPane scrollPane = new JScrollPane(shoppingTable);
        add(scrollPane, BorderLayout.CENTER);

        // Initialize the totalPriceLabel
        JPanel labelsPanel = new JPanel();
        labelsPanel.setLayout(new BoxLayout(labelsPanel, BoxLayout.Y_AXIS));

        // Create and add labels to the panel
        totCostLabel1 = new JLabel();
        totCostLabel1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        labelsPanel.add(totCostLabel1);

        totCostLabel2 = new JLabel();
        totCostLabel2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        labelsPanel.add(totCostLabel2);

        totCostLabel3 = new JLabel();
        totCostLabel3.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        labelsPanel.add(totCostLabel3);

        totCostLabel4 = new JLabel();
        totCostLabel4.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        labelsPanel.add(totCostLabel4);

        // Add the panel to the bottom of the main frame
        add(labelsPanel, BorderLayout.SOUTH);

        refreshCart(); // Refresh cart to update the label
        setVisible(true); // Make the window visible
    }
    /**
     * Refreshes the shopping cart display by updating both the cart contents and the total price label.
     */
    public void refreshCart() {
        updateCartDisplay();
        updateTotalPriceDisplay();
    }
    /**
     * Updates the total price labels on the shopping cart frame.
     */
    private void updateTotalPriceDisplay() {
        // Calculate the total price, discount, and final total from the shopping cart
        double totalPrice = shoppingCart.calculateTotalPrice();
        double totalPrice1 = shoppingCart.threeItemsDiscont();
        double finalTotal = shoppingCart.finalTotal();
        // Set the corresponding labels with formatted text
        totCostLabel1.setText("Total: $" + String.format("%.2f", totalPrice));
        totCostLabel2.setText("Frist Purchase Discount (10% ): $" );
        totCostLabel3.setText("Three Items in same Category Discount (20%): $" + String.format("%.2f", totalPrice1));
        totCostLabel4.setText("Final Total: $" + String.format("%.2f", finalTotal));
    }
    /**
     * Updates the display of the shopping cart contents on the shopping cart frame.
     */
    public void updateCartDisplay() {
        // Get the table model from the shoppingTable
        DefaultTableModel model = (DefaultTableModel) shoppingTable.getModel();
        // Clear existing rows in the table model
        model.setRowCount(0);
        // Iterate through products in the shopping cart and add them to the table model
        for (Product product : shoppingCart.getProductList()) {
            String productInfo;
            if(product.getCategory().equalsIgnoreCase("Clothing")){
                productInfo = product.getPrdId() + " - " + product.getPrdName() + " -  " + ((Clothing) product).getSize() + ", " + ((Clothing) product).getColor() ;
            }
            else {
                productInfo = product.getPrdId() + " - " + product.getPrdName() + " - " + ((Electronics) product).getBrand() + ", " + ((Electronics) product).getWarranty() ;
            }
            // Get quantity from the shopping cart and calculate the total price for the product
            int quantity = shoppingCart.getAddingProduct(product);
            // getting quaantity from hasmap
            double price = product.getPrice() * quantity;
            // Add a new row to the table model with product details
            model.addRow(new Object[]{productInfo, quantity, price});
        }
    }



}


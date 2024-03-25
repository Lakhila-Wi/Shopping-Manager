package com.example.cwoop;
import com.example.cwoop.interfaces.ShoppingManager;
import java.util.*;
import java.io.*;

public class WestminsterShoppingManager implements ShoppingManager {
    private boolean available = false;
//  Creating arraylist to contain the product list
    private ArrayList<Product> prd_list = new ArrayList<>();
// Method for display the menu
    public void menu() {
        System.out.println("********** Menu **********");
        System.out.println("1. Add a new product");
        System.out.println("2. Delete a product");
        System.out.println("3. Print the list of the products");
        System.out.println("4. Save in a file");
        System.out.println("5. Load the file");
        System.out.println("6. Open GUI");
        System.out.println("0. Exit");
    }
//  Method for add product to the product list
    public void add_prd() {
        Scanner scan = new Scanner(System.in);
//       check the product list is full
        if (prd_list.size() < 50) {
//            Getting product id
            System.out.println("Enter the product Id: ");
            String prdId = scan.nextLine();
            //            Getting product is on which category
            System.out.println("Enter the Category of the Item (Electronic or Clothing): ");
            String category = scan.nextLine();
//            Getting product Name
            System.out.println("Enter the product name: ");
            String prdName = scan.nextLine();
//            Getting Number of product
            System.out.println("Enter the Number of Item: ");
            int noOfItem = scan.nextInt();
            scan.nextLine();
//            Getting price of the product
            System.out.println("Enter the price of the Item: ");
            double price = scan.nextDouble();
            scan.nextLine();


//            if the product is electronic ask the details related to the electronics
            if (category.equalsIgnoreCase("electronic")) {
//                Getting input for the brand of the electronic
                System.out.println("Enter the Brand: ");
                String brand = scan.nextLine();
//            Getting input for the warranty of the electronic
                System.out.println("Enter the warranty");
                String warranty = scan.nextLine();
//            Create a new Electronics product instance using the provided constructor parameters.
                Product electronicProduct = new Electronics(prdId, prdName, noOfItem, price, category, brand, warranty);
//                Adding newly added electronic product to the product list
                prd_list.add(electronicProduct);

//             if the product is clothing's ask the details related to the clothing
            } else if (category.equalsIgnoreCase("clothing")) {
//             Getting input for the size of the clothing
                System.out.println("Enter the Size: ");
                String size = scan.nextLine();
//             Getting input for the color of the clothing
                System.out.println("Enter the Color: ");
                String color = scan.nextLine();
//            Create a new clothing product instance using the provided constructor parameters.
                Product clothingProduct = new Clothing(prdId, prdName, noOfItem, price, category, size, color);
//                Adding newly added clothing product to the product list
                prd_list.add(clothingProduct);
                System.out.println("Product was added");
//                If the user entered wrong category, display an error
            } else {
                System.out.println("Enter the Correct Category of the product.");
                menu();
            }
        }
    }
    /**
     * Allows the user to delete a product from the prd_list based on the provided Product Id.
     */
    public void delete_prd() {
        Scanner scan = new Scanner(System.in);
//        Prompt the user to enter the Product Id for deletion
        System.out.println("Enter the Product Id that you want to delete: ");
        String prdId = scan.nextLine();


//          Iterate through the prd_list to find the product with the specified Product Id
        for (Product product : prd_list) {
            if (product.getPrdId().equals(prdId)) {
                available = true;
                System.out.println("Product Found. Do you want to remove(Y/N): ");
                String rem = scan.nextLine();
//          Prompt the user for confirmation to remove the product
                if (rem.equalsIgnoreCase("Y")) {
                    prd_list.remove(product);
                    System.out.println("Product Removed.");
                    System.out.println("Category of the Product: " + product.getCategory());
                } else {
                    System.out.println("Not Removed.");
                }
//          Exit the loop after processing the specified Product Id
                break;
            }
        }
//          Display a message if the specified Product Id is not found
        if (!available) {
            System.out.println("Can't find the product relevant to that Product ID.");
        }
    }
    /**
     * Prints the list of products in ascending order based on their Product Id.
     */
    public void printProductList() {
//      Sort the prd_list in ascending order based on Product Id
        Collections.sort(prd_list, Comparator.comparing(Product::getPrdId));
//       Iterate through the sorted prd_list and print each product's details
        for (Product product : prd_list) {
            System.out.println(product.toString());
        }
    }
    /**
     * Saves the product information to a specified file, distinguishing between Electronics and Clothing items.
     * Using BufferedWriter and FileWriter for efficient file writing.
     */
    public void save_in_a_file(ArrayList<Product> data, String Products) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Products))) {

//           Iterating through the product List
            for (Product items : data) {
//           Identify the type of product and write corresponding label
                if (items instanceof Electronics) {
                    writer.write("Electronic Item" + "\n");
                } else {
                    writer.write("Clothing Item" + "\n");
                }
//             Product Information
                writer.write("Product ID - " + items.getPrdId() + "\n");
                writer.write("Product Name - " + items.getPrdName() + "\n");
                writer.write("Available Product Count - " + items.getNoofItem() + "\n");
                writer.write("Product Price - " + (int) items.getPrice() + "\n");
//             write detail about the electronics
                if (items instanceof Electronics) {
                    writer.write("Product Brand - " + ((Electronics) items).getBrand() + "\n");
                    writer.write("Product Warranty Period - " + ((Electronics) items).getWarranty() + "\n");
//             Write details about the Clothings
                } else if (items instanceof Clothing) {
                    writer.write("Product Colour - " + ((Clothing) items).getColor() + "\n");
                    writer.write("Product Size - " + ((Clothing) items).getSize() + "\n");
                }
                // Line to separate each new product
                writer.newLine();
            }
//             Display user to file was saved
            System.out.println("Products were saved to the file successfully");
        } catch (IOException e) {
            System.out.println();
        }
    }
    /**
     * Loads product information from a specified file and populates the prd_list with corresponding Product objects.
     * Using BufferedReader and FileReader for file reading.
     */
    public void loadFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
//          Iterate through each line in the file
            while ((line = reader.readLine()) != null) {
                Product product = null;
                String prdId = "", prdName = "", category = "";
                int noOfItem = 0;
                double price = 0.0;
                String brand = "", warranty = "", color = "", size = "";
//          Identify the product category and construct the corresponding Product object
                if (line.equals("Electronic Item")) {
                    category = "Electronic";
                    prdId = reader.readLine().split(" - ")[1];
                    prdName = reader.readLine().split(" - ")[1];
                    noOfItem = Integer.parseInt(reader.readLine().split(" - ")[1]);
                    price = Double.parseDouble(reader.readLine().split(" - ")[1]);
                    brand = reader.readLine().split(" - ")[1];
                    warranty = reader.readLine().split(" - ")[1];
                    product = new Electronics(prdId, prdName, noOfItem, price, category, brand, warranty);
                } else if (line.equals("Clothing Item")) {
                    category = "Clothing";
                    prdId = reader.readLine().split(" - ")[1];
                    prdName = reader.readLine().split(" - ")[1];
                    noOfItem = Integer.parseInt(reader.readLine().split(" - ")[1]);
                    price = Double.parseDouble(reader.readLine().split(" - ")[1]);
                    color = reader.readLine().split(" - ")[1];
                    size = reader.readLine().split(" - ")[1];
                    product = new Clothing(prdId, prdName, noOfItem, price, category, size, color);
                }

                // Add product to the list if not null
                if (product != null) {
                    prd_list.add(product);
                }
                // Skip the empty line separating products
                reader.readLine();
            }
//             Display successfully load the file
            System.out.println("Products loaded from file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Retrieves the current list of products stored in the prd_list attribute.
     */
    public ArrayList<Product> getPrd_list() {
        return prd_list;
    }
}

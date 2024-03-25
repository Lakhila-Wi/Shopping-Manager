package com.example.cwoop;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        //calling the westminsterShoppingManager object
        WestminsterShoppingManager west = new WestminsterShoppingManager();
        west.menu();    //calling the menu method
        //Creating scanner object
        Scanner scanner = new Scanner(System.in);
        //Asking the inputs for the users choice
        System.out.println("Enter your choice: ");
        int choice = scanner.nextInt();
        //run the while loop until the user need to exit the code
        while (choice != 0) {
            switch (choice) {
                case 1:
                    //if user select 1 calling the adding product method
                    west.add_prd();
                    break;
                case 2:
                    //if user select 2 calling the delete product method
                    west.delete_prd();
                    break;
                case 3:
                    //if user select 3 calling the print product list method
                    west.printProductList();
                    break;
                case 4:
                    //if user select 4 calling the save product list to file method
                    west.save_in_a_file(west.getPrd_list(), "data.txt");
                    break;
                case 5:
                    //if user select 5 calling the load the file method
                    west.loadFromFile("data.txt");
                    break;
                case 6:
                    MainFrame mainFrame = new MainFrame(west);
                    break;
                case 0:
                    //if user select 0 successfully exited from the code
                    System.out.println("Successfully Exited");
                    break;
                default:
                    //if user gave wrong answer it will show an error message
                    System.out.println("Entered option is invalid.");
                    break;
            }
            // Calling the menu
            west.menu();
            System.out.println("Enter your choic: ");
            choice = scanner.nextInt();
        }
    }
}